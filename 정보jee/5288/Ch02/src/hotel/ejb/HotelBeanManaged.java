package hotel.ejb;

import sql.SqlFactory;
import hotel.util.HotelDetails;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/** The implementation for the hotel EJB
*/

public class HotelBeanManaged extends HotelEJB {

    static final boolean VERBOSE = true;

    static  {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        }
        catch(Exception _ex) {
            System.out.println("org.gjt.mm.mysql.Driver is not found!!!!");
        }
    }

  /** Loads the ejb from the persistent store.
  */
    public void ejbLoad()
        throws RemoteException {
        System.out.println("ejbLoad: (" + super.id + ")");
        try {
            refresh((String)super.ectx.getPrimaryKey());
            return;
        }
        catch(FinderException finderexception) {
            throw new RemoteException(finderexception.getMessage());
        }
    }

  /** The EJB finder method.
  */
    public String ejbFindByPrimaryKey(String s)
        throws ObjectNotFoundException {
        try {
            refresh(s);
        }
        catch(Exception exception) {
            throw new ObjectNotFoundException(exception.toString());
        }
        return s;
    }

  /** Reloads the EJB from the persistent store. 
  */
    private void refresh(String s)
        throws FinderException, RemoteException {
        if(s == null)
            throw new RemoteException("primary key cannot be null");
        super.id = s;
        System.out.println("About to refresh (" + super.id + ")");
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            System.out.println("About to get Connection");
            connection = getConnection();
            System.out.println("got Connection");
            preparedstatement = connection.prepareStatement("SELECT id, name, type,chain,purpose,gym,regionOrTown,swimmingPool,conferenceRooms FROM hotel WHERE id=?");
            System.out.println("created prepared statement");
            preparedstatement.setString(1, s);
            preparedstatement.executeQuery();
            ResultSet resultset = preparedstatement.getResultSet();
            Object obj = null;
            if(resultset.next()) {
                 try {
                obj = resultset.getObject("name");
                if(obj instanceof String)
                    super.name = (String)obj;
                } catch( Exception e ) {}
                try {
                obj = resultset.getObject("type");
                if(obj instanceof String)
                    super.type = (String)obj;
                } catch( Exception e ) {}
                try {
                obj = resultset.getObject("chain");
                if(obj instanceof String)
                    super.chain = (String)obj;
                } catch( Exception e ) {}
                try {
                obj = resultset.getObject("purpose");
                if(obj instanceof String)
                    super.purpose = (String)obj;
                } catch( Exception e ) {}
                try {
                obj = resultset.getObject("gym");
                if(obj instanceof BigDecimal)
                    super.gym = (BigDecimal)obj;
                } catch( Exception e ) {}
                obj = resultset.getObject("regionOrTown");
                if(obj instanceof String)
                    super.regionOrTown = (String)obj;
                try {
                obj = resultset.getObject("swimmingPool");
                if(obj instanceof BigDecimal)
                    super.swimmingPool = (BigDecimal)obj;
                } catch( Exception e ) {}
                try {
                obj = resultset.getObject("conferenceRooms");  
                if(obj instanceof BigDecimal)
                    super.conferenceRooms = (BigDecimal)obj;
                } catch( Exception e ) {}
                System.out.println("Refreshed (" + super.id + ")");
            } else {
                throw new FinderException("Refresh: HotelEJBImp (" + s + ") not found");
            }
        }
        catch(SQLException sqlexception) {
            throw new RemoteException(sqlexception.getMessage());
        }
        finally {
            cleanup(connection, preparedstatement);
        }
    }

  /** Stores the EJB details in the persistent store.
  */
    public void ejbStore()
        throws RemoteException {
        System.out.println("ejbStore (" + super.id + ")");
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = getConnection();
            preparedstatement = connection.prepareStatement("UPDATE hotel SET name=?,type=?,chain=?,purpose=?,gym=?,regionOrTown=?,swimmingPool=?,conferenceRooms=? WHERE id=?");
            if(super.name == null)
                preparedstatement.setNull(1, 12);
            else
                preparedstatement.setString(1, super.name);
            if(super.type == null)
                preparedstatement.setNull(2, 12);
            else
                preparedstatement.setString(2, super.type);
            if(super.chain == null)
                preparedstatement.setNull(3, 12);
            else
                preparedstatement.setString(3, super.chain);
            if(super.purpose == null)
                preparedstatement.setNull(4, 12);
            else
                preparedstatement.setString(4, super.purpose);
            if(super.gym == null)
                preparedstatement.setNull(5, 2);
            else
                preparedstatement.setObject(5, super.gym);
            if(super.regionOrTown == null)
                preparedstatement.setNull(6, 12);
            else
                preparedstatement.setString(6, super.regionOrTown);
            if(super.swimmingPool == null)
                preparedstatement.setNull(7, 2);
            else
                preparedstatement.setObject(7, super.swimmingPool);
            if(super.conferenceRooms == null)
                preparedstatement.setNull(8, 2);
            else
                preparedstatement.setObject(8, super.conferenceRooms);
            preparedstatement.setString(9, super.id);
            int i = preparedstatement.executeUpdate();
            if(i == 0)
                throw new RemoteException("HotelEJBImpl (" + super.id + ") not updated");
        }
        catch(RemoteException remoteexception) {
            throw remoteexception;
        }
        catch(SQLException sqlexception) {
            throw new RemoteException(sqlexception.getMessage());
        }
        finally {
            cleanup(connection, preparedstatement);
        }
    }

  /** Removes the EJB from the persistent store. 
  */
    public void ejbRemove()
        throws RemoveException, RemoteException {
        System.out.println("ejbRemove (" + super.id + ")");
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = getConnection();
            String s = (String)super.ectx.getPrimaryKey();
            preparedstatement = connection.prepareStatement("delete from hotel where id = ?");
            preparedstatement.setString(1, s);
            int i = preparedstatement.executeUpdate();
            if(i == 0)
                throw new RemoteException("HotelEJBImpl (" + s + " not found");
        }
        catch(RemoteException remoteexception) {
            throw remoteexception;
        }
        catch(SQLException sqlexception) {
            throw new RemoteException(sqlexception.getMessage());
        }
        finally {
            cleanup(connection, preparedstatement);
        }
    }

  /** Creates the EJB in the persistent store.
  */
    public String ejbCreate(String s, HotelDetails hoteldetails)
        throws CreateException, RemoteException {
        super.ejbCreate(s, hoteldetails);
        System.out.println("created " + toString());
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        try {
            connection = getConnection();
            StringBuffer stringbuffer = new StringBuffer("INSERT INTO hotel (");
            StringBuffer stringbuffer1 = new StringBuffer(" VALUES (");
            stringbuffer.append("id");
            stringbuffer.append(",");
            stringbuffer1.append("?,");
            if(super.name != null) {
                stringbuffer.append("name");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.type != null) {
                stringbuffer.append("type");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.chain != null) {
                stringbuffer.append("chain");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.purpose != null) {
                stringbuffer.append("purpose");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.gym != null) {
                stringbuffer.append("gym");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.regionOrTown != null) {
                stringbuffer.append("regionOrTown");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.swimmingPool != null) {
                stringbuffer.append("swimmingPool");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            if(super.conferenceRooms != null) {
                stringbuffer.append("conferenceRooms");
                stringbuffer.append(",");
                stringbuffer1.append("?,");
            }
            stringbuffer.setCharAt(stringbuffer.length() - 1, ')');
            stringbuffer1.setCharAt(stringbuffer1.length() - 1, ')');
            preparedstatement = connection.prepareStatement(stringbuffer.toString() + stringbuffer1);
            int i = 0;
            preparedstatement.setString(++i, s);
            if(super.name != null)
                preparedstatement.setString(++i, super.name);
            if(super.type != null)
                preparedstatement.setString(++i, super.type);
            if(super.chain != null)
                preparedstatement.setString(++i, super.chain);
            if(super.purpose != null)
                preparedstatement.setString(++i, super.purpose);
            if(super.gym != null)
                preparedstatement.setObject(++i, super.gym);
            if(super.regionOrTown != null)
                preparedstatement.setString(++i, super.regionOrTown);
            if(super.swimmingPool != null)
                preparedstatement.setObject(++i, super.swimmingPool);
            if(super.conferenceRooms != null)
                preparedstatement.setObject(++i, super.conferenceRooms);
            if(preparedstatement.executeUpdate() != 1)
                throw new CreateException("JDBC did not create any row");
            String s1 = s;
            return s1;
        }
        catch(CreateException createexception) {
            throw createexception;
        }
        catch(SQLException sqlexception) {
            throw new CreateException(sqlexception.getMessage());
        }
        finally {
            cleanup(connection, preparedstatement);
        }
    }

  /** EJB finder method for a dynamic set of properties.
  */
    public Enumeration ejbFindByCriteria(Properties properties)
        throws FinderException, RemoteException {
        System.out.println("findByCriteria " + properties);
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        Vector vector = new Vector();
        try {
            connection = getConnection();
            preparedstatement = connection.prepareStatement(SqlFactory.getSelectQuery("hotel", "id", properties));
            int i = 1;
            for(Enumeration enumeration1 = SqlFactory.getCriteriaValues(properties); enumeration1.hasMoreElements();) {
                preparedstatement.setObject(i, enumeration1.nextElement());
                i++;
            }

            for(resultset = preparedstatement.executeQuery(); resultset.next();) {
                Object obj = resultset.getObject(1);
                if(obj instanceof String)
                    vector.addElement((String)obj);
            }

            resultset.close();
            Enumeration enumeration = vector.elements();
            return enumeration;
        }
        catch(SQLException sqlexception) {
            throw new FinderException(sqlexception.getMessage());
        }
        finally {
            cleanup(connection, preparedstatement, resultset);
        }
    }

  /** Returns a new connection from the pool.
  */
    private Connection getConnection()
        throws SQLException {
        InitialContext initialcontext = null;
        try {
            initialcontext = new InitialContext();
            DataSource datasource = (DataSource)initialcontext.lookup("java:comp/env/jdbc/hotelPool");
            Connection connection = datasource.getConnection();
            return connection;
        }
        catch(NamingException namingexception) {
            log("UNABLE to get a connection from hotelPool!");
            log("Please make sure that you have setup the connection pool properly");
            throw new EJBException(namingexception);
        }
        finally {
            try {
                if(initialcontext != null)
                    initialcontext.close();
            }
            catch(NamingException namingexception1) {
                log("Error closing context: " + namingexception1);
                throw new EJBException(namingexception1);
            }
        }
    }

  /** Logs a message 
  */
    private void log(String s) {
        System.out.println(s);
    }

  /** Closes open conncetions, prepared statements and result sets.
  */
    private void cleanup(Connection connection, PreparedStatement preparedstatement) {
        cleanup(connection, preparedstatement, null);
    }

  /** Closes open conncetions, prepared statements and result sets.
  */
    private void cleanup(Connection connection, PreparedStatement preparedstatement, ResultSet resultset) {
        try {
            if(preparedstatement != null)
                preparedstatement.close();
        }
        catch(Exception exception) {
            log("Error closing PreparedStatement: " + exception);
            throw new EJBException(exception);
        }
        try {
            if(connection != null)
                connection.close();
        }
        catch(Exception exception1) {
            log("Error closing Connection: " + exception1);
            throw new EJBException(exception1);
        }
        try {
            if(resultset != null) {
                resultset.close();
                return;
            }
        }
        catch(Exception exception2) {
            log("Error closing ResultSet: " + exception2);
            throw new EJBException(exception2);
        }
    }
}