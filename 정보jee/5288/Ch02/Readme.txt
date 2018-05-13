Deployment Information for Hotel Case Study in Chapter 2

Setting up the Hotel Connection Pool (instructions for the J2EE Reference implementation. For other servers consult your documentation on how to setup connection pools). In this case we are using a MySQL database:

1.	To be able to connect to the MySQL database, we need to setup a connection pool for the hotel application. 
        This can be done by using the deploytool of the j2ee RI. The deploytool is started by typing the 
	command "deploytool" in the bin directory of J2EE home.

2.	In the deployment tool , select the Server Configuration ment option from the Tools menu. This will 
	open the Configure Installation window that allows us to configure various resource factories amd 
	secirity settings for the server.

3.	Select the Standard Data Sources node in the left hand pane to bring up a list of the installed JDBC 
	drivers and configured data sources for this server. You will see that the server has some datasources 
	for Cloudscape pre-configured.	We need to add both a driver and a data source. To add a new driver, 
	press the Add button to the left of the JDBC Drivers pane and enter the fully qualified name for 
	your database driver. In our case we are using the MySQL driver.Enter the driver class for 
	mysql "org.gjt.mm.mysql.Driver" here.

4.	Now we need to enable the data source to lookup the database server. 
	Press ADD in the "Data Sources" pane and enter the following values for the jndi name 
	and the JDBC url.
        
	JNDI name: jdbc/mysql
	JDBC url : jdbc:mysql://localhost:3306/hotel

5.	In order for the server to be able to use this new driver it needs the driver class to be in
	the classpath.

6.	Now restart the server to load the new driver and datasource - you should see them appear in the command
	window if you start the server in -verbose mode.


Setting up and Running the Application:

1.	Build the class files and enterprise archive yourself or use the provided Hotel.ear file.

2. 	To deploy the application, select File | Open and browse thru to the location of the ear file. Then
	deploy it by selecting Tools | Deploy. 

3.	Launch your browser and type the following in the address bar and you should be able to run the 
	application 
	
	http://localhost:8000/hotel/adminHotelFrame.html.


