package delegate;

import common.*;
import facade.customer.*;
import vo.CustomerVO;

public class CustomerDelegate {
	
	private static CustomerFacade cFacade = null;
	
	//Get the remote interface to the CustomerFacade session bean
	private static void setCustomerFacade() {

		try {
			ServiceLocator locator = ServiceLocator.getInstance();
			CustomerFacadeHome cfhome = (CustomerFacadeHome) locator.getEJBHome(ServiceLocator.CUSTOMERFACADE);
			cFacade = cfhome.create();
		} catch (ServiceLocatorException sle) {
			System.out.println("ServiceLocator exception thrown: " + sle);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//Create a new customer
	public void registerNewCustomer(String name, String addr1, String addr2, String state,
									String zip, String email, String password) {
	
		//Construct Customer Value Object
		CustomerVO vo = new CustomerVO();
		vo.name = name;
		vo.addr1 = addr1;
		vo.addr2 = addr2;
		vo.state = state;
		vo.zip = zip;
		vo.email = email;
		vo.pwd = password;

		//Connect to facade and pass on value object
		try {
			if (cFacade == null) {
				setCustomerFacade();
			}					
			cFacade.registerNewCustomer(vo);
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//Send email and password info to get customer id
	public Long loginCustomer(String email, String pwd) {

		CustomerVO vo = null;

		try {
			if (cFacade == null) {
				setCustomerFacade();
			}					
			
			vo = cFacade.loginCustomer(email, pwd);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return vo.customerId;
	}


}