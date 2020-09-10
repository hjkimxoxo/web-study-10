package web_study_10.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import web_study_10.dto.Department;
import web_study_10.dto.Employee;
import web_study_10.dto.Title;
import web_study_10.service.DepartmentService;
import web_study_10.service.EmployeeService;
import web_study_10.service.TitleService;


@WebServlet("/EmployeeListHandler")
public class EmployeeListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;
	private DepartmentService dService;
	private TitleService tService;

	public void init(ServletConfig config) throws ServletException {
		service = new EmployeeService();
		dService = new DepartmentService();
		tService = new TitleService();
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
            List<Employee> list = service.selectEmpByAll();
            List<Department> dept = dService.DeptList();
            //System.out.println(dept);
            
            request.setAttribute("dept", dept);
            request.setAttribute("list", list);
            request.getRequestDispatcher("empList.jsp").forward(request, response);
            
            
        }else {
         
            
        }
	
	}

}
