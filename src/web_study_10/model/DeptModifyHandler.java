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

import web_study_10.dto.Department;
import web_study_10.service.DepartmentService;

@WebServlet("/DeptModifyHandler")
public class DeptModifyHandler extends HttpServlet {
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
	            
	        }else {
	            System.out.println("POST"); //수정이니까 post
	            Gson gson = new Gson();
	            Department updateDept = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Department.class);
	            System.out.println(updateDept);
	            
	            int res = service.updateDept(updateDept);
	            response.getWriter().print(res);
	        }
		
	}

}
