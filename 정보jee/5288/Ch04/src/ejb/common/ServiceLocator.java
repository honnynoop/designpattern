package common;

import java.sql.*;
import javax.sql.DataSource;
import java.util.Hashtable;
import javax.naming.*;
import javax.ejb.*;
import javax.rmi.PortableRemoteObject;
import common.counter.CounterHome;
import entity.booking.BookingLocalHome;
import entity.city.CityLocalHome;
import entity.flight.FlightLocalHome;
import entity.customer.CustomerLocalHome;
import entity.hotel.HotelLocalHome;
import javax.jms.*;
import facade.customer.*;
import facade.citybreak.*;
import facade.bookingmsg.*;

/*
 *  The ServiceLocator pattern is abstracts away the JNDI
 *  logic necessary for retrieving a JDBC Connection or EJBHome 
 *  interface
 */
public class ServiceLocator{
  private static ServiceLocator serviceLocatorRef			= null;
  private static Hashtable      ejbHomeCache				= null;
  private static Hashtable      dataSourceCache				= null;
  private static Hashtable		queueConnectionFactoryCache	= null;
  private static Hashtable		queueCache					= null;
  private static Hashtable		ejbLocalHomeCache			= null;

  /*Enumerating the different services available from the Service Locator*/
  public static final int COUNTER		    = 0;
  public static final int BOOKING			= 1;
  public static final int CITY				= 2;
  public static final int FLIGHT			= 3;
  public static final int CUSTOMER			= 4;
  public static final int HOTEL				= 5;
  public static final int COUNTERDB			= 6;
  public static final int CITYDB			= 7;
  public static final int BOOKING_COMPLETE	= 8;
  public static final int NEW_BOOKINGS		= 9;
  public static final int QUEUECONNFACTORY	= 10; 
  public static final int CUSTOMERFACADE	= 11;
  public static final int CITYBREAKFACADE	= 12;

  /*The JNDI Names used to lookup a service*/
  private static final String COUNTER_JNDINAME="ejb/Counter";
  private static final String BOOKING_JNDINAME="java:comp/env/ejb/Booking";
  private static final String CITY_JNDINAME="java:comp/env/ejb/City"; 
  private static final String FLIGHT_JNDINAME="java:comp/env/ejb/Flight";
  private static final String CUSTOMER_JNDINAME="java:comp/env/ejb/Customer";
  private static final String HOTEL_JNDINAME="java:comp/env/ejb/Hotel";
  private static final String COUNTERDB_JNDINAME="jdbc/mysqlcity";
  private static final String CITYDB_JNDINAME="jdbc/mysqlcity";
  private static final String QUEUECONNFACTORY_JNDINAME = "java:comp/env/jms/QueueConnectionFactory";
  private static final String NEW_BOOKINGS_JNDINAME = "jms/newBookingQ";
  private static final String BOOKING_COMPLETE_JNDINAME = "java:comp/env/jms/bookingCompleteQ";
  private static final String CUSTOMERFACADE_JNDINAME = "ejb/CustomerFacade";
  private static final String CITYBREAKFACADE_JNDINAME = "ejb/CityBreakFacade";

  /*References to each of the different EJB Home Interfaces*/
  private static final Class COUNTERCLASSREF			= CounterHome.class;
  private static final Class BOOKINGCLASSREF			= BookingLocalHome.class;
  private static final Class CITYCLASSREF				= CityLocalHome.class;
  private static final Class FLIGHTCLASSREF				= FlightLocalHome.class;
  private static final Class CUSTOMERCLASSREF			= CustomerLocalHome.class;
  private static final Class HOTELCLASSREF				= HotelLocalHome.class;
  private static final Class CUSTOMERFACADECLASSREF		= CustomerFacadeHome.class;
  private static final Class CITYBREAKFACADECLASSREF	= CityBreakFacadeHome.class;

  static {
	serviceLocatorRef = new ServiceLocator();
  }

  /*Constructor for the ServiceLocator*/
  public ServiceLocator(){
	ejbHomeCache				= new Hashtable();
	ejbLocalHomeCache			= new Hashtable();
    dataSourceCache				= new Hashtable();
	queueConnectionFactoryCache	= new Hashtable();
	queueCache					= new Hashtable();
  }

  /*
   * The ServiceLocator is implemented as a Singleton.  The getInstance()
   * method will return the static reference to the ServiceLocator stored
   * inside of the ServiceLocator Class.
   */
  public static ServiceLocator getInstance(){
    if (serviceLocatorRef == null) {
	    serviceLocatorRef = new ServiceLocator();	
    }
	return serviceLocatorRef;
  }

