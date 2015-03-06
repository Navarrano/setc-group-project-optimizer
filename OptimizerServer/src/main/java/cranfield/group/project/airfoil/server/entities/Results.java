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
public class Results extends AbstractEntityObject<Long, Results>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
