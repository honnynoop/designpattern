package hotel.util;
import hotel.util.Command;
public interface CommandFactory {

  /** Creates a Command instance for a specified action and a 
  * specified set of properties.
  * @param action can be a string defined command.
  */
    public Command createCommand( String action );

}
