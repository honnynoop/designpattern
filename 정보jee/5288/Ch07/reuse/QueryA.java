package reuse;

/** A special query of type A
*/
final class QueryA extends QueryImpl {

  /** Creates instance of me
  */  
  QueryA( String queryHeader,
          String queryToken,
          String queryBody ) {
    super( queryHeader, queryToken, queryBody );
  }

  /** A primitive operation called by super class
  */
  protected String getExtraHeaderInfo() {
  	return "";
  }
  
  /** A primitive operation called by super class
  */
  protected String getExtraTokens() {
  	return "A";
  }
  
  /** A primitive operation called by super class
  */
  protected String getExtraBody() {
    return "className=" + this.getClass().getName();
  }

}