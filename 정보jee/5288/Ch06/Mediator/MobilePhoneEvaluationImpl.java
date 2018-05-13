import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;
import java.util.Date;

public class MobilePhoneEvaluationImpl extends PortableRemoteObject implements MobilePhoneEvaluation  {

  public MobilePhoneEvaluationImpl() throws RemoteException {
  }

  public double evaluateConsumption (Date fromDate, Date toDate) throws RemoteException {
                   	
     double cost = 0;
     // connect to the domestic mobile phone evaluation existing applciation
     // connect to the international mobile phone evaluation existing applciation
     
     cost = 55;
     
     return cost;
  }
}
