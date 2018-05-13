package session.title;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;
import javax.ejb.CreateException;

public interface TitleDAOHome extends EJBHome{
  TitleDAO create() throws RemoteException, CreateException;
}
