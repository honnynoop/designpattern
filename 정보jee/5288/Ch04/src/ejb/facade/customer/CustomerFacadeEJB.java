package facade.customer;

import javax.ejb.*;
import common.*;
import vo.CustomerVO;
import entity.customer.*;

public class CustomerFacadeEJB implements SessionBean {

	public void ejbCreate() throws CreateException {}
	public void ejbRemove() {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void setSessionContext(SessionContext ctx) {}
	public void unsetSessionContext() {}

	//Create a new customer from Customer value object
	public void registerNewCustomer(CustomerVO vo) {
		
		try	{
			ServiceLocator locator = ServiceLocator.getInstance(); 
			CustomerLocalHome home = (CustomerLocalHome) locator.getEJBLocalHome(ServiceLocator.CUSTOMER);
			home.create(vo);
		} catch (Exception e) {
			throw new EJBException (e);
		}
	}

	//Return customer details from a customer's email and password
	public CustomerVO loginCustomer(String email, String pwd) {

		CustomerVO vo = null;

		try	{
			ServiceLocator locator = ServiceLocator.getInstance(); 
			CustomerLocalHome home = (CustomerLocalHome) locator.getEJBLocalHome(ServiceLocator.CUSTOMER);
			CustomerLocal cust = home.findByEmailandPassword(email, pwd);
			vo = cust.getCustomer();				
		} catch (Exception e) {
			throw new EJBException (e);
		}

		return vo;			
	}
}