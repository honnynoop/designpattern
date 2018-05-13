package hotel.presentationtier;

import hotel.util.Command;
import hotel.util.CommandFactory;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Properties;


class CommandFactoryImpl implements CommandFactory {
    
    private HashMap commands = new HashMap();
    private final static String 
         ACTION_MAPPING_PROPERTIES = "actionMapping.properties";


    public CommandFactoryImpl() {
      try {
        Properties properties = new Properties();
        properties.load( getClass().getResourceAsStream(ACTION_MAPPING_PROPERTIES ));

for(Enumeration e = properties.keys(); e.hasMoreElements(); ) {
          String action = ( String )e.nextElement();
           commands.put( action, ObjectCreator.createObject(properties.getProperty(action)));
        }
      }
      catch( Exception e ) {
        System.out.println( "Error: "  + e.toString() );
        e.printStackTrace();
      }
    }      
  /** Creates a Command instance for a specified action and a 
  * specified set of properties.
  * @param action can be one of the following "create", "find" or "remove".
  * @param properties the properties for the action
  */    
    public Command createCommand( String action ) {
      return ( Command )commands.get( action );    
    } 
}