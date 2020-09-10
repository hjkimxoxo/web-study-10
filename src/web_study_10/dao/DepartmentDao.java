package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Department;

public interface DepartmentDao {
	
	List<Department> selectDeptByAll();
	
	Department selectDeptByNo(Department dept);
	
	int getNextNo();
	
	int insertDept(Department dept);
	
	int deleteDept(Department dept);
	
	int updateDept(Department dept);

	
	
}
