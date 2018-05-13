package CustomerManagement;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server {

   public static void main(String[] args) {

     try {
	    ORB orb = ORB.init(args,null);

	    CustomerImpl c = new CustomerImpl();

            POA rPOA = (POA)orb.resolve_initial_references("RootPOA");

            rPOA.the_POAManager().activate( );

            rPOA.activate_object( c );

	    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	    NamingContext ncRef = NamingContextHelper.narrow(objRef);
	
	    NameComponent nc;
	    NameComponent path[]={null};
	
	    nc = new NameComponent("CustomerWrapper", "");
	    path[0] = nc;
	    ncRef.rebind(path, rPOA.servant_to_reference( c ));
	
	    System.out.println("Server is ready...");
	
            orb.run();

     } catch (Exception e) {
       System.out.println(e);
     }
   }
}

