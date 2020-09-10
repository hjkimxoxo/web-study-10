package web_study_10.service;

import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.dao.impl.EmployeeDaoImpl;
import web_study_10.dto.Employee;

public class EmployeeService {
	private EmployeeDao dao = EmployeeDaoImpl.getInstance();
	
	public List<Employee> selectEmpByAll(){
		return dao.selectEmpByAll();
	}
	
	public Employee selectEmpByNo(Employee emp) {
		return dao.selectEmpByNo(emp);
	}
	
	public int insertEmployee(Employee emp) {
		return dao.insertEmployee(emp);
	}
	
	public int deleteEmployee(Employee emp) {
		return dao.deleteEmployee(emp);
	}
	
	public int updateEmployee(Employee emp) {
		return dao.updateEmployee(emp);
	}
}
