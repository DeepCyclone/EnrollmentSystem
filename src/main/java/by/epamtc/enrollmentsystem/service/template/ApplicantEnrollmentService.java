package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ApplicantEnrollmentService {
    Map<Long, List<Long>> getSelectedFacultiesByUserId(long userId) throws ServiceException;
    void updateEducationForm(long userId,long facultyId,long educationFormId) throws ServiceException;
    boolean userHasFaculty(long userId,long facultyId) throws ServiceException;
    void deleteFacultiesByUserId(long userId);
    void updateEnrollmentStatusByUserId(ApplicantEnrollment note) throws ServiceException;
    Set<StringifiedApplicantEnrollment> getStringifiedTable(long userId) throws ServiceException;
    int getUserRequestsAmount(long facultyID,long educationFormID) throws ServiceException;
    Map<String,Integer> buildRequestAmountDtos(long educationFormID,int from,int offset) throws ServiceException;
    int getPriorityOfSelectedFaculty(long userID,long facultyID,long educationForm) throws ServiceException;
}
