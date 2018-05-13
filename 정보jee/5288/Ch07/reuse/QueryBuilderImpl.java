package reuse;

import java.util.Iterator;

class QueryBuilderImpl implements QueryBuilder {

  private String queryHeader = "";
  private String queryToken = "";
  private String queryBody = "";
  private ValueObject bodyParams;
  
  /** Creates instance of me
  */
  public QueryBuilderImpl( ValueObject bodyParams ) {
    this.bodyParams = bodyParams;
  }
  
  /** Builds the header of the query
  */
  public void buildQueryHeader() {
  	queryHeader = "External Data Source Query";
  }
  
  /** Builds the query token
  */
  public void buildQueryToken() {
  	queryToken = "CR";
  }
  
  /** Builds the query body
  */
  public void buildQueryBody() {
  	StringBuffer buf = new StringBuffer();
    Iterator propertyNames = bodyParams.getPropertyNames();
       while( propertyNames.hasNext() ) {
       	 String propertyName = ( String )propertyNames.next(); 
         buf.append( propertyName 
                     + "=" 
                     +  bodyParams.getProperty( propertyName ) );
         if( propertyNames.hasNext() ) {
         	buf.append(",");
         }
       }
    queryBody = buf.toString();
  }

  private String getQueryHeader() {
  	return queryHeader;
  }
  
  private String getQueryToken() {
  	return queryToken;
  }
  
  private String getQueryBody() {
  	return queryBody;
  }

  /** Returns the built query
  */
  public Query getQuery( String type ) {
  	if( type.equals( "A" ) ) {
      return new QueryA( getQueryHeader(),
                         getQueryToken(),
                         getQueryBody() );
    }
    else {
      return new QueryB( getQueryHeader(),
                         getQueryToken(),
                         getQueryBody() );
    }
  }
}