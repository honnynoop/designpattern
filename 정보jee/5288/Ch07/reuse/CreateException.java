package reuse;

import java.io.PrintStream;
import java.io.PrintWriter;

/** Indicates that a creation exceptional condition has occured
*/

public class CreateException extends BaseException {

  /**Creates an instance of me
  */
  public CreateException() { super(); }

  /**Creates an instance of me
  */
  public CreateException(String msg) { super(msg); }

  /**Creates an instance of me
  */
  public CreateException(String msg, Throwable cause) { super(msg, cause); }

  /**Creates an instance of me
  */
  public CreateException(Throwable cause) { super(cause); }

}
