package entity.city;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface CityHome extends EJBHome {

	public City create(String name, String desc, String airport) throws CreateException, RemoteException;

	public City findByPrimaryKey(Long id) throws FinderException, RemoteException;

}