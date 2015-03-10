package cranfield.group.project.airfoil.api.model;

import java.io.Serializable;

public class ResultsDTO implements Serializable {

	private Long id;
	private int iteration;
	private double angle;
	private double chord;
	private double span;
	private double dragForce;
	private double liftForce;
	private double ratio;

	public ResultsDTO(){}
	
	public ResultsDTO(Long id, int iteration, double angle, double chord, double span, double dragForce, double liftForce, double ratio){
		this.id = id;
		this.iteration = iteration;
		this.angle = angle;
		this.chord = chord;
		this.span = span;
		this.dragForce = dragForce;
		this.liftForce = liftForce;
		this.ratio = ratio;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIteration() {
		return iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getChord() {
		return chord;
	}

	public void setChord(double chord) {
		this.chord = chord;
	}

	public double getSpan() {
		return span;
	}

	public void setSpan(double span) {
		this.span = span;
	}

	public double getDragForce() {
		return dragForce;
	}

	public void setDragForce(double dragForce) {
		this.dragForce = dragForce;
	}

	public double getLiftForce() {
		return liftForce;
	}

	public void setLiftForce(double liftForce) {
		this.liftForce = liftForce;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
