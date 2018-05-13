package CustomerManagement;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
 
public class Client
{
    public static void main(String args[])
    {
	try{
            // Connect to the wrapper
	    ORB orb = ORB.init(args, null);
 
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContext ncRef = NamingContextHelper.narrow(objRef);

            NameComponent nc = new NameComponent("CustomerWrapper", "");
            NameComponent path[] = {nc};
            Customer c = CustomerHelper.narrow(ncRef.resolve(path));
 
            // test the functionality 
            StringHolder firstName = new StringHolder();
            StringHolder lastName = new StringHolder();
            StringHolder address = new StringHolder();
            ShortHolder numberOfPhoneAccounts = new ShortHolder();
            CustomerManagement.CustomerPackage.statusTypeHolder status = new CustomerManagement.CustomerPackage.statusTypeHolder();
            
            c.getCustomer ((int)1 , firstName, lastName, address, numberOfPhoneAccounts, status);
            System.out.println("First name: " + firstName.value);
            System.out.println("Last name:  " + lastName.value);
            System.out.println("Address:    " + address.value);
            System.out.println("No. of phone accounts: " + numberOfPhoneAccounts.value);
            
            int customerId = c.setCustomer("Jack", "B. Good", "Birmingham", (short)1, CustomerManagement.CustomerPackage.statusType.gold);
            System.out.println("Setting new customer... id: "+customerId);
 
	} catch (UserException e) {
	    System.out.println("User exception: " + e) ;

	} catch (SystemException e) {
	    System.out.println("System exception: " + e) ;

	} catch (Exception e) {
	    System.out.println("Exception: " + e) ;
	}
    }
}
