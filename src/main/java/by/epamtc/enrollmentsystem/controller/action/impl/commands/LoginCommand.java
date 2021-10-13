package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.services.CredentialsValidator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            HttpSession httpSession = request.getSession(true);
            if (login == null || password == null) {//TODO Place it in filter
                request.getRequestDispatcher("/login").forward(request, response);
                return;
            }
            if (!CredentialsValidator.isCorrectCredentials(login, password)) {//TODO DB info
                httpSession.setAttribute("invalidCredentials", "true");
                request.getRequestDispatcher("/login").forward(request, response);
            } else {
                httpSession.removeAttribute("invalidCredentials");
                httpSession.setAttribute("login", login);//что сюда поместить (name:sha-2(pass))?
                UserMySQL userMySQL = DAOProvider.getInstance().getUserDAO();
                int id = userMySQL.getIdByLogin(login);
                int roleId = userMySQL.getRoleByLogin(login);
                httpSession.setAttribute("role", roleId);
                httpSession.setAttribute("id",id);
                response.sendRedirect(request.getContextPath());
            }
        }
        catch (Exception e){
            throw new ServletException(e.getMessage(),e);
        }
    }
}
