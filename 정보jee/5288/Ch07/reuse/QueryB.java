package reuse;

/** A special query of type B
*/

final class QueryB extends QueryImpl {

  
  /** Creates instance of me
  */  
  QueryB( String queryHeader,
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
  	return "B";
  }
  
  /** A primitive operation called by super class
  */
  protected String getExtraBody() {
    return "className=" + this.getClass().getName();
  }

}