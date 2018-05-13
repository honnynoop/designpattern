Setup instructions
--------------------

The entire application code was written on a Windows 2000 machine with the J2EE Reference Implemenation 
as the Application Server.

Setting up MySQL
----------------
1.  Download the MySql-Max from http://mysql.org/downloads/mysql-max-3.23.html.
2.  Install the MySQL-Max database by following the instructions that come with MySQL.
    Note:  When you start MySQL-Max you need to start it with the berkley table db 
    support.  If you do not start it with this feature enabled, you will not be able
    to use transactional support in MySQL.  To enable the BerkleyDB support start MySQL
    with:
    
3.  Download the MySQL-Max JDBC drivers from 
    http://www.mysql.com/downloads/download.php?file=Downloads/Contrib/mm.mysql-2.0.4-bin.jar.
4.  The code for the database runs under the test database that comes setup with MySQL.
    You will need to create and grant a user account that JBoss can use to connect to
    the MySQL server.  This is done by connectingin to MySQL:

c:\mysql\bin>  mysql test
       mysql>  grant all on test.* to yourusername@youripaddress identified by "password"
       mysql> grant all on test.* to yourusername@localhost identified by "password" 

  Remember, password has to be in double quotes 

      -  Log into the MySQL account by issuing the following command:
           mysql -u yourusername -p (You will be prompted for a password).
5.  Once you have logged into the database, you should run the two SQL scripts located
    in the /sql directory by issuing the following commands:

      -  source counter.sql
      -  source editor.sql

Setup and Configuration Settings for Reference Implementation
-------------------------------------------------------------
1. To be able to build and deploy the classes you shall need to download and install the J2EE Reference Implementation application server which 
   is available for downloads from the Sun Java site at http://java.sun.com/j2ee/download.html.
2. To setup the connection pooling of Reference Implemenation for MySQL, start the deploytool, and go to 
   Tools | Server Configuration. You will get a window which has a number of configuration setting options.
   To do the connection pooling, you will have to go to Data Sources (one of the options on the window), and click
   on Standards.
3. Add the MySQL Driver Class to the JDBC driver class window. Click Add and enter the following:
   org.gjt.mm.mysql.Driver.
4. To add MySQL to the Data Sources, we need to provide a JNDI lookup name and the url for the datasource.
   Add the following at the for the JNDI name and JDBC url for the Data Source.
   
   JNDI NAME: jdbc/SubscriptionDS
   JDBC URL : jdbc:mysql://localhost:3306/test

5) Finally, we need to include the MySQL dirver classes to the Classpath of J2EE. Go to the bin directory of the
   installation directory of your J2EE RI server (usually at C:\j2sdkee1.3\bin ). Edit the userconfig.bat 
   file and include the mysql driver classes by uncommenting the line which reads as follows. This is done by
   adding the path to the mysql JDBC jar files.
  
   For Example:
   set J2EE_CLASSPATH=c:\mm.mysql-2.0.4-bin.jar;
  
   This is it as far as the configuration setting of the application server with MySQL is concerned.
  
Application Setup
-------------------

1.  There is a persistence.properties file in the /config directory that
    contains all of the JNDI settings need to get a Context from wrox.  This file
    is used by the PersistenceConstants class.  You should not have to change this file.

2.  There is one file in all of the source that has a hard-coded path in it.  This file is
    /common/PersistenceConstants.java file.  Please change the path in
    this file to point to the directory where your have the persistence.properties file located.

3.  At this point you should be able compile and deploy all of the pieces 
    of this application.  There are three JSP pages used in this application:

       badExample.jsp - Shows how not get access to the MySQL database
       selectExample.jsp - Shows how to use the TitleDAO to retrieve data.
       insertExample.jsp - Shows how to use the TitleDAO and Editor DAO to retrieve records


				   


	   




 
