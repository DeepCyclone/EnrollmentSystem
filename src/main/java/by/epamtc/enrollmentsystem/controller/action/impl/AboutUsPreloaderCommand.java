package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.SystemInformationService;
import by.epamtc.enrollmentsystem.service.template.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AboutUsPreloaderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AboutUsPreloaderCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            SystemInformationService service = ServiceProvider.getInstance().getSystemInformationService();
            Optional<SystemInformation> information = service.getByName(RequestMapping.ABOUT_US_MSG1);
            information.ifPresent(systemInformation -> request.setAttribute(RequestMapping.ABOUT_US_MSG1, systemInformation.getValue()));
            Router.forwardWithSavingURL(request,response,URLHolder.ABOUT_US);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
