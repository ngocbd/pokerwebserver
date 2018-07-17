import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

/**
 * Filter required by Objectify to clean up any thread-local transaction contexts and pending
 * asynchronous operations that remain at the end of a request.
 */
@WebFilter(urlPatterns = {"/*"})
public class ErrorHandlerFilter implements Filter {

	
	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException {
	    try {
	        chain.doFilter(rq, rs); // this calls the servlet which is where your exceptions will bubble up from
	    } catch (ServletException t) {
	        // deal with exception, then do redirect to custom jsp page
	    	HttpServletResponse response = (HttpServletResponse)rs;
	    	response.sendError(500, t.getMessage());
	    	response.getWriter().println(t.getMessage());
	    	
	    	
	    }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}