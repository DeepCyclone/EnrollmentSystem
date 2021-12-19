package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.dto.UserTotalResultWithFaculty;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.comparator.UserResultComparator;
import by.epamtc.enrollmentsystem.service.template.FacultyService;

import java.util.*;


/*
 * A core utility, which maintains enroll request
 */
public class EnrollmentService {

    private static final int BUDGET_EDUCATION_FORM = 1;
    private static final int PAID_EDUCATION_FORM = 2;
    private static final int FORM_ENROLLED = 2;
    Map<Long,Integer> availableBudgetPlaces;
    Map<Long,Integer> availablePaidPlaces;
    public void processEnrollment() throws ServiceException {
        try {
            final int[] EDUCATION_FORMS = {BUDGET_EDUCATION_FORM,PAID_EDUCATION_FORM};

            DAOProvider provider = DAOProvider.getInstance();
            ResultDAO resultDAO = provider.getResultDAO();
            ApplicantEnrollmentDAO applicantEnrollmentDAO = provider.getApplicantEnrollmentDAO();
            FacultyDAO facultyDAO = provider.getFacultyDAO();

            availableBudgetPlaces = populateAvailablePlacesMap(BUDGET_EDUCATION_FORM);
            availablePaidPlaces = populateAvailablePlacesMap(PAID_EDUCATION_FORM);

            for(int eduForm:EDUCATION_FORMS){
                List<UserTotalResultWithFaculty> results = resultDAO.getUserTotalResults(eduForm);
                long lastUserID = 0L;
                List<UserTotalResultWithFaculty> userResults = null;
                for(UserTotalResultWithFaculty result:results){
                    if(result.getUserId() != lastUserID){
                        if(userResults != null){
                            userResults.sort(new UserResultComparator());
                            makeEnrollment(userResults,eduForm);
                        }
                        userResults = new ArrayList<>();
                        lastUserID = result.getUserId();
                    }
                    userResults.add(result);
                }
                userResults.sort(new UserResultComparator());
                makeEnrollment(userResults,eduForm);
                }

        }
        catch (DAOException e){
            throw new ServiceException();
        }
    }

    private void makeEnrollment(List<UserTotalResultWithFaculty> userResults,int educationFormID) throws DAOException {
        Map<Long,Integer> availablePlacesMap = availableBudgetPlaces;
        if(educationFormID == PAID_EDUCATION_FORM){
            availablePlacesMap = availablePaidPlaces;
        }
        for(UserTotalResultWithFaculty res:userResults){
            int availablePlaces = availablePlacesMap.get(res.getFacultyId());

            if(availablePlaces > 0){
                if(availablePlaces == 1){
                    //check urgency
                }
                availablePlaces--;
                availablePlacesMap.put(res.getFacultyId(), availablePlaces);
                changeEnrollmentStatus(res,educationFormID);
                return;
            }
        }
    }

    private void changeEnrollmentStatus(UserTotalResultWithFaculty result,int educationFormID) throws DAOException {
        DAOProvider provider = DAOProvider.getInstance();
        ApplicantEnrollmentDAO applicantEnrollmentDAO = provider.getApplicantEnrollmentDAO();
            ApplicantEnrollment enrollment = new ApplicantEnrollment();
            enrollment.setUserId(result.getUserId());
            enrollment.setFacultyId(result.getFacultyId());
            enrollment.setEducationFormId(educationFormID);
            enrollment.setEnrollmentStatusId(FORM_ENROLLED);
            applicantEnrollmentDAO.updateEnrollmentStatusByUserId(enrollment);
    }

    private Map<Long,Integer> populateAvailablePlacesMap(int educationFormID) throws ServiceException {
        FacultyService facultyService = ServiceProvider.getInstance().getFacultyService();
        return facultyService.getAvailableSpacesForFaculties(educationFormID);

    }


}
