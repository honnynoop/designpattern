/*
 * Account.java
 *
 * Created on 27 April 2002, 10:44
 */

package ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

import java.util.ArrayList;

/**
 *
 * @author  pznwc5
 */
public interface Account extends EJBObject {
    
    public Double getBalance() throws RemoteException, AccountException;
    
    public ArrayList getStatement() throws RemoteException, AccountException;
    
}
