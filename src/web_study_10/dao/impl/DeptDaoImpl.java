package web_study_10.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web_study_10.dao.DepartmentDao;
import web_study_10.ds.JdbcUtil;
import web_study_10.ds.JndiDS;
import web_study_10.dto.Department;

public class DeptDaoImpl implements DepartmentDao{
	
	private static final DeptDaoImpl instance = new DeptDaoImpl();

	public DeptDaoImpl() {}
	
	public static DeptDaoImpl getInstance() {
		return instance;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int dept_no = rs.getInt("DEPT_NO");
		String dept_name = rs.getString("DEPT_NAME");
		int floor = rs.getInt("FLOOR");
		
		return new Department(dept_no, dept_name, floor);
	}
	
	@Override
	public List<Department> selectDeptByAll() {
		String sql = "SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT";
		try(Connection con = JndiDS.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					List<Department> list = new ArrayList<>();
					do {
						list.add(getDepartment(rs));
					}while(rs.next());
					return list;
				}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	

			
	}

	@Override
	public Department selectDeptByNo(Department dept) {
		String sql = "select dept_no, dept_name, floor from department where dept_no = ?";
		try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setInt(1, dept.getDeptNo());
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getDepartment(rs);
                }
            }
        }catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
	}

	@Override
	public int getNextNo() {
		String sql = "SELECT MAX(DEPT_NO)+1 FROM DEPARTMENT";
		try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return 0;
	}

	@Override
	public int insertDept(Department dept) {
		String sql = "insert into department values(?, ?, ?)";
		 try(Connection con = JndiDS.getConnection();
	                PreparedStatement pstmt = con.prepareStatement(sql)){
	            pstmt.setInt(1, dept.getDeptNo());
	            pstmt.setString(2, dept.getDeptName());
	            pstmt.setInt(3, dept.getFloor());
	            return pstmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new CustomSQLException(e);
	        }
	}

	@Override
	public int deleteDept(Department dept) {
		String sql = "delete from department where dept_no=?";
		try(Connection con = JndiDS.getConnection();
                PreparedStatement pstmt = con.prepareStatement(sql);){
            pstmt.setInt(1, dept.getDeptNo());
            return pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new CustomSQLException(e);
        }
       
	}

	@Override
	public int updateDept(Department dept) {
		String sql = "update department set dept_name = ?, floor = ? where dept_no = ?";
		 try(Connection con = JdbcUtil.getConnection();
	                PreparedStatement pstmt = con.prepareStatement(sql)){
	            pstmt.setString(1, dept.getDeptName());
	        	pstmt.setInt(2, dept.getFloor());
	        	pstmt.setInt(3, dept.getDeptNo());
	     
	            return pstmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new CustomSQLException(e);
	        }
	}

}
