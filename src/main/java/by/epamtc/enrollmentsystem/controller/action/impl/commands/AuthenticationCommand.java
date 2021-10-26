package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.CredentialsValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            HttpSession httpSession = request.getSession(true);
            if (!CredentialsValidator.isCorrectCredentials(login, password)) {
                request.setAttribute("invalidCredentials", "true");
                request.getRequestDispatcher("/login").forward(request, response);
            }
            else {
                httpSession.setAttribute("login", login);
                UserService userService = ServiceProvider.getInstance().getUserService();
                Optional<User> user = userService.getByLogin(login);
                long id = user.get().getId();
                long roleId = user.get().getRoleId();
                httpSession.setAttribute("role", roleId);
                httpSession.setAttribute("id",id);
                response.sendRedirect(request.getContextPath());
            }
        }
        catch (ServiceException | ServletException | IOException e){
            //logger
        }
    }
}
