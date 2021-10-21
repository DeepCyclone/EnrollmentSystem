package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.tables.fields.ApplicantEnrollmentFields;
import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentDAO;
import by.epamtc.enrollmentsystem.dao.templates.EducationFormDAO;
import by.epamtc.enrollmentsystem.dao.templates.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.dao.templates.FacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;
import by.epamtc.enrollmentsystem.service.ApplicantEnrollmentService;

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
    public void updateEducationForm(long userId, long facultyId, long educationFormId) {

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
    public Set<StringifiedApplicantEnrollment> getStringifiedTable(long userId) throws ServiceException {//TODO а вот такие штуки - точно слой сервисов
        try {
            ApplicantEnrollmentDAO applicantEnrollmentDAO = DAOProvider.getInstance().getApplicantEnrollmentDAO();
            FacultyDAO facultyDAO = DAOProvider.getInstance().getFacultyDAO();
            EducationFormDAO educationFormDAO = DAOProvider.getInstance().getEducationFormDAO();
            EnrollmentStatusDAO enrollmentStatusDAO = DAOProvider.getInstance().getEnrollmentStatusDAO();
            List<ApplicantEnrollment> applicantEnrollmentList = applicantEnrollmentDAO.getByUserId(userId);
            Set<StringifiedApplicantEnrollment> stringifiedApplicantEnrollmentSet = new HashSet<>();
            Map<String,Map<String,String>> facultyFormsStatusesMap = new HashMap<>();//TODO переделать через алгоритм без доп.выделения для удобства

            for(ApplicantEnrollment applicantEnrollment:applicantEnrollmentList){
                String facultyName = facultyDAO.getNameById(applicantEnrollment.getFacultyId());
                if(!facultyFormsStatusesMap.containsKey(facultyName)){
                    Map<String,String> formsStatusesMap = new HashMap<>();
                    facultyFormsStatusesMap.put(facultyName,formsStatusesMap);
                }
                String enrollmentStatusName = enrollmentStatusDAO.getNameById(applicantEnrollment.getEnrollmentStatusId());
                String educationFormName = educationFormDAO.getNameById(applicantEnrollment.getEducationFormId());
                facultyFormsStatusesMap.get(facultyName).put(educationFormName,enrollmentStatusName);
            }

            for(Map.Entry<String,Map<String,String>> entry:facultyFormsStatusesMap.entrySet()){
                StringifiedApplicantEnrollment stringifiedApplicantEnrollment = new StringifiedApplicantEnrollment();
                stringifiedApplicantEnrollment.setFacultyName(entry.getKey());
                stringifiedApplicantEnrollment.setEducationFormStatuses(entry.getValue());
                stringifiedApplicantEnrollmentSet.add(stringifiedApplicantEnrollment);
            }
            return stringifiedApplicantEnrollmentSet;
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
