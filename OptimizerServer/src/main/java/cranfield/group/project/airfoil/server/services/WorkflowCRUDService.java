/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.api.model.OptimizationObject;
import cranfield.group.project.airfoil.server.entities.AstralUser;
import cranfield.group.project.airfoil.server.entities.Results;
import cranfield.group.project.airfoil.server.entities.Workflow;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emi
 */
public class WorkflowCRUDService extends AbstractCRUDService<Long, Workflow>{
    
         EntityManagerFactory emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
         
         //add new workflow
        public void addWorkflow(Workflow obj){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                //AstralUser userObj = obj.getCreator();
                //userObj.setNextWorkflow(obj);
                em.persist(obj);
                //em.persist(userObj);
                em.getTransaction().commit();
	}
        
        //all warkflows from particulat user
        public List<Workflow> allUsersWorkflows(){
            List<Workflow> userWorkflows = new ArrayList<Workflow>();
            
            return userWorkflows;
        }
        
        public Workflow getWorflowWithId(Long id){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Workflow> worflows = em.createNamedQuery("chosenWorkflow").setParameter("id", id)
                                            .getResultList();
            Workflow chosenWorflow = worflows.get(0);
            List<Results> results = em.createNamedQuery("resultsForWorflow").setParameter("workflow", chosenWorflow)
                                            .getResultList();
            
            chosenWorflow.setResults(results);
            return chosenWorflow;
                    
        }    
}
