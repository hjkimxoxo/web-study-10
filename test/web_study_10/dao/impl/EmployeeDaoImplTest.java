package web_study_10.dao.impl;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import oracle.sql.DATE;
import web_study_10.dao.EmployeeDao;
import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoImplTest {
	private EmployeeDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = EmployeeDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testSelectEmpByAll() {
		System.out.println("all");
		List<Employee> list = dao.selectEmpByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	
	@Test 
	public void test01InsertEmp() {
		System.out.println("insert");
		Date date = new Date();
		Employee emp = new Employee(1018, "김혜진", new Title(5), new Employee(4377), 1500000, new Department(3), "sdfk@nakvsdkf", "1234", date, "123-123-1223", "asd");
		
		int res = dao.insertEmployee(emp);
		Assert.assertEquals(1, res);
		//System.out.println(emp);
	}
	
	@Test
	public void test02UpdateEmp() {
		System.out.println("update");
		Date date = new Date();
		Employee emp = dao.selectEmpByNo(new Employee(1018));
		
		emp.setEmpName("김변경");
		emp.setTitle(new Title(2));
		emp.setManager(new Employee(3427));
		emp.setSalary(2500000);
		emp.setDept(new Department(1));
		emp.setEmail("asjhdj@jsfk");
		emp.setPasswd("123456");
		emp.setRegDate(date);
		emp.setTel("010-5656-5656");
		emp.setPicUrl("1219");
		
		int res = dao.updateEmployee(emp);
		Assert.assertEquals(1, res);
		System.out.println("변경후");
		List<Employee> list = dao.selectEmpByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}
	
}
