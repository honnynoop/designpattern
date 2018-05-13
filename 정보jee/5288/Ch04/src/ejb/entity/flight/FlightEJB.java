package entity.flight;

import javax.ejb.*;
import vo.FlightVO;
import common.*;
import common.counter.*;

public abstract class FlightEJB implements EntityBean {

	public abstract Long getId();
	public abstract void setId(Long id);

	public abstract String getFrom();
	public abstract void setFrom(String from);

	public abstract String getTo();
	public abstract void setTo(String to);

	public abstract String getDepartureTime();
	public abstract void setDepartureTime(String time);

	public abstract String getArrivalTime();
	public abstract void setArrivalTime(String time);

	public abstract String getAirline();
	public abstract void setAirline(String airline);

	public abstract int getAvailableSeats();
	public abstract void setAvailableSeats(int seats);

	public abstract String getDate();
	public abstract void setDate(String date);

	public abstract double getPrice();
	public abstract void setPrice(double price);

	public Long ejbCreate(String from, String to, String dept, 
	                      String arrival, String airline, int seats, String date, double price)
			throws CreateException {

		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("FLIGHT"));
			setFrom(from);
			setTo(to);
			setDepartureTime(dept);
			setArrivalTime(arrival);
			setAirline(airline);
			setAvailableSeats(seats);
			setDate(date);
			setPrice(price);
		} catch (ServiceLocatorException sle) {
			throw new CreateException();
		} catch (java.rmi.RemoteException re) {
			throw new CreateException();
		}

		return null;

	}

	public void ejbPostCreate(String from, String to, String dept, 
	                      String arrival, String airline, int seats, String date, double price)
			throws CreateException {}

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbLoad() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}

	public FlightVO getFlightInfo() {

		FlightVO vo = new FlightVO();
		
		vo.flightId = getId();
		vo.from = getFrom();
		vo.to = getTo();
		vo.departure = getDepartureTime();
		vo.arrival = getArrivalTime();
		vo.airline = getAirline();
		vo.availableSeats = getAvailableSeats();
		vo.date = getDate();
		vo.price = getPrice();

		return vo;

	}

	public void bookSeats(int seats) {
		
		setAvailableSeats(getAvailableSeats() - seats);
	}
}
