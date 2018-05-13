package session.editor;

import dataaccess.*;
import valueobject.ValueObject;
import valueobject.EditorVO;
import session.counter.Counter;
import session.counter.CounterHome;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import java.sql.*;
import java.rmi.RemoteException;
import java.util.HashMap;


public class EditorDAOBean implements DAOBean{
  private SessionContext ctx;
  
  public ValueObject findByPrimaryKey(String pPrimaryKey) 
    throws DataAccessException, NoDataFoundException, ServiceLocatorException{
    ServiceLocator serviceLocator = ServiceLocator.getInstance();

    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);
    ResultSet  rs   = null;

    /*Getting my SQL Code for retrieving all of the editors.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sql = sqlCode.getSQLStatement("editordao.findbyprimarykey().select");

    EditorVO editorVO = new EditorVO();

    try{
      PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());

      preparedStatement.setLong(1, new Long(pPrimaryKey).longValue());
      rs = preparedStatement.executeQuery();

      /*
       * Populating a the value object with the data retrieved from the
       * resultset.
       *
       */
      if (rs.next()){
        editorVO.setEditorId(rs.getLong("editor_id"));
        editorVO.setFirstName(rs.getString("first_name"));
        editorVO.setMiddleName(rs.getString("middle_name"));
        editorVO.setLastName(rs.getString("last_name"));
        editorVO.setRowVersion(rs.getLong("row_version"));
      }
      else{
	throw new NoDataFoundException("Record id: " + pPrimaryKey + 
			              " is not found in the editor table.");
      }
   
