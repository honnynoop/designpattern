package dataaccess;

import common.PersistenceConstants;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.*;
import javax.sql.DataSource;

// Referenced classes of package dataaccess:
//            DataAccessException

public class DataSourceFactory
{

    public DataSourceFactory()
    {
    }

    public static Connection getSubscriptionConn()
        throws DataAccessException
    {
        return getConnection(PersistenceConstants.getSubscriptionJndiConn());
    }

    public static Context getDefaultContext()
        throws DataAccessException
    {
        Properties containerEnv = new Properties();
        containerEnv.setProperty("java.naming.factory.initial", PersistenceConstants.getJavaNamingFactory());
        containerEnv.setProperty("java.naming.provider.url", PersistenceConstants.getJavaNamingProvider());
          InitialContext ctx = null;
        try
        {
            ctx = new InitialContext(containerEnv);
        }
        catch(NamingException e)
        {
            System.out.println("Error in DataSourceFactory.getDefaultContext(): " + e.getMessage());
            throw new DataAccessException(e.getMessage());
        }
        return ctx;
    }

    private static Connection getConnection(String pJndi)
        throws DataAccessException
    {
        return getConnection(pJndi, getDefaultContext());
    }

    private static Connection getConnection(String pJndi, Context pCtx)
        throws DataAccessException
    {
        Connection conn = null;
        try
        {
            DataSource ds = (DataSource)pCtx.lookup(pJndi);
            conn = ds.getConnection();
        }
        catch(SQLException e)
        {
            System.out.println("Error in DataSourceFactory.getConnection(String, IntialContext) " + e.getMessage());
            throw new DataAccessException(e.getMessage());
        }
        catch(NamingException e)
        {
            System.out.println("Error in DataSourceFactory.getConnection(String, IntialContext) " + e.getMessage());
            throw new DataAccessException(e.getMessage());
        }
        return conn;
    }
}