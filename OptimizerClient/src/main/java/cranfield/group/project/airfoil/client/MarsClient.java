package cranfield.group.project.airfoil.client;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Vector;

import cranfield.group.project.airfoil.client.models.IterationValuesSet;

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

	public String areValidatedCredentials(String username, String password) {
		// boolean areValidatedCredentials = false;
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
			// areValidatedCredentials = (boolean) in.readObject();
			msg = (String) in.readObject();
			System.out.println("here!");
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
		   System.out.println("Send Optimization inputs to Server");
		   ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		   out.writeObject(messageForServerAction);
		   out.writeObject(inputs);
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	}

   public void receiveOptimizationOutputs() {
	   ObjectInputStream in;
	   
	   try {
		   in = new ObjectInputStream(clientSocket.getInputStream());
		   Vector<cranfield.group.project.airfoil.client.models.IterationValuesSet> optimizationResults = (Vector<IterationValuesSet>) in.readObject();
		   System.out.println(optimizationResults.toString());
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
}