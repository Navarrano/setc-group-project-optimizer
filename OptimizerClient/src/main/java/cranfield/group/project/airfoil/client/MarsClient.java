package cranfield.group.project.airfoil.client;
import cranfield.group.project.airfoil.api.model.IterationValuesSet;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class MarsClient extends Observable implements AutoCloseable {
	private Socket clientSocket;

	public MarsClient(String serverName, Integer port) throws IOException {
		clientSocket = new Socket(serverName, port);
		clientSocket.getOutputStream().write(1);
		System.out.println("CLIENT : Just connected to "
				+ clientSocket.getRemoteSocketAddress());
		setChanged();
	}
	
	public Socket getClientSocket(){
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

   public void sendOptimizationInputs(Hashtable<String, Double> inputs) {
	   String messageForServerAction[]={"optimization"};
	   try {
		   setChanged();
		   notifyObservers("Sending Optimization inputs to the server: ");
		   setChanged();
		   notifyObservers(inputs);
		   ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		   out.writeObject(messageForServerAction);
		   out.writeObject(inputs);
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}

   public Vector<IterationValuesSet> receiveOptimizationOutputs() {
	   ObjectInputStream in;
	   Vector<IterationValuesSet> optimizationResults = new Vector<IterationValuesSet>();
	   try {
		   in = new ObjectInputStream(clientSocket.getInputStream());
		   optimizationResults = (Vector<IterationValuesSet>) in.readObject();
		   setChanged();
		   notifyObservers("Receiving Optimization results from the server: ");
		   setChanged();
		   notifyObservers(optimizationResults);
		   setChanged();
		   notifyObservers("End of Optimization results reception");
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return optimizationResults;
   }
}