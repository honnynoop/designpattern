package vo;

import java.util.Collection;
import java.io.Serializable;
import java.util.LinkedList;

public class CityBreakVO implements Serializable {

	public String cityName;
	public String cityDesc;	
	public String airport;
	
	
	private LinkedList hotels_list = new LinkedList();

	public Collection getHotels() {
		
		return hotels_list;
	}

	public void addHotel(HotelVO vo) {
		
		hotels_list.add(vo);
	}

}
