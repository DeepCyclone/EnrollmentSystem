package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.User;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.FacultyService;
import by.epamtc.enrollmentsystem.service.template.UserService;
import by.epamtc.enrollmentsystem.service.validator.CredentialsValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class PreloadFacultiesCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PreloadFacultiesCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            FacultyService service = ServiceProvider.getInstance().getFacultyService();
            List<Faculty> faculties = service.getAll();
            request.setAttribute(RequestMapping.FACULTIES_LIST,faculties);
            Router.forward(request,response,URLHolder.FACULTIES);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
