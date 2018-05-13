package session.counter;

import java.io.Serializable;
import javax.ejb.CreateException;
import java.rmi.RemoteException;
import javax.ejb.EJBHome;

public interface CounterHome extends EJBHome {
  Counter create() throws CreateException, RemoteException;
}


