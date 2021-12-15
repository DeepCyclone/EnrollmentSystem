package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.util.PasswordCodec;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignupCommand.class);
    private static final int APPLICANT_ROLE = 3;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService service = ServiceProvider.getInstance().getUserService();
        if(!service.isValidData(username,email,password)){
            Router.redirect(response, request.getContextPath() + URLHolder.MAIN_PAGE);
        }

        byte[] encodedPassword = PasswordCodec.generateEncodedPassword(password);
        User user = new User(0,username,encodedPassword,email,APPLICANT_ROLE);

        try {
            service.insertInto(user);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
        }
        Router.redirect(response, request.getContextPath() + URLHolder.MAIN_PAGE);
    }
}
