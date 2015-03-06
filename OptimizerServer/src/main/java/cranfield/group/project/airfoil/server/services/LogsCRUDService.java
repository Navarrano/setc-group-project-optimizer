/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.Logs;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emi
 */
public class LogsCRUDService  extends AbstractCRUDService<Long, Logs>{
    
        EntityManagerFactory emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
    	/**
	 * Adds an event log to the event logs table of the database.
	 * An event log consists in a message (description of the event that occurred), a log type (information, error) and
	 * a log subject indicating the origin of the log.
	 *
	 * @param message the message describing the event that occurred
	 * @param logType the log critical level
	 * @param logSubject indicates the origin of the log
	 */
	public void addEventLog(Logs obj){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
	}

}
