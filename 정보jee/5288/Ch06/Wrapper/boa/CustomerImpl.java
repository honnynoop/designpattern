package CustomerManagement;

public class CustomerImpl extends _CustomerImplBase {
	
  public void getCustomer (int customerId, 
                  org.omg.CORBA.StringHolder firstName, 
                  org.omg.CORBA.StringHolder lastName, 
                  org.omg.CORBA.StringHolder address, 
                  org.omg.CORBA.ShortHolder numberOfPhoneAccounts, 
                  CustomerManagement.CustomerPackage.statusTypeHolder status) 
           throws CustomerManagement.CustomerPackage.customerNotFound
  {
     // connect to the legacy system, locate the customer and retrieve the data
     boolean successful = true;
     
     if (successful) {
       // fill in the data
       firstName.value = "Jack";
       lastName.value = "B. Good";
       address.value = "Warwick Road";
       numberOfPhoneAccounts.value = 2;
       status.value = CustomerManagement.CustomerPackage.statusType.silver;
     } else {
       throw new CustomerManagement.CustomerPackage.customerNotFound("Bad customerID",(short)1);     	
     }	
  }
  
  public int setCustomer (String firstName, 
                  String lastName, 
                  String address, 
                  short numberOfPhoneAccounts, 
                  CustomerManagement.CustomerPackage.statusType status)
           throws CustomerManagement.CustomerPackage.customerNotUpdated
  {
     // connect to the legacy system	
     // generate the customerID
     // add the customer data
     int customerId = 1;
     boolean successful = true;
     
     if (successful) {
       return customerId;
     } else {
       throw new CustomerManagement.CustomerPackage.customerNotUpdated("DB error",(short)1);
     }	
  }

  public void newAccount (String phoneNumber, 
                  String startDate, 
                  CustomerManagement.CustomerPackage.typeOfAccounts typeOfAccount) 
           throws CustomerManagement.CustomerPackage.accountNotAdded
  {
  	
  }

  public void deleteAccount (String phoneNumber) 
           throws CustomerManagement.CustomerPackage.accountNotDeleted
  {
  	
  }
   
   
}
