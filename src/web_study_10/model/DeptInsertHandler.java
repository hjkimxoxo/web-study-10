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

@WebServlet("/DeptInsertHandler")
public class DeptInsertHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentService service;
	
	public void init(ServletConfig config) throws ServletException {
        service = new DepartmentService();
    }
	
	protected void doGet(HttpServletRequest request	, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if (request.getMethod().equalsIgnoreCase("GET")) { //정보조회
            System.out.println("GET");
            int nextNo = service.getNextNo();
            System.out.println(nextNo);
            
            response.getWriter().print(nextNo);
            //request.setAttribute("nextNo", nextNo);
            //request.getRequestDispatcher("deptInsert.jsp").forward(request, response);
            
		}else {
			 System.out.println("POST"); //가져와서 수정
			 Gson gson = new Gson();
			 Department dept = gson.fromJson(new InputStreamReader(request.getInputStream(), "UTF-8"), Department.class);
			 System.out.println(dept);
			 
			 int res = service.insertDept(dept);
			 
			 response.getWriter().print(res);
		}
		
	}

}
