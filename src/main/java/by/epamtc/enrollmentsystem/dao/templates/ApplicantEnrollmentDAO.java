package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;

import java.util.List;
import java.util.Map;

public interface ApplicantEnrollmentDAO extends DAOTemplate<ApplicantEnrollment> {
    Map<Long, List<Long>> getFacultiesWithFormsByUserId(long userId) throws DAOException;
    void updateEducationForm(long userId,long facultyId,long educationFormId) throws DAOException;
    boolean userHasFaculty(long userId,long facultyId) throws DAOException;
    void deleteFacultiesByUserId(long userId) throws DAOException;
    List<ApplicantEnrollment> getByUserId(long userId) throws DAOException;
    void updateRowByUserId(long userId,ApplicantEnrollment note) throws DAOException;
}
