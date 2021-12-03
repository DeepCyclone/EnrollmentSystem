package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.Subjectm2mFacultyDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.service.template.Subjectm2mFacultyService;

import java.util.List;
import java.util.Map;

public class Subjectm2mFacultyServiceImpl implements Subjectm2mFacultyService {
    @Override
    public Map<String, List<String>> getFacultiesCorrespondingToSubjects() throws ServiceException {
        try {
            Subjectm2mFacultyDAO dao = DAOProvider.getInstance().getSubjectm2mFacultyDAO();
            return dao.getFacultiesCorrespondingToSubjects();
        }
        catch (DAOException exception){
            throw new ServiceException(exception.getMessage(),exception);
        }
    }
}
