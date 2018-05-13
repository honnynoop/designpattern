package reuse;

/** value object factory interface 
*/

interface ValueObjectFactory {
	
  /** Creates a product called based on the name specified
  * @param name of product to create
  * @return the product created
  */	
  public ValueObject createValueObject( String name ) throws CreateException;

 
}