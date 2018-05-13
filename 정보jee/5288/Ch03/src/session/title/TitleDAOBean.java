package session.title;

import dataaccess.*;
import valueobject.*;
import session.counter.Counter;
import session.counter.CounterHome;
import session.editor.EditorDAO;
import session.editor.EditorDAOHome;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import java.sql.*;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;


public class TitleDAOBean implements DAOBean{
  private SessionContext ctx;
  
  /*
   * The findByPrimaryKey() method will retrieve a single title record from the 
   * subscription database.  It returns a TitleVO that contains all
   * of the title data.
   *
   */
  public ValueObject findByPrimaryKey(String pPrimaryKey) 
    throws DataAccessException, ServiceLocatorException, NoDataFoundException{
    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);
    ResultSet  rs   = null;

    /*Getting my SQL Code for retrieving all of the titles in my application.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sql = sqlCode.getSQLStatement("titledao.findbyprimarykey().select");

    TitleVO titleVO = new TitleVO();

    try{
      PreparedStatement preparedStatement = conn.prepareStatement(sql);

      preparedStatement.setLong(1, Long.parseLong(pPrimaryKey));
      rs = preparedStatement.executeQuery();

      /*
       * Populating a the value object with the data retrieved from the
       * resultset.
       *
       */
      if (rs.next()){
        titleVO.setTitleId(rs.getLong("title_id"));
        titleVO.setTitleDescr(rs.getString("title_descr"));
        titleVO.setTitleCost(rs.getFloat("title_cost"));
        titleVO.setRowVersion(rs.getLong("row_version"));

        EditorDAOHome editorDAOHome = (EditorDAOHome) serviceLocator.getEJBHome(ServiceLocator.EDITORDAO); 
        EditorDAO     editorDAO     = editorDAOHome.create();
        titleVO.setEditors(editorDAO.findEditorByTitle(String.valueOf(titleVO.getTitleId())));
      }
      else{
	throw new NoDataFoundException("Record id: " + pPrimaryKey + 
			              " is not found in the title table.");
      }
   
