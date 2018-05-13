package common.counter;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import common.ServiceLocatorException;

public interface Counter extends EJBObject{
  public Long getNextVal(String pCounterName) 
    throws ServiceLocatorException, RemoteException;
}


