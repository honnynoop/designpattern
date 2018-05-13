/*
 * CommandFactory.java
 *
 * Created on 27 April 2002, 11:44
 */

package web;

/**
 *
 * @author  pznwc5
 */
public class CommandFactory {
    
    public static Command getCommand(String path) {
        
        if(path.equals("/home.do")) return new HomeCommand();
        else if(path.equals("/balance.do")) return new BalanceCommand();
        else if(path.equals("/statement.do")) return new StatementCommand();
        else throw new IllegalArgumentException("Invalid path:" + path);
        
    }
    
}
