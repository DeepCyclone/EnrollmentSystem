package by.epamtc.enrollmentsystem.service.template;

import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserTotalResultWithFaculty;

import java.util.List;

public interface ResultService {
    List<MarkValue> retrieveResultByUserId(long userID) throws ServiceException;
    void updateUserResult(Result res) throws ServiceException;
    boolean userHasMarkOnSubject(long userId,long subjectId);
    List<UserTotalResultWithFaculty> getUserTotalResultByFaculty(long educationFormId) throws ServiceException;
    int getResultValueBySubjectName(String subjectName,long userId) throws ServiceException;

}
