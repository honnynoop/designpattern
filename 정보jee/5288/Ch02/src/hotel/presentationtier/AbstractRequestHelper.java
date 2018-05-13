package hotel.presentationtier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import java.util.Properties;
import java.math.BigDecimal;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hotel.util.RequestHelper;
import hotel.util.Command;

abstract class AbstractRequestHelper implements RequestHelper {

    private HttpServletRequest request = null;
    private HttpServletResponse response = null;
    private Properties properties = null;
   
  /** Creates instance of me
  * @param request servlet request
  * @param response servlet response
  */
    AbstractRequestHelper( HttpServletRequest request, HttpServletResponse response){
this.request = request;
this.response = response; 
this.properties = getProperties( request );
}
 
  /** Returns the servlet request for this session
  */
    public HttpServletRequest getRequest() {
      return request;
    }
  
  /** Returns the servlet response for this session
  */
    public HttpServletResponse getResponse() {
      return response;
    }
    
  /** Returns the command to exceute.
  */
    public abstract Command getCommand();

    public Properties getProperties( ) { 
      return properties;
    }
    
  /** Transulates servlet request properties to java properties. 
  */
    private Properties getProperties(HttpServletRequest httpservletrequest) {
        Properties properties = new Properties();
        for( Enumeration enumeration = 
                   httpservletrequest.getParameterNames(); 
                   enumeration.hasMoreElements();) {
          Object obj = enumeration.nextElement();
          String s = httpservletrequest.getParameterValues((String)obj)[0];
System.out.println( "Parameter name ="+ obj.toString() + ", Parameter value =" + s );
          if( ! isAny(s)) {
            try {
              properties.put(obj, new BigDecimal(s));
            } catch(NumberFormatException _ex) {  
              properties.put(obj, s);
            }
          }
        }
        return properties;
    }

    private static boolean isAny(String s) {
        return s.equals("Any");
    }       
}
	 
