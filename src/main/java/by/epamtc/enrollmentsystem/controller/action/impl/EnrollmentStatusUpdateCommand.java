package by.epamtc.enrollmentsystem.controller.action.impl;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.controller.mapping.RequestMapping;
import by.epamtc.enrollmentsystem.controller.routing.Router;
import by.epamtc.enrollmentsystem.controller.routing.URLHolder;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.service.*;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.template.EducationFormService;
import by.epamtc.enrollmentsystem.service.template.EnrollmentStatusService;
import by.epamtc.enrollmentsystem.service.template.FacultyService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class EnrollmentStatusUpdateCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EnrollmentStatusUpdateCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int userId = Integer.parseInt(request.getParameter(RequestMapping.UPDATE_USER_ID));
        String facultyName = request.getParameter(RequestMapping.FACULTY);
        String educationFormName = request.getParameter(RequestMapping.EDUCATION_FORM);
        String enrollmentStatusName = request.getParameter(RequestMapping.ENROLLMENT_STATUS);


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FacultyService facultyService = serviceProvider.getFacultyService();
        EducationFormService educationFormService = serviceProvider.getEducationFormService();
        EnrollmentStatusService enrollmentStatusService = serviceProvider.getEnrollmentStatusService();
        ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();

        ApplicantEnrollment applicantEnrollment = new ApplicantEnrollment();
        try {

            Optional<Faculty> faculty = facultyService.getByName(facultyName);
            Optional<EducationForm> educationForm = educationFormService.getByName(educationFormName);
            Optional<EnrollmentStatus> enrollmentStatus = enrollmentStatusService.getByName(enrollmentStatusName);

            if(faculty.isPresent() &&
                    educationForm.isPresent() &&
                    enrollmentStatus.isPresent()) {
                long facultyId = faculty.get().getId();
                long educationFormId = educationForm.get().getId();
                long enrollmentStatusId = enrollmentStatus.get().getId();


                applicantEnrollment.setEnrollmentStatusId(enrollmentStatusId);
                applicantEnrollment.setEducationFormId(educationFormId);
                applicantEnrollment.setFacultyId(facultyId);
                applicantEnrollment.setUserId(userId);
                applicantEnrollmentService.updateEnrollmentStatusByUserId(applicantEnrollment);
            }
            Router.redirect(response,request.getContextPath() + URLHolder.ADMIN_PAGE_PRELOADER);
        }
        catch (ServiceException e){
            LOGGER.log(Level.ERROR,e.getMessage());
            Router.redirect(response, URLHolder.MAIN_PAGE);
        }
    }
}
