package cranfield.group.project.airfoil.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import cranfield.group.project.airfoil.api.model.AstralUserDTO;
import cranfield.group.project.airfoil.api.model.ResultsDTO;

public class MarsClient extends Observable implements AutoCloseable, Observer {
	private Socket clientSocket;
	private AstralUserDTO user;

	public MarsClient(String serverName, Integer port) throws IOException {
		clientSocket = new Socket(serverName, port);
		clientSocket.getOutputStream().write(1);
		System.out.println("CLIENT : Just connected to "
				+ clientSocket.getRemoteSocketAddress());
		setChanged();
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public boolean isConnected() {
		return clientSocket.isConnected();
	}

	public String areValidatedCredentials(String username, String password) {
		String msg = null;
		String credentials[] = { "credentials", username, password };
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject(credentials);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ObjectInputStream in;
		try {
			in = new ObjectInputStream(clientSocket.getInputStream());
			msg = (String) in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	public void close() throws Exception {
		terminateConnection();
	}

	public void terminateConnection() {
		String quitString[] = { "quit" };
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject(quitString);
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendOptimizationInputs(String workflowName,
			Hashtable<String, Double> inputs) {
		String messageForServerAction[] = { "optimization", workflowName };
		try {
			setChanged();
			notifyObservers("Sending Optimization inputs to the server: ");
			setChanged();
			notifyObservers(inputs);
			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject(messageForServerAction);
			out.writeObject(inputs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultsDTO receiveResult() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					clientSocket.getInputStream());
			ResultsDTO receivedResults = (ResultsDTO) in.readObject();
			return receivedResults;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void receiveOptimizationResult() {
		boolean receivingData = true;

		while(receivingData){
			try {
				ObjectInputStream in = new ObjectInputStream(
						clientSocket.getInputStream());
				ResultsDTO receivedResults = (ResultsDTO) in.readObject();

				if (receivedResults.getId() == -1)
					receivingData = false;
				else {
					System.out.println("In receivedOptimization: "+receivedResults.toString());
					setChanged();
					notifyObservers(receivedResults);
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// if(((String) arg).equalsIgnoreCase("Data reception")){
		// receiveOptimizationResult();
		// }
	}
}