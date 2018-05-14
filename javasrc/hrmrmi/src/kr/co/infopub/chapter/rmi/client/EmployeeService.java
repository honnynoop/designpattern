package kr.co.infopub.chapter.rmi.client;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;
import kr.co.infopub.chapter.rmi.client.dto.DepConvert;
import kr.co.infopub.chapter.rmi.client.dto.DepCount;
import kr.co.infopub.chapter.rmi.client.dto.DepCountConvert;
import kr.co.infopub.chapter.rmi.client.dto.Department;
import kr.co.infopub.chapter.rmi.client.dto.EmpConvert;
import kr.co.infopub.chapter.rmi.client.dto.Employee;
import kr.co.infopub.chapter.rmi.common.DepCountDto;
import kr.co.infopub.chapter.rmi.common.DepartmentDto;
import kr.co.infopub.chapter.rmi.common.EmployeeDAO;
import kr.co.infopub.chapter.rmi.common.EmployeeDto;
public class EmployeeService { 
 // 자신을 private static 
 private static EmployeeService employeeService;
 private static EmployeeDAO employeeDAO; 
 // 생성자도 private
 private EmployeeService() {
	try {
		String rmiurl="rmi://localhost:1099/EmployeeDAO";
		try {
			employeeDAO=(EmployeeDAO)Naming.lookup(rmiurl);
		} catch (MalformedURLException e) {
			System.out.println("rmi 서버 위치 이상");
		} catch (RemoteException e) {
			System.out.println("원격 객체  이상");
		} catch (NotBoundException e) {
			System.out.println("바인딩 실패 ");
		}
	} catch (Exception e) {
		close();
	}
 }
 public void close(){

 }
 // static -> 한번의 객체생성 
 public static EmployeeService getInstance(){
	if(employeeService==null){
		employeeService=new EmployeeService();
	}
	return employeeService;
 }//--------------DAO를 감싸고, List -> ObservableList
 public ObservableList<Department> 
                     findAllDepartments() throws SQLException{
	List<DepartmentDto> blist=null;
	try {
		blist = employeeDAO.findAllDepartments();
	} catch (RemoteException e) {
	}
	return DepConvert.toObservProFromDto(blist);
 }
 public ObservableList<Employee> findAllEmployees() throws SQLException{
	List<EmployeeDto> blist=null;
	try {
		blist = employeeDAO.findAllEmployees ();
	} catch (RemoteException e) {
	}
	return EmpConvert.toObservProFromDto(blist);
 }
 public ObservableList<Employee> findTreeManagerInEmployee()
		                                            throws SQLException{
	List<EmployeeDto> bdlists=null;
	try {
		bdlists = employeeDAO.findTreeManagerInEmployee();
	} catch (RemoteException e) {
	}
    return EmpConvert.toObservProFromDto(bdlists);
 }
 public int getTreeMaxLevel() throws SQLException{
	 int level=0;
	try {
		level = employeeDAO.getTreeMaxLevel();
	} catch (RemoteException e) {
	}
	 return  level;
 }
 public ObservableList<DepCount> findAllDepCounts() throws SQLException{	
    List<DepCountDto> deplist = null;
	try {
		deplist = employeeDAO.findAllDepCounts();
	} catch (RemoteException e) {
	}
    
    return DepCountConvert.toObservProFromDto(deplist);
 }
 public ObservableList<Employee> 
          findEmployeesByDepartName(String val) throws SQLException{
	 List<EmployeeDto> emplists=null;
	try {
		emplists = employeeDAO.findEmployeesByDepartName(val);
	} catch (RemoteException e) {
	}
	 return EmpConvert.toObservProFromDto(emplists);
 }
 public ObservableList<Employee> 
                 findEmployeesByEmpId(String val) throws SQLException{
	  List<EmployeeDto> elists=null;
	try {
		elists = employeeDAO.findEmployeesByEmpId(val);
	} catch (RemoteException e) {
	}//100
	return EmpConvert.toObservProFromDto(elists);
 }
 public Employee findEmployeeById(String string) throws SQLException{
	EmployeeDto edto=null;
	try {
		edto = employeeDAO.findEmployeeById(string);
	} catch (RemoteException e) {
	}
	return EmpConvert.toPro(edto);
 }
 public ObservableList<Employee> 
            findManagersByName(String searchname) throws SQLException{
	List<EmployeeDto> elists=null;
	try {
		elists = employeeDAO.findManagersByName(searchname);
	} catch (RemoteException e) {
	}
	return EmpConvert.toObservProFromDto(elists);
 }
 public ObservableList<String> findAllJobs() throws SQLException{
    List<String> jlists=null;
	try {
		jlists = employeeDAO.findAllJobs();
	} catch (RemoteException e) {
	}
	return EmpConvert.strList(jlists);
 }
 public ObservableList<Department>
                           findAllDepartments2() throws SQLException{
    List<DepartmentDto> edeps=null;
	try {
		edeps = employeeDAO.findAllDepartments2 ();
	} catch (RemoteException e) {
	}
	return DepConvert.toObservProFromDto(edeps);
 }
 public int addEmployee(EmployeeDto empdto) throws SQLException{
	int add=0;
	try {
		add= employeeDAO.addEmployee(empdto);
	} catch (RemoteException e) {
	}
	return add;
 }
 public boolean updateEmployee(Employee emp) throws SQLException{
	EmployeeDto edot=EmpConvert.toDto(emp);
	boolean update=false;
	try {
		update= employeeDAO.updateEmployee(edot);
	} catch (RemoteException e) {
	}
	return update;
 }
 public boolean deleteEmployee(Employee emp) throws SQLException{
	EmployeeDto edot=EmpConvert.toDto(emp);
	boolean update=false;
	try {
		update= employeeDAO.deleteEmployee(edot);
	} catch (RemoteException e) {
	}
	return update;
 }
 public int getEmployeesTotal() throws SQLException{
	int update=0;
	try {
		update=employeeDAO.getEmployeesTotal();
	} catch (RemoteException e) {
	}
	return update;
 }
}
