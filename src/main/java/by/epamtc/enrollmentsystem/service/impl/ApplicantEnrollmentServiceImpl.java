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
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.template.ApplicantEnrollmentService;

import java.util.*;

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
            FacultyDAO facultyDAO = DAOProvider.getInstance().getFacultyDAO();
            EducationFormDAO educationFormDAO = DAOProvider.getInstance().getEducationFormDAO();
            EnrollmentStatusDAO enrollmentStatusDAO = DAOProvider.getInstance().getEnrollmentStatusDAO();
            List<ApplicantEnrollment> enrollments = applicantEnrollmentDAO.getByUserId(userId);
            Set<StringifiedApplicantEnrollment> stringifiedEnrollments = new HashSet<>();
            Map<String,Map<String,String>> facultyFormsStatuses = new HashMap<>();//TODO переделать без выделения

            for(ApplicantEnrollment applicantEnrollment:enrollments){
                String facultyName = facultyDAO.getByID(applicantEnrollment.getFacultyId()).get().getName();
                if(!facultyFormsStatuses.containsKey(facultyName)){
                    Map<String,String> formsStatusesMap = new HashMap<>();
                    facultyFormsStatuses.put(facultyName,formsStatusesMap);
                }
                Optional<EnrollmentStatus> enrollmentStatus = enrollmentStatusDAO.getByID(applicantEnrollment.getEnrollmentStatusId());
                String enrollmentStatusName = "";
                if(enrollmentStatus.isPresent()){
                    enrollmentStatusName = enrollmentStatus.get().getName();
                }
                Optional<EducationForm> educationForm = educationFormDAO.getByID(applicantEnrollment.getEducationFormId());
                String educationFormName = "";
                if(enrollmentStatus.isPresent()){
                    educationFormName = educationForm.get().getName();
                }
                facultyFormsStatuses.get(facultyName).put(educationFormName,enrollmentStatusName);
            }

            for(Map.Entry<String,Map<String,String>> entry:facultyFormsStatuses.entrySet()){
                StringifiedApplicantEnrollment stringifiedEnrollment = new StringifiedApplicantEnrollment();
                stringifiedEnrollment.setFacultyName(entry.getKey());
                stringifiedEnrollment.setEducationFormStatuses(entry.getValue());
                stringifiedEnrollments.add(stringifiedEnrollment);
            }
            return stringifiedEnrollments;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
