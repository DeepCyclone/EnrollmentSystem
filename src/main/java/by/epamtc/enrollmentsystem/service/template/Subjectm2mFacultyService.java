package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface Subjectm2mFacultyService {
    Map<String, List<String>> getFacultiesCorrespondingToSubjects() throws ServiceException;
}
