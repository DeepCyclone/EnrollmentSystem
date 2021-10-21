package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.dao.templates.UserDAO;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.UserService;
import by.epamtc.enrollmentsystem.service.validators.CredentialsValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
                long id = userDAO.getIdByLogin(login);
                long roleId = userDAO.getRoleByLogin(login);
                httpSession.setAttribute("role", roleId);
                httpSession.setAttribute("id",id);
                response.sendRedirect(request.getContextPath());
            }
        }
        catch (Exception e){

        }
    }
}
