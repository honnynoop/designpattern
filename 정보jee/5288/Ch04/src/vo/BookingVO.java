package vo;

import java.sql.Date;
import java.io.Serializable;

public class BookingVO implements Serializable {

	public Long hotelId;
	public Long flightIdOut;
	public Long flightIdIn;
	public Long customerId;
	public String fromDate;
	public String toDate;
	public int noSeats;
	public int noRooms;
	public double price;

}
