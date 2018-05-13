package msg;

import javax.ejb.*;
import javax.jms.*;

public class DisableAccountsBean implements MessageDrivenBean, MessageListener {

  private MessageDrivenContext mctx;

  public void ejbRemove() {
  }

  public void setMessageDrivenContext(MessageDrivenContext ctx) {
    mctx = ctx;
  }

  public void ejbCreate () throws CreateException {
  }

  // Implementation of MessageListener

  public void onMessage(Message msg) {
    try {
      // Extract the message
      TextMessage tm = (TextMessage) msg;
      String text = tm.getText();
      System.out.println("Received request: " + text);
      
      // Process the request 
      System.out.println("Processing the request: disabling accounts...");
      
      // Search for accounts and disable them (connect to existing applciaitons)
    }
    catch(JMSException ex) {
      ex.printStackTrace();
    }
  }
}
