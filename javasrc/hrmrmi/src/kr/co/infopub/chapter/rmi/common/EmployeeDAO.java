package kr.co.infopub.chapter.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
public interface EmployeeDAO extends Remote{
	  public  int getEmployeesTotal () throws SQLException,RemoteException ;
	  public  List<DepartmentDto> findAllDepartments () throws SQLException,RemoteException ;
	  public  List<DepartmentDto> findAllDepartments2 () throws SQLException,RemoteException ;
	  public  List<DepCountDto> findAllDepCounts ()   throws SQLException,RemoteException;
	  public  List<EmployeeDto> findAllEmployees ()  throws SQLException,RemoteException;
	  public  List<EmployeeDto> findTreeManagerInEmployee ()  throws SQLException,RemoteException;
	  public  int getTreeMaxLevel () throws SQLException,RemoteException;
	  public  List<EmployeeDto> findEmployeesByManagerId (String empid)  throws SQLException,RemoteException;
	  public  List<String> findAllJobs () throws SQLException,RemoteException;
	  public  List<EmployeeDto> findEmployeesByDepartName (String department_name) throws SQLException,RemoteException ;
	  public  List<EmployeeDto> findEmployeesByEmpId (String empid) throws SQLException ,RemoteException;
	  public  EmployeeDto findEmployeeById (String empId) throws SQLException,RemoteException ;
	  public List<EmployeeDto> findManagersByName(String searchManagerId) throws SQLException,RemoteException;
	  public  int addEmployee (EmployeeDto emp) throws SQLException ,RemoteException;
	  public  boolean updateEmployee(EmployeeDto emp) throws SQLException ,RemoteException;
	  public  boolean updateJobHistory(EmployeeDto emp) throws SQLException,RemoteException ;
	  public  boolean deleteEmployee (EmployeeDto emp) throws SQLException ,RemoteException;
}
