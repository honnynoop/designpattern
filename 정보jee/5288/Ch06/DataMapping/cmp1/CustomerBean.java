package cmp1;

import javax.ejb.*;

abstract public class CustomerBean implements EntityBean {

  private EntityContext ctx;
  
  public CustomerBean() {
  }

  // Container managed fields
  abstract public String getCustomerId();
  abstract public void setCustomerId(String customerID);
  abstract public String getName();
  abstract public void setName(String name);
  abstract public String getAddress();
  abstract public void setAddress(String address);
  abstract public int getNoOfPhoneAccounts();
  abstract public void setNoOfPhoneAccounts(int noOfPhAcc);

  // Component interface
  public String getCustomerPrimaryKey() {
    return getCustomerId();	
  }
  public String getCustomerName() {
    return getName();
  }
  public void setCustomerName(String name) {
    setName(name);
  }
  public String getCustomerAddress() {
    return getAddress();
  }
  public void setCustomerAddress(String address) {
    setAddress(address);
  }
  public int getCustomerNoOfPhoneAccounts() {
    return getNoOfPhoneAccounts();
  }
  public void setCustomerNoOfPhoneAccounts(int noOfPhAcc) {
    setNoOfPhoneAccounts(noOfPhAcc);
  }
  
  public CustomerData getCustomerData() {
    CustomerData cd = new CustomerData();
    cd.setCustomerId(getCustomerId());
    cd.setName(getName());
    cd.setAddress(getAddress());
    cd.setNoOfPhoneAccounts(getNoOfPhoneAccounts());
    return cd;
  }
  public void setCustomerData(CustomerData cd) {
    setCustomerId(cd.getCustomerId());
    setName(cd.getName());
    setAddress(cd.getAddress());
    setNoOfPhoneAccounts(cd.getNoOfPhoneAccounts());
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
  }

  public void ejbStore() {
  }

  public void ejbRemove() throws RemoveException {
  }

  // home methods
  public String ejbCreate(String customerId, String name, String address, int noOfPhAcc) throws CreateException {
    setCustomerId(customerId);
    setName(name);
    setAddress(address);
    setNoOfPhoneAccounts(noOfPhAcc);
    return null;
  }

  public void ejbPostCreate(String customerId, String name, String address, int noOfPhAcc) {
  }

  public String ejbCreate(CustomerData cd) throws CreateException {
    setCustomerId(cd.getCustomerId());
    setName(cd.getName());
    setAddress(cd.getAddress());
    setNoOfPhoneAccounts(cd.getNoOfPhoneAccounts());
    return null;
  }

  public void ejbPostCreate(CustomerData cd) {
  }
}
