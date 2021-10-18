package by.epamtc.enrollmentsystem.controller.action.impl.commands;

import by.epamtc.enrollmentsystem.controller.action.Command;
import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.impl.ApplicantEnrollmentMySQL;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.templates.EducationFormDAO;
import by.epamtc.enrollmentsystem.dao.templates.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.EnrollmentStatusService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnrollmentStatusUpdateCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request");
        int userId = Integer.parseInt(request.getParameter("userId"));
        String facultyName = request.getParameter("faculty");
        String educationForm = request.getParameter("educationForm");
        String enrollmentStatus = request.getParameter("enrollmentStatus");


        DAOProvider provider = DAOProvider.getInstance();
        FacultyDAO facultyDAO = provider.getFacultyDAO();
        EducationFormDAO educationFormDAO = provider.getEducationFormDAO();
        EnrollmentStatusDAO enrollmentStatusDAO = provider.getEnrollmentStatusDAO();
        ApplicantEnrollmentDAO applicantEnrollmentDAO = provider.getApplicantEnrollmentDAO();

        ApplicantEnrollment applicantEnrollment = new ApplicantEnrollment();
        try {
            long facultyId = facultyDAO.getIdByName(facultyName);
            long educationFormId = educationFormDAO.getIdByName(educationForm);
            long enrollmentStatusId = enrollmentStatusDAO.getIdByName(enrollmentStatus);
            applicantEnrollment.setEnrollmentStatusId(enrollmentStatusId);
            applicantEnrollment.setEducationFormId(educationFormId);
            applicantEnrollment.setFacultyId(facultyId);
            applicantEnrollment.setUserId(userId);
            applicantEnrollmentDAO.updateRowByUserId(userId,applicantEnrollment);
        }
        catch (Exception e){

        }
    }
}
