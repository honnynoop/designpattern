package msg;

import java.rmi.RemoteException;
import java.util.Properties;
import javax.jms.*;
import javax.naming.*;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.rmi.PortableRemoteObject;

public class Client {
  // The name of the topic the client and the message bean use
  static private String TOPIC_NAME = "disableAccounts";

  public Client() {
  }

  public static void main(String[] args) {
    System.out.println("EAI message client");
    System.out.println();

    try {
      Client client = new Client();
      client.example();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void example() {
    try {
      // Create a message context
      Context mctx = new InitialContext();
      
      // Create the topic connection and start it
      TopicConnectionFactory cf = (TopicConnectionFactory) mctx.lookup("weblogic.jms.ConnectionFactory");
      TopicConnection mTopicConn = cf.createTopicConnection();
      mTopicConn.start();

      Topic newTopic = null;
      TopicSession session = null;

      try {
        // CReate a new topic session 
        session = mTopicConn.createTopicSession(
                               false,   // non transacted
                               Session.AUTO_ACKNOWLEDGE);
        // Lookup the topic name (if exist, otherwise exception)
        newTopic = (Topic) mctx.lookup(TOPIC_NAME);
      } catch(NamingException ex) {
        // If the topic doesn't exist create it 
        newTopic = session.createTopic(TOPIC_NAME);
        mctx.bind(TOPIC_NAME, newTopic);
      }

      // Create a topic publisher
      TopicPublisher sender = session.createPublisher(newTopic);

      // Create a text message
      TextMessage tm = session.createTextMessage();
      tm.setText("EAI-dissableAccounts");

      // Publish the message
      sender.publish(tm);

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
