package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.exception.ServiceException;
import by.epamtc.enrollmentsystem.model.UserInfo;

public interface UserInfoService {
    public boolean hasNoteWithId(int id) throws ServiceException;

    void updateRowByID(UserInfo ui) throws ServiceException;

    void insertInto(UserInfo ui) throws ServiceException;
}
