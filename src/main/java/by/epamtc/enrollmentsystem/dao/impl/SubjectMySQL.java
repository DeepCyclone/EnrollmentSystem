package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.templates.SubjectTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Subject;

import java.util.List;

public class SubjectMySQL implements SubjectTempl {
    @Override
    public Subject getByID(int id) {
        return null;
    }

    @Override
    public void insertInto(Subject object) throws DAOException {

    }


    @Override
    public void deleteAll() {

    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public void updateRowByID(Subject note, int id) {

    }
}
