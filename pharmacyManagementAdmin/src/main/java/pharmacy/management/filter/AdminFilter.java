package pharmacy.management.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
public class AdminFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("idLogin") == null) {
			// con cac truong hop con lai thi ko cho thuc hien
			req.getRequestDispatcher("/login").forward(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
