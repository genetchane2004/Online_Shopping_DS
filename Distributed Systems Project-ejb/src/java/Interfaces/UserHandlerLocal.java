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
package Interfaces;

import DB_Entities.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * Interface for UserHandler
 */
@Local
public interface UserHandlerLocal {
    
    /**
     * Handles customer login requests. Searches Customer entity for username, returns the associated
     * Customer entry. Checks the entered password against the stored value in the database. Returns
     * null for invalid login details.
     * @param username
     * @param password
     * @return user
     */
    public Customer login(String username, String password);
    
    /**
     * Add a new entry to the Customer table
     * @param newuser
     */
    public void addUser(Customer newuser);
    
    /**
     * Build query to search the Customer table for a username. Returns the data associated with the customer entry 
     * found by the query.
     * @param name
     * @return List<Customer>
     */
    public List<Customer> searchName(String name);
    
    /**
     * Update an existing entry with new data
     * @param user
     */
    public void replaceUser(Customer user);
    
    /**
     * Build query to search the Customer table for a customer ID. Returns the data associated with the customer entry 
     * found by the query.
     * @param id
     * @return List<Customer>
     */
    public List<Customer> searchId(int id);

    /**
     *
     * @param object
     */
    public void persist(Object object);
    
}
