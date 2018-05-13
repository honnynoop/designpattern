package reuse;

import java.io.PrintStream;
import java.io.PrintWriter;

/** Indicates that a validation exceptional condition has occured
*/

public class ValidateException extends BaseException {

  /**Creates an instance of me
  */
  public ValidateException() { super(); }

  /**Creates an instance of me
  */
  public ValidateException(String msg) { super(msg); }

  /**Creates an instance of me
  */
  public ValidateException(String msg, Throwable cause) { super(msg, cause); }

  /**Creates an instance of me
  */
  public ValidateException(Throwable cause) { super(cause); }

}
