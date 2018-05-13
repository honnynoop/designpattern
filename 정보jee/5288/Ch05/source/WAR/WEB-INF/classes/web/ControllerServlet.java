/*
 * ControllerServlet.java
 *
 * Created on 27 April 2002, 10:42
 */

package web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;

/**
 *
 * @author  pznwc5
 */
public class ControllerServlet extends HttpServlet {
    
    public void process(HttpServletRequest req, HttpServletResponse res) 
    throws ServletException, IOException {
       
        try {
            
            Command com = CommandFactory.getCommand(req.getServletPath());
            
            System.out.println("<DEBUG>");
            System.out.println("user:" + req.getRemoteUser());
            System.out.println("path:" + req.getServletPath());
            System.out.println("Preferred:" + req.isUserInRole(Roles.PREFERRED));
            System.out.println("Standard:" + req.isUserInRole(Roles.STANDARD));
            System.out.println("Command:" + com.getClass());
            
            String view = com.process(req, res);
            
            System.out.println("view:" + view);
            System.out.println("</DEBUG>");
            
            RequestDispatcher rd = getServletContext().getRequestDispatcher(view);
            rd.forward(req, res);
            
        }catch(Throwable th) {
            throw new ServletException(th);
        }
        
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        process(req, res);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        process(req, res);
    }
    
}
