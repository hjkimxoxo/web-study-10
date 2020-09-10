package web_study_10.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/main.jsp")
public class LoginCheckFilter implements Filter {

    public LoginCheckFilter() {
        
    }

	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("LoginCheckFilter - doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		if(session != null && session.getAttribute("loginUser") != null) {
			//유저가 있는거면 
			chain.doFilter(request, response);
		}else {
			request.getRequestDispatcher("login.do").forward(request, response);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
