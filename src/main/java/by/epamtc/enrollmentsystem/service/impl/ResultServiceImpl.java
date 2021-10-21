package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.dao.DAOProvider;
import by.epamtc.enrollmentsystem.dao.templates.ResultDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;
import by.epamtc.enrollmentsystem.service.ResultService;

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
    public void updateUserResult(Result res) {

    }

    @Override
    public boolean userHasMarkOnSubject(long userId, long subjectId) {
        return false;
    }

    @Override
    public List<UserResultByFaculty> getUserTotalResultByFaculty(long educationFormId) throws ServiceException {
        List<UserResultByFaculty> totalResultByFaculty = new LinkedList<>();
        ResultDAO dao = DAOProvider.getInstance().getResultDAO();
        List<UserResultByFaculty> allResult = null;
        try {
            allResult = dao.getUserResultByFacultyAndEduForm(educationFormId);
        } catch (DAOException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
        Map<Map<Long, Long>, Integer> res = new HashMap<>();
        for (UserResultByFaculty result : allResult) {
            long userId = result.getUserId();
            long facultyId = result.getFacultyId();
            Map<Long, Long> userFacultyMap = new HashMap<>();
            if (!res.containsKey(userFacultyMap)) {
                res.put(userFacultyMap, result.getResult());
            } else {

            }
        }
        return null;
    }
}
