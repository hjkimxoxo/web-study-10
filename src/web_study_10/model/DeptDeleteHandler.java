package web_study_10.model;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web_study_10.dto.Department;
import web_study_10.service.DepartmentService;
import web_study_10.service.TitleService;

@WebServlet("/DeptDeleteHandler")
public class DeptDeleteHandler extends HttpServlet {
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

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if (request.getMethod().equalsIgnoreCase("GET")) { //정보조회
            System.out.println("GET");
            int dept = Integer.parseInt(request.getParameter("deptNo").trim());
            int res = service.deleteDept(new Department(dept));
            response.getWriter().print(res);
            
		}else {
			 System.out.println("POST"); //가져와서 수정
			
		}
		
	}

}
