package vo;

import java.io.Serializable;

public class HotelVO implements Serializable{

	public Long id;
	public String name;
	public String location;
	public int noAvailableRooms;
	public String description;
	public double pricePerNight;
}