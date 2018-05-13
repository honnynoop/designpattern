package reuse;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Provides a ValueObject implementation. 
 */
class ValueObjectImpl implements ValueObject {
   private Map nameMap = new HashMap (10);

   /**
    * Returns the names of the properties stored in this value object
    * @return java.util.Iterator
    */
  public Iterator getPropertyNames() {
    return nameMap.keySet().iterator();
  }

   /**
    * Sets a property to a given value. 
    * @param name  Name of the property to set.
    * @param value New value for the property.
    */
  public void setProperty(java.lang.String name, java.lang.Object value) {
    nameMap.put(name, value);
  }

   /**
    * Returns the value of a property.
    * @param name Name of the property to retreive.
    * @return java.lang.Object representing the value, or null if no property has
    * been set with this name.
    */
  public Object getProperty(java.lang.String name) {
    return nameMap.get(name);
  }

  /**
   * returns a string representation of myself
   */
  public String toString() {
    StringBuffer buf = new StringBuffer( this.getClass().getName() + "::\n" );
    for (Iterator it = getPropertyNames(); it.hasNext(); ) {
      String name = it.next().toString();
      Object value = getProperty(name);
      buf.append(name + "=" + value);
      if( it.hasNext() ) {
        buf.append( ", " );
      } 
    }
    return buf.toString();
  }

}
