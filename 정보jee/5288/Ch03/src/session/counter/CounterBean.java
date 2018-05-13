package session.counter; 

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.sql.*;
import dataaccess.*;

public class CounterBean implements SessionBean{
  SessionContext ctx = null;
  
  /*
   *  The getNextVal method will retrieve the next sequence number
   *  of the sequence the user requests.  The sequence values are stored in
   *  a database table called counter.
   *
   */
  public long getNextVal(String pCounterName) 
    throws DataAccessException, ServiceLocatorException{

    long              valueReturned   = 0;
    Connection        conn            = null;
    PreparedStatement updateStatement = null;
    ResultSet         rsCounter       = null;

    try{
      /*Getting a connection to the subscription database*/
      ServiceLocator serviceLocator = ServiceLocator.getInstance();
      conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);

      /*Building a SQL statement that will update the counter*/
      SQLCode sqlCode = SQLCode.getInstance();
      String sql = sqlCode.getSQLStatement("counter.nextval.update");

      /*Executing the SQL statement*/
      updateStatement = conn.prepareStatement(sql.toString());
      updateStatement.setString(1,pCounterName);
      updateStatement.execute();
      
      /*
       * Building the SQL statement that will retrieve the newly incremented
       * record from the database.
       *
       */
      sql = sqlCode.getSQLStatement("counter.nextval.select");

      /*Executing the sql statement*/
      updateStatement = conn.prepareStatement(sql.toString());
      updateStatement.setString(1,pCounterName);
      rsCounter = updateStatement.executeQuery();

      /*Pulling the new sequence id*/
      if (rsCounter.next()){
        valueReturned = rsCounter.getLong("seq_num");  
      }
    }
    catch(SQLException e){
      /*Marking the session bean for rollback*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error has occurred in CounterBean.getNextVal()",e);
    }
    finally{
      try{
	/*Close my database connections*/
	if (updateStatement != null) updateStatement.close();
	if (rsCounter!=null) rsCounter.close();
        if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection" +
			   " or statement in Counter.getNextVal"); 
      }

    }

    return valueReturned;
  };

  public void ejbCreate(){}
  public void ejbRemove() {}
  public void ejbActivate() {}
  public void ejbPassivate() {}
  public void setSessionContext(SessionContext pCtx){
    ctx = pCtx;
  }
}


