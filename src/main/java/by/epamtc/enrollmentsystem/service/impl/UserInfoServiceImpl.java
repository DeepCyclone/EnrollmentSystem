package by.epamtc.enrollmentsystem.service.impl;

import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.service.UserInfoService;

public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public boolean hasNoteWithId(int id) throws DAOException {
        return false;
    }
}
