package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.controller.filter.UserType;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.util.EnrollmentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartEnrollmentCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(StartEnrollmentCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
        HttpSession session = request.getSession(false);
        long roleId = (long)session.getAttribute(SessionMapping.ROLE);
        if(roleId!=UserType.ADMIN_ROLE){
            response.sendRedirect(request.getContextPath() + URLHolder.MAIN_PAGE);
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        EnrollmentService service = provider.getEnrollmentService();
        service.makeEnrollment();
        response.sendRedirect(request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
        }
        catch (ServiceException | IOException exception){
            LOGGER.log(Level.ERROR,exception.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
