package entity.booking;

import javax.ejb.*;
import vo.BookingVO;
import common.*;
import common.counter.*;

public abstract class BookingEJB implements EntityBean {

	public abstract Long getId();
	public abstract void setId(Long id);

	public abstract Long getCustomerId();
	public abstract void setCustomerId(Long id);

	public abstract Long getFlightIdOut();
	public abstract void setFlightIdOut(Long id);

	public abstract Long getFlightIdIn();
	public abstract void setFlightIdIn(Long id);

	public abstract Long getHotelId();
	public abstract void setHotelId(Long id);

	public abstract double getPrice();
	public abstract void setPrice(double price);
	
	public abstract int getNoRooms();
	public abstract void setNoRooms(int rooms);

	public abstract int getNoSeats();
	public abstract void setNoSeats(int seats);

	public abstract String getFromDate();
	public abstract void setFromDate(String from);

	public abstract String getToDate();
	public abstract void setToDate(String to);

	public Long ejbCreate(Long customerId, Long flightIdOut, Long flightIdIn, Long hotelId, 
						  double price, int noRooms, int noSeats, String fromDate, 
						  String toDate) 
		  throws CreateException {
		
		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("BOOKING"));
			setCustomerId(customerId);
			setFlightIdOut(flightIdOut);
			setFlightIdIn(flightIdIn);
			setHotelId(hotelId);
			setPrice(price);
			setNoRooms(noRooms);
			setNoSeats(noSeats);
			setFromDate(fromDate);
			setToDate(toDate);
		} catch (ServiceLocatorException sle) {
			throw new CreateException();
		} catch (java.rmi.RemoteException re) {
			throw new CreateException();
		}
	
		return null;

	}

	public void ejbPostCreate(Long customerId, Long flightIdOut, Long flightIdIn, Long hotelId, 
							  double price, int noRooms, int noSeats, String fromDate, 
							  String toDate) 
		throws CreateException {}


	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbLoad() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}

    public Long ejbCreate(BookingVO vo) throws CreateException {
	
		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("BOOKING"));
			setCustomerId(vo.customerId);
			setFlightIdOut(vo.flightIdOut);
			setFlightIdIn(vo.flightIdIn);
			setHotelId(vo.hotelId);
			setPrice(vo.price);
			setNoRooms(vo.noRooms);
			setNoSeats(vo.noSeats);
			setFromDate(vo.fromDate);
			setToDate(vo.toDate);
		} catch (ServiceLocatorException e) {
			throw new EJBException(e);
		} catch (java.rmi.RemoteException re) {
			throw new EJBException(re);
		}

		return null;
	}


	public void ejbPostCreate(BookingVO vo) throws CreateException {}
}
