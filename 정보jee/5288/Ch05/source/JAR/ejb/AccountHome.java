/*
 * AccountHome.java
 *
 * Created on 27 April 2002, 10:44
 */

package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 *
 * @author  pznwc5
 */
public interface AccountHome extends EJBHome {
    
    public Account create() throws RemoteException, CreateException;
    
}
