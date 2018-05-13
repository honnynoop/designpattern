package hotel.presentationtier;

import hotel.util.Validator;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ValidationFilter implements Filter {
  
  private FilterConfig filterConfig = null;
  private Validator validator = new                                                       
                            ValidatorFactoryImpl().createValidator();
  
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
                        FilterChain chain ) 
           throws IOException, ServletException {
    HttpServletRequest httpRequest = ( HttpServletRequest )request;
    HttpServletResponse httpResponse = ( HttpServletResponse )response;
    if( ! validator.validate( request ) ) {
      httpRequest.setAttribute( "result", 
             "Error data entered is not valid. Please check your input" );
RequestDispatcher dispatcher =
      filterConfig.getServletContext()
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
