package hotel.ejb;

import hotel.util.HotelDetails;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import javax.ejb.*;

/** The implementation for the hotel EJB
*/


public class HotelEJB implements EntityBean {

    protected String name;
    protected String purpose;
    protected String regionOrTown;
    protected String type;
    protected String chain;
    protected BigDecimal swimmingPool;
    protected BigDecimal gym;
    protected BigDecimal conferenceRooms;
    protected String id;
    protected EntityContext ectx;

  /** Changes my hotel details.
  */
    public void changeHotelDetails( HotelDetails hoteldetails ) {
        name = hoteldetails.getName();
        purpose = hoteldetails.getPurpose();
        regionOrTown = hoteldetails.getRegionOrTown();
        type = hoteldetails.getType();
        chain = hoteldetails.getChain();
        swimmingPool = hoteldetails.getSwimmingPool();
        gym = hoteldetails.getGym();
        conferenceRooms = hoteldetails.getConferenceRooms();
    }

  /** Called by the container to create a session bean instance. Its parameters typically
   * contain the information the client uses to customize the bean instance for its use.
   * It requires a matching pair in the bean class and its home interface.     
   */
    public String ejbCreate(String s, HotelDetails hoteldetails)
        throws CreateException, RemoteException {
        id = s;
        changeHotelDetails(hoteldetails);
        System.out.println("exiting ejbCreate");
        return s;
    }

  /** Returns my hotel details.
  */
    public HotelDetails getHotelDetails() {
        return new HotelDetails(name, purpose, regionOrTown, type, chain, swimmingPool, gym, conferenceRooms);
    }
    
  /** Returns my id.
  */
    public String getHotelId() {
        return id;
    }

  /** The passivate method is called before the instance enters the 'passive' state. The
   * instance should release any resources that it can re-acquire later in the ejbActivate()
   * method. After the passivate method completes, the instance must be in a state that
   * allows the container to use the Java Serialization protocol to externalize and store
   * away the instance's state. This method is called with no transaction context.
   */
    public void ejbPassivate()
        throws RemoteException {
    }

  /** A container invokes this method before it ends the life of the session object. This
   * happens as a result of a client's invoking a remove operation, or when a container
   * decides to terminate the session object after a timeout. This method is called with
   * no transaction context.
   */
    public void ejbRemove()
        throws RemoveException, RemoteException {
    }

    public void setSessionContext(SessionContext sessioncontext)
        throws RemoteException {
    }

  /** The activate method is called when the instance is activated from its 'passive' state.
   * The instance should acquire any resource that it has released earlier in the ejbPassivate()
   * method. This method is called with no transaction context.
   */
    public void ejbActivate()
        throws RemoteException {
    }

  /** Loads the ejb from the persistent store.
  */
    public void ejbLoad()
        throws RemoteException {
    }
    
  /** Stores the EJB details in the persistent store.
  */
    public void ejbStore()
        throws RemoteException {
    }

  /** Empty implementation not needed for the purposes of this bean.
  */
    public void ejbPostCreate(String s, HotelDetails hoteldetails)
        throws RemoteException {
    }

  /** Sets the associated context. The container calls this method after the instance
   * creation. The enterprise Bean instance should store the reference to the context
   * object in an instance variable. This method is called with no transaction context.
   * @param entitycontext the context for this bean.
   * @exception java.rmi.RemoteException if remote method call fails.
   */
    public void setEntityContext(EntityContext entitycontext)
        throws RemoteException {
        ectx = entitycontext;
    }

  /** Unsets the associated context. The container calls this method after the instance
   * creation. The enterprise Bean instance should store the reference to the context
   * object in an instance variable. This method is called with no transaction context.
   * @exception java.rmi.RemoteException if remote method call fails.
   */
    public void unsetEntityContext()
        throws RemoteException {
        ectx = null;
    }
}