package reuse;

/** represets a query implemenation
*/

abstract class QueryImpl implements Query {

  private String queryHeader = "";
  private String queryToken = "";
  private String queryBody = "";
  
  /** Creates instance of me
  */
  QueryImpl( String queryHeader,
             String queryToken,
             String queryBody ) {
    this.queryHeader = queryHeader;
    this.queryToken = queryToken;
    this.queryBody = queryBody;
  }

  public String toString() {
    return stringValue(); 
  }
  
  public final String stringValue() {
     StringBuffer buf = new StringBuffer();
     buf.append( getQueryHeader() );
     buf.append( getExtraHeaderInfo() + "|" );
     buf.append( getQueryToken() + "," );
     buf.append( getExtraTokens() + "|" );
     buf.append( getQueryBody() + "," );
     buf.append( getExtraBody() + "||" );
     return buf.toString();
  }

  /** A Template method uses a primitive operation 
  */
  protected String getQueryHeader() {
  	return queryHeader;
  }
  
  /** A Template method uses a primitive operation 
  */
  protected String getQueryToken() {
  	return queryToken;
  }
  
  /** A Template method uses a primitive operation 
  */
  protected String getQueryBody() {
  	return queryBody;
  }
  
  /** A primitive operation defined by derived class
  */
  abstract protected String getExtraHeaderInfo(); 

  /** A primitive operation defined by derived class
  */
  abstract protected String getExtraTokens(); 

  /** A primitive operation defined by derived class
  */
  abstract protected String getExtraBody(); 

}