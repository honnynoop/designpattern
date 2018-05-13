package session1;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import java.util.*;

public class CheckBalanceBean implements SessionBean {

  private SessionContext ctx;
  private MobilePhoneEvaluation mpe;

  public void ejbActivate() {
  }

  public void ejbRemove() {
  }

  public void ejbPassivate() {
  }

  public void setSessionContext(SessionContext ctx) {
    this.ctx = ctx;
  }

  // home
  public void ejbCreate() throws CreateException {
    System.out.println("create: Connecting to the mediator");
    try {
      Properties h = new Properties();
      h.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.cosnaming.CNCtxFactory");
//            "weblogic.jndi.WLInitialContextFactory");
      h.put(Context.PROVIDER_URL, "iiop://localhost:900");
      Context inc = new InitialContext(h);
      Object a = inc.lookup("MobilePhoneEvaluation");
    	mpe = (MobilePhoneEvaluation)PortableRemoteObject.narrow(inc.lookup("MobilePhoneEvaluation"),MobilePhoneEvaluation.class);
    } catch (Exception e) {
      System.out.println(e);
      throw new CreateException();
    }
  }

  // business method
  public double calculateBalance(int accountNo) {
    double result = 0;
    System.out.println("calculateBalance: invoking remote method on the mediator");
    try {

      result = mpe.evaluateConsumption((new GregorianCalendar(2,4,1)).getTime(), (new GregorianCalendar(2,4,1)).getTime());
      
     } catch (Exception e) {
     	System.out.println(e);
    }

    return result;
  }
}
