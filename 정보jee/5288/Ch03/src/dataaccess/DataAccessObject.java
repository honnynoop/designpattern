package dataaccess;

import valueobject.ValueObject;
import javax.naming.*;


public abstract class DataAccessObject{
  private Context ctx       = null;

  public abstract ValueObject create() throws DataAccessException;
  public abstract ValueObject find(String pPrimaryKey) throws DataAccessException;
  
  public abstract void        insert(ValueObject pValueObject) 
    throws DataAccessException;
  public abstract void        delete(ValueObject pValueObject) 
    throws DataAccessException;
  public abstract void        update(ValueObject pValueObjectt) 
    throws DataAccessException; 

  public DataAccessObject() throws DataAccessException{
    ctx = DataSourceFactory.getDefaultContext();    
  }

  public DataAccessObject(Context pCtx) throws DataAccessException{
    ctx = pCtx;
  }

  protected Context getDAOContext(){
    return ctx;
  }
}
