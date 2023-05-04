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
import Exceptions.ProductNotFoundException;
import Interfaces.ProductHandlerLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * Managed bean used to handle requests to edit product entries on the Product table
 */
@Named(value = "editProducts")
@RequestScoped
public class EditProducts {
    
    
    @EJB
    ProductHandlerLocal productHandler;
    
    @Inject
    SessionHandler sessionHandler;
    
    
    private String name;
    private String author;
    private int price;
    private int stock;
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
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
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
     * Verify that the current user has authority to add new products
     * @return "add_product"
     */
    public String authenticateAdd(){
   
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
            return "add_product";
        }
    }
    
    /**
     * Verify that the current user has authority to delete products
     * @return "delete_product"
     */
    public String authenticateDelete(){
   
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
            return "delete_product";
        }
   }
    
    /**
     * Verify that the current user has authority to change the available quantity of a product
     * @return "delete_product"
     */    
    public String authenticateQuantityChange(){
   
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
            return "change_quantity";
        }
   }
    
    /**
     * Request to productHandler to change the quantity available of a particular product on Product table
     * @param productId
     * @return "all_products"
     */
    public String updateQuantity(int productId){
        
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
            productHandler.updateQuantity(productId, stock);
            return "all_products";
        }
    }
    
    /**
     * Send a request to ProductHandler to delete a product entry from the Product table
     * @param productId
     * @return "all_products"
     */
    public String deleteProduct(int productId){
    
        if (!(sessionHandler.checkAdmin())) {
                return "admin_login";
            }
        else{
            ProductNotFoundException productNotFoundEx = null;
            try{
                productHandler.deleteProduct(productId);
            }catch(ProductNotFoundException pnf){
                    productNotFoundEx = pnf;
            }
            return "all_products";
        }
}
    
    /**
     * Request ProductHandler to add a product entry to the Product table
     * @return
     */
    public String addProduct(){
    
        if (!(sessionHandler.checkAdmin())) {
            return "admin_login";
        }
        else{
        
            Product newproduct = new Product();
            newproduct.setProductName(name);
            newproduct.setProductAuthor(author);
            newproduct.setProductPrice(price);
            newproduct.setProductStock(stock);
            newproduct.setProductDescription(description);

            productHandler.addProduct(newproduct);

            return "all_products";
        }
    }
    
    /**
     * Create a new instance of EditProducts
     */
    public EditProducts() {
    }
    
}
