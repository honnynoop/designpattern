package hotel.ejb;

import java.rmi.RemoteException;

/** Indicates that an exception condiiton has occured  in the Hotel EJB.
*/

public class HotelException extends RemoteException {

  /** Creates instance of me.
  */
    public HotelException() {
    }

  /** Creates instance of me.
  */
    public HotelException(String s) {
      super(s);
    }

  /** Creates instance of me.
  */
    public HotelException(String s, Throwable throwable) {
      super(s, throwable);
    }
}