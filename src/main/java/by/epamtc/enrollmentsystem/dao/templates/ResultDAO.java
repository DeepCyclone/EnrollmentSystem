package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;

import java.util.List;

public interface ResultDAO extends DAOTemplate<Result> {
    List<MarkValue> retrieveResultByUserId(long userID) throws DAOException;
    void updateUserResult(Result res) throws DAOException;
    boolean userHasMarkOnSubject(long userId,long subjectId) throws DAOException;
    List<UserResultByFaculty> getUserResultByFacultyAndEduForm(long educationFormId) throws DAOException;
}
