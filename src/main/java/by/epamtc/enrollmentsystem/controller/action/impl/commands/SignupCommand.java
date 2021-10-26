package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.util.PasswordCodec;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupCommand implements Command {
    private static final int APPLICANT_ROLE = 3;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService service = ServiceProvider.getInstance().getUserService();
        if(!service.isValidData(username,email,password)){
            return;
        }

        byte[] encodedPassword = PasswordCodec.generateEncodedPassword(password);
        User user = new User(0,username,encodedPassword,email,APPLICANT_ROLE);

        try {
            service.insertInto(user);
            response.sendRedirect(request.getContextPath());
        }
        catch (ServiceException e){
            //logger
            //redirector
        }
    }
}
