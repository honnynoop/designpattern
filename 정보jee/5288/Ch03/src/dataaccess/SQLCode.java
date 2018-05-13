package dataaccess;

import java.util.Properties;
import java.io.*;

/*
 *  The SQLCode class is a helper class that loads all of the SQL
 *  code used within the persistence framework into a private properties
 *  object stored within the SQLCode class.
 *
 */
public class SQLCode{
  private static SQLCode sqlCode = null;
  private static Properties sqlCache = new Properties();

  /*Calling the SQLCode's constructor*/
  static{
    sqlCode = new SQLCode();
  }

  /*Retrieves the Singleton for the SQLCode class*/
  public static SQLCode getInstance(){
    return sqlCode;
  }

  /*
   * The SQLCode constructor loads all of the SQL code used in the
   * persistence framework from the sql.properties class into the sqlCache
   * properties object
   *
   */
  private SQLCode(){
    try{
      String sqlFileName = "c:\\tempnilesh\\three\\wrox\\apps\\j2patterns\\config\\sql.properties";

      sqlCache.load(new FileInputStream(sqlFileName));
    }
    catch(IOException e){
      System.out.println(e.toString());
    }
  }

  /* 
   * The getSQLStatement() method will try to retrieve a SQL statement
   * from the SQLCache properties object based on the key passed into 
   * the method.  If it can not find an the SQL statement, a DataAccess
   * Exception will be raised.
   *
   */
  public String getSQLStatement(String pSQLKeyName) throws DataAccessException{

     /*Checking to see if the requested SQL statement is in the sqlCache*/
     if (sqlCache.containsKey(pSQLKeyName)){ 
       return (String) sqlCache.get(pSQLKeyName);
     }
     else{
       throw new DataAccessException("Unable to locate the SQL statement requested in SQLCode.getSQLCode() " + pSQLKeyName);
     }
  }
}
