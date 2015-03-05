package cranfield.group.project.airfoil.server.models;

public class IterationValuesSet {
	protected int iteration;
	protected double dragForce;
	protected double liftForce;
	protected double angle;
	protected double ratio;
	
	public IterationValuesSet(int iteration, double dragForce, double liftForce, double angle, double ratio){
		this.iteration = iteration;
		this.dragForce = dragForce;
		this.liftForce = liftForce;
		this.angle = angle;
		this.ratio = ratio;
	}
}