      /*Reset the flags so that we know the object is in pristine state.*/
      editorVO.resetFlags();
    }
    catch(SQLException e){

      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditoDAO.findByPrimaryKey()",e);
    }    
    finally{
      try{
        if (rs!=null)   rs.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection " +
			   "or statement in EditorDAO.findByPrimaryKey");
      }

      return editorVO;
    }
  }

  public HashMap findEditorByTitle(String pTitleId) 
    throws DataAccessException, ServiceLocatorException{
    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);
    ResultSet  rs   = null;

    /*Getting my SQL Code for retrieving all of the editors by title.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sql = sqlCode.getSQLStatement("editordao.findeditorbytitle().select");
    HashMap editorList = new HashMap();

    try{
      PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());

      preparedStatement.setLong(1, new Long(pTitleId).longValue());
      rs = preparedStatement.executeQuery();

      /*
       * Populating a the value object with the data retrieved from the
       * resultset.
       *
       */

      while (rs.next()){
	EditorVO editorVO = new EditorVO();

        editorVO.setEditorId(rs.getLong("editor_id"));
        editorVO.setFirstName(rs.getString("first_name"));
        editorVO.setMiddleName(rs.getString("middle_name"));
        editorVO.setLastName(rs.getString("last_name"));
        editorVO.setRowVersion(rs.getLong("row_version"));

        /*Reset the flags so that we know the object is in pristine state.*/
        editorVO.resetFlags();

	editorList.put(new Long(editorVO.getEditorId()), editorVO);
      }
   
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.findByEditorTitle()",e);
    }    
    finally{
      try{
        if (rs!=null)   rs.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection " +
			   "or statement in EditorDAO.findByEditorTitle()");
      }

      return editorList;
    }
  }

  public void update(ValueObject pValueObject) throws DataAccessException ,
                                                      OptimisticLockException{
    /*Getting my SQL Code for retrieving all of the editors by title.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sql = sqlCode.getSQLStatement("editordao.update().update");

    EditorVO editorVO = (EditorVO) pValueObject;
    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);
    PreparedStatement preparedStatement = null;

    try{
      /*Retrieving a row version number via the Counter session bean*/
      CounterHome counterHome = 
        (CounterHome) serviceLocator.getEJBHome(ServiceLocator.COUNTER); 
      Counter     counter     = counterHome.create();
      long  nextRowVersion = counter.getNextVal("editor_rv");

      /*Populating the prepared statement's parameters*/
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(1, editorVO.getFirstName());
      preparedStatement.setString( 2, editorVO.getMiddleName());
      preparedStatement.setString( 3, editorVO.getLastName());
      preparedStatement.setLong(  4, nextRowVersion);
      preparedStatement.setLong(  5, editorVO.getEditorId());
      preparedStatement.setLong(  6, editorVO.getRowVersion());

      /*
       * Checking to see if we were successful in updating the record.
       * If the queryResult does not equal 1, then we no we have run
       * into an optimistic lock situation.
       *
       */
      int queryResults = preparedStatement.executeUpdate();
      if (queryResults != 1){
	throw new OptimisticLockException("Stale data for editor record: " 
			                  + editorVO.getEditorId()); 
      }
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.update()",e);
    }
    catch(CreateException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.update()",e);
    }
    catch(RemoteException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.update()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection " +
			   "or statement in EditorDAO.update()");
      }
    }
						      
  }

  public void delete(ValueObject pValueObject) 
     throws DataAccessException{
    /*Getting my SQL Code for retrieving all of the editors by title.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sqlEditor = sqlCode.getSQLStatement("editordao.delete().editor");
    String sqlTitleEditor = sqlCode.getSQLStatement("editordao.delete().titleeditor");

    EditorVO editorVO = (EditorVO) pValueObject;

    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);
    PreparedStatement preparedStatement = null;

    try{
      /*Deleting the record from the editor table*/
      preparedStatement = conn.prepareStatement(sqlEditor.toString());
      preparedStatement.setLong(1,editorVO.getEditorId());

      preparedStatement.executeQuery();

      /*Deleting the record from the title_editor table*/
      preparedStatement = conn.prepareStatement(sqlTitleEditor.toString());
      preparedStatement.setLong(1,editorVO.getEditorId());
      preparedStatement.executeQuery();
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.delete()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null)       conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection " +
			   "or statement in EditorDAO.delete()");
      }
    }
  }

  public void insert(ValueObject pValueObject) 
    throws DataAccessException{

    /*Getting my SQL Code for retrieving all of the editors by title.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String  sql     = sqlCode.getSQLStatement("editordao.insert().insert");

    EditorVO editorVO = (EditorVO) pValueObject;
    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);

    PreparedStatement preparedStatement = null;

    try{
      /*Populating the prepared statement with data from the value object*/
      preparedStatement = conn.prepareStatement(sql.toString());
    
      preparedStatement.setLong(1, editorVO.getEditorId());
      preparedStatement.setString(2, editorVO.getFirstName());
      preparedStatement.setString(3, editorVO.getMiddleName());
      preparedStatement.setString(4, editorVO.getLastName());
      preparedStatement.setLong(5,1);

      preparedStatement.execute();
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.delete()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("Unable to close resultset, database connection " +
			   "or statement in EditorDAO.insert()");
      }
    }
  }

  public ValueObject createValueObject() 
    throws DataAccessException{ 
    EditorVO editorVO = new EditorVO();

    try{
      /*
       *
       * Creating a counter instace.  We will use this counter instance to
       * retrieve a primary key for the new record being used.
       *
       */
      ServiceLocator serviceLocator = ServiceLocator.getInstance();
      CounterHome counterHome = 
        (CounterHome) serviceLocator.getEJBHome(ServiceLocator.COUNTER); 
      Counter     counter     = counterHome.create();
      editorVO.setEditorId(counter.getNextVal("editor_pk"));

      /*Populating the rest of the value object with data*/
      editorVO.setFirstName("");
      editorVO.setMiddleName("");
      editorVO.setLastName("");
      editorVO.setRowVersion(1);
      editorVO.resetFlags();
    }
    catch(CreateException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.createValueObject()",e);
    }
    catch(RemoteException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in EditorDAO.createValueObject()",e);
    }
    finally{
      return editorVO;
    }
  }

  public void ejbCreate() {}
  public void ejbRemove() {}
  public void ejbActivate(){}
  public void ejbPassivate(){}
  public void setSessionContext(SessionContext sessionCtx){
    this.ctx = sessionCtx; 
  }
}
