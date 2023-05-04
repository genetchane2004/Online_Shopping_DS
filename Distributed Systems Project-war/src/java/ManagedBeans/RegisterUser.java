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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


/**
 *
 * Handle requests to create a new Customer entry on the database
 */
@Named(value = "registerUser")
@RequestScoped
public class RegisterUser {
    
    @Inject
    SessionHandler sessionHandler;
    
    @EJB
    UserHandlerLocal userHandler;
        
      
    private String name;
    private String password;
    private String email;
    private String address;
    private String description;

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
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Create a new Customer object. Send request to UserHandler to store new object as entry on Customer table
     * @return "customer_home"
     */
    public String addUser(){
        
        
        
        Customer newuser = new Customer();
       
        newuser.setCustomerName(name);
        newuser.setCustomerPassword(password);
        newuser.setCustomerEmail(email);
        newuser.setCustomerAddress(address);
        newuser.setCustomerDescription(description);
        
        userHandler.addUser(newuser);
        
        Customer user = sessionHandler.login(name, password);
        if (user != null) {
            return "customer_home";
        } else {
            return "login_failed";
        }
    }
        
        
    

    /**
     * Creates a new instance of RegisterUser
     */
    public RegisterUser() {
    }
    
}
