package reuse;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A set of dynamic properties expressed as { name, value } pairs.
 * Typically, client code is expected to use the Facade interface
 * to retrieve the correct ValueObject implementation.
 */
public interface ValueObject extends Serializable {

   /**
    * Returns the names of the properties stored in the name space.
    * @return java.util.Iterator
    */
   public Iterator getPropertyNames();

   /**
    * Sets a property to a given value. Validation may be performed for
    * any given value, depending on the implementation being used.
    * @param name  Name of the property to set.
    * @param value New value for the property.
    * @throws ValidationException if the value is incorrect.
    * @throws com.woe.cmi.exception.ValidationException
    */
   public void setProperty(String name, Object value) throws ValidateException;

   /**
    * Returns the value of a property.
    * @param name Name of the property to retrieve.
    * @return java.lang.Object representing the value, or null if no property has
    * been set with this name.
    */
   public Object getProperty(String name);
}