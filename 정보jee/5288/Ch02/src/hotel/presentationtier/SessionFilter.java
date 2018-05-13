package hotel.presentationtier;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;


import java.io.IOException;

public class SessionFilter implements Filter {
  
  private FilterConfig filterConfig = null;
  
  public void init( FilterConfig filterConfig ) throws ServletException {
    this.filterConfig = filterConfig;
  }

  public FilterConfig getFilterConfig() {
    return filterConfig;
  }
  
  public void setFilterConfig( FilterConfig filterConfig ) {
    this.filterConfig = filterConfig;  
  }
  
  public void doFilter( ServletRequest request, 
                        ServletResponse response, 
                        FilterChain chain ) throws IOException, ServletException {
    HttpServletRequest httpRequest = ( HttpServletRequest )request;
    HttpServletResponse httpResponse = ( HttpServletResponse )response;
    String session = ( String )httpRequest.getAttribute( "session" );
    if( session == null ) {
      httpRequest.setAttribute( "result", 
                                "session is not valid" );
      RequestDispatcher dispatcher = filterConfig.getServletContext()
                              .getRequestDispatcher("/errorPage.jsp");
      dispatcher.forward( request, response );
    }
    else {      
      chain.doFilter( request, response );
    }
  } 
  
  public void destroy() {
  } 
} 
