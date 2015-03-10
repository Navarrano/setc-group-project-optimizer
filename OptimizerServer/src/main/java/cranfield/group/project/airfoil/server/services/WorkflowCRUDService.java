/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.api.model.OptimizationObject;
import cranfield.group.project.airfoil.server.entities.AstralUser;
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
                em.persist(obj);
                em.getTransaction().commit();
	}
        
        //all warkflows from particulat user
        public List<Workflow> allUsersWorkflows(){
            List<Workflow> userWorkflows = new ArrayList<Workflow>();
            
            return userWorkflows;
        }
        
        public List<OptimizationObject> getIdAndNameOfWorflows(AstralUser userObj){
            List<OptimizationObject> worflowList = new ArrayList<>();
             
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<AstralUser> results = em.createNamedQuery("allUsersWorflows").setParameter("creator", userObj)
                                            .getResultList();
            
            System.out.print("bjsfhdjbdsjf" + results);
            //if(results.isEmpty()){
             // existsUser = false;
            //}
                
            return worflowList;
        }
}
