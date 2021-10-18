package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.Subject;

import java.util.List;

public interface SubjectDAO extends DAOTemplate<Subject> {
    List<String> getSubjectsNames() throws DAOException;
}
