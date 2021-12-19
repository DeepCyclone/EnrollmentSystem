package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.mapping.SessionMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.template.EducationFormService;
import by.epamtc.enrollmentsystem.service.template.FacultyService;
import by.epamtc.enrollmentsystem.service.template.SystemInformationService;
import com.google.gson.Gson;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PrioritiesPreloaderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(PrioritiesPreloaderCommand.class);
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String facultyName = request.getParameter("faculty");
            String educationForm = request.getParameter("educationForm");
            long userID = (long) request.getSession(false).getAttribute(SessionMapping.USER_ID);
            ApplicantEnrollmentService service = ServiceProvider.getInstance().getApplicantEnrollmentService();
            FacultyService service1 = ServiceProvider.getInstance().getFacultyService();
            EducationFormService service2 = ServiceProvider.getInstance().getEducationFormService();
            long facultyID = service1.getByName(facultyName).get().getId();
            long educationFormID = service2.getByName(educationForm).get().getId();
            long priority = service.getPriorityOfSelectedFaculty(userID,facultyID,educationFormID);
            Gson gson = new Gson();
            String json = gson.toJson(priority);
            response.getWriter().write(json);
        }
        catch (ServiceException | IOException exception){

        }
    }
}
