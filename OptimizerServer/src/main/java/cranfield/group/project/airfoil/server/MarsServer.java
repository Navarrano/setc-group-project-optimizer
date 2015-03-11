package cranfield.group.project.airfoil.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.jcraft.jsch.JSchException;

import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.api.model.WorkflowDTO;
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

	public void run() {
		try {
			boolean isClientConnected = true;

			while (isClientConnected) {

				ObjectInputStream in = new ObjectInputStream(
						client.getInputStream());

				String[] dataFromClient = (String[]) in.readObject();

				switch (dataFromClient[0]) {
				case "credentials":
					boolean isConnected = validateConnection(client,
							dataFromClient);
					if (isConnected) {
						ObjectOutputStream out = new ObjectOutputStream(
								client.getOutputStream());
						out.writeObject(getWorkflowList());
					}
					break;
				case "optimization":
					Hashtable<String, Double> inputValues = (Hashtable<String, Double>) in
							.readObject();
					runOptimization(client, dataFromClient[1], inputValues);
					System.out.println(inputValues.toString());
					System.out.println(inputValues.get("Span: "));
					break;
				case "loading workflow":
					Long workflowId = Long.parseLong(dataFromClient[1]);
					ObjectOutputStream out = new ObjectOutputStream(
							client.getOutputStream());
					out.writeObject(getSelectedWorkflowData(workflowId));
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

	protected WorkflowDTO getSelectedWorkflowData(Long workflowId) {
		Workflow w = workflow.getWorflowWithId(workflowId);
		List<Results> tmpResults = w.getResults();

		List<ResultsDTO> workflowResults = new LinkedList<>();

		if (tmpResults != null) {
			for (Results r : tmpResults) {
				workflowResults
						.add(new ResultsDTO(r.getId(), r.getIteration(), r
								.getAngle(), r.getChord(), r.getSpan(), r
								.getDragForce(), r.getLiftForce(), r.getRatio()));
			}
		}

		WorkflowDTO selectedWorkflow = new WorkflowDTO(w.getId(), w.getName(),
				w.getNbIterations(), w.getMinDragCoef(), w.getAeroplaneMass(),
				w.getMaxLiftCoef(), w.getAirSpeed(), w.getMinAirSpeed(),
				w.getAngle(), w.getChord(), w.getSpan(), workflowResults);

		return selectedWorkflow;
	}

	protected List<WorkflowDTO> getWorkflowList() {
		List<WorkflowDTO> tmp = new LinkedList<>();
		if (astralus.getWorkflows() != null) {
			for (Workflow w : astralus.getWorkflows()) {
				tmp.add(new WorkflowDTO(w.getId(), w.getName()));
			}
		}
		return tmp;
	}

	private void runOptimization(Socket server, String workflowName,
			Hashtable<String, Double> inputValues) {
		String name = workflowName;
		double minDragCoef = inputValues.get("Minimal drag coefficient: ");
		double aeroPlaneMass = inputValues.get("Aeroplane mass: ");
		double maxLiftCoef = inputValues.get("Maximum lift coeficient: ");
		double airSpeed = inputValues.get("Air speed: ");
		double minAirSpeed = inputValues.get("Minimal air speed: ");

		double span = inputValues.get("Span: ");
		double chord = inputValues.get("Chord: ");
		int nbIterations = inputValues.get("Iteration Number").intValue();
		double leadingEdge = inputValues.get("Leading edge: ");

		Workflow workflowObj = new Workflow(astralus, name, nbIterations,
				minDragCoef, aeroPlaneMass, maxLiftCoef, airSpeed, minAirSpeed,
				leadingEdge, chord, span);
		workflow.addWorkflow(workflowObj);

		AirfoilCalculator calculator = new AirfoilCalculator(minDragCoef,
				aeroPlaneMass, maxLiftCoef, airSpeed, minAirSpeed);
		calculator
				.optimize(span, chord, leadingEdge, nbIterations, workflowObj);

		// Vector<IterationValuesSet> optimizationResults = calculator
		// .getIterationsValuesSet();
		List<ResultsDTO> results = calculator.getResults();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					server.getOutputStream());
			// out.writeObject(optimizationResults);
			out.writeObject(prepareWorkflowDTO(workflowObj, results));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private WorkflowDTO prepareWorkflowDTO(Workflow w, List<ResultsDTO> results) {
		WorkflowDTO dto = new WorkflowDTO(w.getId(), w.getName());
		dto.setResults(results);
		return dto;
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
	protected boolean validateConnection(Socket server, String[] credentials) {
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(server.getOutputStream());

			String msg = checkCredentials(credentials);

			if (msg == null) {
				out.writeObject(msg);
				writeUserInformationInDatabase(credentials[1]);
				logs.addEventLog(new Logs("Connection granted : username "
						+ credentials[1], "info", "connection"));
				System.out.println("Good credentials");
				return true;
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
		return false;
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

		if (astralUser.existsUser(username)) {
			astralus = astralUser.getUserObj(username);
			astralUser.updateUserConnectionInformation(astralus);
		} else {
			astralus = new AstralUser(username);
			astralUser.addNewUser(astralus);
			logs.addEventLog(new Logs("User " + username
					+ " added to AstralUsers table.", "info", "database"));
		}
	}

	/**
	 * Check whether the credentials passed as arguments are correct or not. In
	 * order to do so, an attempt to connect to Astral using these credentials
	 * is made.
	 *
	 * @param credentials
	 *            the credentials to be checked
	 * @return msg with error or null if successful
	 */
	protected String checkCredentials(String[] credentials) {
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
