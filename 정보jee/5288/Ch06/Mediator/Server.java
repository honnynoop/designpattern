import java.rmi.RemoteException;
import javax.rmi.PortableRemoteObject;
import javax.naming.*;

public class Server {

  public static void main(String[] args) {

    try {
 
      Context iNamingContext = new InitialContext();

      MobilePhoneEvaluation mpe = new MobilePhoneEvaluationImpl();

      iNamingContext.rebind("MobilePhoneEvaluation", mpe);

      System.out.println("Server ready...");

    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace(System.out);
    }
  }
}

