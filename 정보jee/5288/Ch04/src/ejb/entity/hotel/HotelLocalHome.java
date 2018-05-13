package entity.hotel;

import javax.ejb.*;
import java.util.Collection;

public interface HotelLocalHome extends EJBLocalHome {

	public HotelLocal create(String name, String location, int noRooms,
						String description, double price) throws CreateException;

	public HotelLocal findByPrimaryKey(Long id) throws FinderException;

	public Collection findByLocation(String location) throws FinderException;

}
