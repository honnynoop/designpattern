package entity.hotel;

import javax.ejb.*;
import common.*;
import common.counter.*;
import vo.HotelVO;

public abstract class HotelEJB implements EntityBean {

	public abstract Long getId();
	public abstract void setId(Long id);

	public abstract String getName();
	public abstract void setName(String name);

	public abstract String getLocation();
	public abstract void setLocation(String location);

	public abstract int getAvailableRooms();
	public abstract void setAvailableRooms(int rooms);

	public abstract String getDescription();
	public abstract void setDescription(String desc);

	public abstract double getPricePerNight();
	public abstract void setPricePerNight(double price);

	public Long ejbCreate(String name, String location, int noRooms,
							String description, double price) throws CreateException {


		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			System.out.println("Getting an instance...in ejbCreate() of HotelHome\n");
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("HOTEL"));
			setName(name);
			setLocation(location);
			setAvailableRooms(noRooms);
			setDescription(description);
			setPricePerNight(price);
		} catch (ServiceLocatorException sle) {
			throw new CreateException();
		} catch (java.rmi.RemoteException re) {
			throw new CreateException();
		}

		return null;
	}

    public void ejbPostCreate(String name, String location, int noRooms,
							String description, double price) throws CreateException {}

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbLoad() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}

	public HotelVO getHotelInfo() {

		HotelVO vo = new HotelVO();

		vo.id = getId();
		vo.name = getName();
		vo.description = getDescription();
		vo.location = getLocation();
		vo.noAvailableRooms = getAvailableRooms();
		vo.pricePerNight = getPricePerNight();

		return vo;

	}

	public void bookRooms(int rooms) {
		setAvailableRooms(getAvailableRooms() - rooms);
	}

}