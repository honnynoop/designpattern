package hotel.ejb;

import hotel.util.HotelDetails;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Properties;
import javax.ejb.*;

/** Represents the home interface for the Hotel EJB.
*/

public interface HotelHome
    extends EJBHome {

  /** Creates a Hotel EJB
  */
    public abstract Hotel create( String id, HotelDetails hoteldetails )
        throws CreateException, RemoteException;

  /** Finder method for a hotel by id
  */
    public abstract Hotel findByPrimaryKey( String id )
        throws FinderException, RemoteException;

  /** Finder method for a hotel by set of dynamic properties
  */
    public abstract Enumeration findByCriteria( Properties properties )
        throws FinderException, RemoteException;
}