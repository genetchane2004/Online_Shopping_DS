/*
* Authors
*
* Conor Egan 13138782
* Mark Dempsey 12062863
* Niall Phillips 13153382 
* Luke Robinson 13132822
* Simon Griffin 13125648
*
*/
package EJBs;

import DB_Entities.Administrator;
import Interfaces.AdminHandlerLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Handles requests to edit or search the Administrator database table. Provides a means to interact with the 
 * Administrator entity.
 * 
 */
@Stateless
public class AdminHandler implements AdminHandlerLocal {

    
    @PersistenceContext(unitName = "Distributed_Systems_Project-ejbPU")
    private EntityManager em;
    
    /**
     * Handles admin login requests. Searches Administrator entity for username, returns the associated
     * Administrator entry. Checks the entered password against the stored value in the database. Returns
     * null for invalid login details.
     * @param username
     * @param password
     * @return admin
     */
    @Override
    public Administrator login(String username, String password) {
        Administrator admin;
        Query query = em.createNamedQuery("Administrator.findByAdminName")
                .setParameter("adminName", username);
        try {
            admin = (Administrator) query.getSingleResult();
        } catch (NoResultException nre) {
            admin = null;
        }
        if (admin == null) {
            return null;
        } else if (admin.getAdminPassword().equals(password)) {
            return admin;
        } else {
            return null;
        }
    }
    
    /**
     * Add a new entry to the Administrator table
     * @param newadmin
     */
    @Override
    public void addAdmin(Administrator newadmin){
           
        em.persist(newadmin);
    
    }

    /**
     *
     * @param object
     */
    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * Build query to search Administrator table for administrator name. Return data list associated with the 
     * found table row.
     * @param name
     * @return List<Administrator>
     */
    @Override
    public List<Administrator> searchName(String name) {
        
        Query query = em.createNamedQuery("Administrator.findByAdminName")
                .setParameter("adminName", name);
        
        return query.getResultList();
    }
    
    /**
     * Build a query to return a list of all Administrator entries on the database.
     * @return List<Administrator>
     */
    @Override
    public List<Administrator> showAllAdmins() {
        
        
       Query query = em.createNamedQuery("Administrator.findAll");
        
        return query.getResultList();
    }
}
