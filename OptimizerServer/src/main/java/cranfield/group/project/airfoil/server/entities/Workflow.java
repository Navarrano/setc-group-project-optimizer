/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author emi
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name="allUsersWorflows", query = "FROM Workflow w WHERE w.creator=:creator"),
    @NamedQuery(name="chosenWorkflow", query = "FROM Workflow w WHERE w.id=:id"),
    @NamedQuery(name="resultsForWorflow", query = "FROM Results r WHERE r.workflow=:workflow")
})
public class Workflow extends AbstractEntityObject<Long, Workflow> implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workflow", fetch = FetchType.LAZY)
    private List<Results> results;

    @ManyToOne
    private AstralUser creator;

    public Workflow(){}

    public Workflow(AstralUser creator, String name, int nbIterations, double minDragCoef, double aeroplaneMass, double maxLiftCoef, double airSpeed, double minAirSpeed, double angle, double chord, double span) {
        this.name = name;
    	this.nbIterations = nbIterations;
        this.minDragCoef = minDragCoef;
        this.aeroplaneMass = aeroplaneMass;
        this.maxLiftCoef = maxLiftCoef;
        this.airSpeed = airSpeed;
        this.minAirSpeed = minAirSpeed;
        this.angle = angle;
        this.chord = chord;
        this.span = span;
        this.creator = creator;
    }

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }


    public AstralUser getCreator() {
        return creator;
    }

    public void setCreator(AstralUser creator) {
        this.creator = creator;
    }

}
