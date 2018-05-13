package facade.customer;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CustomerFacadeHome extends EJBHome {

	public CustomerFacade create() throws CreateException, RemoteException;

}