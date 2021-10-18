package by.epamtc.enrollmentsystem.service;

import by.epamtc.enrollmentsystem.exception.DAOException;

public interface UserInfoService {
    public boolean hasNoteWithId(int id) throws DAOException;
}
