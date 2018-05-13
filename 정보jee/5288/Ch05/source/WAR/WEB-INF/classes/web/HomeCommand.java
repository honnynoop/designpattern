/*
 * LoginAction.java
 *
 * Created on 27 April 2002, 11:18
 */

package web;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  pznwc5
 */
public class HomeCommand implements Command {
    
    private static interface Views {
        
        public static final String STATEMENT = "/statement.do";
        public static final String BALANCE = "/balance.do";
        
    }
    
    public String process(HttpServletRequest req, HttpServletResponse res) {
        
        if(req.isUserInRole(Roles.PREFERRED)) return Views.STATEMENT;
        else if(req.isUserInRole(Roles.STANDARD)) return Views.BALANCE;
        else throw new IllegalArgumentException("Unexpected Role.");
        
    }
    
    
}
