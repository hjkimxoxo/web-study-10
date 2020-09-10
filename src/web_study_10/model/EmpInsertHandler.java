package web_study_10.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web_study_10.dto.Employee;
import web_study_10.service.EmployeeService;

@WebServlet("/EmpInsertHandler")
public class EmpInsertHandler extends HttpServlet {
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
		if (request.getMethod().equalsIgnoreCase("GET")) { //정보조회
            System.out.println("GET");
            
		}else {
			List<Employee> list = service.selectEmpByAll();
			System.out.println(list);
			System.out.println("post");
			Gson gson = new Gson();
			Employee emp = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Employee.class);
			
			int res = service.insertEmployee(emp);
			System.out.println(res);
			response.getWriter().print(res);
			
			
		}
		
	}

}
