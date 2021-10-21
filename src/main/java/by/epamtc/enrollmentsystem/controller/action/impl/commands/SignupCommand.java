package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.UserMySQL;
import by.epamtc.enrollmentsystem.dao.templates.UserDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.UserService;
import by.epamtc.enrollmentsystem.utils.PasswordCodec;
import sun.security.util.Password;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupCommand implements Command {
    private static final int APPLICANT_ROLE = 3;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO не впускать с пустыми полями => проверка в фильтре
        String username = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService service = ServiceProvider.getInstance().getUserService();
        if(!service.isValidData(username,email,password)){
            return;
        }
        UserDAO userMySQL = DAOProvider.getInstance().getUserDAO();

        byte[] encodedPassword = PasswordCodec.generateEncodedPassword(password);
        User user = new User(0,username,encodedPassword,email,APPLICANT_ROLE);

        try {
            userMySQL.insertInto(user);
            response.sendRedirect(request.getContextPath());
        }
        catch (DAOException e){
            throw new ServletException(e.getMessage(),e);
        }
    }
}
