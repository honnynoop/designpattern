package hotel.util;

import java.util.Properties;
import java.math.BigDecimal;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestHelper {

    public HttpServletRequest getRequest();
    public HttpServletResponse getResponse();
    public Command getCommand();
    public Properties getProperties();       
}
