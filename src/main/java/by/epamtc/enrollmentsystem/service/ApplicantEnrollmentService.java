package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ApplicantEnrollmentService {
    Map<Long, List<Long>> getSelectedFacultiesByUserId(long userId) throws ServiceException;
    void updateEducationForm(long userId,long facultyId,long educationFormId);
    boolean userHasFaculty(long userId,long facultyId) throws ServiceException;
    void deleteFacultiesByUserId(long userId);
    Set<StringifiedApplicantEnrollment> getStringifiedTable(long userId) throws ServiceException;
}
