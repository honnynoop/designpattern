To get this application running there are a number of installation and configuration steps required:

1. Set up a couple of JMS Queue Destinations called jms/newBookingQ and jms/bookingCompleteQ. 
   you will also need a QueueConnectionFactory bound to jms/QueueConnectionFactory but the 
   J2EE RI should already have this configured.

2. In the database you need to setup a table called COUNTER. See the counter.sql file.

3. Once the application is deployed run the poptables.cmd file to load some data into the database 

To test the deployment there are a number of cmd files that execute client classes located
in the clients directory:

1. Step1 will query the db for the entries in the CityTable
2. Step2 will list the hotels for a specific city. You need to provide a city id for this client to execute. The table only contains 
   data for hotels in Paris so make sure you get the right id
3. Step3 will get a list of flights
4. Step4 will create a new customer
5. Step5 will make a new booking. You need to provide the hotel id, customer id, and two flight ids for this client to execute. This client also acts as a message listener on the bookingCompleteQ 
   so it should consume a successfully made booking message