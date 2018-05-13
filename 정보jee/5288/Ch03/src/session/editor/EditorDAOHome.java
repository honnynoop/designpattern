package session.editor;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;
import javax.ejb.CreateException;

public interface EditorDAOHome extends EJBHome{
  EditorDAO create() throws RemoteException, CreateException;
}
