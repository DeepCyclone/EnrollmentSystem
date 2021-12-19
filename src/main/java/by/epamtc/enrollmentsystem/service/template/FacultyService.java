package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.util.List;
import java.util.Map;

public interface FacultyService extends Identifiable<Faculty>,Nameable<Faculty> {
    List<Faculty> getAll() throws ServiceException;
    List<Faculty> getFacultiesRange(int from,int offset) throws ServiceException;
    int getFacultiesNumber() throws ServiceException;
    Map<Long, Integer> getAvailableSpacesForFaculties(int educationFormID) throws ServiceException;
}
