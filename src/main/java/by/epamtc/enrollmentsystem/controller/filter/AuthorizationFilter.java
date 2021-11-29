package by.epamtc.enrollmentsystem.controller.filter;

import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.action.CommandType;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


/*
 * This class contains conformance USER_TYPE to ALLOWED_ACTIONS.
 * Also has routine to check if this user can do certain action
 *
 * @author Flexus
 */

public class AuthorizationFilter implements Filter {

    private final Map<Long, List<CommandType>> allowedActionsForUsers = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig){
        List<CommandType> commonCommands = Arrays.asList(CommandType.PRELOAD_WELCOMEPAGE,
                                                         CommandType.CHANGE_LANGUAGE);

        allowedActionsForUsers.put(UserType.APPLICANT_ROLE,
                                    new ArrayList<>(Arrays.asList(CommandType.PRELOAD_USERINFO_PAGE,
                                                  CommandType.PRELOAD_FACULTIES_PAGE,
                                                  CommandType.PRELOAD_FACILITIES_TAB,
                                                  CommandType.PRELOAD_SELECTED_FACULTIES,
                                                  CommandType.UPDATE_STUDYING_INFO,
                                                  CommandType.UPDATE_INFO,
                                                  CommandType.LOGOUT)));
        allowedActionsForUsers.get(UserType.APPLICANT_ROLE).addAll(commonCommands);

        allowedActionsForUsers.put(UserType.ADMIN_ROLE,
                new ArrayList<>(Arrays.asList(CommandType.LOGOUT,
                                                  CommandType.PRELOAD_WELCOMEPAGE,
                                                  CommandType.PRELOAD_ADMIN_PAGE,
                                                  CommandType.PRELOAD_USER_POPUP,
                                                  CommandType.UPDATE_ENROLLMENT_STATUS,
                                                  CommandType.START_ENROLLMENT,
                                                  CommandType.DELETE_USER)));
        allowedActionsForUsers.get(UserType.ADMIN_ROLE).addAll(commonCommands);

        allowedActionsForUsers.put(UserType.GUEST,
                new ArrayList<>(Arrays.asList(CommandType.PRELOAD_WELCOMEPAGE,
                                                  CommandType.AUTHORIZATION,
                                                  CommandType.SIGN_UP)));
        allowedActionsForUsers.get(UserType.GUEST).addAll(commonCommands);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        String action = httpServletRequest.getParameter(RequestMapping.ACTION);
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
        Router.redirect(httpServletResponse,httpServletRequest.getContextPath() + URLHolder.MAIN_PAGE);
    }

}
