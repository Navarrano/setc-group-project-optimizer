package cranfield.group.project.airfoil.server.controllers;

import java.util.Observable;

import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.server.entities.Results;
import cranfield.group.project.airfoil.server.entities.Workflow;
import cranfield.group.project.airfoil.server.services.ResultsCRUDService;

public class AirfoilCalculator extends Observable {

	private static final double AIR_DENSITY = 1.225;
	private static final double GRAVITATIONAL_ACCELERATION = 9.81;
	private static final double STEP_SIZE = 0.00005;
	private static final double ANGLE = 0.0;
	private final double minDragCoeff;
	private final double aeroplaneMass;
	private final double maxLiftCoeff;
	private final double airSpeed;
	private final double minAirSpeed;

	// private final List<ResultsDTO> results;

	// TODO find the beter solution to write tthis to db in marsServer and not
	// here
	protected ResultsCRUDService resultService;

	public AirfoilCalculator(double minDragCoeff, double aeroplaneMass,
			double maxLiftCoeff, double airSpeed, double minAirSpeed) {
		super();
		this.minDragCoeff = minDragCoeff;
		this.aeroplaneMass = aeroplaneMass;
		this.maxLiftCoeff = maxLiftCoeff;
		this.airSpeed = airSpeed;
		this.minAirSpeed = minAirSpeed;

		this.resultService = new ResultsCRUDService();
	}

	public double[] optimize(double b, double c) {
		double oldB = b;
		double oldC = c;
		b = b - STEP_SIZE * calcNumericalDerivativeByB(oldB, oldC, STEP_SIZE);
		c = c - STEP_SIZE * calcNumericalDerivativeByC(oldB, oldC, STEP_SIZE);
		return new double[] { b, c };
	}

	public void optimize(double b, double c, int iterations,
			Workflow workflowObj) {
		double oldB;
		double oldC;

		// TODO: Logs
		for (int i = 0; i < iterations; i++) {
			oldB = b;
			oldC = c;
			double dragForce = calcTotalDrag(oldB, oldC);
			double liftForce = calcLiftForce(oldB, oldC);
			double ratio = liftForce / dragForce;
			System.out
					.format("%d ==> Drag force: %.4f, Lift force: %.4f, RATIO: %.8f, b: %.3f c: %.3f\n",
							i, dragForce, liftForce, ratio, b, c);
			b = b - STEP_SIZE
					* calcNumericalDerivativeByB(oldB, oldC, STEP_SIZE);
			c = c - STEP_SIZE
					* calcNumericalDerivativeByC(oldB, oldC, STEP_SIZE);

			// Save current iteration results in the DB
			Results result = new Results(workflowObj, i + 1, ANGLE, c, b,
					dragForce, liftForce, ratio);
			resultService.addResult(result);

			// Send iteration results to client
			ResultsDTO resultToBeSent = new ResultsDTO(result.getId(), i + 1,
					ANGLE, c, b, dragForce, liftForce, ratio);
			setChanged();
			notifyObservers(resultToBeSent);
		}
		setChanged();
		notifyObservers("End Optimization");
		// TODO: Logs
	}

	private double calcNumericalDerivativeByB(double b, double c, double delta) {
		return (calcObjectiveFunction(b + delta, c) - calcObjectiveFunction(b
				- delta, c))
				/ (2 * delta);
	}

	private double calcNumericalDerivativeByC(double b, double c, double delta) {
		return (calcObjectiveFunction(b, c + delta) - calcObjectiveFunction(b,
				c - delta)) / (2 * delta);
	}

	private double calcObjectiveFunction(double b, double c) {
		return calcTotalDrag(b, c) + calcPenalty(b, c);
	}

	private double calcTotalDrag(double b, double c) {
		double partA = AIR_DENSITY * airSpeed * airSpeed / 2
				* calcBearingSurface(b, c);
		double partB = minDragCoeff + calcLiftCoeff(b, c, airSpeed)
				* calcLiftCoeff(b, c, airSpeed)
				/ (Math.PI * calcTaperRatio(b, c) * calcOswaldsCeoff(b, c));

		return partA * partB;
	}

	private double calcPenalty(double b, double c) {
		double part = 1 / (maxLiftCoeff - calcLiftCoeff(b, c, airSpeed)) - 1
				/ (maxLiftCoeff - calcLiftCoeff(b, c, minAirSpeed));
		return part * part;
	}

	private double calcBearingSurface(double b, double c) {
		return b * c;
	}

	private double calcTaperRatio(double b, double c) {
		return b * b / calcBearingSurface(b, c);
	}

	private double calcOswaldsCeoff(double b, double c) {
		return 4.61 * (1 - 0.045 * Math.pow(calcTaperRatio(b, c), 0.68))
				* Math.pow(Math.cos(ANGLE), 0.15) - 3.1;
	}

	private double calcMass(double b, double c) {
		return aeroplaneMass + 4.936 * calcBearingSurface(b, c)
				* Math.pow(calcTaperRatio(b, c), 0.3);
	}

	private double calcLiftForce(double b, double c) {
		return calcLiftCoeff(b, c, airSpeed) * AIR_DENSITY
				* calcBearingSurface(b, c) * airSpeed * airSpeed / 2;
	}

	private double calcLiftCoeff(double b, double c, double v) {
		return 2 * calcMass(b, c) * GRAVITATIONAL_ACCELERATION
				/ (AIR_DENSITY * calcBearingSurface(b, c) * v * v);
	}
}
