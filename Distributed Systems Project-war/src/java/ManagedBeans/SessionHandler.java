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

import DB_Entities.Administrator;
import DB_Entities.Customer;
import Interfaces.AdminHandlerLocal;
import Interfaces.UserHandlerLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.*;
import javax.inject.Inject;

/**
 *
 * Session bean to persist a users login details across an HTTP session
 */
@Named(value = "sessionHandler")
@SessionScoped
public class SessionHandler implements Serializable {

    @EJB
    UserHandlerLocal userHandler;

    @EJB
    AdminHandlerLocal adminHandler;

    @Inject
    CartSession cartSession;

    private Customer user;
    private Administrator admin;

    /**
     *
     * @return user
     */
    public Customer getUser() {
        return user;
    }

    /**
     *
     * @return
     */
    public Administrator getAdmin() {
        return admin;
    }

    /**
     * Log into the application
     *
     * @param username username attribute
     * @param password password attribute
     * @return logged in user, null if login fails
     */
    public Customer login(String username, String password) {
        user = userHandler.login(username, password);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Log in as administrator
     * @param username
     * @param password
     * @return
     */
    public Administrator adminLogin(String username, String password) {
        admin = adminHandler.login(username, password);
        if (admin != null) {
            return admin;
        } else {
            return null;
        }
    }

    /**
     * Check to see if the current session user is logged in as a customer.
     * @return user
     */
    public boolean checkLogin() {
        return user != null;
    }
    
    /**
     * Check to see if the current session user is logged in as an admin
     * @return admin
     */
    public boolean checkAdmin() {
        return admin != null;
    }

    /**
     * Log out of application
     * @return
     */
    public String logout() {
        user = null;
        cartSession.clearCart();
        admin = null;
        return "login";

    }

}
