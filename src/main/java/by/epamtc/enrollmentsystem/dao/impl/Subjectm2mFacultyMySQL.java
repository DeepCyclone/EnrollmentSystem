package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.Subjectm2mFacultyTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Subjectm2mFaculty;

import java.util.List;

public class Subjectm2mFacultyMySQL implements Subjectm2mFacultyTempl {
    @Override
    public List<Subjectm2mFaculty> getAll() throws DAOException {
        return null;
    }

    @Override
    public Subjectm2mFaculty getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(Subjectm2mFaculty object) throws DAOException {

    }

    @Override
    public void updateRowByID(Subjectm2mFaculty note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }
}
