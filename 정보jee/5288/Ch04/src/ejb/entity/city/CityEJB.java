package entity.city;

import javax.ejb.*;
import common.*;
import common.counter.*;

public abstract class CityEJB implements EntityBean {

	public abstract Long getId();
	public abstract void setId(Long id);

	public abstract String getName();
	public abstract void setName(String name);

	public abstract String getDescription();
	public abstract void setDescription(String desc);

	public abstract String getAirport();
	public abstract void setAirport(String airport);

	public Long ejbCreate(String name, String desc, String airport) 
		throws CreateException {
			System.out.println("This is ejbCreate()\n");
		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
						System.out.println("Created Service Locator Object for city\n");
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
			setId(counter.getNextVal("CITY"));
			setName(name);
			setDescription(desc);
			setAirport(airport);
		} catch (ServiceLocatorException sle) {
			throw new EJBException(sle);
		} catch (java.rmi.RemoteException re) {
			throw new EJBException(re);
		}

		return null;
	}

    public void ejbPostCreate(String name, String desc, String airport) 
		throws CreateException {}

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbLoad() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}

}