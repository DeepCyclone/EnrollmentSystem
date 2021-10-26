package by.epamtc.enrollmentsystem.controller.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession(false) != null && request.getParameter("login") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("login");
    }
}
