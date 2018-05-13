package reuse;

/** Represets a facade implementaion
*/

public class FacadeImpl implements Facade {

  ValueObjectFactory factory = new ValueObjectFactoryImpl();

  /** Creates and returns a value object specific to this package
  * @return ValueObject specific to this package
  */	  
  public ValueObject getValueObject( String name ) throws CreateException {
    return factory.createValueObject( name );
  }
  
  /** Builds and returns a query for specified value object
  * @param type the type of the query
  * @param valueObject query parameters
  * @return Query the built query
  */
  public Query getQuery( String type, ValueObject valueObject ) {
    QueryBuilder builder = new QueryBuilderImpl( valueObject );
    builder.buildQueryHeader();
    builder.buildQueryToken();
    builder.buildQueryBody();
    return builder.getQuery( type );
  }

  //other facade methods

}