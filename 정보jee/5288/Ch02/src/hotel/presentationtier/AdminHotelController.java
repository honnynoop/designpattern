package hotel.presentationtier;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import hotel.util.HotelDetails;
import hotel.ejb.HotelHome;
import hotel.ejb.Hotel;

import java.util.*;
import java.math.BigDecimal;
import java.util.Properties;
import hotel.util.Command;
import hotel.presentationtier.CommandFactoryImpl;
import hotel.util.RequestHelper;


public class AdminHotelController extends HttpServlet {


  /** Processes a request for both HTTP 
  * <code> GET </code> and <code> POST </code> methods.
  * @param request servlet request
  * @param response servlet response
  */
    private void processRequest( 
                HttpServletRequest request, 
                HttpServletResponse response ) 
      throws ServletException, IOException {
      //log request here

      String next = "";
      try { 
        RequestHelper helper = new HotelRequestHelper( request, response );
        Command command = helper.getCommand();
        next = command.execute( helper );
      }
      catch( Exception e ) {
      //this should return an error page
      //next = ApplicationResources.getInstance().getErrorPage( e );
        e.printStackTrace();
      }
      dispatch( request, response, next );      
    }
    
protected void doGet(
                HttpServletRequest request, 
                HttpServletResponse response ) 
      throws ServletException, IOException {
      processRequest( request, response );
    }
    
 protected void doPost(
                HttpServletRequest request, 
           HttpServletResponse response ) 
      throws ServletException, IOException {
      processRequest( request, response );
    }
    
  /** returns a description of the servlet
  */
    public String getServletInfo() {
      return getSignature();
    }
    
private void dispatch(HttpServletRequest request, 
                          HttpServletResponse response,
                          String page ) 
      throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher( page );
      dispatcher.forward( request, response );  
    }
    
    private String getSignature() {
      return "ServiceToWorker-Controller";
    }
}