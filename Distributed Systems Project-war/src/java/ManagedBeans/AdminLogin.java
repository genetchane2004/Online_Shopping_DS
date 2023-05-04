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
import Interfaces.AdminHandlerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * Managed bean used to handle admin login and registration requests
 */
@Named(value = "adminLogin")
@RequestScoped
public class AdminLogin {

    private String name;
    private String password;
   
    
    @Inject
    SessionHandler sessionHandler;
    
    @EJB
    AdminHandlerLocal adminHandler;
    
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
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Validate the name and password entered by the admin user in the login fields. 
     * @return "admin_home"
     */
    public String login() {
       
        Administrator admin = sessionHandler.adminLogin(name, password);
        if (admin != null) {
            return "admin_home";
        } else {
            return "login_failed";
        }
    }
   
    /**
     * Check to see if user is a valid admin. Allow access to add_admin page if user is valid.
     * @return "add_admin"
     */
    public String addAdmin(){
   
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
            return "add_admin";
        }
   }
   
    /**
     * Create a new Administrator entry on the database
     * @return "admin_list"
     */
    public String registerAdmin(){       
            
        Administrator newadmin = new Administrator();
       
        newadmin.setAdminName(name);
        newadmin.setAdminPassword(password);
               
        adminHandler.addAdmin(newadmin);
        return "admin_list";
    }
    
    /**
     * Return a list of all registered administrators
     * @return List<Administrator>
     */
    public List<Administrator> showAllAdmins(){
    
        return adminHandler.showAllAdmins();
    }
    
}
