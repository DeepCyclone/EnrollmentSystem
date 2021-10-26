package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;

import java.util.*;

public class EnrollmentService {

    private static final int BUDGET_EDUCATION_FORM = 1;
    private static final int PAID_EDUCATION_FORM = 2;
    private static final int FORM_ENROLLED = 2;
    public void makeEnrollment() throws ServiceException {
        try {
            DAOProvider provider = DAOProvider.getInstance();
            ResultDAO resultDAO = provider.getResultDAO();
            ApplicantEnrollmentDAO applicantEnrollmentDAO = provider.getApplicantEnrollmentDAO();
            FacultyDAO facultyDAO = provider.getFacultyDAO();
            List<UserResultByFaculty> results = resultDAO.getUserResultByFacultyAndEduForm(BUDGET_EDUCATION_FORM);
            Set<Long> faculties = new LinkedHashSet<>();
            for(UserResultByFaculty result:results){
                faculties.add(result.getFacultyId());
            }

            for(Long facultyId:faculties){
                Optional<Faculty> faculty = facultyDAO.getByID(facultyId);
                int availablePlaces;
                if(faculty.isPresent()) {
                    availablePlaces = faculty.get().getBudgetAdmissionPlan();
                }
                else {
                    continue;
                }
                List<UserResultByFaculty> resultByFaculty = getResultsByFacultyId(facultyId,results,availablePlaces);
                for(UserResultByFaculty result:resultByFaculty){
                    ApplicantEnrollment enrollment = new ApplicantEnrollment();
                    enrollment.setUserId(result.getUserId());
                    enrollment.setFacultyId(result.getFacultyId());
                    enrollment.setEducationFormId(BUDGET_EDUCATION_FORM);
                    enrollment.setEnrollmentStatusId(FORM_ENROLLED);
                    applicantEnrollmentDAO.updateEnrollmentStatusByUserId(enrollment);
                }
            }
        }
        catch (DAOException e){
            throw new ServiceException();
        }
    }

    private List<UserResultByFaculty> getResultsByFacultyId(long facultyId,List<UserResultByFaculty> allResults,int admissionPlan){
        List<UserResultByFaculty> userResultByFaculties = new LinkedList<>();
        for(UserResultByFaculty result:allResults){
            if(result.getFacultyId() == facultyId){
                userResultByFaculties.add(result);
                if(userResultByFaculties.size() == admissionPlan){
                    break;
                }
            }
        }
        return userResultByFaculties;
    }
}
