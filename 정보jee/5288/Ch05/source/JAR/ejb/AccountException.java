/*
 * AccountException.java
 *
 * Created on 27 April 2002, 10:44
 */

package ejb;

/**
 *
 * @author  pznwc5
 */
public class AccountException extends Exception {
    
    /** Creates a new instance of AccountException */
    public AccountException() {
    }
    
    public AccountException(String message) { super(message); }
    
}
