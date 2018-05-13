package common;

/*
 * The ServiceLocatorException class is an exception that is
 * thrown whenever a user requests an EJB or database connection from the
 * ServiceLocator and the ServiceLocator can not find the item the user is
 * loogkinh for.
 *
 */
public class ServiceLocatorException extends Exception {
  public ServiceLocatorException(String pExceptionMsg) {
    super(pExceptionMsg);
  }   
}