  /*
   * The getServiceName will retrieve the JNDI name for a requested
   * service.  The service is indicated by the ServiceId passed into
   * the method.
   */
  static private String getServiceName(int pServiceId)
    throws ServiceLocatorException{
    String serviceName = null;

    switch (pServiceId){
      case COUNTER:
			serviceName = COUNTER_JNDINAME;
	        break; 
      case BOOKING:
			serviceName = BOOKING_JNDINAME;
			break;
      case CITY:
			serviceName = CITY_JNDINAME;
			break;
      case FLIGHT:
			serviceName = FLIGHT_JNDINAME;
			break;
      case CUSTOMER:
			serviceName = CUSTOMER_JNDINAME;
			break;
      case HOTEL:
			serviceName = HOTEL_JNDINAME;
			break;
      case COUNTERDB:   
			serviceName = COUNTERDB_JNDINAME;
			break;
	  case CITYDB:
			serviceName = CITYDB_JNDINAME;
			break;
	  case BOOKING_COMPLETE:
			serviceName = BOOKING_COMPLETE_JNDINAME;
			break;
	  case NEW_BOOKINGS:
			serviceName = NEW_BOOKINGS_JNDINAME;
			break;
	  case QUEUECONNFACTORY:
			serviceName = QUEUECONNFACTORY_JNDINAME;
			break;
	  case CUSTOMERFACADE:
			serviceName = CUSTOMERFACADE_JNDINAME;
			break;
	  case CITYBREAKFACADE:
			serviceName = CITYBREAKFACADE_JNDINAME;
			break;
	  default:
			throw new ServiceLocatorException("Unable to locate the service requested in " +
							  			      "ServiceLocator.getServiceName() method.  ");
    }

    return serviceName;
  }

  /*
   * Returns the EJBHome Class reference for a requested service.
   * If the method can not make a match, it will throw a ServiceLocatorException
   * .
   */
  static private Class getEJBHomeRef(int pServiceId) 
    throws ServiceLocatorException{
    Class homeRef = null;

    switch (pServiceId){
      case COUNTER:  
			homeRef = COUNTERCLASSREF;
	        break; 
      case BOOKING: 
			homeRef = BOOKINGCLASSREF;
			break;
      case CITY:
			homeRef = CITYCLASSREF;
			break;
      case FLIGHT:
			homeRef = FLIGHTCLASSREF;
			break;
      case CUSTOMER:
			homeRef = CUSTOMERCLASSREF;
			break;
      case HOTEL:
			homeRef = HOTELCLASSREF;
			break;
	  case CUSTOMERFACADE:
			homeRef = CUSTOMERFACADECLASSREF;
			break;
	  case CITYBREAKFACADE:			
			homeRef = CITYBREAKFACADECLASSREF;
			break;

      default: 
			throw new ServiceLocatorException("Unable to locate the service requested in " +
										      "ServiceLocator.getEJBHomeRef() method.  ");
    }

    return homeRef;
  }

  /*
   * The getEJBHome method will return an EJBHome interface for a requested
   * service.  If it can not find the requested EJB, it will throw a
   * servicelocator exception.
   *
   * The getEJBHome interface caches a requested EJBHome so that the first 
   * time an EJB is requested, a home interface will be retrieved but then
   * be placed into a cache.
   *
   */
  public EJBHome getEJBHome(int pServiceId)
    throws ServiceLocatorException{
    /*Trying to find the JNDI Name for the requested service*/
    String serviceName = getServiceName(pServiceId);
    EJBHome ejbHome    = null;

    try{
      /*Checking to see if I can find the EJBHome interface in cache*/
      if (ejbHomeCache.containsKey(serviceName)){
		ejbHome = (EJBHome) ejbHomeCache.get(serviceName);
		return ejbHome;
      }
      else{
        /*
	 * If I could not find the EJBHome interface in the cache, look it
	 * up and then cache it.
	 * */
		Context ctx = new InitialContext();
        Object jndiRef     = ctx.lookup(serviceName);
        Object portableObj = 
          PortableRemoteObject.narrow(jndiRef, getEJBHomeRef(pServiceId));
        ejbHome = (EJBHome) portableObj;
        ejbHomeCache.put(serviceName, ejbHome);
		return ejbHome;
      }
    }
    catch(NamingException e){
      throw new ServiceLocatorException("Naming exception error in ServiceLocator.getEJBHome()" + e);
    }
    catch(Exception e){
      throw new ServiceLocatorException("General exception in ServiceLocator.getEJBHome" + e);
    }
  }

