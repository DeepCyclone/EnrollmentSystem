package by.epamtc.enrollmentsystem.controller.filter;

import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.action.CommandType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AuthorizationFilter implements Filter {

    private final Map<Long, List<CommandType>> allowedActionsForUsers = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig){
        allowedActionsForUsers.put(UserType.APPLICANT_ROLE,
                                    Arrays.asList(CommandType.PRELOAD_WELCOMEPAGE,
                                                  CommandType.PRELOAD_USERINFO_PAGE,
                                                  CommandType.LOGOUT));
        allowedActionsForUsers.put(UserType.ADMIN_ROLE,
                                    Arrays.asList(CommandType.LOGOUT,
                                                  CommandType.PRELOAD_WELCOMEPAGE,
                                                  CommandType.PRELOAD_ADMIN_PAGE));
        allowedActionsForUsers.put(UserType.GUEST,
                                    Arrays.asList(CommandType.PRELOAD_WELCOMEPAGE,
                                                  CommandType.AUTHORIZATION,
                                                  CommandType.CHANGE_LANGUAGE));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        String action = httpServletRequest.getParameter("action");
        String query = httpServletRequest.getQueryString();
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(action.toUpperCase());
        }
        catch (IllegalArgumentException exception) {
            commandType = CommandType.EMPTY;
        }
        Object role = session.getAttribute(SessionMapping.ROLE);

        long roleID = UserType.GUEST;

        if(role != null){
            roleID = (long) role;
        }

        if(allowedActionsForUsers.containsKey(roleID)){
            if(allowedActionsForUsers.get(roleID).contains(commandType)){
                chain.doFilter(request,response);
                return;
            }
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());//unauthorized access page
    }

}
