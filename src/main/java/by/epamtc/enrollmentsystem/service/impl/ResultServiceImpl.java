package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.template.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;
import by.epamtc.enrollmentsystem.service.template.ResultService;

import java.util.*;

public class ResultServiceImpl implements ResultService {
    @Override
    public List<MarkValue> retrieveResultByUserId(long userID) throws ServiceException {
        try {
            ResultDAO dao = DAOProvider.getInstance().getResultDAO();
            return dao.retrieveResultsByUserId(userID);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public void updateUserResult(Result res) throws ServiceException {
        try {
            ResultDAO dao = DAOProvider.getInstance().getResultDAO();
            dao.updateUserResult(res);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public boolean userHasMarkOnSubject(long userId, long subjectId) {
        return false;
    }

    @Override
    public List<UserResultByFaculty> getUserTotalResultByFaculty(long educationFormId) throws ServiceException {
        try {
            ResultDAO dao = DAOProvider.getInstance().getResultDAO();
            return dao.getUserResultByFacultyAndEduForm(educationFormId);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    public int getResultValueBySubjectName(String subjectName,long userId) throws ServiceException {
        try {
            ResultDAO dao = DAOProvider.getInstance().getResultDAO();
            return dao.getResultValueBySubjectName(subjectName,userId);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
