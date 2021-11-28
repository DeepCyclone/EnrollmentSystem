package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.controller.filter.UserType;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.util.EnrollmentService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartEnrollmentCommand implements Command {
    private static Logger logger = LogManager.getLogger(StartEnrollmentCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
        HttpSession session = request.getSession(false);
        int roleId = (int)session.getAttribute("role");
        if(roleId!=UserType.ADMIN_ROLE){
            response.sendRedirect(request.getContextPath() + URLHolder.MAIN_PAGE);//TODO 404 page
        }
        ServiceProvider provider = ServiceProvider.getInstance();
        EnrollmentService service = provider.getEnrollmentService();
        service.makeEnrollment();
        response.sendRedirect(request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
        }
        catch (ServiceException | IOException exception){
            logger.log(Level.ERROR,exception.getMessage());
        }
    }
}
