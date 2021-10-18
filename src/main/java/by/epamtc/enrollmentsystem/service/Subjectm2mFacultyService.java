package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface Subjectm2mFacultyService {
    public Map<String, List<String>> getFacultiesCorrespondingToSubjects() throws ServiceException;
}
