package cranfield.group.project.airfoil.server.controllers;

import java.util.Observable;

import cranfield.group.project.airfoil.api.model.ResultsDTO;
import cranfield.group.project.airfoil.server.entities.Results;
import cranfield.group.project.airfoil.server.entities.Workflow;
import cranfield.group.project.airfoil.server.services.ResultsCRUDService;

/**
 * Class for optimizing airfoil contour
 *
 * @author Jan
 *
 */
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

	protected ResultsCRUDService resultService;

	/**
	 * Constructor
	 *
	 * @param minDragCoeff
	 * @param aeroplaneMass
	 * @param maxLiftCoeff
	 * @param airSpeed
	 * @param minAirSpeed
	 */
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

	/**
	 * Performs one iteration of optimization
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param stepSize
	 *            step size
	 * @return new [b,c]
	 */
	public double[] optimize(double b, double c, double stepSize) {
		double oldB = b;
		double oldC = c;
		b = b - stepSize * calcNumericalDerivativeByB(oldB, oldC, stepSize);
		c = c - stepSize * calcNumericalDerivativeByC(oldB, oldC, stepSize);
		return new double[] { b, c };
	}

	/**
	 * Performs number of iterations starting from offset
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param iterations
	 *            number of iterations
	 * @param iterationsOffset
	 *            starting offset
	 * @param workflow
	 *            workflow to save results to
	 */
	public void optimize(double b, double c, int iterations,
			int iterationsOffset, Workflow workflow) {
		double oldB;
		double oldC;
		double stepSize = STEP_SIZE;
		Results result = null;
		// TODO: Logs
		for (int i = iterationsOffset; i < iterations + iterationsOffset; i++) {
			oldB = b;
			oldC = c;
			double dragForce = calcTotalDrag(oldB, oldC);
			double liftForce = calcLiftForce(oldB, oldC);
			double ratio = liftForce / dragForce;
			System.out
					.format("%d ==> Drag force: %.4f, Lift force: %.4f, RATIO: %.8f, b: %.3f c: %.3f\n",
							i, dragForce, liftForce, ratio, b, c);
			b = b - stepSize * calcNumericalDerivativeByB(oldB, oldC, stepSize);
			c = c - stepSize * calcNumericalDerivativeByC(oldB, oldC, stepSize);
			if (ratio == Double.NaN || b == Double.NaN || c == Double.NaN) {
				setChanged();
				notifyObservers(new Object[] { "NaN error", workflow });
				break;
			}

			// Save current iteration results in the DB
			result = new Results(workflow, i + 1, ANGLE, c, b,
					dragForce, liftForce, ratio);
			resultService.addResult(result);

			// Send iteration results to client
			ResultsDTO resultToBeSent = new ResultsDTO(result.getId(), i + 1,
					ANGLE, c, b, dragForce, liftForce, ratio);
			setChanged();
			notifyObservers(resultToBeSent);

			if (i % 500 == 0 && i != 0)
				stepSize /= 2.0;
		}
		setChanged();
		notifyObservers(new Object[] { "End Optimization", workflow, result });
	}

	/**
	 * Performs number of iterations starting with 0 offset
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param iterations
	 *            number of iterations
	 * @param workflowObj
	 *            workflow to save results to
	 */
	public void optimize(double b, double c, int iterations,
			Workflow workflowObj) {
		optimize(b, c, iterations, 0, workflowObj);
	}

	/**
	 * Calculates objective function derivative around point b with given delta
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param delta
	 *            delta
	 * @return objective function value
	 */
	private double calcNumericalDerivativeByB(double b, double c, double delta) {
		return (calcObjectiveFunction(b + delta, c) - calcObjectiveFunction(b
				- delta, c))
				/ (2 * delta);
	}

	/**
	 * Calculates objective function derivative around point c with given delta
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param delta
	 *            delta
	 * @return objective function value
	 */
	private double calcNumericalDerivativeByC(double b, double c, double delta) {
		return (calcObjectiveFunction(b, c + delta) - calcObjectiveFunction(b,
				c - delta)) / (2 * delta);
	}

	/**
	 * Calculates objective function
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcObjectiveFunction(double b, double c) {
		return calcTotalDrag(b, c) + calcPenalty(b, c);
	}

	/**
	 * Calculates total drag force
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcTotalDrag(double b, double c) {
		double partA = AIR_DENSITY * airSpeed * airSpeed / 2
				* calcBearingSurface(b, c);
		double partB = minDragCoeff + calcLiftCoeff(b, c, airSpeed)
				* calcLiftCoeff(b, c, airSpeed)
				/ (Math.PI * calcTaperRatio(b, c) * calcOswaldsCeoff(b, c));

		return partA * partB;
	}

	/**
	 * Calculates penalty method
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcPenalty(double b, double c) {
		double part = 1 / (maxLiftCoeff - calcLiftCoeff(b, c, airSpeed)) - 1
				/ (maxLiftCoeff - calcLiftCoeff(b, c, minAirSpeed));
		return part * part;
	}

	/**
	 * Calculates bearing surface
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcBearingSurface(double b, double c) {
		return b * c;
	}

	/**
	 * Calculates taper ratio
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcTaperRatio(double b, double c) {
		return b * b / calcBearingSurface(b, c);
	}

	/**
	 * Calculates oswald's coefficient
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcOswaldsCeoff(double b, double c) {
		return 4.61 * (1 - 0.045 * Math.pow(calcTaperRatio(b, c), 0.68))
				* Math.pow(Math.cos(ANGLE), 0.15) - 3.1;
	}

	/**
	 * Calculates total mass
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcMass(double b, double c) {
		return aeroplaneMass + 4.936 * calcBearingSurface(b, c)
				* Math.pow(calcTaperRatio(b, c), 0.3);
	}

	/**
	 * Calculates lift force
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @return value
	 */
	private double calcLiftForce(double b, double c) {
		return calcLiftCoeff(b, c, airSpeed) * AIR_DENSITY
				* calcBearingSurface(b, c) * airSpeed * airSpeed / 2;
	}

	/**
	 * Calculates lift force coefficient
	 *
	 * @param b
	 *            span
	 * @param c
	 *            chord
	 * @param v
	 *            air speed
	 * @return value
	 */
	private double calcLiftCoeff(double b, double c, double v) {
		return 2 * calcMass(b, c) * GRAVITATIONAL_ACCELERATION
				/ (AIR_DENSITY * calcBearingSurface(b, c) * v * v);
	}
}
