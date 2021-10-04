package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.ApplicantEnrollmentTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.ApplicantEnrollment;

import java.util.List;

public class ApplicantEnrollmentMySQL implements ApplicantEnrollmentTempl {
    @Override
    public List<ApplicantEnrollment> getAll() throws DAOException {
        return null;
    }

    @Override
    public ApplicantEnrollment getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(ApplicantEnrollment object) throws DAOException {

    }

    @Override
    public void updateRowByID(ApplicantEnrollment note, int id) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }
}
