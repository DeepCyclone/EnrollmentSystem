package by.epamtc.enrollmentsystem.controller.action.impl;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import org.apache.logging.log4j.*;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.CredentialsValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthenticationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter(RequestMapping.USER_LOGIN);
            String password = request.getParameter(RequestMapping.USER_PASSWORD);
            HttpSession httpSession = request.getSession(true);
            if (!CredentialsValidator.isCorrectCredentials(login, password)) {
                request.setAttribute(RequestMapping.INVALID_CREDENTIALS, "true");
                Router.forward(request,response, URLHolder.LOGIN_PAGE);
            }
            else {
                httpSession.setAttribute(SessionMapping.USER_LOGIN, login);
                UserService userService = ServiceProvider.getInstance().getUserService();
                Optional<User> user = userService.getByLogin(login);
                long id = user.get().getId();
                long roleId = user.get().getRoleId();
                httpSession.setAttribute(SessionMapping.ROLE, roleId);
                httpSession.setAttribute(SessionMapping.USER_ID,id);
                Router.redirect(response,request.getContextPath());
            }
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
