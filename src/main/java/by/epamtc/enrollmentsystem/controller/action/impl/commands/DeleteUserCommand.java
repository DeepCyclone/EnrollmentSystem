package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.CredentialsValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class DeleteUserCommand implements Command {
    private static Logger logger = LogManager.getLogger(DeleteUserCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserService service = ServiceProvider.getInstance().getUserService();
            long userPretendedToDelete = Long.parseLong(request.getParameter("userID"));
            service.deleteRowByID(userPretendedToDelete);
            response.sendRedirect(request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
        }
        catch (ServiceException | IOException e){
            logger.log(Level.ERROR,e.getMessage());
        }
    }
}
