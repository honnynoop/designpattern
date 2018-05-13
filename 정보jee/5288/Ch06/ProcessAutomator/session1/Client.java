package session1;

import java.rmi.*;
import javax.rmi.*;
import java.util.*;

import javax.ejb.*;
import javax.naming.*;

public class Client {

  public Client() {
  }

  public static void main(String[] args) {
    System.out.println("EAI process automator client");
    System.out.println();

    try {
      Client client = new Client();
      client.example();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void example() {
    
    try {

//      Properties h = new Properties();
//      h.put(Context.INITIAL_CONTEXT_FACTORY,
//            "weblogic.jndi.WLInitialContextFactory");
//      h.put(Context.PROVIDER_URL, "t3://localhost:7001");
      Context ctx = new InitialContext();

      CheckBalanceHome home = (CheckBalanceHome)PortableRemoteObject.narrow((CheckBalanceHome)ctx.lookup("eaiSS-CheckBalanceHome"), CheckBalanceHome.class);

      CheckBalance cb = home.create();

      System.out.println("The balance of account no. 1 is: "+cb.calculateBalance(1));

      cb.remove();

    } catch(Exception e) {
       System.out.println(e);
    }
  }

}
