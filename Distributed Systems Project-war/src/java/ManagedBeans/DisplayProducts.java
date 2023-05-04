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

import DB_Entities.Product;
import Interfaces.ProductHandlerLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * Managed bean to handle displaying product items. Returns List items representing entries on the Product table.
 */
@Named(value = "displayProducts")
@SessionScoped
public class DisplayProducts implements Serializable{
    
    @EJB
    ProductHandlerLocal productHandler;

    @Inject
    SessionHandler sessionHandler;
    
    private String name;
    private int productId;

    /**
     *
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
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
     * Request to ProductHandler EJB to search Product table for product name
     * @return List<Product>
     */
    public List<Product> searchName() {
      
        return productHandler.searchName(name);
    }
    
     /**
     * Request to ProductHandler EJB to search Product table for product ID
     * @return List<Product>
     */
    public List<Product> searchId() {
      
        return productHandler.searchId(productId);
    }
    
    /**
     * Request to ProductHandler EJB to return a List of all products on the Product table
     * @return List<Product>
     */
    public List<Product> showAllProducts(){
    
        return productHandler.showAllProducts();
    }
    /**
     * Creates a new instance of DisplayProducts
     */
    public DisplayProducts() {
    }
    
}
