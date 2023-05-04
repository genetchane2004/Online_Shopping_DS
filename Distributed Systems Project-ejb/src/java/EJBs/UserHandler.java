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

import DB_Entities.Customer;
import Interfaces.UserHandlerLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * Handles requests to edit or search the Customer database table. Provides a means to interact with the 
 * Customer entity.
 */
@Stateless
public class UserHandler implements UserHandlerLocal {

    @PersistenceContext(unitName = "Distributed_Systems_Project-ejbPU")
    private EntityManager em;

    /**
     * Handles customer login requests. Searches Customer entity for username, returns the associated
     * Customer entry. Checks the entered password against the stored value in the database. Returns
     * null for invalid login details.
     * @param username
     * @param password
     * @return user
     */
    @Override
    public Customer login(String username, String password) {
        Customer user;
        Query query = em.createNamedQuery("Customer.findByCustomerName")
                .setParameter("customerName", username);
        try {
            user = (Customer) query.getSingleResult();
        } catch (NoResultException nre) {
            user = null;
        }
        if (user == null) {
            return null;
        } else if (user.getCustomerPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
    
    /**
     * Add a new entry to the Customer table
     * @param newuser
     */
    @Override
    public void addUser(Customer newuser){
           
        em.persist(newuser);
    
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
     * Build query to search the Customer table for a username. Returns the data associated with the customer entry 
     * found by the query.
     * @param name
     * @return List<Customer>
     */
    @Override
    public List<Customer> searchName(String name) {
        
        Query query = em.createNamedQuery("Customer.findByCustomerName")
                .setParameter("customerName", name);
        
        return query.getResultList();
    }
    
    /**
     * Build query to search the Customer table for a customer ID. Returns the data associated with the customer entry 
     * found by the query.
     * @param id
     * @return List<Customer>
     */
    @Override
    public List<Customer> searchId(int id) {
        
        Query query = em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", id);
        
        return query.getResultList();
    }
    
    /**
     * Update an existing entry with new data
     * @param user
     */
    @Override
    public void replaceUser(Customer user) {
        em.merge(user);
    }
}
