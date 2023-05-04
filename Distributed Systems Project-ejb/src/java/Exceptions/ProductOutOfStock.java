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
package Exceptions;

/**
 *
 * Exception thrown if user attempts to check out a quantity of products greater than the available stock
 */
public class ProductOutOfStock extends Exception {

   public ProductOutOfStock(String message) {
        super(message);
    }

    /**
     *
     * @param message exception message
     * @param throwable exception
     */
    public ProductOutOfStock(String message, Throwable throwable) {
        super(message, throwable);
    }

}
