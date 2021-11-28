package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.template.EducationFormDAO;
import by.epamtc.enrollmentsystem.dao.template.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.dao.template.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.EducationForm;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.model.Faculty;
import by.epamtc.enrollmentsystem.model.dto.RequestAmount;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.ServiceProvider;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;

import javax.management.monitor.Monitor;
import java.util.*;
import java.util.concurrent.Exchanger;

public class ApplicantEnrollmentServiceImpl implements ApplicantEnrollmentService {

    @Override
    public Map<Long, List<Long>> getSelectedFacultiesByUserId(long userId) throws ServiceException {
        try {
            ApplicantEnrollmentDAO dao = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            List<ApplicantEnrollment> userData = dao.getByUserId(userId);
            Map<Long,List<Long>> facultyForms = new HashMap<>();
            for(ApplicantEnrollment data:userData){
                long key = data.getFacultyId();
                if(!facultyForms.containsKey(key)) {
                    facultyForms.put(key,new ArrayList<>());
                }
                long value = data.getEducationFormId();
                facultyForms.get(key).add(value);
            }
            return facultyForms;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void updateEducationForm(long userId, long facultyId, long educationFormId) throws ServiceException {
        try {
            ApplicantEnrollmentDAO dao = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            dao.updateEducationForm(userId,facultyId,educationFormId);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void updateEnrollmentStatusByUserId(ApplicantEnrollment note) throws ServiceException {
        try {
            ApplicantEnrollmentDAO dao = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            dao.updateEnrollmentStatusByUserId(note);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public boolean userHasFaculty(long userId, long facultyId) throws ServiceException {
        try {
            ApplicantEnrollmentDAO dao = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            Optional<ApplicantEnrollment> userData = dao.getByFacultyAndUserIds(userId,facultyId);
            return userData.isPresent();
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public void deleteFacultiesByUserId(long userId) {

    }


    /*
    * @author Alexey
    * @params userId
    * @throws ServiceException
    * This method takes an userId,gets notes from ApplicantEnrollment table corresponding to userId.
    * Then it stringifies ApplicantEnrollment internal id's by calling corresponding stringifiers of field's dao's
    *
    * */

    @Override
    public Set<StringifiedApplicantEnrollment> getStringifiedTable(long userId) throws ServiceException {
        try {
            ApplicantEnrollmentDAO applicantEnrollmentDAO = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            return applicantEnrollmentDAO.getStringifiedTableByUserId(userId);
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }

    @Override
    public Map<String,Integer> buildRequestAmountDtos(long educationFormID,int from,int offset) throws ServiceException {
        ServiceProvider provider = ServiceProvider.getInstance();
        List<Faculty> faculties = provider.getFacultyService().getFacultiesRange(from,offset);
        Map<String,Integer> requestsAmount = new HashMap<>();
        for(Faculty f:faculties){
            long facultyID = f.getId();
            int requests = getUserRequestsAmount(facultyID,educationFormID);
            requestsAmount.put(f.getName(),requests);
        }
        return requestsAmount;
    }

    @Override
    public int getUserRequestsAmount(long facultyID,long educationFormID) throws ServiceException{
        try {
            ApplicantEnrollmentDAO dao = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            int requests = dao.getUserRequestsAmount(facultyID,educationFormID);
            return requests;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
