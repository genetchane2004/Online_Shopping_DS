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
package EJBs;

import DB_Entities.Product;
import Exceptions.ProductNotFoundException;
import Exceptions.ProductOutOfStock;
import Interfaces.ProductHandlerLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * Handler bean to allow interaction with the Product entity. Requests to add/delete product entries 
 * or update existing entries are handled here.
 */
@Stateless
public class ProductHandler implements ProductHandlerLocal {
    
    @PersistenceContext(unitName = "Distributed_Systems_Project-ejbPU")
    private EntityManager em;

    /**
     * Build a query to search Product table for a product name. Return data list associated with the 
     * entry it finds
     * 
     * @param name
     * @return List<Product>
     */
    @Override
    public List<Product> searchName(String name) {
        
        
       Query query = em.createNamedQuery("Product.findByProductName")
                .setParameter("productName", name);
        
        return query.getResultList();
    }
    
    /**
     * Build a query to search Product table for a product ID. Return data list associated with the 
     * entry it finds
     * 
     * @param productId
     * @return List<Product>
     */
    @Override
    public List<Product> searchId(int productId) {
        
        
       Query query = em.createNamedQuery("Product.findByProductId")
                .setParameter("productId", productId);
        
        return query.getResultList();
    }
    
    /**
     * Build a query to return all entries on the Product table.
     * 
     * @return List<Product>
     */
    @Override
    public List<Product> showAllProducts() {
        
        
       Query query = em.createNamedQuery("Product.findAll");
        
        return query.getResultList();
    }
    
    /**
     * Add a new product entry to the Product table
     * @param newproduct
     */
    @Override
    public void addProduct(Product newproduct){
           
        em.persist(newproduct);
    
    }
    
    /**
     *
     * @param object
     */
    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     * Delete a product entry for the Product table.
     * @param productID
     * @throws ProductNotFoundException
     */
    @Override
    public void deleteProduct(int productID) throws ProductNotFoundException{
        em.remove(getSingleProduct(productID));
    }
   
    /**
     * Build a query to return the data associated with a single product entry on the database. Search for specified 
     * product using product ID.
     * 
     * @param productID
     * @return product
     * @throws ProductNotFoundException
     */
    @Override
    public Product getSingleProduct(int productID) throws ProductNotFoundException {
        Product product;
        Query query = em.createNamedQuery("Product.findByProductId")
                .setParameter("productId", productID);
       
        try{
            product = (Product) query.getSingleResult();
        }catch (NoResultException nre) {
            /*
             * if product is not found throw exception
             */
            product = null;
            throw new ProductNotFoundException("Product ID " + productID
                    + " not found.", nre);
        }
        
        return product;
    }
    
    /**
     * Change the amount of available stock (quantity on hand) for a specified product entry. Search Product 
     * table using product ID.
     * 
     * @param productId
     * @param quantity
     */
    @Override
    public void updateQuantity(int productId, int quantity){
        
        ProductNotFoundException productNotFoundEx = null;
        Product product = null;
        try{
            product = getSingleProduct(productId);
        }catch(ProductNotFoundException pnf){
                    productNotFoundEx = pnf;
        }
        
        product.setProductStock(quantity);
 
    }
   
    /**
     * Decrease the amount of available stock (quantity on hand) for a particular product. Search
     * using produc ID.
     * 
     * @param productId
     * @param quantity
     */
    @Override
    public void removeStock(int productId, int quantity){
        ProductNotFoundException productNotFoundEx = null;
        ProductOutOfStock productOutOfStock = null;
        Product product = null;
        try{
            product = getSingleProduct(productId);
        }catch(ProductNotFoundException pnf){
                    productNotFoundEx = pnf;
        }
        
        try {
            product.removeStock(quantity);
        } catch (ProductOutOfStock poos) {
           
            productOutOfStock = poos;
        }
        
    }
    
}
