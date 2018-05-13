package hotel.businesstier;

import hotel.util.HotelDetails;

/** This interface represents a Business Delegate. A business delegate decouples presentation
* and services tiers, and provides a facade and proxy interface to services.
*/

public interface HotelBusinessDelegate {
  
  /* returns the hotel with the specified id
  * @param id the id for the hotel
  * @return the hotel with the specified id if found in the persistent store 
  */
    public HotelDetails getHotel( String id ) throws Exception;

  /* Creates the hotel with the id specified and details specified and persists it in the 
  * persistent store.
  * @param id the id for the hotel to be created
  * @param hotelDetails the hotel details for the hotel to be created.
  */
    public void createHotel( String id, HotelDetails hotelDetails ) throws Exception;
    
  /* Removes the hotel with the id specified from the persistent store.
  * @param id the id for the hotel to be removed
  */
    public void removeHotel( String id ) throws Exception;
} 