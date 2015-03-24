package cranfield.group.project.airfoil.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import cranfield.group.project.airfoil.api.model.AstralUserDTO;
import cranfield.group.project.airfoil.api.model.OperationType;
import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.api.model.WorkflowDTO;
import cranfield.group.project.airfoil.client.model.ClientSocket;
import cranfield.group.project.airfoil.client.model.ServerOfflineException;

public class MarsClient extends Observable implements AutoCloseable, Observer {
	private AstralUserDTO user;
	private ClientSocket clientSocket;

	public MarsClient(String serverName, Integer port) throws IOException {
		clientSocket = new ClientSocket(serverName, port);
		clientSocket.getOutputStream().write(1);
		System.out.println("CLIENT : Just connected to "
				+ clientSocket.getRemoteSocketAddress());
		setChanged();
	}

	public ClientSocket getClientSocket() {
		return clientSocket;
	}

	public boolean isConnected() {
		return clientSocket.isConnected();
	}

	public String areValidatedCredentials(String username, String password)
			throws ServerOfflineException {
		String msg = null;

		clientSocket.sendArray(OperationType.CREDENTIALS,
				new String[] { username, password });
		msg = clientSocket.receive(String.class);

		return msg;
	}

	@Override
	public void close() throws Exception {
		terminateConnection();
	}

	public void terminateConnection() {
		// clientSocket.send(OperationType.QUIT);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					clientSocket.getOutputStream());
			out.writeObject(OperationType.QUIT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendOptimizationInputs(String workflowName,
			Hashtable<String, Double> inputs) throws ServerOfflineException {
		setChanged();
		notifyObservers("Sending Optimization inputs to the server: ");
		setChanged();
		notifyObservers(inputs);
		clientSocket.send(OperationType.OPTIMIZATION,
				workflowName, inputs);
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

	public void removeWorkflow(long id) throws ServerOfflineException {
		clientSocket.send(OperationType.REMOVE_WORKFLOW, id);
	}

	public WorkflowDTO receiveWorkflow(long id) throws ServerOfflineException {
		clientSocket.send(OperationType.LOAD_WORKFLOW, id);
		return clientSocket.receive(WorkflowDTO.class);
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