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

import DB_Entities.Administrator;
import java.util.List;
import javax.ejb.Local;

/*
* Interface for the AdminHandler EJB.
*/

@Local
public interface AdminHandlerLocal {
    /**
     * Handles admin login requests. Searches Administrator entity for username, returns the associated
     * Administrator entry. Checks the entered password against the stored value in the database. Returns
     * null for invalid login details.
     * @param username
     * @param password
     * @return admin
     */
    public Administrator login(String username, String password);
    
    /**
     * Add a new entry to the Administrator table
     * @param newadmin
     */
    public void addAdmin(Administrator newadmin);
    
    /**
     * Build query to search Administrator table for administrator name. Return data list associated with the 
     * found table row.
     * @param name
     * @return List<Administrator>
     */
    public List<Administrator> searchName(String name);
    
    /**
     * Build a query to return a list of all Administrator entries on the database.
     * @return List<Administrator>
     */
    public List<Administrator> showAllAdmins();
    
    /**
     *
     * @param object
     */
    public void persist(Object object);
  
}
