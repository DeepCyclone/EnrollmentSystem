package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAll() throws ServiceException;
    List<Faculty> getFacultiesRange(int from,int offset) throws ServiceException;
    int getFacultiesNumber() throws ServiceException;
}
