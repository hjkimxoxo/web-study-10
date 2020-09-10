package web_study_10.service;

import java.util.List;

import web_study_10.dao.DepartmentDao;
import web_study_10.dao.impl.DeptDaoImpl;
import web_study_10.dto.Department;

public class DepartmentService {
	private DepartmentDao dao = DeptDaoImpl.getInstance();
	
	public List<Department> DeptList(){
		return dao.selectDeptByAll();
	}
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	public Department selectDeptByNo(Department dept) {
		return dao.selectDeptByNo(dept);
	}
	
	public int insertDept(Department dept) {
		return dao.insertDept(dept);
	}
	
	public int updateDept(Department dept) {
		return dao.updateDept(dept);
	}
	
	public int deleteDept(Department dept) {
		return dao.deleteDept(dept);
	}
}
