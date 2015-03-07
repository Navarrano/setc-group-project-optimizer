package cranfield.group.project.airfoil.api.model;

import java.io.Serializable;

public class IterationValuesSet implements Serializable {
	protected int iteration;
	protected double chord;
	protected double span;
	protected double angle;
	protected double dragForce;
	protected double liftForce;
	protected double ratio;
	
	
	public IterationValuesSet(int iteration, double chord, double span, double angle, double dragForce, double liftForce, double ratio){
		this.iteration = iteration;
		this.chord = chord;
		this.span = span;
		this.dragForce = dragForce;
		this.liftForce = liftForce;
		this.angle = angle;
		this.ratio = ratio;
	}
	
	public double getRatio(){
		return this.ratio;
	}
	
	@Override
	public String toString(){
		String res = "Nb Iterations: " + iteration + "\n";
		res += "Chord: " + chord + "\n";
		res += "Span: " + span + "\n";
		res += "Angle: " + angle + "\n";
		res += "Drag Force: " + dragForce + "\n";
		res += "Lift Force: " + liftForce + "\n";
		res += "Ratio: " + ratio + "\n";
		return res;
	}
}
