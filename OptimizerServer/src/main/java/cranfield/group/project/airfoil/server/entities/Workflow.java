/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author emi
 */
@Entity
@Table
public class Workflow extends AbstractEntityObject<Long, Workflow>{
    
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
   
}
