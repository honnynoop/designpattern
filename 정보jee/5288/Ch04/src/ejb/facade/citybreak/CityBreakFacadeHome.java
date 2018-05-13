package facade.citybreak;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CityBreakFacadeHome extends EJBHome {

	public CityBreakFacade create() throws CreateException, RemoteException;
}
