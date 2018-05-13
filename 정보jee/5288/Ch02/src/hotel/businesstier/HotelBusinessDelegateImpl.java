package hotel.businesstier;

import hotel.util.HotelDetails;
import hotel.ejb.*;
import javax.ejb.EJBHome;
import javax.naming.*;
import javax.naming.NamingException;
import java.util.Properties;

/** This class represents the hotel Business Delegate. A business delegate decouples presentation
* and services tiers, and provides a facade and proxy interface to services.
*/

public class HotelBusinessDelegateImpl implements  HotelBusinessDelegate {
  
    protected String url = "http://localhost:8000";;
    protected String user;
    protected String password;

  /* returns the hotel with the specified id
  * @param id the id for the hotel
  * @return the hotel with the specified id if found in the persistent store 
  */
    public HotelDetails getHotel( String id ) throws Exception {
      Hotel hotel = ( ( HotelHome ) getHome() ).findByPrimaryKey( id );
      return hotel.getHotelDetails();
    }

  /* Creates the hotel with the id specified and details specified and persists it in the 
  * persistent store.
  * @param id the id for the hotel to be created
  * @param hotelDetails the hotel details for the hotel to be created.
  */
    public void createHotel( String id, HotelDetails hotelDetails ) throws Exception {
      ((HotelHome)getHome()).create(  id, hotelDetails);
  }
    
  /* Removes the hotel with the id specified from the persistent store.
  * @param id the id for the hotel to be removed
  */
    public void removeHotel( String id ) throws Exception {
      getHome().remove( id );
    }
    
  /** Returns the hotel home.
  */  
    private final EJBHome getHome() throws NamingException {
        return getHome("Hotel", url, user, password);
    }
    
  /** Returns the hotel home.
  */  
    private final EJBHome getHome(String s, String s1, String s2, String s3)
        throws NamingException {
      
	  Properties properties = new Properties();
      properties.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
      properties.put("java.naming.provider.url", s1);
      if(s2 != null) {
        properties.put("java.naming.security.principal", s2);
        if(s3 == null) {
          s3 = "";
        }
        properties.put("java.naming.security.credentials", s3);
      } 

      InitialContext initialcontext = new InitialContext(properties);
    
	     return (EJBHome)initialcontext.lookup(s);
    }    
} 