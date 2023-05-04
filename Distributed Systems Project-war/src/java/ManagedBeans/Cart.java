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
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * Bean used to store contents of a customer's cart. Persists across the HTTP session.
 */
@Stateful
@LocalBean
public class Cart {

    private int quantity;
    private Product product;

    public Cart() {
    }

    /**
     * Number of particular product items in the cart
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Create a new instance of Cart
     * @param product
     * @param quantity
     */
    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Instance of Product table entry
     * @return product
     */
    public Product getProduct() {
        return product;
    }
    
    /**
     *
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    /**
     * Get ID of a particular product in the cart
     * @return productId
     */
    public Integer getProductId() {
        return product.getProductId();
    }

    /**
     * Set the product id
     *
     * @param productid 
     */
    public void setProductId(Integer productid) {
        product.setProductId(productid);
    }

    /**
     * Get name of a particular product in the cart
     * @return productName
     */
    public String getName() {
        return product.getProductName();
    }

    /**
     * Set product name
     * @param name
     */
    public void setName(String name) {
        product.setProductName(name);
    }

    /**
     * Get particular product description
     * @return productDescription
     */ 
    public String getDescription() {
        return product.getProductDescription();
    }

    /**
     * Set product description
     * @param description
     */
    public void setDescription(String description) {
        product.setProductDescription(description);
    }

    /**
     * Get the number of product items in stock
     * @return productStock
     */
    public Integer getStock() {
        return product.getProductStock();
    }

    /**
     * Set product stock
     * @param quantity
     */
    public void setStock(Integer quantity) {
        product.setProductStock(quantity);
    }

    /**
     * Get product price
     * @return productPrice
     */
    public int getPrice() {
        return product.getProductPrice();
    }

    /**
     * Set product price
     * @param productPrice
     */
    public void setPrice(int productPrice) {
        product.setProductPrice(productPrice);
    }
  
}
