package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.Facilitym2mUserInfoTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facilitym2mUserInfo;

import java.util.List;

public class Facilitym2mUserInfoMySQL implements Facilitym2mUserInfoTempl {
    @Override
    public List<Facilitym2mUserInfo> getAll() throws DAOException {
        return null;
    }

    @Override
    public Facilitym2mUserInfo getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Facilitym2mUserInfo object) throws DAOException {

    }

    @Override
    public void updateRowByID(Facilitym2mUserInfo note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }
}
