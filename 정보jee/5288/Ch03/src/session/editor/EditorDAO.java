package session.editor;

import dataaccess.DAOObject;
import dataaccess.DataAccessException;
import dataaccess.ServiceLocatorException;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface EditorDAO extends DAOObject{
  public HashMap findEditorByTitle(String pTitleId) throws 
                                                     DataAccessException,
                                                     ServiceLocatorException,
						     RemoteException;
                                                     
}
