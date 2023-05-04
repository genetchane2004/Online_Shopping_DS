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
import DB_Entities.Purchases;
import Interfaces.ProductHandlerLocal;
import Interfaces.PurchaseHandlerLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;

/**
 *
 * Session bean used to manage the contents of a users cart across a HTTP session. Handles items added/deleted from cart,
 * checkout functionality and updates purchase orders.
 */
@SessionScoped
@Named(value = "cartSession")
public class CartSession implements Serializable {

    @Inject
    SessionHandler sessionHandler;
    
    @EJB
    ProductHandlerLocal productHandler;
    
    @Inject
    MessageClient messageClient;
    
    @EJB
    PurchaseHandlerLocal purchaseHandler;
    
       
    private int quantity;
    private int totalPrice;
    
    private List<Cart> items = new ArrayList<>();

    /**
     *
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
     * Add item to cart. Products in cart are handled as a List. If product ID already exists in list, then quantity 
     * of that product increased. If product ID does not already exist in list, new product added to list.
     * @param product
     * @return "cart_page"
     */
    public String addToCart(Product product){
        
        /*Check that Customer is logged in. Redirect to login page if not*/
        if (!(sessionHandler.checkLogin())) {
            return "login";
        }
       
        boolean exists = false;
        for (Cart item : items) {
            if (Objects.equals(item.getProductId(), product.getProductId())) {
                exists = true;
                item.setQuantity(item.getQuantity() + quantity);
                break;
            }
        }
        if (!exists) {
            items.add(new Cart(product, quantity));
        }
        
       return "cart_page";
    }

    /**
     * Remove product from cart
     * @param productId
     * @return
     */
    public String clearItem(int productId){
    
        /*Check that Customer is logged in. Redirect to login page if not*/
        if (!(sessionHandler.checkLogin())) {
            return "login";
        }
            
        for (Cart item : items) {
            if (item.getProductId() == productId) {
                
                items.remove(item);
                break;
            }
        }
        return "cart_page";
    }
    
    /**
     * Return all products in cart
     * @return items
     */
    public List<Cart> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(List<Cart> items) {
        this.items = items;
    }

    /**
     * Clear all products from cart
     */
    public void clearCart() {
        items.clear();
    }
    
    /**
     * Check out cart contents. All items are removed from cart, an entry is added to the database log,
     * and the purchase orders database table is updated
     * 
     * @return "checkout_success"
     */
    public String checkout(){
        /* Get all items in the cart */
        List<Cart> CheckoutItems = getItems();
        List<Cart> BoughtItems = new ArrayList();
        totalPrice = 0;        
        
        for (Cart item : CheckoutItems) {
                
                    int prodID = item.getProductId();
                    int prodQuantity = item.getQuantity();
                    productHandler.removeStock(prodID, prodQuantity);
                    BoughtItems.add(item);
                    totalPrice = totalPrice + (item.getPrice()*item.getQuantity());
                }
               
            /* add item to cart*/
           
        clearCart();
        String message = "Order Completed.";
        try{
        messageClient.logMessage(message, sessionHandler.getUser().getCustomerName());
        }catch (JMSException ex) {
               
            }
        addPurchaseOrder();
        return "checkout_success";
        
    }
    
    /**
     * Add a new purchase order entry to the database
     */
    public void addPurchaseOrder(){
    
        Date date = new Date();
        Purchases purchase = new Purchases();
        
        purchase.setTime(date);
        purchase.setCustomerName(sessionHandler.getUser().getCustomerName());
        purchase.setPurchaseAmount(totalPrice);
        
        purchaseHandler.addPurchaseOrder(purchase);
        
    }
     
}
