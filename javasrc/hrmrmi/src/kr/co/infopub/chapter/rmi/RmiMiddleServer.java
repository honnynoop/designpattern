package kr.co.infopub.chapter.rmi;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
//javac -d . *.java
//start rmiregistry 
//java kr.co.infopub.chapter.rmi.RmiMiddleServer
public class RmiMiddleServer {
	public static void main(String[] args) throws AlreadyBoundException {
		EmployeeDAOImpl dao=null;
		try {
			dao = new EmployeeDAOImpl();
            //EmployeeDAO stub = (EmployeeDAO) UnicastRemoteObject.exportObject(dao, 0);
            //Registry registry = LocateRegistry.getRegistry(1099);
            //registry.bind("EmployeeDAO", stub);
			Naming.rebind("rmi://localhost:1099/EmployeeDAO",dao);
            System.out.println("Server ready");
			System.out.println("�̵� ���� ���� ����  "+dao.getClass().getName());
		} catch (RemoteException e) {
			System.out.println("Rmi ���� ���� ����  ");
		} catch (Exception e) {
			System.out.println("remote exception !!! ");
		}
	}
}
