package entity.customer;

import javax.ejb.*;
import vo.CustomerVO;

public interface CustomerLocalHome extends EJBLocalHome {

	public CustomerLocal create(String name, String addr1, String addr2,
						   String state, String zip, String email, String pwd) 
		throws CreateException;

	public CustomerLocal create(CustomerVO vo) throws CreateException;

	public CustomerLocal findByPrimaryKey(Long id) throws FinderException;

	public CustomerLocal findByEmailandPassword(String email, String pwd) throws FinderException;

}