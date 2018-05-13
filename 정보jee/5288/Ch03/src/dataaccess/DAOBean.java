package dataaccess;

import javax.ejb.SessionBean;
import java.rmi.RemoteException;
import valueobject.ValueObject;

public interface DAOBean extends SessionBean{
 public ValueObject findByPrimaryKey(String pPrimaryKey)  throws 
                                                     DataAccessException,
                                                     NoDataFoundException,
						     ServiceLocatorException;
 public void insert(ValueObject pValueObject) throws DataAccessException,
                                                     ServiceLocatorException;
 public void update(ValueObject pValueObject) throws DataAccessException,
                                                     OptimisticLockException,
						     ServiceLocatorException;
 public void delete(ValueObject pValueObject) throws DataAccessException,
                                                     ServiceLocatorException;
 public ValueObject createValueObject()       throws DataAccessException,
                                                     ServiceLocatorException;
}
