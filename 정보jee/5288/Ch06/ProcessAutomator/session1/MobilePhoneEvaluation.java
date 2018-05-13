package session1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface MobilePhoneEvaluation extends Remote {

  double evaluateConsumption (Date fromDate, Date toDate)
                   throws RemoteException;

}
