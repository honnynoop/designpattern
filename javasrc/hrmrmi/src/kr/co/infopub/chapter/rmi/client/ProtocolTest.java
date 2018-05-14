package kr.co.infopub.chapter.rmi.client;
//kr.co.infopub.chapter.rmi.client.client.ProtocolTest
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import kr.co.infopub.chapter.rmi.common.EmployeeDAO;
import kr.co.infopub.chapter.rmi.common.EmployeeDto;
public class ProtocolTest {
	public static void main(String[] args) {
		try {
			String rmiurl="rmi://localhost:1099/EmployeeDAO";
			try {
				EmployeeDAO employeeDAO=(EmployeeDAO)Naming.lookup(rmiurl);
				List<EmployeeDto> emps=employeeDAO.findTreeManagerInEmployee();
				for (EmployeeDto emp:emps) {
					System.out.println(emp);
				}
			} catch (MalformedURLException e) {
				System.out.println("rmi 서버 위치 이상");
			} catch (RemoteException e) {
				System.out.println("원격 객체  이상");
			} catch (NotBoundException e) {
				System.out.println("바인딩 실패 ");
			}
		} catch (Exception e) {
		}
	}
}
