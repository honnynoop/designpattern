import java.util.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

public class Client {

  public static void main(String[] args) {

    MobilePhoneEvaluation mpe = null;

    try {
      Context inc = new InitialContext();
    	mpe = (MobilePhoneEvaluation)PortableRemoteObject.narrow(inc.lookup("MobilePhoneEvaluation"),MobilePhoneEvaluation.class);
    } catch (Exception e) {
     	System.out.println(e);
    }

    try {

      double r = mpe.evaluateConsumption((new GregorianCalendar(2,4,1)).getTime(), (new GregorianCalendar(2,4,1)).getTime());
      System.out.println(r);

    } catch (Exception e) {
	    System.out.println(e);
    }
  }
}
