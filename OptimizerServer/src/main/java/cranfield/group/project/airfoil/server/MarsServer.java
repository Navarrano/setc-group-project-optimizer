package cranfield.group.project.airfoil.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Set;

import com.jcraft.jsch.JSchException;

import cranfield.group.project.airfoil.server.controllers.AstralConnection;

/**
 * The Class MarsServer. Represents the entry point of the server, that handles
 * the communication with the clients.
 */
public class MarsServer extends Thread {

	/** The server socket */
	protected Socket client;
	protected Set<String> connectedUsers;
	protected String username;

	/** The database handler. */
	// protected DatabaseHandler databaseHandler;

	public MarsServer(Socket client, Set<String> users) {
		this.client = client;
		this.connectedUsers = users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		try {
			boolean isClientConnected = true;

			while (isClientConnected) {
				ObjectInputStream in = new ObjectInputStream(
						client.getInputStream());

				String[] dataFromClient = (String[]) in.readObject();

				switch (dataFromClient[0]) {
				case "credentials":
					validateConnection(client, dataFromClient);
					break;
				case "quit":
					connectedUsers.remove(username);
					isClientConnected = false;
					client.close();
					break;
				}
			}

		} catch (SocketTimeoutException s) {
			System.out.println("SERVER : Socket timed out!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check whether the credentials passed as arguments are valid or not. If
	 * they are, a "true" boolean is sent to the client. Else a "false" boolean
	 * is sent. In addition, the appropriate event logs are added to the
	 * database.
	 *
	 * @param server
	 *            the server
	 * @param credentials
	 *            the credentials
	 */
	protected void validateConnection(Socket server, String[] credentials) {
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(server.getOutputStream());

			String msg = areValidCredentialsWithError(credentials);

			if (msg == null) {
				out.writeObject(msg);
				writeUserInformationInDatabase(credentials[1]);
				// databaseHandler.addEventLog("Connection granted : username "
				// + credentials[1], "info", "connection");
				System.out.println("Good credentials");
			} else {
				out.writeObject(msg);
				// databaseHandler.addEventLog("Connection denied : username "
				// + credentials[1], "error", "connection");
				System.out.println("Wrong credentials");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Check whether the user whose username is passed as the argument already
	 * exists in the database. If it is the case, the user connection
	 * information is updated. Else, the new user is added to the database.
	 *
	 * @param username
	 *            the username
	 */
	protected void writeUserInformationInDatabase(String username) {
		// if (databaseHandler.existsUser(username)) {
		// databaseHandler.updateUserConnectionInformation(username);
		// } else {
		// databaseHandler.addNewUser(username);
		// databaseHandler.addEventLog("User " + username
		// + " added to AstralUsers table.", "info", "database");
		// }
	}

	/**
	 * Check whether the credentials passed as arguments are correct or not. In
	 * order to do so, an attempt to connect to Astral using these credentials
	 * is made.
	 *
	 * @param credentials
	 *            the credentials to be checked
	 * @return true, if the credentials allow to connect to Astral. Else false.
	 */
	protected boolean areValidCredentials(String[] credentials) {
		boolean canConnectToAstral = true;
		try (AstralConnection astralConnection = new AstralConnection(
				credentials[1], credentials[2])) {
		} catch (JSchException e) {
			canConnectToAstral = false;
		}
		return canConnectToAstral;
	}

	protected String areValidCredentialsWithError(String[] credentials) {
		if (connectedUsers.contains(credentials[1]))
			return "User \"" + credentials[1]
					+ "\" is already connected with server";
		try (AstralConnection astralConnection = new AstralConnection(
				credentials[1], credentials[2])) {
			username = credentials[1];
			connectedUsers.add(credentials[1]);
		} catch (JSchException e) {
			return e.getMessage();
		}
		return null;
	}
}