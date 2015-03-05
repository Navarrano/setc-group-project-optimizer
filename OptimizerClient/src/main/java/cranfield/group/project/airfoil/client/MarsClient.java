package cranfield.group.project.airfoil.client;

import java.io.IOException;
import java.util.Hashtable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MarsClient implements AutoCloseable {
	private Socket clientSocket;

	public MarsClient(String serverName, Integer port) throws IOException {
		clientSocket = new Socket(serverName, port);
		System.out.println("CLIENT : Just connected to "
				+ clientSocket.getRemoteSocketAddress());
	}

	public boolean isConnected() {
		return clientSocket.isConnected();
	}

	public boolean areValidatedCredentials(String username, String password) {
		boolean areValidatedCredentials = false;
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
			areValidatedCredentials = (boolean) in.readObject();
			System.out.println("here!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return areValidatedCredentials;
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

   public void sendOptimizationInputs(Hashtable<String, Double> inputs) {
	   String messageForServerAction[]={"optimization"};
	   try {
		   ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		   out.writeObject(messageForServerAction);
		   out.writeObject(inputs);
		   clientSocket.close();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}
}