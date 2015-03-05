package cranfield.group.project.airfoil.server.services;

public class AirfoilCalculator {

	private static final double AIR_DENSITY = 1.225;
	private static final double GRAVITATIONAL_ACCELERATION = 9.81;
	private final double minDragCoeff;
	private final double aeroplaneMass;
	private final double maxLiftCoeff;
	private final double airSpeed;
	private final double minAirSpeed;

	private final static double STEP_SIZE = 0.00001;

	public AirfoilCalculator(double minDragCoeff, double aeroplaneMass,
			double maxLiftCoeff, double airSpeed, double minAirSpeed) {
		super();
		this.minDragCoeff = minDragCoeff;
		this.aeroplaneMass = aeroplaneMass;
		this.maxLiftCoeff = maxLiftCoeff;
		this.airSpeed = airSpeed;
		this.minAirSpeed = minAirSpeed;
	}

	public void optimize(double b, double c, double angle, int iterations) {
		double deltaB = 1 / 100000.0;
		double deltaC = 1 / 100000.0;
		double oldB;
		for (int i = 0; i < iterations; i++) {
			oldB = b;
			double dragForce = calcTotalDrag(oldB, c, angle);
			double liftForce = calcLiftForce(oldB, c);
			System.out.format(
"%d ==> Drag force: %.4f, Lift force: %.4f, RATIO: %.8f, b: %.3f c: %.3f\n",
							i, dragForce, liftForce, liftForce / dragForce,
							b, c);

			b = b - STEP_SIZE
					* calcNumericalDerivativeByB(oldB, c, angle, deltaB);
			c = c - STEP_SIZE
					* calcNumericalDerivativeByC(oldB, c, angle, deltaC);
		}
	}

	public double calcNumericalDerivativeByB(double b, double c, double angle,
			double delta) {
		return (calcObjectiveFunction(b + delta, c, angle) - calcObjectiveFunction(
				b - delta, c, angle)) / (2 * delta);
	}

	public double calcNumericalDerivativeByC(double b, double c, double angle,
			double delta) {
		return (calcObjectiveFunction(b, c + delta, angle) - calcObjectiveFunction(
				b, c - delta, angle)) / (2 * delta);
	}

	private double calcObjectiveFunction(double b, double c, double angle) {
		return calcTotalDrag(b, c, angle) + calcPenalty(b, c);
	}

	public double calcTotalDrag(double b, double c, double angle) {
		double partA = AIR_DENSITY * airSpeed * airSpeed / 2
				* calcBearingSurface(b, c);
		double partB = minDragCoeff
				+ calcLiftCoeff(b, c, airSpeed)
				* calcLiftCoeff(b, c, airSpeed)
				/ (Math.PI * calcTaperRatio(b, c) * calcOswaldsCeoff(b, c,
						angle));

		return partA * partB;
	}

	public double calcPenalty(double b, double c){
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

	private double calcOswaldsCeoff(double b, double c, double angle) {
		return 4.61 * (1 - 0.045 * Math.pow(calcTaperRatio(b, c), 0.68))
				* Math.pow(Math.cos(angle), 0.15) - 3.1;
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
