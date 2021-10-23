package by.epamtc.enrollmentsystem.service.templates;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Facility;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.util.List;

public interface FacultyService extends Identifiable<Faculty>,Nameable<Faculty> {
    List<Faculty> getAll() throws ServiceException;
    List<Faculty> getFacultiesRange(int from,int offset) throws ServiceException;
    int getFacultiesNumber() throws ServiceException;
}
