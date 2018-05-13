package bmp2;

import javax.ejb.*;
import java.util.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class CustomerBean implements EntityBean {

  private EntityContext ctx;
  private CustomerManagement.Customer cust;
  
  public CustomerBean() {
  }

  private CustomerData cd;
  
  // Component interface
  public String getCustomerPrimaryKey() {
    return cd.getCustomerId();	
  }
  public String getCustomerName() {
    return cd.getName();
  }
  public void setCustomerName(String name) {
    cd.setName(name);
  }
  public String getCustomerAddress() {
    return cd.getAddress();
  }
  public void setCustomerAddress(String address) {
    cd.setAddress(address);
  }
  public int getCustomerNoOfPhoneAccounts() {
    return cd.getNoOfPhoneAccounts();
  }
  public void setCustomerNoOfPhoneAccounts(int noOfPhAcc) {
    cd.setNoOfPhoneAccounts(noOfPhAcc);
  }
  
  public CustomerData getCustomerData() {
    return cd;
  }
  public void setCustomerData(CustomerData cd) {
    this.cd = cd;
  }
    

  public void setEntityContext(EntityContext ctx) {
    this.ctx = ctx;
    try {
       // Connect with the ORB
       String args[] = new String[1];
       args[0] = "";
       ORB orb = ORB.init(args, null);
    
       // Connect with the component wrapper if stateless
       org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
       NamingContext ncRef = NamingContextHelper.narrow(objRef);

       NameComponent nc = new NameComponent("CustomerWrapper", "");
       NameComponent path[] = {nc};
       cust = CustomerManagement.CustomerHelper.narrow(ncRef.resolve(path));
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void unsetEntityContext() {
    // Disconnect with the component wrapper if stateless
    cust = null;

    this.ctx = null;
  }

  public void ejbActivate() {
    // Connect with the component wrapper if stateful
  }

  public void ejbPassivate() {
    // Disconnect with the component wrapper if stateful
  }

  public void ejbLoad() {
    cd = new CustomerData();

    try {
       // Acquire the data from component wrapper
       StringHolder firstName = new StringHolder();
       StringHolder lastName = new StringHolder();
       StringHolder address = new StringHolder();
       ShortHolder numberOfPhoneAccounts = new ShortHolder();
       CustomerManagement.CustomerPackage.statusTypeHolder status = new CustomerManagement.CustomerPackage.statusTypeHolder();
    
       String cId = (String) ctx.getPrimaryKey();
       int ciId = (new Integer(cId)).intValue();
    
       cust.getCustomer (ciId, firstName, lastName, address, numberOfPhoneAccounts, status);

       cd.setCustomerId(cId);
       cd.setName(lastName.value);
       cd.setAddress(address.value);
       cd.setNoOfPhoneAccounts(numberOfPhoneAccounts.value);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void ejbStore() {
    // Store the data in the component wrapper  
    try {
       int r = cust.setCustomer(cd.getName(), "",
                             cd.getAddress(), 
                             (short)cd.getNoOfPhoneAccounts(), 
                             CustomerManagement.CustomerPackage.statusType.gold);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void ejbRemove() throws RemoveException {
  }

  // home methods
  public String ejbCreate(String customerId, String name, String address, int noOfPhAcc) throws CreateException {
    cd = new CustomerData();

    try {
       // Store to the bean
       cd.setCustomerId(customerId);
       cd.setName(name);
       cd.setAddress(address);
       cd.setNoOfPhoneAccounts(noOfPhAcc);

       // Create the corresponding record in the existing system though wrapper
       int r = cust.setCustomer(cd.getName(), "",
                             cd.getAddress(), 
                             (short)cd.getNoOfPhoneAccounts(), 
                             CustomerManagement.CustomerPackage.statusType.gold);
       // Return the primary key    
    } catch (Exception e) {
      System.out.println(e);
      throw new CreateException();
    }
    return customerId;
  }

  public void ejbPostCreate(String customerId, String name, String address, int noOfPhAcc) {
  }

  public String ejbCreate(CustomerData cd) throws CreateException {
    try { 
       this.cd = new CustomerData();
       this.cd = cd;

       // Create the corresponding record in the existing system though wrapper
       int r = cust.setCustomer(cd.getName(), "",
                             cd.getAddress(), 
                             (short)cd.getNoOfPhoneAccounts(), 
                             CustomerManagement.CustomerPackage.statusType.gold);

       return cd.getCustomerId();
    } catch (Exception e) {
      System.out.println(e);
      throw new CreateException();
    }
  }

  public void ejbPostCreate(CustomerData cd) {
  }

  public String ejbFindByPrimaryKey(String pk) throws ObjectNotFoundException {
    cd = new CustomerData();

    try {
       StringHolder firstName = new StringHolder();
       StringHolder lastName = new StringHolder();
       StringHolder address = new StringHolder();
       ShortHolder numberOfPhoneAccounts = new ShortHolder();
       CustomerManagement.CustomerPackage.statusTypeHolder status = new CustomerManagement.CustomerPackage.statusTypeHolder();
    
       int ciId = (new Integer(pk)).intValue();
    
       cust.getCustomer (ciId, firstName, lastName, address, numberOfPhoneAccounts, status);

       cd.setCustomerId(pk);
       cd.setName(lastName.value);
       cd.setAddress(address.value);
       cd.setNoOfPhoneAccounts(numberOfPhoneAccounts.value);

       return pk;
    } catch (Exception e) {
      System.out.println(e);
      throw new ObjectNotFoundException();
    }
  }

  public Collection ejbFindByName(String name) {
    Vector v = new Vector();

    try {
       // Delegate the search to the wrapper
       String[] ms = new String[100];
//    ms = cust.findByName(name);
    
//    for (int i = 0; i<ms.sizeOf(); i++)
//      v.addElement(ms[i]);

    } catch (Exception e) {
      System.out.println(e);
    }
    return v;
  }
}
