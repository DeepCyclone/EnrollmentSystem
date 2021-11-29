package by.epamtc.enrollmentsystem.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * This filter sets request encoding for all operations
 * @author Flexus
  */

public class CharsetFilter implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException{

        encoding=config.getInitParameter("requestEncoding");

    }

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain next)
            throws IOException, ServletException{
        request.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }

    public void destroy(){}
}
