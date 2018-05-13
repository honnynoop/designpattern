package entity.city;

import javax.ejb.*;

public interface CityLocalHome extends EJBLocalHome {

	public CityLocal create(String name, String desc, String airport) throws CreateException;

	public CityLocal findByPrimaryKey(Long id) throws FinderException;

}