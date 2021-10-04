package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.templates.EnrollmentStatusTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;

import java.util.List;

public class EnrollmentStatusMySQL implements EnrollmentStatusTempl {
    @Override
    public EnrollmentStatus getByID(int id) {
        return null;
    }

    @Override
    public void insertInto(EnrollmentStatus object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<EnrollmentStatus> getAll() {
        return null;
    }

    @Override
    public void updateRowByID(EnrollmentStatus note, int id) {

    }
}
