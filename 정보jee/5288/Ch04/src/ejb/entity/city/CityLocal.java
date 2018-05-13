package entity.city;

import javax.ejb.*;

public interface CityLocal extends EJBLocalObject {

	public Long getId();
	public String getName();
	public String getDescription();
	public String getAirport();

}
