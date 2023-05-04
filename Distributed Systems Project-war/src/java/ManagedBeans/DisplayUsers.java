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
package ManagedBeans;

import DB_Entities.Customer;
import Interfaces.UserHandlerLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * Session bean to handle displaying Customer details. Requests the UserHandler EJB to return user information
 * from the Customer entity
 */
@Named(value = "displayUsers")
@SessionScoped
public class DisplayUsers implements Serializable {

    @EJB
    UserHandlerLocal userHandler;
    
    private String name;
    private int id;

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Request for the UserHandler EJB to search Customer table for a username
     * @return List<Customer>
     */
    public List<Customer> searchName() {
      
        return userHandler.searchName(name);
    }  
     
    /**
     * Request for the UserHandler EJB to search Customer table for a user ID
     * @return List<Customer>
     */
    public List<Customer> searchId() {
      
        return userHandler.searchId(id);
    }  
    
    /**
     * Create a new instance of DisplayUsers
     */
    public DisplayUsers() {
    }
    
}
