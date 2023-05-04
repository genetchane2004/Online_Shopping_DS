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
 * Exception thrown if a product entry cannot be found on the Product table
 */
public class ProductNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ProductNotFoundException</code> without
     * detail message.
     * @param message
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs an instance of <code>ProductNotFoundException</code> with the
     * specified detail message.
     *
     * @param message
     * @param throwable
     */
    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
