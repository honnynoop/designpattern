package entity.flight;

import javax.ejb.*;
import java.util.Collection;
import java.sql.Date;
import java.rmi.RemoteException;

public interface FlightHome extends EJBHome {

	public Flight create(String from, String to, String dept, String arrival, 
						 String airline, int seats, String date, double price) throws CreateException, RemoteException;

	public Collection findByFlightDateToFrom(String dt, String from, String to) throws FinderException, RemoteException;
	public Flight findByPrimaryKey(Long id) throws FinderException, RemoteException;

}