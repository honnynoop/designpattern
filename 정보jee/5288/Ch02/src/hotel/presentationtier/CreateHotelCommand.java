package hotel.presentationtier;

import hotel.util.Command;
import hotel.util.HotelDetails;
import hotel.util.RequestHelper;

import hotel.businesstier.HotelBusinessDelegate;
import hotel.businesstier.HotelBusinessDelegateImpl;

import java.util.Properties;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;

class CreateHotelCommand implements Command {

      private HotelDetails hoteldetails = null;
      private HotelBusinessDelegate delegate = 
                               new HotelBusinessDelegateImpl();
      public CreateHotelCommand( ) {
      }

    /** Returns a string representation of me.
    */
      private String getDisplayMessage( Properties properties ) {
          return properties.getProperty("id") + " is successfully created";
      }

    /** Executes me.
    * @param helper helper Object gathers parameter specific information
    */
      public synchronized String execute( RequestHelper helper ) 
            throws ServletException, IOException {
        try {
        Properties properties = helper.getProperties();  
        hoteldetails = new HotelDetails(
                            properties.getProperty("name"), 
                            properties.getProperty("purpose"),                                                            
                            properties.getProperty("regionOrTown"), 
                            properties.getProperty("type"), 
                            properties.getProperty("chain"), 
(BigDecimal)properties.get("swimmingPool"),                           (BigDecimal)properties.get("gym"),                     
                            (BigDecimal)properties.get("conferenceRooms"));

        delegate.createHotel(properties.getProperty("id"), hoteldetails);
        helper.getRequest().setAttribute( "result", 
                                     this.getDisplayMessage( properties ) );
        helper.getRequest().setAttribute( "hotel", hoteldetails );
        }
        catch( Exception e ) {
          e.printStackTrace();
          helper.getRequest().setAttribute( 
                               "result", 
                               "Error processing Create Hotel Command"  + 
                               e.toString() );
          return "/errorPage.jsp";
        }
        return "/adminHotelResult.jsp";
      }
    }
