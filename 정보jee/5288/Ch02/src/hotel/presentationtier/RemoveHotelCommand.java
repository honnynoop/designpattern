package hotel.presentationtier;

import hotel.util.Command;
import hotel.util.HotelDetails;
import hotel.util.RequestHelper;

import hotel.businesstier.HotelBusinessDelegate;
import hotel.businesstier.HotelBusinessDelegateImpl;

import java.util.Properties;
import java.io.IOException;
import javax.servlet.ServletException;

class RemoveHotelCommand implements Command {

      private HotelDetails hoteldetails;
      private HotelBusinessDelegate delegate = 
                                new HotelBusinessDelegateImpl();
      
    /** Creates instance of me.
    */
      public RemoveHotelCommand( ) {
      }

    /** Returns a string representation of me.
    */
      private String getDisplayMessage( String id ) {
        return "Hotel Id " + id + " is successfully removed";
      }

    /** Executes me.
    * @param helper helper Object gathers parameter specific information
    */
      public synchronized String execute( RequestHelper helper ) 
                               throws ServletException, IOException {
        try {
        String id = helper.getProperties().getProperty("id");
        hoteldetails = delegate.getHotel( id );
        delegate.removeHotel( id );
        helper.getRequest().setAttribute( "result", 
                                          this.getDisplayMessage( id ) );
        helper.getRequest().setAttribute( "hotel", hoteldetails );
} catch( Exception e ) {
          e.printStackTrace();
          helper.getRequest().setAttribute( 
                                 "result", 
                                 "Error processing Remove Hotel Command"  + 
                                 e.toString() );
          return "/errorPage.jsp";
        }
        return "/adminHotelResult.jsp";
      }
    }
