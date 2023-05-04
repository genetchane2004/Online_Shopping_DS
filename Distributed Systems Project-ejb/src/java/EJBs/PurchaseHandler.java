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

import DB_Entities.Purchases;
import Interfaces.PurchaseHandlerLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * Handler bean to allow interaction with the Purchases entity. Handles the creation of new purchase orders when users
 * checkout their cart.
 */
@Stateless
public class PurchaseHandler implements PurchaseHandlerLocal {

   @PersistenceContext(unitName = "Distributed_Systems_Project-ejbPU")
    private EntityManager em;
   
    /**
     * Add a new purchase order entry to the Purchases table.
     * @param purchase
     */
    @Override
    public void addPurchaseOrder(Purchases purchase){
        em.persist(purchase);
    }

    /**
     *
     * @param object
     */
    @Override
    public void persist(Object object) {
        em.persist(object);
    }
}
