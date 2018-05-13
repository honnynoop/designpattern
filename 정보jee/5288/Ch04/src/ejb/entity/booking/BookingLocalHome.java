package entity.booking;

import javax.ejb.*;
import vo.BookingVO;

public interface BookingLocalHome extends EJBLocalHome {

	public BookingLocal create(Long customerId, Long flightIdOut, Long flightIdIn, Long hotelId, 
 						  double price, int noRooms, int noSeats, String fromDate, 
						  String toDate) throws CreateException;

	public BookingLocal create(BookingVO vo) throws CreateException;

	public BookingLocal findByPrimaryKey(Long id) throws FinderException;

}
