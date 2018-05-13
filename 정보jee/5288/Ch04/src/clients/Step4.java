package clients;

import delegate.CustomerDelegate;

class  Step4
{
	public static void main(String[] args) 	{
		Step4 me = new Step4();
		me.registerCustomer();
		me.loginCustomer();
	}

	private void registerCustomer() {

		CustomerDelegate cd = new CustomerDelegate();
		cd.registerNewCustomer("Craig Berry", "Arden House", "Birmingham", "AL", "12345", "craigb@wrox.com", "cab");
		System.out.println("New customer registered");
	}

	private void loginCustomer() {
	 
	    CustomerDelegate cd = new CustomerDelegate();
		Long id = cd.loginCustomer("craigb@wrox.com", "cab");
		System.out.println("Customer id: " + id);

	}
}
