package by.epamtc.enrollmentsystem.controller.filter;


import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
 * Filter helps to authenticate account
 * @author Flexus
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getSession(false) != null && request.getParameter(RequestMapping.USER_LOGIN) != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Router.redirect(response, request.getContextPath()+URLHolder.LOGIN_PAGE);
        }
    }
}
