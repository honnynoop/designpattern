package bmp;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Customer extends EJBObject {

  public String getCustomerPrimaryKey() throws RemoteException;
  public String getCustomerName() throws RemoteException;
  public void setCustomerName(String name) throws RemoteException;
  public String getCustomerAddress() throws RemoteException;
  public void setCustomerAddress(String address) throws RemoteException;
  public int getCustomerNoOfPhoneAccounts() throws RemoteException;
  public void setCustomerNoOfPhoneAccounts(int noOfPhAcc) throws RemoteException;
  
  public CustomerData getCustomerData() throws RemoteException;
  public void setCustomerData(CustomerData custData) throws RemoteException;

}

