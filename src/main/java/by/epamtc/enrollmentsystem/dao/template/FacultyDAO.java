package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.util.Map;


public interface FacultyDAO extends DAOTemplate<Faculty>, Identifiable<Faculty>, Nameable<Faculty> {
    Map<Long,Integer> getAvailableSpacesForFaculties(int educationFormID) throws DAOException;
}
