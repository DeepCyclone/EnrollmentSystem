package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.util.MarksAndFacultiesUpdater;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateStudyingInfoCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(UpdateStudyingInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            long userID = (long) request.getSession(false).getAttribute("id");
            Map<String, String[]> a = request.getParameterMap();
            MarksAndFacultiesUpdater.update(a, userID);
            Router.redirect(response, request.getContextPath() + URLHolder.USER_INFO_PAGE_PRELOADER);
        }
        catch (ServiceException exception){
            LOGGER.log(Level.ERROR,exception.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
