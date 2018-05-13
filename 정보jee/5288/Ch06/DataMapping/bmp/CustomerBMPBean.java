package bmp;

import javax.ejb.*;
import java.util.*;

public class CustomerBMPBean extends CustomerBean implements EntityBean {

  private CustomerData cd;

  // implement the abstract getter/setter methods
  
  public String getCustomerId() {
    return cd.getCustomerId();
  }
  public void setCustomerId(String customerId) {
    cd.setCustomerId(customerId);
  }
  public String getName() {
    return cd.getName();
  }
  public void setName(String name) {
    cd.setName(name);
  }
  public String getAddress() {
    return cd.getAddress();
  }
  public void setAddress(String address) {
    cd.setAddress(address);
  }
  public int getNoOfPhoneAccounts() {
    return cd.getNoOfPhoneAccounts();
  }
  public void setNoOfPhoneAccounts(int noOfPhAcc) {
    cd.setNoOfPhoneAccounts(noOfPhAcc);
  }
  
  // Component interface alread implemented
  
  // We have to implement these EJB related methods
  public void ejbLoad() {
    cd = new CustomerData();

    // Impelement
    cd.setCustomerId("1");
    cd.setName("Matjaz");
    cd.setAddress("Wrox");
    cd.setNoOfPhoneAccounts(5);
  }

  public void ejbStore() {
    // Impelment
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
s