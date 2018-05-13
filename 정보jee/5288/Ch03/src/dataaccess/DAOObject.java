package dataaccess;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import valueobject.ValueObject;

public interface DAOObject extends EJBObject{
  public ValueObject findByPrimaryKey(String pPrimaryKey) throws 
                                                     DataAccessException,
                                                     NoDataFoundException,
						     ServiceLocatorException,
						     RemoteException;
  public void insert(ValueObject pValueObject) throws DataAccessException,
                                                      ServiceLocatorException,
						      RemoteException;
  public void update(ValueObject pValueObject) throws DataAccessException    ,
                                                      OptimisticLockException,
						      ServiceLocatorException,
						      RemoteException;
  public void delete(ValueObject pValueObject) throws DataAccessException,
                                                      ServiceLocatorException,
						      RemoteException;
  public ValueObject createValueObject() throws DataAccessException,
                                                ServiceLocatorException,
						RemoteException;
}
