package bmp;

import javax.ejb.*;
import java.util.*;

public class CustomerBean implements EntityBean {

  private EntityContext ctx;
  
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
  }

  public void unsetEntityContext() {
    this.ctx = null;
  }

  public void ejbActivate() {
  }

  public void ejbPassivate() {
  }

  public void ejbLoad() {
    cd = new CustomerData();
    cd.setCustomerId("1");
    cd.setName("Matjaz");
    cd.setAddress("Wrox");
    cd.setNoOfPhoneAccounts(5);
  }

  public void ejbStore() {
  }

  public void ejbRemove() throws RemoveException {
  }

  // home methods
  public String ejbCreate(String customerId, String name, String address, int noOfPhAcc) throws CreateException {
    cd = new CustomerData();
    cd.setCustomerId(customerId);
    cd.setName(name);
    cd.setAddress(address);
    cd.setNoOfPhoneAccounts(noOfPhAcc);
    return customerId;
  }

  public void ejbPostCreate(String customerId, String name, String address, int noOfPhAcc) {
  }

  public String ejbCreate(CustomerData cd) throws CreateException {
    this.cd = new CustomerData();
    this.cd = cd;
    return cd.getCustomerId();
  }

  public void ejbPostCreate(CustomerData cd) {
  }

  public String ejbFindByPrimaryKey(String pk) throws ObjectNotFoundException {
    cd = new CustomerData();
    cd.setCustomerId("1");
    cd.setName("Matjaz");
    cd.setAddress("Wrox");
    cd.setNoOfPhoneAccounts(5);

    return "1";
  }

  public Collection ejbFindByName(String name) {
      Vector v = new Vector();
      v.addElement("1");
      return v;
  }

}
