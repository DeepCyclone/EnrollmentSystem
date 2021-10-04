package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.templates.DAOTemplate;
import by.epamtc.enrollmentsystem.dao.templates.FacilityTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.Facility;

import java.util.List;

public class FacilityMySQL implements FacilityTempl {

    @Override
    public Facility getByID(int id) {
        return null;
    }

    @Override
    public void insertInto(Facility object) throws DAOException {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Facility> getAll() {
        return null;
    }

    @Override
    public void updateRowByID(Facility note, int id) {

    }
}
