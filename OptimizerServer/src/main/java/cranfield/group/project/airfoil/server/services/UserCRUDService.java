/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cranfield.group.project.airfoil.server.services;

import cranfield.group.project.airfoil.server.entities.AstralUser;
import cranfield.group.project.airfoil.server.entities.Workflow;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emi
 */
public class UserCRUDService extends AbstractCRUDService<Long, AstralUser>{
    
         EntityManagerFactory emf = EntityFactoryProvider.getInstance().createEntityManagerFactory();
         
         /**
	 * Determine if the user whose username is the function argument already
	 * exists in the AstralUser table of the MARS database.
	 *
	 * @param username, the username which existence in the table is to be tested.
	 * @return true, if the username is in the table. False, if it is not.
	 */
	public boolean existsUser(String login){
		boolean existsUser = true;
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                List<AstralUser> results = em.createNamedQuery("existingUser").setParameter("login", login)
                                            .getResultList();
                if(results.isEmpty()){
                    existsUser = false;
                }
		return existsUser;
	}

	/**
	 * Adds the new user whose username is the function argument in the AstralUser table of the database.
	 *
	 * @param username, the username of the user to be added in the database
	 */
	public void addNewUser(AstralUser obj){
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
	}

	/**
	 * Update user connection information of the user whose username is the function argument.
	 * The update consists in increasing the number of times the user has connected to MARS and
	 * to update the last connection time/date to the current one.
	 * @param username, the username of the user the connection information has to be updated.
	 */
	public void updateUserConnectionInformation(AstralUser obj){              
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                //obj = find(AstralUser.class, obj.getId());
                obj.setLast_connection_date(new Timestamp(new java.util.Date().getTime()));
                obj.setNb_connections(obj.getNb_connections() + 1);
                em.getTransaction().commit();
	}

        public Long getUserId(String login){
            Long userId;
            EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                 List<AstralUser> results = em.createNamedQuery("existingUser").setParameter("login", login)
                                            .getResultList();
                 
                if(results.isEmpty()){
                    System.out.print("No user with given login");
                    Long i =  new Long(0);
                    userId = i;
                } else {
                   userId = results.get(0).getId();
                }
              
                return userId;
        }
        
        public AstralUser getUserObj(String login){
             
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<AstralUser> results = em.createNamedQuery("existingUser").setParameter("login", login)
                                            .getResultList();
            AstralUser userObj = results.get(0);
            List<Workflow> worflows = em.createNamedQuery("getWorkflows").setParameter("creator", userObj)
                                            .getResultList();
            userObj.setWorkflows(worflows);
            return userObj;
        }
}
