package hotel.util;

import java.io.Serializable;
import java.math.BigDecimal;

/** Represents a Hotel Details object. Design is based on the ValueObject design pattern. Value object
* facilitates data exchange between tiers by reducing network chattiness.   
*/

public class HotelDetails implements Serializable {

    private String name = "";
    private String purpose = "";
    private String regionOrTown = "";
    private String type = "";
    private String chain = "";
    private BigDecimal swimmingPool = new BigDecimal(0.0D);
    private BigDecimal gym = new BigDecimal(0.0D);
    private BigDecimal conferenceRooms = new BigDecimal(0.0D);

  /** Creates instance of me.
  * @param name is the name of the hotel.
  * @param purpose main purpose of use of the hotel..
  * @param regionOrTown the location of the hotel.
  * @param type the type of the hotel, eg. 5 star, 4 star etc.
  * @param chain the chain of the hotel.
  * @param swimmingPool, 1 if the hotel has a swimming pool or 0 if it does not
  * @param gym 1 if the hotel has a gym or 0 if it does not
  * @param gym 1 if the hotel has a conference rooms or 0 if it does not
  */
    public HotelDetails( String name, 
                        String purpose, 
                        String regionOrTown, 
                        String type, 
                        String chain, 
                        BigDecimal swimmingPool, 
                        BigDecimal gym, BigDecimal conferenceRooms ) {
      if( name != null ) {
        this.name = name;
      }
      if ( purpose != null ) {
        this.purpose = purpose;
      }
      if ( regionOrTown != null ) {
        this.regionOrTown = regionOrTown;
      }
      if ( type != null ) {
        this.type = type;
      }
      if ( chain != null ) {
        this.chain = chain;
      }
      if ( swimmingPool != null ) {
        this.swimmingPool = swimmingPool;
      }
      if ( gym != null ) { 
        this.gym = gym;
      }
      if ( conferenceRooms != null ) {
        this.conferenceRooms = conferenceRooms;
      }
    }

  /** Returns the name of the hotel.
  * @return name the name of the hotel. 
  */
    public String getName() {
      return name;
    }

  /** Returns the purpose of the hotel.
  * @return the purpose of the hotel.
  */
    public String getPurpose() {
      return purpose;
    }

  /** Returns the location of the hotel.
  * @return the location of the hotel.
  */
    public String getRegionOrTown() {
      return regionOrTown;
    }

  /** Returns the type of the hotel.
  * @return the type of the hotel.
  */
    public String getType() {
      return type;
    }

  /** Returns the chain of the hotel.
  * @return the chain of the hotel.
  */
    public String getChain() {
      return chain;
    }

  /** Returns 1 if the hotel has a swimming pool or 0 if it does not.
  * @return 1 if the hotel has a swimming pool or 0 if it does not.
  */
    public BigDecimal getSwimmingPool() {
      return swimmingPool;
    }

  /** Returns 1 if the hotel has a gym or 0 if it does not.
  * @return 1 if the hotel has a gym or 0 if it does not.
  */
    public BigDecimal getGym() {
      return gym;
    }

  /** Returns 1 if the hotel has conference rooms or 0 if it does not.
  * @return 1 if the hotel has conference rooms or 0 if it does not.
  */
    public BigDecimal getConferenceRooms() {
      return conferenceRooms;
    }

  /** Returns a string representation of me. 
  */
    public String toString() {
      StringBuffer stringbuffer = new StringBuffer(getClass().getName() + "::\n");
      stringbuffer.append("name=" + name + ", purpose=" + purpose + ", region or town=" + regionOrTown + ", ");
      stringbuffer.append("type=" + type + ", chain=" + chain + ", ");
      stringbuffer.append("swimmingPool=" + swimmingPool + ", gym=" + gym + ", ");
      stringbuffer.append("conferenceRooms=" + conferenceRooms);
      return stringbuffer.toString();
    }
}
