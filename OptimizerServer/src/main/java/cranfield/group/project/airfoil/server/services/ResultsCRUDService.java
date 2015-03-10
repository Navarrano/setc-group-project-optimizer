/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;


import cranfield.group.project.airfoil.server.entities.Results;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emi
 */
public class ResultsCRUDService extends AbstractCRUDService<Long, Results>{
    
         EntityManagerFactory emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
         
         //add new result
        public void addResult(Results obj){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
	}
        
        //add list of results
        public void addListOfResults(List<Results> listOfResults){
            
        }
        
        
        //get list of results for particualr worflow
        public List<Results> getAllWorkflowResults(){
            List<Results> worflowResults = new ArrayList<Results>();
            
            return worflowResults;
        }
        
        //get result of last iteration for particular workflow
        public Results getLastResult(){
            Results lastIterResult = new Results();
            
            
            return lastIterResult;
            
        }
}