      /*Reset the flags so that we know the object is in pristine state.*/
      titleVO.resetFlags();
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.findByPrimaryKey()",e);
    }    
    catch(RemoteException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.findByPrimaryKey()",e);
    }
    catch(CreateException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.findByPrimaryKey()",e);
    }
    finally{
      try{
        if (rs!=null)   rs.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("I am unable to close a resultset, statement, or connection TitleDAO.findByPrimaryKey().");
      }

      return titleVO;
    }
  }

  /*
   * The update() method will update a single record in the title table
   * with the valueobject passed in as a parameter.
   *
   */
  public void update(ValueObject pValueObject) throws DataAccessException ,
                                                      OptimisticLockException,
						      ServiceLocatorException
						      {
    /*Getting my SQL Code for updating all of the titles in my application.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sql = sqlCode.getSQLStatement("titledao.update().update");

    TitleVO titleVO = (TitleVO) pValueObject;

    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);

    PreparedStatement preparedStatement = null;

    try{
      /*Retrieving a row version number via the Counter session bean*/
      CounterHome counterHome = 
        (CounterHome) serviceLocator.getEJBHome(ServiceLocator.COUNTER); 
      Counter     counter     = counterHome.create();
      long  nextRowVersion = counter.getNextVal("title_rv");

      /*Populating the prepared statement's parameters*/
      preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setString(1, titleVO.getTitleDescr());
      preparedStatement.setFloat( 2, titleVO.getTitleCost());
      preparedStatement.setLong(  3, nextRowVersion);
      preparedStatement.setLong(  4, titleVO.getTitleId());
      preparedStatement.setLong(  5, titleVO.getRowVersion());

      /*
       * Checking to see if we were successful in updating the record.
       * If the queryResult does not equal 1, then we no we have run
       * into an optimistic lock situation.
       *
       */
      int queryResults = preparedStatement.executeUpdate();
      if (queryResults != 1){
	throw new OptimisticLockException("Stale data for title record: " 
			                  + titleVO.getTitleId()); 
      }

      /*
       * Cycling through all of the editors and seeing if an editor
       * needs to be updated.
       */
       Collection col = titleVO.getEditors().values();
       Iterator iterator = col.iterator();

       EditorDAOHome editorDAOHome = 
         (EditorDAOHome) serviceLocator.getEJBHome(ServiceLocator.EDITORDAO); 
       EditorDAO     editorDAO     = editorDAOHome.create();

       while (iterator.hasNext()){
	 EditorVO editorVO = (EditorVO) iterator.next();

	 /*
	  * If the updateFlag has been set to true then update the editor vo
	  * record by invoking update on the editorDAO.
	  */
	 if (editorVO.getUpdateFlag()){
           editorDAO.update(editorVO);
         }
       }
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.update()",e);
    } 
    catch(CreateException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.update()",e);
    }
    catch(RemoteException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.update()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("I am unable to close a resultset, statement, or connection TitleDAO.update().");
      }
    }
						      
  }

  /*
   * The delete() record will delete a single record from the title 
   * table in the subscription database.  It does this based on the 
   * id of the value object passed into the table.
   *
   */
  public void delete(ValueObject pValueObject) 
     throws DataAccessException, ServiceLocatorException {
    /*Building my SQL Code for deleting a record from the title table.*/
    SQLCode sqlCode = SQLCode.getInstance();
    String sqlTitle = sqlCode.getSQLStatement("titledao.delete().deletetitle");
    String sqlTitleEditor = sqlCode.getSQLStatement("titledao.delete().deletetitleeditor");

    TitleVO titleVO = (TitleVO) pValueObject;

    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);

    PreparedStatement preparedStatement = null;

    try{
      /*Deleting the row from the title table*/
      preparedStatement = conn.prepareStatement(sqlTitle);
      preparedStatement.setLong(1,titleVO.getTitleId());
      preparedStatement.executeQuery();

      /*Deleting all rows from the title_editor table that match the title_id*/
      preparedStatement = conn.prepareStatement(sqlTitleEditor);
      preparedStatement.setLong(1,titleVO.getTitleId());
      preparedStatement.executeQuery();
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.delete()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null)       conn.close();
      }
      catch(SQLException e){}
	System.out.println("I am unable to close a resultset, statement, or connection. TitleDAO.delete()");
    }
  }

  /*
   * The insert() method will insert a single record into the title table.
   * The data it inserts will be pulled from the value object passed into
   * the method.
   *
   */
  public void insert(ValueObject pValueObject) 
    throws DataAccessException,
	   ServiceLocatorException{
    /*Getting all of my SQL code for inserting a title*/
    SQLCode sqlCode         = SQLCode.getInstance();
    String  sqlTitle        = sqlCode.getSQLStatement("titledao.insert().insert");
    String  sqlTitleEditor  =  sqlCode.getSQLStatement("titledao.insert().inserttitleeditor");

    /*Building my SQL Code for inserting a record into the title table.*/
    TitleVO titleVO = (TitleVO) pValueObject;
    ServiceLocator serviceLocator = ServiceLocator.getInstance();
    Connection conn = serviceLocator.getDBConn(ServiceLocator.SUBSCRIPTIONDB);

    PreparedStatement preparedStatement = null;

    try{
      /*Populating the prepared statement with data from the value object*/
      preparedStatement = conn.prepareStatement(sqlTitle);
      preparedStatement.setLong(1, titleVO.getTitleId());
      preparedStatement.setString(2, titleVO.getTitleDescr());
      preparedStatement.setFloat(3, titleVO.getTitleCost());
      preparedStatement.setLong(4,1);

      preparedStatement.execute();

      HashMap    editorHash     = titleVO.getEditors();
      Collection editorCol      = editorHash.values();
      Iterator   editorIterator = editorCol.iterator();

      while (editorIterator.hasNext()){
 	EditorVO editorVO = (EditorVO) editorIterator.next();

	preparedStatement = conn.prepareStatement(sqlTitleEditor);
	preparedStatement.setLong(1,titleVO.getTitleId());
	preparedStatement.setLong(2,editorVO.getEditorId());
        preparedStatement.execute();
      }
    }
    catch(SQLException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.insert()",e);
    }
    finally{
      try{
	if (preparedStatement!=null) preparedStatement.close();
	if (conn!=null) conn.close();
      }
      catch(SQLException e){
	System.out.println("I am unable to close a resultset, statement, or connection in TitleDAO.Insert().");
      }
    }
  }

  /*
   * The createValueObject() method will create an empty value object pre-
   * populated with any mandatory data.  An example of this mandatory data
   * might be a primary key that for a particular record..
   * 
   */
  public ValueObject createValueObject() 
    throws DataAccessException, ServiceLocatorException{ 
    TitleVO titleVO = new TitleVO();

    try{
      /*
       *
       * Creating a counter instance.  We will use this counter instance to
       * retrieve a primary key for the new record being used.
       *
       */
      ServiceLocator serviceLocator = ServiceLocator.getInstance();

      CounterHome counterHome = (CounterHome) serviceLocator.getEJBHome(ServiceLocator.COUNTER); 
      Counter     counter     = counterHome.create();
      titleVO.setTitleId(counter.getNextVal("title_pk"));

      /*Populating the rest of the value object with data*/
      titleVO.setTitleDescr("");
      titleVO.setTitleCost(0);
      titleVO.setRowVersion(1);
      titleVO.resetFlags();
    }
    catch(CreateException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.createValueObject()",e);
    }
    catch(RemoteException e){
      /*Aborting the transaction*/
      ctx.setRollbackOnly();
      throw new DataAccessException("Error in TitleDAO.createValueObject()",e);
    }
    finally{
      return titleVO;
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
