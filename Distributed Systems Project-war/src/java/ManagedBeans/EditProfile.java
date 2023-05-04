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
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * Session bean to handle requests to edit a Customer entry on the Customer table
 */
@Named(value = "editProfile")
@SessionScoped
public class EditProfile implements Serializable {
    
     @EJB
    UserHandlerLocal userHandler;

    @Inject
    SessionHandler sessionHandler;

    private Customer user;

    /**
     *
     * @return user
     */
    public Customer getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(Customer user) {
        this.user = user;
    }

    /**
     * Restrict access to the update profile page to logged in users only
     * @param user
     * @return
     */
    public String editUser(Customer user) {
        /* Only allow users to edit their own profile */
        if (sessionHandler.getUser().equals(user)) {
            this.user = user;
            return "update_profile";
        }
        return "login_failed";
    }

    /**
     * Request UserHandler EJB to update an existing Customer entry
     * @return
     */
    public String updateUser() {
        if (sessionHandler.getUser().equals(user)) {
            this.user = user;
            userHandler.replaceUser(user);
            return "customer_home";
        }

        return "login_failed";
    }
}
