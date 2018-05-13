/*
 * PreferredCommand.java
 *
 * Created on 27 April 2002, 10:43
 */

package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.AccountException;

/**
 *
 * @author  pznwc5
 */
public class StatementCommand implements Command {
    
    private static interface Views {
        
        public static final String STATEMENT = "/WEB-INF/Statement.jsp";
        public static final String ERROR = "/WEB-INF/Error.jsp";
        
    }
    
    public String process(HttpServletRequest req, HttpServletResponse res) {
        
        try {
            
            AccountDelegate delegate = new AccountDelegate();
            req.setAttribute(BeanNames.STATEMENT, delegate.getStatement());            
            return Views.STATEMENT;
            
        }catch(AccountException ex) {
            
            req.setAttribute(BeanNames.ERROR, ex.getMessage());
            return Views.ERROR;
            
        }
    
    }
    
}
