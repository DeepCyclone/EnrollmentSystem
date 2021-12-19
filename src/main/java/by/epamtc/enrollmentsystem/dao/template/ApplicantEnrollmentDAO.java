package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.dto.StringifiedApplicantEnrollment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ApplicantEnrollmentDAO extends DAOTemplate<ApplicantEnrollment> {
    void updateEducationForm(long userId,long facultyId,long educationFormId) throws DAOException;
    Optional<ApplicantEnrollment> getByFacultyAndUserIds(long userId, long facultyId) throws DAOException;
    void deleteFacultiesByUserId(long userId) throws DAOException;
    List<ApplicantEnrollment> getByUserId(long userId) throws DAOException;
    void updateEnrollmentStatusByUserId(ApplicantEnrollment note) throws DAOException;
    Set<StringifiedApplicantEnrollment> getStringifiedTableByUserId(long userId) throws DAOException;
    int getUserRequestsAmount(long facultyID,long educationFormID) throws DAOException;
    int getPriorityOfSelectedFaculty(long userID,long facultyID,long educationForm) throws DAOException;
}
