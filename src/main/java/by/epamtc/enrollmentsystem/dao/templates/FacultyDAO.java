package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.util.List;

public interface FacultyDAO extends DAOTemplate<Faculty> {
    List<Faculty> getFacultiesRange(int from, int offset) throws DAOException;
}