  public EJBLocalHome getEJBLocalHome(int pServiceId)
    throws ServiceLocatorException{
    /*Trying to find the JNDI Name for the requested service*/
    String serviceName = getServiceName(pServiceId);
    EJBLocalHome ejbLocalHome    = null;

    try{
      /*Checking to see if I can find the EJBLocalHome interface in cache*/
      if (ejbLocalHomeCache.containsKey(serviceName)){
		ejbLocalHome = (EJBLocalHome) ejbLocalHomeCache.get(serviceName);
		return ejbLocalHome;
      }
      else{
        /*
	 * If I could not find the EJBLocalHome interface in the cache, look it
	 * up and then cache it.
	 * */
		Context ctx = new InitialContext();
        Object jndiRef     = ctx.lookup(serviceName);
        ejbLocalHome = (EJBLocalHome) jndiRef;
        ejbLocalHomeCache.put(serviceName, ejbLocalHome);
		return ejbLocalHome;
      }
    }
    catch(NamingException e){
      throw new ServiceLocatorException("Naming exception error in ServiceLocator.getEJBLocalHome()" + e);
    }
    catch(Exception e){
      throw new ServiceLocatorException("General exception in ServiceLocator.getEJBLocalHome" + e);
    }
  }


  /*
   * The getDBConn() method will create a JDBC connection for the
   * requested database.  It too uses a cachin algorithm to minimize
   * the number of JNDI hits that it must perform.
   */
  public java.sql.Connection getDBConn(int pServiceId)
    throws ServiceLocatorException{
    /*Getting the JNDI Service Name*/
    String     serviceName = getServiceName(pServiceId);
    java.sql.Connection conn        = null;

    try{
      /*Checking to see if the requested DataSource is in the Cache*/ 
      if (dataSourceCache.containsKey(serviceName)){
	DataSource ds = (DataSource) dataSourceCache.get(serviceName);

	conn = ((DataSource)ds).getConnection();
	return conn;
      }
      else{
	/*
	 * The DataSource was not in the cache.  Retrieve it from JNDI
	 * and put it in the cache.
	 */
	Context ctx = new InitialContext();
        DataSource newDataSource = (DataSource) ctx.lookup(serviceName);

	dataSourceCache.put(serviceName, newDataSource);

	conn = newDataSource.getConnection();
	return conn;
      }
    }
    catch(SQLException e){
      throw new ServiceLocatorException("A SQL error has occurred in ServiceLocator.getDBConn()" + e);
    }
    catch(NamingException e){
      throw new ServiceLocatorException("A JNDI Naming exception has occurred in ServiceLocator.getDBConn()" + e);
    }
    catch(Exception e){
      throw new ServiceLocatorException("An exception has occurred in ServiceLocator.getDBConn()" + e);
    }
  }

  public QueueConnection getJMSQueueConn(int pServiceId) throws ServiceLocatorException {
	
	//Getting JNDI Service Name
	String serviceName = getServiceName(pServiceId);
	QueueConnection qc = null;

	//Check to see if requested service is in cache
	try	{
		if (queueConnectionFactoryCache.containsKey(serviceName)) {
			QueueConnectionFactory qcf = (QueueConnectionFactory) queueConnectionFactoryCache.get(serviceName);
			qc = qcf.createQueueConnection();
		} else {
			Context ctx = new InitialContext();
			QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup(serviceName);
			queueConnectionFactoryCache.put(serviceName, qcf);
			qc = qcf.createQueueConnection();
		}
    
	} catch(JMSException e){
		throw new ServiceLocatorException("A JMS exception has occurred in ServiceLocator.getJMSQueueConn()" + e);
    } catch(NamingException e){
		throw new ServiceLocatorException("A JNDI Naming exception has occurred in ServiceLocator.getJMSQueueConn()" + e);
    } catch(Exception e){
		 throw new ServiceLocatorException("An exception has occurred in ServiceLocator.getJMSQueueConn()" + e);
    }
	
	return qc;

  }

  public Queue getJMSQueue(int pServiceId) throws ServiceLocatorException {

	String serviceName = getServiceName(pServiceId);
	Queue newQ = null;

	try	{
		if (queueCache.containsKey(serviceName)) {
			newQ = (Queue) queueCache.get(serviceName);
		} else {
			Context ctx = new InitialContext();
			newQ = (Queue) ctx.lookup(serviceName);
		}
    } catch(NamingException e){
		throw new ServiceLocatorException("A JNDI Naming exception has occurred in ServiceLocator.getJMSQueue()" + e);
    } catch(Exception e){
      throw new ServiceLocatorException("An exception has occurred in ServiceLocator.getJMSQueue()" + e);
    }

	return newQ;
  }

}
