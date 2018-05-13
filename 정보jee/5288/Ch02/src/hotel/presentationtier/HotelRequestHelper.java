package hotel.presentationtier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import hotel.util.CommandFactory;
import hotel.util.Command;
import hotel.util.RequestHelper;

final class HotelRequestHelper extends AbstractRequestHelper {
  
    private CommandFactory commandFactory = new CommandFactoryImpl();
   
  /** Creates instance of me
  * @param request servlet request
  * @param response servlet response
  */
    HotelRequestHelper( HttpServletRequest request, 
HttpServletResponse response) {
super( request, response );
    } 
      
  /** Returns the command to exceute.
  */
    public Command getCommand() {
      return commandFactory.createCommand( 
             getProperties().getProperty("action") );
    }
    
    
}
