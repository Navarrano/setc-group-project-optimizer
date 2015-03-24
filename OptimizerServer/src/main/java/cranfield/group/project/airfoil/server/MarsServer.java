package cranfield.group.project.airfoil.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.jcraft.jsch.JSchException;

import cranfield.group.project.airfoil.api.model.OperationType;
import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.api.model.WorkflowDTO;
import cranfield.group.project.airfoil.client.util.ConnectionUtils;
import cranfield.group.project.airfoil.server.controllers.AirfoilCalculator;
import cranfield.group.project.airfoil.server.controllers.AstralConnection;
import cranfield.group.project.airfoil.server.entities.AstralUser;
import cranfield.group.project.airfoil.server.entities.Logs;
import cranfield.group.project.airfoil.server.entities.Results;
import cranfield.group.project.airfoil.server.entities.Workflow;
import cranfield.group.project.airfoil.server.models.ConnectedUsers;
import cranfield.group.project.airfoil.server.services.LogsCRUDService;
import cranfield.group.project.airfoil.server.services.ResultsCRUDService;
import cranfield.group.project.airfoil.server.services.UserCRUDService;
import cranfield.group.project.airfoil.server.services.WorkflowCRUDService;

/**
 * The Class MarsServer. Represents the entry point of the server, that handles
 * the communication with the clients.
 */
public class MarsServer extends Thread implements Observer {

	/** The server socket */
	protected Socket client;
	protected ConnectedUsers users;

	protected UserCRUDService astralUser;
	protected LogsCRUDService logs;
	protected WorkflowCRUDService workflowService;
	protected AstralUser astralus;
	protected ResultsCRUDService resultsService;

	public MarsServer(Socket client, ConnectedUsers users) {
		this.client = client;
		this.users = users;
		logs = new LogsCRUDService();
		astralUser = new UserCRUDService();
		workflowService = new WorkflowCRUDService();
		resultsService = new ResultsCRUDService();
	}

	public void run() {
		try {
			boolean isClientConnected = true;

			while (isClientConnected) {
				OperationType operation = ConnectionUtils
						.receiveOperation(client);

				switch (operation) {
				case CREDENTIALS:
					boolean isConnected = validateConnection(ConnectionUtils
							.receive(client, new String[] {}));
					if (isConnected) {
						ConnectionUtils.send(client, getWorkflowList());
					}
					break;
				case OPTIMIZATION:
					runOptimization(ConnectionUtils.receive(client,
							String.class), ConnectionUtils.receive(client,
							new Hashtable<String, Double>()));
					break;
				case LOAD_WORKFLOW:
					Long workflowId = ConnectionUtils.receive(client,
							Long.class);
					ConnectionUtils.send(client,
							getSelectedWorkflowData(workflowId));
					break;
				case REMOVE_WORKFLOW:
					Long workId = ConnectionUtils.receive(client, Long.class);
					workflowService.removeWorkflow(workId);
					break;
				case ITERATE:
					Long id = ConnectionUtils.receive(client, Long.class);
					Integer iterations = ConnectionUtils.receive(client,
							Integer.class);
					runAdditionalIterations(id, iterations);
					break;
				case QUIT:
					users.remove(astralus.getLogin());
					isClientConnected = false;
					client.close();
					break;
				}
			}

		} catch (SocketTimeoutException s) {
			System.out.println("SERVER : Socket timed out!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected WorkflowDTO getSelectedWorkflowData(Long workflowId) {
		Workflow w = workflowService.getWorflowWithId(workflowId);
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

	private void runAdditionalIterations(Long workflowId, Integer iterations) {
		Workflow workflow = workflowService.find(workflowId);
		AirfoilCalculator calculator = new AirfoilCalculator(
				workflow.getMinDragCoef(), workflow.getAeroplaneMass(),
				workflow.getMaxLiftCoef(), workflow.getAirSpeed(),
				workflow.getMinAirSpeed());
		calculator.addObserver(this);
		// Results result = workflow.getResults().get(
		// workflow.getResults().size() - 1);
		Results result = resultsService.getLastResult(workflowId);

		calculator.optimize(result.getSpan(), result.getChord(), iterations,
				result.getIteration(), workflow);
	}

	private void runOptimization(String workflowName,
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

		Workflow workflowObj = new Workflow(astralus, name, nbIterations,
				minDragCoef, aeroPlaneMass, maxLiftCoef, airSpeed, minAirSpeed,
				0, chord, span);
		workflowService.addWorkflow(workflowObj);

		try {
			ObjectOutputStream out = new ObjectOutputStream(
					client.getOutputStream());
			out.writeObject(prepareWorkflowDTO(workflowObj, null));
		} catch (IOException e) {
			e.printStackTrace();
		}

		AirfoilCalculator calculator = new AirfoilCalculator(minDragCoef,
				aeroPlaneMass, maxLiftCoef, airSpeed, minAirSpeed);
		calculator.addObserver(this);

		calculator.optimize(span, chord, nbIterations, workflowObj);
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
	 * @param client
	 *            the server
	 * @param credentials
	 *            the credentials
	 */
	protected boolean validateConnection(String[] credentials) {
		ObjectOutputStream out;

		try {
			out = new ObjectOutputStream(client.getOutputStream());

			String msg = checkCredentials(credentials);
			if (msg == null) {
				out.writeObject(msg);
				writeUserInformationInDatabase(credentials[0]);
				logs.addEventLog(new Logs(astralus, "Connection granted : username "
						+ credentials[0], "info", "connection"));
				System.out.println("Good credentials");
				return true;
			} else {
				out.writeObject(msg);
				logs.addEventLog(new Logs(astralus, "Connection denied : username "
						+ credentials[0], "error", "connection"));
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
			logs.addEventLog(new Logs(astralus, "User " + username
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
		if (users.contains(credentials[0]))
			return "User \"" + credentials[0]
					+ "\" is already connected with server";
		try (AstralConnection astralConnection = new AstralConnection(
				credentials[0], credentials[1])) {
			users.add(credentials[0]);
		} catch (JSchException e) {
			return e.getMessage();
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			long id = -1L;
			if (arg.getClass() == ResultsDTO.class) {
				ResultsDTO resultsToBeSent = (ResultsDTO) arg;
				out.writeObject(resultsToBeSent);
			} else if (((String) arg).equalsIgnoreCase("NaN error")) {
				id = -2L;
			} else if (((String) arg).equalsIgnoreCase("End Optimization")) {
				out.writeObject(new ResultsDTO(id));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
