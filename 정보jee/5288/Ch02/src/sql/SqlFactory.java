package sql;

import java.io.PrintStream;
import java.util.*;

/** A factory for sql statements
*/

public class SqlFactory {

    private SqlFactory() {
    }

  /** Builds a select query.
  */
    public static String getSelectQuery( String s, String s1, Properties properties ) {
        StringBuffer stringbuffer = new StringBuffer( "SELECT " + s1 + " " );
        stringbuffer.append( getFromSubQuery( s ) );
        stringbuffer.append( getWhereSubQuery( properties ) );
        System.out.println( "query is " + stringbuffer.toString() );
        return stringbuffer.toString();
    }

  /** Builds a where sub-query.
  */
    public static String getWhereSubQuery( Properties properties ) {
        StringBuffer stringbuffer = new StringBuffer( "" );
        Enumeration enumeration = getColumnNames( properties );
        if( enumeration.hasMoreElements() )
            stringbuffer.append( "WHERE " );
        stringbuffer.append( getWhereSubQueryParameters( enumeration ) );
        return stringbuffer.toString();
    }

    private static String getWhereSubQueryParameters( Enumeration enumeration ) {
        StringBuffer stringbuffer = new StringBuffer( "" );
        while( enumeration.hasMoreElements() ) {
            String s = ( String )enumeration.nextElement();
            stringbuffer.append( s + "=?" );
            if( enumeration.hasMoreElements() )
                stringbuffer.append( " and " );
        }
        return stringbuffer.toString();
    }

  /** Builds a from sub-query.
  */
    public static String getFromSubQuery( String s ) {
        StringBuffer stringbuffer = new StringBuffer( "FROM " + s + " " );
        return stringbuffer.toString();
    }

  /** returns column names from a set of properies.
  */
    public static Enumeration getColumnNames( Properties properties ) {
        return properties.propertyNames();
    }

  /** returns values from a set of properties.
  */
    public static Enumeration getCriteriaValues( Properties properties ) {
        Vector vector = new Vector();
        for( Enumeration enumeration = properties.propertyNames(); 
             enumeration.hasMoreElements(); 
          vector.addElement( properties.get( enumeration.nextElement() ) ) );
        return vector.elements();
    }
}