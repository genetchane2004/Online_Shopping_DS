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

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * Session bean used to display all data associated with a particular product entry on the Product table
 */
@Named (value = "individualProduct")
@SessionScoped
public class IndividualProduct implements Serializable {
    
   private Product product;
    
    /**
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     *
     * @param product
     * @return "product_page"
     */
    public String setProduct(Product product) {
        this.product = product;
        return "product_page";
    }
    
    /**
     *
     * @return productId
     */
    public int getID(){
        return product.getProductId();
    }

    /**
     *
     * @return productName
     */
    public String getName(){
        return product.getProductName();
    }    

    /**
     *
     * @return productAuthor
     */
    public String getAuthor(){
        return product.getProductAuthor();
    }

    /**
     *
     * @return productDescription
     */
    public String getDescription(){
        return product.getProductDescription();
    }
    
    /**
     *
     * @return productPrice
     */
    public int getPrice(){
        return product.getProductPrice();
    }
    
    /**
     *
     * @return productStock
     */
    public int getStock(){
        return product.getProductStock();
    }
    
    /**
     *
     * @return productImage
     */
    public String getImage(){
        return product.getProductImage();
    }
 
    
}
