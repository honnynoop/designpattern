package session1;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface CheckBalance extends EJBObject {

  public double calculateBalance(int accountNo) throws RemoteException;

}
