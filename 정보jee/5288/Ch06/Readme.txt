
The application code is grouped under the pattern headings discussed in the chapter.

Wrapper :To implement the Wrapper pattern CORBA and Java 2 SDK 1.3 are used.
CORBA is a part of Java 2 SDK Standard Edition under the name of Java IDL.

Building the CORBA integration wrapper (or component wrapper)includes the following steps:

1. Define the interface that the component wrapper should expose in CORBA IDL   
   The interface named Customer is defined in the module CustomerManagement.

2. Compile the IDL interface to get hte stubs and skeletons
    To compile the IDL interface(customer interface), use the -idlj compiler which is a part
   of JDK(1.3 and higher versions).To generate mappings for the client side and server side
   use the following option.

   idlj -fall CustomerInt.idl
   
   The IDL compiler generates a set of interfaces and classes and places it under the folder
   CustomerManagement folder.This folder apart from the above mentioned classes and interfaces
   contains the user defined data types, user defined exceptions, holder and helper classes.    
 
3. Connect the operations from the interface with the existing application
   The connection is made by, by defining a class that implements the interface, this class is
   named as _CustomerImplBase. 

4. Implement the server process
   To implement the server process the Server class is defined which performas the following 
    Obtain the reference to the ORB
    Create a new instance of the Customer Wrapper
    Connect the instance with the ORB
   
5. Implement the client to test the component wrapper
   The Client class is defined to test the Component Wrapper,it does the following 
     Obtain the reference to the ORB
     Obtain a reference to the Naming Service
     Resolve the name binding
     Narrow the reference to the Customer interface
     Invoke the methods on the wrapper

  Final task is to compile and run the example:

  To compile the Java code use the following command, assuming the appropriate CLASSPATH preset:

  javac CustomerManagement\*.java

  To run the example start the Naming Service, called the tnameserv provided by Java SDK 1.3.Then 
  after setting appropriate CLASSPATH start Server.java and Client.java


Integration Mediator Pattern: To implement the Mediator pattern we use RMI-IIOP. RMI is the native 
distributed object networking protocol for the Java platform and uses the CORBA complaint IIOP protocol
called RMI-IIOP.

1. The RMI-IIOP interface named MobilePhoneEvaluation is defined.

2. The interface defined is connected to the existing application by defining a class named 
   MobilePhoneEvaluationImpl which implements the above interface.

3. A Server class is defined to instantiate and register the MobilePhoneEvaluationImpl.

4. A Client class is defined to test the application.

Final task, to run the example

  First compile all the Java code.

  Before starting the server and the client code activate the transient naming service.
   
     start tnameserv 

  Now run the Server class, specify the naming initial factory and the naming provider URL.

   Djava.naming.provider.url=iiop//localhost: 900 Server

  Similarly run the Client class, specify the naming initial factory and the naming provider URL.

   Djava.naming.provider.url=iiop//localhost: 900 Client

  to get the desired result.


Data Mapping Pattern: To implement this pattern EJB is used.

1. First the remote component interface named Customer is defined.

2. Value object class called CustomerData is defined whose instances are used later in the home
   interface defined below.

3. Then the home interface named CustomerHome is defined, followed by the abstract class CustomerBean
   which implements the EntityBean.

4. Then a suitable deployment descriptor is defined,which includes the necessary information 
   required for the deployment of the enterprise bean.

5. Finally, before deploying the bean all the java classes should be compiled and packed into 
   jar file(eai1_cutomer.jar).We created a simple client to test the application.Type the following command 

    java -classpath %J2EE_HOME%\lib\j2ee.jar;eai1_customer.jar;.cmp1.Client to get the desired result.
   


