package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.impl.UserStudyingInfoBuilder;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class PreloadSelectedFacultiesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PreloadSelectedFacultiesCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        long id = (long)session.getAttribute(SessionMapping.USER_ID);
        try{
            Set<StringifiedApplicantEnrollment> stringifiedApplicantEnrollmentSet = UserStudyingInfoBuilder.
                                                                                    buildFacultiesAndEducationForms(id);

            Gson gson = new Gson();
            String json = gson.toJson(stringifiedApplicantEnrollmentSet);
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
