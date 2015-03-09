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
import javax.persistence.Table;

/**
 *
 * @author emi
 */
@Entity
@Table
public class Logs extends AbstractEntityObject<Long, Logs> implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String logType;
    private String logSubject;
    
    public Logs(){}
    
    public Logs(String logSubject, String logType, String message){
        this.logSubject = logSubject;
        this.logType = logType;
        this.message = message;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogSubject() {
        return logSubject;
    }

    public void setLogSubject(String logSubject) {
        this.logSubject = logSubject;
    }
}
