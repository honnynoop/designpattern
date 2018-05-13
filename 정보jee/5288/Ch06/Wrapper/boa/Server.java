package CustomerManagement;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class Server {

   public static void main(String[] args) {

     try {
	    ORB orb = ORB.init(args,null);
	
	    Customer c = new CustomerImpl();
	    
	    orb.connect(c);
	
	    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	    NamingContext ncRef = NamingContextHelper.narrow(objRef);
	
	    NameComponent nc;
	    NameComponent path[]={null};
	
	    nc = new NameComponent("CustomerWrapper", "");
	    path[0] = nc;
	    ncRef.rebind(path, c);
	
	    System.out.println("Server is ready...");
	
	    java.lang.Object sync = new java.lang.Object();
	    synchronized (sync) {
	         sync.wait();
	    }

     } catch (Exception e) {
       System.out.println(e);
     }
   }
}

