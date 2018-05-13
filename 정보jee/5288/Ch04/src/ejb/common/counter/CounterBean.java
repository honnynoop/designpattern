package common.counter; 

import javax.ejb.*;
import java.sql.*;
import common.*;

public class CounterBean implements SessionBean{
  SessionContext ctx = null;
  
  /*
   *  The getNextVal method will retrieve the next sequence number
   *  of the sequence the user requests.  The sequence values are stored in
   *  a database table called counter.
   *
   */
  public Long getNextVal(String pCounterName) 
    throws ServiceLocatorException{

    long              valueReturned   = 0;
	long			  newValue		  = 0;
    Connection        conn            = null;
	Statement		  statement		  = null;
    ResultSet         rsCounter       = null;

    try{
      /*Getting a connection to the subscription database*/
	  ServiceLocator serviceLocator = ServiceLocator.getInstance();
      conn = serviceLocator.getDBConn(ServiceLocator.COUNTERDB);

      /*Building a SQL statement that will retrieve current value of the counter*/
      String sql = "SELECT seq_num FROM counter WHERE counter_name = '" + pCounterName + "'";

      /*Executing the SQL statement*/
	  statement = conn.createStatement();
	  rsCounter = statement.executeQuery(sql);
      
      /*Pulling the new sequence id*/
      if (rsCounter.next()){
        valueReturned = rsCounter.getLong("seq_num");  
      }
	  newValue = valueReturned + 1;

	  /*
       * Building the SQL statement that will retrieve the newly incremented
       * record from the database.       
       */
	   sql = "UPDATE counter SET seq_num = " + newValue + " WHERE counter_name = '" + pCounterName + "'";

      /*Executing the sql statement*/
	  statement.execute(sql);	

    }
    catch(SQLException e){
      /*Marking the session bean for rollback*/
      ctx.setRollbackOnly();
      throw new EJBException("Error has occurred in CounterBean.getNextVal()",e);
    }
    finally{
      try{
	/*Close my database connections*/
	if (statement != null) statement.close();
	if (rsCounter!=null) rsCounter.close();
        if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection" +
			   " or statement in Counter.getNextVal"); 
      }

    }

    return new Long(newValue);
  };

  public void ejbCreate(){}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(SessionContext pCtx){
    ctx = pCtx;
  }
}


