package bmp;

import java.rmi.*;
import javax.rmi.*;
import java.util.*;

import javax.ejb.*;
import javax.naming.*;

public class Client {

  public Client() {
  }

  public static void main(String[] args) {
    System.out.println("EAI BMP client");
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

      CustomerHome home = (CustomerHome)PortableRemoteObject.narrow((CustomerHome)ctx.lookup("eai2-CustomerHome"), CustomerHome.class);

      Customer c = home.create("1","Matjaz","Wrox",5);

      System.out.println("Customer name is: "+c.getCustomerName());

      c.remove();

    } catch(Exception e) {
       System.out.println(e);
    }
  }

}
