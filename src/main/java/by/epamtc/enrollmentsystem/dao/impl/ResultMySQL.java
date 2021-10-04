package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.ResultTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Result;

import java.util.List;

public class ResultMySQL implements ResultTempl {
    @Override
    public List<Result> getAll() throws DAOException {
        return null;
    }

    @Override
    public Result getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Result object) throws DAOException {

    }

    @Override
    public void updateRowByID(Result note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }
}
