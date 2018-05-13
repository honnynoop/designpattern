/*
 * StandardCommand.java
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
public class BalanceCommand implements Command {
    
    private static interface Views {
        
        public static final String BALANCE = "/WEB-INF/Balance.jsp";
        public static final String ERROR = "/WEB-INF/Error.jsp";
        
    }
    
    public String process(HttpServletRequest req, HttpServletResponse res) {
        
        try {
            
            AccountDelegate delegate = new AccountDelegate();
            req.setAttribute(BeanNames.BALANCE, delegate.getBalance());            
            return Views.BALANCE;
            
        }catch(AccountException ex) {
            
            System.out.println(ex.getMessage());
            req.setAttribute(BeanNames.ERROR, ex.getMessage());
            return Views.ERROR;
            
        }
        
    }
    
    
}
