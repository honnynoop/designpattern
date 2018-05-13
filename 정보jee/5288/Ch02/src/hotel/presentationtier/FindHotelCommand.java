package hotel.presentationtier;

import hotel.util.Command;
import hotel.businesstier.HotelBusinessDelegate;
import hotel.businesstier.HotelBusinessDelegateImpl;

import hotel.util.HotelDetails;
import hotel.util.RequestHelper;

import java.util.Properties;
import java.io.IOException;

import javax.servlet.ServletException;

class FindHotelCommand implements Command {

      private HotelDetails hoteldetails;
      private HotelBusinessDelegate delegate = 
                         new HotelBusinessDelegateImpl();

    /** Creates instance of me.
    */
      public FindHotelCommand( ) {
      }

    /** Returns a string representation of me.
    */
      private String getDisplayMessage( Properties properties ) {
        return properties.getProperty("id") + " is found";
      }
     
    /** Executes me.
    * @param helper helper Object gathers parameter specific information
    */
      public synchronized String execute( RequestHelper helper ) 
                                  throws ServletException, IOException {
        try {
        Properties properties = helper.getProperties(); 
HotelDetails hoteldetails =delegate.getHotel( properties.getProperty("id") );
        helper.getRequest().setAttribute( 
                       "result", this.getDisplayMessage( properties ) );
        helper.getRequest().setAttribute( "hotel", hoteldetails );
        }
        catch( Exception e ) {
          e.printStackTrace();
          helper.getRequest().setAttribute( 
                       "result", 
                       "Error processing Find Hotel Command"  + 
                        e.toString() );
          return "/errorPage.jsp";
        }
        return "/adminHotelResult.jsp";
      }
    }
