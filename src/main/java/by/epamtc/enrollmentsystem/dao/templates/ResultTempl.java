package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.dao.DAOTemplate;
import by.epamtc.enrollmentsystem.model.Result;
import by.epamtc.enrollmentsystem.model.dto.MarkValue;

import java.util.List;

public interface ResultTempl{
    List<MarkValue> retrieveResultByUserId(int userID);
    void updateUserResult(Result res);
    boolean userHasMarkOnSubject(int userId,int subjectId);
}
