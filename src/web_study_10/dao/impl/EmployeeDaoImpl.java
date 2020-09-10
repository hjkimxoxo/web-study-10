package web_study_10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import web_study_10.dao.EmployeeDao;
import web_study_10.ds.JdbcUtil;
import web_study_10.ds.JndiDS;
import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final EmployeeDaoImpl instance = new EmployeeDaoImpl();

	public EmployeeDaoImpl() {}
	
	public static EmployeeDaoImpl getInstance() {
		return instance;
	}
	
	private Employee getEmployee(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		Employee manager = new Employee();
		Title title = new Title();
		Department dept = new Department();
		
		emp.setTitle(title);
		emp.setDept(dept);
		emp.setManager(manager);
		
		try {
			emp.setEmpNo(rs.getInt("EMP_NO"));
			emp.setEmpName(rs.getString("EMP_NAME"));
			title.setTitleNo(rs.getInt("TNO"));
			manager.setEmpNo(rs.getInt("MANAGER"));
			emp.setSalary(rs.getInt("SALARY"));
			dept.setDeptNo(rs.getInt("DNO"));
			emp.setRegDate(rs.getTimestamp("REGDATE"));
			emp.setEmail(rs.getString("EMAIL"));
			emp.setTel(rs.getString("TEL"));
			emp.setPicUrl(rs.getString("PIC_URL"));
			title.setTitleName(rs.getString("TITLE_NAME"));
			dept.setDeptName(rs.getString("DEPT_NAME"));
			manager.setEmpName(rs.getString("MANAGER_NAME"));
			
		}catch(SQLException e) {
			
		}
		return emp;
	}
	

	@Override
	public List<Employee> selectEmpByAll() {
		String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO,"
				+ " REGDATE, EMAIL, TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME FROM VW_EMPLOYEE_JOIN";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee(rs));
					}while(rs.next());
					return list;
				}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	

	@Override
	public Employee selectEmpByNo(Employee emp) {
		String sql = "SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE,  EMAIL, "
				+ "TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME FROM VW_EMPLOYEE_JOIN WHERE EMP_NO = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, emp.getEmpNo());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
		return null;
	}


	@Override
	public int insertEmployee(Employee emp) {
		Title title = emp.getTitle();
		Department dept = emp.getDept();
		Employee manager = emp.getManager();
		
		String sql = "INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, PASSWD, REGDATE, TEL, PIC_URL)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setInt(1, emp.getEmpNo());
				pstmt.setString(2, emp.getEmpName());
				pstmt.setInt(3, title.getTitleNo());
				pstmt.setInt(4, manager.getEmpNo());
				pstmt.setInt(5, emp.getSalary());
				pstmt.setInt(6, dept.getDeptNo());
				pstmt.setString(7, emp.getEmail());
				pstmt.setString(8, emp.getPasswd());
				pstmt.setTimestamp(9, new Timestamp(emp.getRegDate().getTime()));
				pstmt.setString(10, emp.getTel());
				pstmt.setString(11, emp.getPicUrl());
				
				return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "DELETE FROM EMPLOYEE WHERE EMP_NO = ?";

		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int updateEmployee(Employee emp) {
		//UPDATE EMPLOYEE SET EMP_NAME = '김혜진', TNO = 1, MANAGER = 4377, SALARY = 2000000, DNO = 3, 
		//EMAIL = 'UPDATE@TEST.CO.KR', PASSWD = '4321', REGDATE = to_date('2020-09-09', 'yyyy-MM-dd'), TEL = '010-1234-1234', PIC_URL = '변경' WHERE EMP_NO = 1000;
		String sql = "UPDATE EMPLOYEE SET EMP_NAME = ?, TNO = ?, MANAGER = ?, SALARY = ?, DNO = ?, EMAIL =?, "
				+ "PASSWD = ?, REGDATE = ?, TEL = ?, PIC_URL = ? WHERE EMP_NO = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTitle().getTitleNo());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			pstmt.setString(6, emp.getEmail());
			pstmt.setString(7, emp.getPasswd());
			pstmt.setTimestamp(8, new Timestamp(emp.getRegDate().getTime()));
			pstmt.setString(9, emp.getTel());
			pstmt.setString(10, emp.getPicUrl());
			pstmt.setInt(11, emp.getEmpNo());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}

}
