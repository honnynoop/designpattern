package common;

import java.io.*;
import java.util.*;

/*
 * The PersistenceConstants class is a wrapper class that provides
 * helper methods for accessing various property elements defined in
 * the persistece.properties file.
 */
public class PersistenceConstants {

	private static String persistenceFileName = 
	  "c:\\5288\\Ch03\\config\\persistence.properties";
        private static String javaNamingFactory    = null;
	private static String javaNamingProvider   = null;
	private static String subscriptionJndiConn = null;
	
	public static String getJavaNamingFactory() {
	  return javaNamingFactory;
	}

	public static String getJavaNamingProvider(){
	  return javaNamingProvider;
	}

	public static String getSubscriptionJndiConn(){
          return subscriptionJndiConn;
	}

	static {
	  Properties persistenceProperties = new Properties();

	  try {
	    persistenceProperties.load(new FileInputStream(persistenceFileName)); 

	    javaNamingFactory     =
	      persistenceProperties.getProperty("java.naming.factory.initial");

	    javaNamingProvider    = 
	      persistenceProperties.getProperty("java.naming.provider.url"); 

	    subscriptionJndiConn  = 
	      persistenceProperties.getProperty("subscription.jndi.conn"); 

	  } catch (Exception e) {
	     System.out.println("Unable to load the persistence.properties " +
		                "files " + e.getMessage());
          }	
	}
}
