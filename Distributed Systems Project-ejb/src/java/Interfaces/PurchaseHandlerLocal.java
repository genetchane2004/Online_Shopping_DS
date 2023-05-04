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
package Interfaces;

import DB_Entities.Purchases;
import javax.ejb.Local;

/**
 *
 * Interface for PurchaseHandler EJB
 */
@Local
public interface PurchaseHandlerLocal {

    /**
     *
     * @param object
     */
    public void persist(Object object);

    /**
     * Add a new purchase order entry to the Purchases table.
     * @param purchase
     */
    public void addPurchaseOrder(Purchases purchase);
    
}
