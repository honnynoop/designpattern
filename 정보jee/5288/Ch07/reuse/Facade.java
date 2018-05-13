package reuse;

/** Represets a facade interface to this package
*/

public interface Facade {

  /** Creates and returns a value object specific to this package
  * @return ValueObject specific to this package
  */	
  public ValueObject getValueObject( String name ) throws CreateException;
  
  /** Builds and returns a query for specified value object
  * @param type the type of the query
  * @param valueObject query parameters
  * @return Query the built query
  */
  public Query getQuery( String type, ValueObject valueObject );


}