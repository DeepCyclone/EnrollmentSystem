package by.epamtc.enrollmentsystem.service.templates;

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
}