/*
 * Command.java
 *
 * Created on 27 April 2002, 10:42
 */

package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  pznwc5
 */
public interface Command {
    
    public String process(HttpServletRequest req, HttpServletResponse res);
    
}
