package dataaccess;

import java.sql.*;
import javax.sql.DataSource;
import java.util.Hashtable;
import javax.naming.*;
import javax.ejb.*;
import javax.rmi.PortableRemoteObject;
import session.counter.CounterHome;
import session.title.TitleDAOHome;
import session.editor.EditorDAOHome;

/*
 *  The ServiceLocator pattern is abstracts away the JNDI
 *  logic necessary for retrieving a JDBC Connection or EJBHome 
 *  interface
 */
public class ServiceLocator{
  private static ServiceLocator serviceLocatorRef = null;
  private static Hashtable      ejbHomeCache      = null;
  private static Hashtable      dataSourceCache   = null;

  /*Enumerating the different services available from the Service Locator*/
  public static final int COUNTER        = 0;
  public static final int TITLEDAO       = 1;
  public static final int EDITORDAO      = 2;
  public static final int SUBSCRIPTIONDB = 3;

  /*The JNDI Names used to lookup a service*/
  private static final String COUNTER_JNDINAME="counter/Counter";
  private static final String TITLEDAO_JNDINAME="title/TitleDAO";
  private static final String EDITORDAO_JNDINAME="editor/EditorDAO";
  private static final String SUBSCRIPTIONDB_JNDINAME="jdbc/subscriptionDS";

  /*References to each of the different EJB Home Interfaces*/
  private static final Class COUNTERCLASSREF = CounterHome.class;
  private static final Class TITLECLASSREF   = TitleDAOHome.class;
  private static final Class EDITORCLASSREF  = EditorDAOHome.class;

  static {
    serviceLocatorRef = new ServiceLocator();
  }

  /*Private Constructor for the ServiceLocator*/
  private ServiceLocator(){
    ejbHomeCache    = new Hashtable();
    dataSourceCache = new Hashtable();
  }

  /*
   * The ServiceLocator is implemented as a Singleton.  The getInstance()
   * method will return the static reference to the ServiceLocator stored
   * inside of the ServiceLocator Class.
   */
  public static ServiceLocator getInstance(){
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
      case COUNTER:          serviceName = COUNTER_JNDINAME;
	                     break; 
      case TITLEDAO:         serviceName = TITLEDAO_JNDINAME;
			     break;
      case EDITORDAO:        serviceName = EDITORDAO_JNDINAME;
			     break;
      case SUBSCRIPTIONDB:   serviceName = SUBSCRIPTIONDB_JNDINAME;
			     break;
      default:               throw new ServiceLocatorException(
			     "Unable to locate the service requested in " +
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
      case COUNTER:          homeRef = COUNTERCLASSREF;
	                     break; 
      case TITLEDAO:         homeRef = TITLECLASSREF;
			     break;
      case EDITORDAO:        homeRef = EDITORCLASSREF;
			     break;
      default:               throw new ServiceLocatorException(
			     "Unable to locate the service requested in " +
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
      throw new ServiceLocatorException("Naming exception error in ServiceLocator.getEJBHome()" ,e);
    }
    catch(Exception e){
      throw new ServiceLocatorException("General exception in ServiceLocator.getEJBHome",e);
    }
  
  }

  /*
   * The getDBConn() method will create a JDBC connection for the
   * requested database.  It too uses a cachin algorithm to minimize
   * the number of JNDI hits that it must perform.
   */
  public Connection getDBConn(int pServiceId)
    throws ServiceLocatorException{
     /*Getting the JNDI Service Name*/
    String     serviceName = getServiceName(pServiceId);
     Connection conn        = null;
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
      throw new ServiceLocatorException("A SQL error has occurred in " +
		                        "ServiceLocator.getDBConn()", e);
    }
    catch(NamingException e){
      throw new ServiceLocatorException("A JNDI Naming exception has occurred "+ 
                         	  " in ServiceLocator.getDBConn()" , e);
    }
    catch(Exception e){
	throw new ServiceLocatorException("An exception has occurred "+
	                        " in ServiceLocator.getDBConn()"  ,e);
    }
  }
}