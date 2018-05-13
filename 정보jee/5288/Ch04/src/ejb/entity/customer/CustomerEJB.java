package entity.customer;

import javax.ejb.*;
import vo.CustomerVO;
import common.*;
import common.counter.*;

public abstract class CustomerEJB implements EntityBean {

	public abstract Long getId();
	public abstract void setId(Long id);

	public abstract String getName();
	public abstract void setName(String name);

	public abstract String getAddress1();
	public abstract void setAddress1(String addr1);

	public abstract String getAddress2();
	public abstract void setAddress2(String addr2);

	public abstract String getState();
	public abstract void setState(String state);

	public abstract String getZip();
	public abstract void setZip(String zip);

	public abstract String getEmail();
	public abstract void setEmail(String email);

	public abstract String getPassword();
	public abstract void setPassword(String pwd);

	public abstract String getBookings();
	public abstract void setBookings(String id);

	public Long ejbCreate(String name, String addr1, String addr2,
						String state, String zip, String email, String pwd) 
			throws CreateException {

		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("CUSTOMER"));
			setName(name);
			setAddress1(addr1);
			setAddress2(addr2);
			setState(state);
			setZip(zip);
			setEmail(email);
			setPassword(pwd);
		} catch (ServiceLocatorException sle) {
			throw new CreateException();
		} catch (java.rmi.RemoteException re) {
			throw new CreateException();
		}

		return null;
	}

	public void ejbPostCreate(String name, String addr1, String addr2,
						String state, String zip, String email, String pwd) 
			throws CreateException {}
					
	public Long ejbCreate(CustomerVO vo) throws CreateException {

		try	{
			ServiceLocator locator = ServiceLocator.getInstance();
			CounterHome home = (CounterHome) locator.getEJBHome(ServiceLocator.COUNTER);
			Counter counter = home.create();
		
			setId(counter.getNextVal("CUSTOMER"));
			setName(vo.name);
			setAddress1(vo.addr1);
			setAddress2(vo.addr2);
			setState(vo.state);
			setZip(vo.zip);
			setEmail(vo.email);
			setPassword(vo.pwd);	
		} catch (ServiceLocatorException e) {
			throw new CreateException();
		} catch (java.rmi.RemoteException re) {
			throw new CreateException();
		}

		return null;
	}

	public void ejbPostCreate(CustomerVO vo) throws CreateException {}

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbLoad() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}

	public CustomerVO getCustomer() {

		CustomerVO vo = new CustomerVO();

		vo.customerId = getId();
		vo.name = getName();
		vo.addr1 = getAddress1();
		vo.addr2 = getAddress2();
		vo.state = getState();
		vo.zip = getZip();
		vo.email = getEmail();
		vo.pwd = getPassword();

		return vo;
	}


	public void addBooking(Long id) {
		setBookings(getBookings() + "|" +id.toString());
	}
}