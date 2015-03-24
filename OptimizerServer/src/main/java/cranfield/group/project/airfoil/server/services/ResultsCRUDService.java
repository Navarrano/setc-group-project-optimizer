/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import cranfield.group.project.airfoil.server.entities.Results;

/**
 *
 * @author emi
 */
public class ResultsCRUDService extends AbstractCRUDService<Long, Results>{

	// EntityManagerFactory emf =
	// EntityFactoryProvider.getInstance().createEntityManagerFactory();

         //add new result
        public void addResult(Results obj){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
	}

        //get result of last iteration for particular workflow
	public Results getLastResult(Long workflowId) {
		Object obj = emf.createEntityManager()
				.createNamedQuery(Results.FIND_LATEST_RESULT_FOR_WORKFLOW)
				.setParameter("id", workflowId).getSingleResult();
		return (Results) obj;
        }
}
