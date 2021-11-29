package by.epamtc.enrollmentsystem.controller.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 * This filter loads selected locale from cookie and sets it as session attribute
 * @author Flexus
 */
public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if(session != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("locale")) {
                    session.setAttribute("locale", cookie.getValue());
                    break;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
