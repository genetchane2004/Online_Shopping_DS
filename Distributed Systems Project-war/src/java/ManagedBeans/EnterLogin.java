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
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * Managed bean used to handle user login requests
 */
@Named(value = "enterLogin")
@RequestScoped
public class EnterLogin {
    
    private String name;
    private String password;
    
    @Inject
    SessionHandler sessionHandler;

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
     * Send a request to SessionHandler to authenticate a user using their entered credentials
     * @return "customer_home"
     */
    public String login() {
        Customer user = sessionHandler.login(name, password);
        if (user != null) {
            return "customer_home";
        } else {
            return "login_failed";
        }
    }
    
    
    /**
     * Creates a new instance of EnterLogin
     */
    public EnterLogin() {
        
    }
    
}
