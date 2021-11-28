package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.action.impl.redirectors.URLHolder;
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
    private static Logger logger = LogManager.getLogger(UpdateStudyingInfoCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        //TODO deny updateflooding
        try {
            request.setCharacterEncoding("UTF-8");
            long userID = (long) request.getSession(false).getAttribute("id");
            Map<String, String[]> a = request.getParameterMap();
            MarksAndFacultiesUpdater.update(a, userID);
            response.sendRedirect(request.getContextPath() + URLHolder.USER_INFO_PAGE_PRELOADER);
        }
        catch (ServiceException | IOException exception){
            logger.log(Level.ERROR,exception.getMessage());
            //redirect
        }
    }
}
