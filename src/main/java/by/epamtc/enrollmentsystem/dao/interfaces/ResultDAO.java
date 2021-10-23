package by.epamtc.enrollmentsystem.dao.interfaces;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;
import by.epamtc.enrollmentsystem.model.dto.UserResultByFaculty;

import java.util.List;

public interface ResultDAO extends DAOTemplate<Result>, Identifiable<Result>, Nameable<Result> {
    List<MarkValue> retrieveResultsByUserId(long userID) throws DAOException;
    void updateUserResult(Result res) throws DAOException;
    boolean userHasMarkOnSubject(long userId,long subjectId) throws DAOException;
    List<UserResultByFaculty> getUserResultByFacultyAndEduForm(long educationFormId) throws DAOException;
    int getResultValueBySubjectName(String subjectName,long userId) throws DAOException;
}
