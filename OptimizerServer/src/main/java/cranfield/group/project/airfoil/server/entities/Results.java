/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author emi
 */
@Entity
@Table
@NamedQueries(value = { @NamedQuery(name = Results.FIND_LATEST_RESULT_FOR_WORKFLOW, query = "select r from Results r WHERE r.workflow.id = :id and r.iteration = (select max(rr.iteration) from Results rr where rr.workflow.id = :id)") })
public class Results extends AbstractEntityObject<Long, Results> implements Serializable{

	public static final String FIND_LATEST_RESULT_FOR_WORKFLOW = "Results.FindLatestResultForWorkflow";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int iteration;
    private double angle;
    private double chord;
    private double span;
    private double dragForce;
    private double liftForce;
    private double ratio;

    @ManyToOne
    private Workflow workflow;

    public Results(){}

    public Results(Workflow workflow, int iteration, double angle, double chord, double span, double dragForce, double liftForce, double ratio) {
        this.iteration = iteration;
        this.angle = angle;
        this.chord = chord;
        this.span = span;
        this.dragForce = dragForce;
        this.liftForce = liftForce;
        this.ratio = ratio;
        this.workflow = workflow;
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
