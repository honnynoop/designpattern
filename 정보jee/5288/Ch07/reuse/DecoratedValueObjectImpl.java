package reuse;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

/** Provides a decorated ValueObject implementation with validation. 
 */
class DecoratedValueObjectImpl implements ValueObject {

   private ValueObject valueObject = null;
   
   /** Creates instance of me 
   */
   DecoratedValueObjectImpl( ValueObject valueObject ) {
     this.valueObject = valueObject;
   }
   
   /** Returns the names of the properties stored in this value object
    * @return java.util.Iterator
    */
   public Iterator getPropertyNames() {
    return valueObject.getPropertyNames();
   }

   /** Sets a property to a given value. 
    * @param name  Name of the property to set.
    * @param value New value for the property.
    */
   public void setProperty(java.lang.String name, java.lang.Object value) throws ValidateException {
     if( name == null || value == null ) {
       throw new ValidateException( "Error: name or value being set is null" );
     } 
     else if ( name != null && name.equals( "" ) ) {
       throw new ValidateException( "Error: name being set is empty value" );
     }
     else {
       valueObject.setProperty(name, value);
     }
   }

   /** Returns the value of a property.
    * @param name Name of the property to retreive.
    * @return java.lang.Object representing the value, or null if no property has
    * been set with this name.
    */
   public Object getProperty( java.lang.String name ) {
    return valueObject.getProperty( name );
   }

  /** Returns a string representation of myself
   */
  public String toString() {
    return valueObject.toString();
  }

}