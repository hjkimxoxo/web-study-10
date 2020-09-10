package web_study_10.model;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.service.EmployeeService;


@WebServlet("/EmpNoHandler")
public class EmpNoHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;
	public void init(ServletConfig config) throws ServletException {
		service = new EmployeeService();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if (request.getMethod().equalsIgnoreCase("GET")) {
	            System.out.println("GET");
	            int empNo = Integer.parseInt(request.getParameter("empNo").trim());
	            Employee emp = service.selectEmpByNo(new Employee(empNo));
	            request.setAttribute("emp", emp);
	            request.getRequestDispatcher("employee.jsp").forward(request, response);
	            
	        }else {
	            System.out.println("POST");
	           
	        }
	}

}
