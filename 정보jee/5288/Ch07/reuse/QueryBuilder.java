package reuse;

/**
 * Interface for building queries uses Builder Pattern
 */

public interface QueryBuilder {
  
  /** Builds the header of the query
  */
  public void buildQueryHeader();
  
  /** Builds the query token
  */
  public void buildQueryToken();
  
  /** Builds the query body
  */
  public void buildQueryBody();

  /** Returns the built query
  */
  public Query getQuery( String type );
}