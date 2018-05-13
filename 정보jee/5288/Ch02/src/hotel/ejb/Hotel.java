package hotel.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import hotel.util.HotelDetails;

/** Represents the remote interface for the hotel EJB.
*/

public interface Hotel
    extends EJBObject {

  /** Returns the hotel details for a hotel
  * @return the hotel details relevant to me
  */
    public abstract HotelDetails getHotelDetails()
        throws RemoteException;

  /** Returns the hotel id for a hotel
  * @return the hotel id relevant to me
  */
    public abstract String getHotelId()
        throws RemoteException;
}