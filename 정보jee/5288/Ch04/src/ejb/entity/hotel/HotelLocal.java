package entity.hotel;

import javax.ejb.*;
import vo.HotelVO;

public interface HotelLocal extends EJBLocalObject {

	public Long getId();

	public HotelVO getHotelInfo();

	public int getAvailableRooms();
	public void bookRooms(int rooms);

}