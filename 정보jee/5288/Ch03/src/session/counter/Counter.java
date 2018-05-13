package session.counter;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import dataaccess.DataAccessException;
import dataaccess.ServiceLocatorException;

public interface Counter extends EJBObject{
  public long getNextVal(String pCounterName) 
    throws DataAccessException, ServiceLocatorException, RemoteException;
}


