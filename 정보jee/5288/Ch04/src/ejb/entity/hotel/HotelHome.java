package entity.hotel;

import javax.ejb.*;
import java.util.Collection;
import java.rmi.RemoteException;

public interface HotelHome extends EJBHome {

	public Hotel create(String name, String location, int noRooms,
						String description, double price) throws CreateException, RemoteException;

	public Hotel findByPrimaryKey(Long id) throws FinderException, RemoteException;

	public Collection findByLocation(String location) throws FinderException, RemoteException;

}
