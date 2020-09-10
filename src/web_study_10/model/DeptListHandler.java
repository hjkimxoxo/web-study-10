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
import web_study_10.dto.Title;
import web_study_10.service.DepartmentService;

@WebServlet("/DeptListHandler")
public class DeptListHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService service;

	public void init(ServletConfig config) throws ServletException {
        service = new DepartmentService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	private void process(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		 if (request.getMethod().equalsIgnoreCase("GET")) {
	            System.out.println("GET");
	            List<Department> list = service.DeptList();
	            request.setAttribute("list", list);
	            request.getRequestDispatcher("deptList.jsp").forward(request, response);
	        }else {
	            System.out.println("POST");
	            System.out.println("POST");
	            List<Department> list = service.DeptList();
	            Gson gson = new Gson(); 
	            String result = gson.toJson(list, new TypeToken<List<Department>>(){}.getType());
	           // System.out.println(result);
	                    
	            response.setContentType("application/json");
	            response.setStatus(HttpServletResponse.SC_ACCEPTED);
	            
	            PrintWriter pw = response.getWriter();
	            pw.print(result);
	            pw.flush();
	        }
		
	}

}
