package bmp;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.util.Collection;
import java.rmi.RemoteException;

public interface CustomerHome extends EJBHome {

  public Customer create(String customerId, String name, String address, int noOfPhAcc) throws RemoteException, CreateException;
  public Customer create(CustomerData custData) throws RemoteException, CreateException;

  public Customer findByPrimaryKey(String primaryKey) throws RemoteException, FinderException;
  public Collection findByName(String name) throws RemoteException, FinderException;

}
