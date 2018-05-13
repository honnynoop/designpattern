package entity.customer;

import javax.ejb.*;
import vo.CustomerVO;

public interface CustomerLocal extends EJBLocalObject {

	public Long getId();

	public CustomerVO getCustomer();

	public void addBooking(Long id);
}


