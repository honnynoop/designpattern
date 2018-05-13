package session1;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface CheckBalanceHome extends EJBHome {

  CheckBalance create() throws CreateException, RemoteException;

}
