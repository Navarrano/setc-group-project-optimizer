package cranfield.group.project.airfoil.api.model;

import java.util.List;

public class WorkflowDTO {

	private Long id;
	private int nbIterations;
	private double minDragCoef;
	private double aeroplaneMass;
	private double maxLiftCoef;
	private double airSpeed;
	private double minAirSpeed;
	private double angle;
	private double chord;
	private double span;

	private List<ResultsDTO> results;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbIterations() {
		return nbIterations;
	}

	public void setNbIterations(int nbIterations) {
		this.nbIterations = nbIterations;
	}

	public double getMinDragCoef() {
		return minDragCoef;
	}

	public void setMinDragCoef(double minDragCoef) {
		this.minDragCoef = minDragCoef;
	}

	public double getAeroplaneMass() {
		return aeroplaneMass;
	}

	public void setAeroplaneMass(double aeroplaneMass) {
		this.aeroplaneMass = aeroplaneMass;
	}

	public double getMaxLiftCoef() {
		return maxLiftCoef;
	}

	public void setMaxLiftCoef(double maxLiftCoef) {
		this.maxLiftCoef = maxLiftCoef;
	}

	public double getAirSpeed() {
		return airSpeed;
	}

	public void setAirSpeed(double airSpeed) {
		this.airSpeed = airSpeed;
	}

	public double getMinAirSpeed() {
		return minAirSpeed;
	}

	public void setMinAirSpeed(double minAirSpeed) {
		this.minAirSpeed = minAirSpeed;
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

	public List<ResultsDTO> getResults() {
		return results;
	}

	public void setResults(List<ResultsDTO> results) {
		this.results = results;
	}

}
