package reuse;

import java.io.PrintStream;
import java.io.PrintWriter;

/** The base exception class for this package
*/

public class BaseException extends Exception {

  protected Throwable cause;

  /**Creates an instance of me
  */
  public BaseException()  {
  }

  /**Creates an instance of me
   * @param message <code>String</code> message indicating
   *                the problem that occurred.
   */
  public BaseException(String message)  {
      super(message);
  }

  /**Creates an instance of me
   * @param message <code>String</code> message indicating
   *                the problem that occurred.
   * @param exception <code>Exception</code> that caused this
   *                  to be thrown.
   */
  public BaseException(String message, Throwable cause)  {
      super(message);
      this.cause = cause;
  }

  /**Creates an instance of me
   * @param exception <code>Exception</code> that caused this
   *                  to be thrown.
   */
  public BaseException(Throwable cause)
  {
    this(cause.getLocalizedMessage(), cause);
  }

  /** Returns the message for the <code>Exception</code>. If
   *   there is a root cause, the message associated with the root
   *   <code>Exception</code> is appended.
   * @return <code>String</code> - message for <code>Exception</code>.
   */
  public String getMessage() {
    if (cause != null) {
      return super.getMessage() + ": " + cause.getMessage();
    } else {
      return super.getMessage();
    }
  }

  /** Prints the stack trace of the <code>Exception</code>. If
   *   there is a root cause, the stack trace of the root
   *   <code>Exception</code> is printed right after.
   */
  public void printStackTrace() {
    super.printStackTrace();
    if (cause != null) {
      System.err.print("Root cause: ");
      cause.printStackTrace();
    }
  }

  /** Prints the stack trace of the <code>Exception</code> to the given
   *   PrintStream. If there is a root cause, the stack trace of the root
   *   <code>Exception</code> is printed right after.
   */
  public void printStackTrace(PrintStream s) {
    super.printStackTrace(s);
    if (cause != null) {
      s.print("Root cause: ");
      cause.printStackTrace(s);
    }
  }

  /**Prints the stack trace of the <code>Exception</code> to the given
   *   PrintWriter. If there is a root cause, the stack trace of the root
   *   <code>Exception</code> is printed right after.
   */
  public void printStackTrace(PrintWriter w) {
    super.printStackTrace(w);
    if (cause != null) {
      w.print("Root cause: ");
      cause.printStackTrace(w);
    }
  }

  /**Returns the root cause <code>Throwable</code>, or null
   *   if one does not exist.
   * @return <code>Throwable</code> - the wrapped <code>Throwable</code>.
   */
  public Throwable getCause()  {
    return cause;
  }

}
