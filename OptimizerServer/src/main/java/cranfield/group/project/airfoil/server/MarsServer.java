package cranfield.group.project.airfoil.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.Vector;

import com.jcraft.jsch.JSchException;

import cranfield.group.project.airfoil.api.model.IterationValuesSet;
import cranfield.group.project.airfoil.server.controllers.AirfoilCalculator;
import cranfield.group.project.airfoil.server.controllers.AstralConnection;
import cranfield.group.project.airfoil.server.entities.AstralUser;
import cranfield.group.project.airfoil.server.entities.Logs;
import cranfield.group.project.airfoil.server.entities.Results;
import cranfield.group.project.airfoil.server.entities.Workflow;
import cranfield.group.project.airfoil.server.models.ConnectedUsers;
import cranfield.group.project.airfoil.server.services.LogsCRUDService;
import cranfield.group.project.airfoil.server.services.UserCRUDService;
import cranfield.group.project.airfoil.server.services.WorkflowCRUDService;

/**
 * The Class MarsServer. Represents the entry point of the server, that handles
 * the communication with the clients.
 */
public class MarsServer extends Thread {

	/** The server socket */
	protected Socket client;
	// protected Set<String> connectedUsers;
	protected String username;
	protected ConnectedUsers users;

        protected UserCRUDService astralUser;
	protected LogsCRUDService logs;
        protected WorkflowCRUDService workflow;
        protected AstralUser astralus;


	public MarsServer(Socket client, ConnectedUsers users) {
		this.client = client;
		this.users = users;
                logs = new LogsCRUDService();
                astralUser = new UserCRUDService();
                workflow = new WorkflowCRUDService();
                
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
				case "optimization":
					Hashtable<String, Double> inputValues = (Hashtable<String, Double>) in.readObject();
					//TODO: Create new workflow record in the DB and get the corresponding id to then pass it to runOptimization
					runOptimization(client,inputValues);
					System.out.println(inputValues.toString());
					System.out.println(inputValues.get("Span: "));
					break;
				case "quit":
					users.remove(username);
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

	private void runOptimization(Socket server, Hashtable<String, Double> inputValues) {
		//double minDragCoef = inputValues.get("Minimal drag coefficient: ");
		double minDragCoef = 0.021;
		double aeroPlaneMass = inputValues.get("Aeroplane mass: ");
		double maxLiftCoef = inputValues.get("Maximum lift coeficient: ");
		double airSpeed = inputValues.get("Air speed: ");
		double minAirSpeed = inputValues.get("Minimal air speed: ");

		double span = inputValues.get("Span: ");
		double chord = inputValues.get("Chord: ");
		int nbIterations = inputValues.get("Iteration Number").intValue();
		double leadingEdge = inputValues.get("Leading edge: ");

                Workflow workflowObj = new Workflow(astralus,nbIterations,minDragCoef,aeroPlaneMass,maxLiftCoef, airSpeed, minAirSpeed, leadingEdge, chord, span);
                workflow.addWorkflow(workflowObj);
                
		AirfoilCalculator calculator = new AirfoilCalculator(minDragCoef,aeroPlaneMass,maxLiftCoef,airSpeed,minAirSpeed);
		calculator.optimize(span, chord, leadingEdge, nbIterations, workflowObj);

		Vector<IterationValuesSet> optimizationResults = calculator.getIterationsValuesSet();
		try {
			ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
			out.writeObject(optimizationResults);

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
				logs.addEventLog(new Logs("Connection granted : username "
                                                + credentials[1], "info", "connection"));
				System.out.println("Good credentials");
			} else {
				out.writeObject(msg);
				logs.addEventLog(new Logs("Connection denied : username "
                                                 + credentials[1], "error", "connection"));
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
                astralus = new AstralUser(username);
                if (astralUser.existsUser(username)) {
                    astralUser.updateUserConnectionInformation(astralus);
		} else {
                    astralUser.addNewUser(astralus);
                    logs.addEventLog(new Logs("User " + username
                                              + " added to AstralUsers table.", "info", "database"));
		}
                //WorkflowCRUDService wor = new WorkflowCRUDService();
                //wor.getIdAndNameOfWorflows(astralus);
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
		if (users.contains(credentials[1]))
			return "User \"" + credentials[1]
					+ "\" is already connected with server";
		try (AstralConnection astralConnection = new AstralConnection(
				credentials[1], credentials[2])) {
			username = credentials[1];
			users.add(credentials[1]);
		} catch (JSchException e) {
			return e.getMessage();
		}
		return null;
	}
}
