package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.UserInfo;

public interface UserInfoTempl extends DAOTemplate<UserInfo>{
    public boolean hasNoteWithId(int id) throws DAOException;
}
