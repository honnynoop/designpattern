package hotel.util;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {

  /** Executes me.
  * @param helper helper Object gathers parameter specific information
  */
    public String execute( RequestHelper helper ) 
                            throws ServletException, IOException; 
}
