package facade.customer;

import javax.ejb.*;
import java.rmi.RemoteException;
import vo.CustomerVO;

public interface CustomerFacade extends EJBObject {

	public void registerNewCustomer(CustomerVO vo) throws RemoteException;
	public CustomerVO loginCustomer(String email, String pwd) throws RemoteException;
}