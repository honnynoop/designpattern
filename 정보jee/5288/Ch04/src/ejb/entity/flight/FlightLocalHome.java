package entity.flight;

import javax.ejb.*;
import java.util.Collection;
import java.sql.Date;

public interface FlightLocalHome extends EJBLocalHome {

	public FlightLocal create(String from, String to, String dept, String arrival, 
						 String airline, int seats, String date, double price) throws CreateException;

	public FlightLocal findByPrimaryKey(Long id) throws FinderException;
	public Collection findByFlightDateToFrom(String dt, String from, String to) throws FinderException;

}