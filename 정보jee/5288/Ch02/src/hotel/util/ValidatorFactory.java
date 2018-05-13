package hotel.util;


/** Represents abstract factory interface to create validators. 
* Abstract Factory Provides an interface for creating families 
* of related or dependent objects without specifying their concrete
* classes.
*/

public interface ValidatorFactory {     

  /** Creates a Validator instance 
  */
    public Validator createValidator( );

}
