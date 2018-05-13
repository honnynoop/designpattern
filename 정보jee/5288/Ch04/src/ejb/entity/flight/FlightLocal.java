package entity.flight;

import javax.ejb.*;
import vo.FlightVO;

public interface FlightLocal extends EJBLocalObject {

	public Long getId();

	public int getAvailableSeats();
	public void bookSeats(int seats);

	public FlightVO getFlightInfo();
}