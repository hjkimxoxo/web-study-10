package web_study_10.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
}


	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//다운받는 경로 
		String savePath = "upload";
		int SizeLimit = 5 * 1024 * 1024;
		String encType = "UTF-8";
		
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리:");
		System.out.println(uploadFilePath);
		
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, //request 객체
					uploadFilePath, // 서버상 실제 디렉토리 
					SizeLimit, //파일크기제한 
					encType, //인코딩방법 
					new DefaultFileRenamePolicy()); //업로드 파일 이름 얻기
			String fileName = multi.getFilesystemName("uploadFile");
			
			//파일업로드 안됐을때
			if(fileName == null) {
				System.out.println("파일 업로드 되지 않았음");
			}else {
				out.println("<br>글쓴이:" + multi.getParameter("name"));
				out.println("<br>제 &nbsp;목:" + multi.getParameter("title"));
				out.println("<br>파일명:" + fileName);
			}
		} catch (Exception e) {
			System.out.println("예외발생:" + e);
		}
	}
		
	}
