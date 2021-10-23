package by.epamtc.enrollmentsystem.dao.interfaces;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;

import java.util.List;
import java.util.Map;

public interface Subjectm2mFacultyDAO extends DAOTemplate<Subjectm2mFaculty> {
    public Map<String, List<String>> getFacultiesCorrespondingToSubjects() throws DAOException;
}
