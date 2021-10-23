package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.service.*;
import by.epamtc.enrollmentsystem.service.templates.ApplicantEnrollmentService;
import by.epamtc.enrollmentsystem.service.templates.EducationFormService;
import by.epamtc.enrollmentsystem.service.templates.EnrollmentStatusService;
import by.epamtc.enrollmentsystem.service.templates.FacultyService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class EnrollmentStatusUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String facultyName = request.getParameter("faculty");
        String educationFormName = request.getParameter("educationForm");
        String enrollmentStatusName = request.getParameter("enrollmentStatus");


        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FacultyService facultyService = serviceProvider.getFacultyService();
        EducationFormService educationFormService = serviceProvider.getEducationFormService();
        EnrollmentStatusService enrollmentStatusService = serviceProvider.getEnrollmentStatusService();
        ApplicantEnrollmentService applicantEnrollmentService = serviceProvider.getApplicantEnrollmentService();

        ApplicantEnrollment applicantEnrollment = new ApplicantEnrollment();
        try {

            Optional<Faculty> faculty = facultyService.getByName(facultyName);
            Optional<EducationForm> educationForm = educationFormService.getByName(facultyName);
            Optional<EnrollmentStatus> enrollmentStatus = enrollmentStatusService.getByName(facultyName);


            long facultyId = faculty.get().getId();
            long educationFormId = educationForm.get().getId();
            long enrollmentStatusId = enrollmentStatus.get().getId();


            applicantEnrollment.setEnrollmentStatusId(enrollmentStatusId);
            applicantEnrollment.setEducationFormId(educationFormId);
            applicantEnrollment.setFacultyId(facultyId);
            applicantEnrollment.setUserId(userId);
            applicantEnrollmentService.updateEnrollmentStatusByUserId(applicantEnrollment);
        }
        catch (ServiceException e){
            //logger
            //redirect
        }
    }
}
