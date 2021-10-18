package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.tables.fields.EducationFormFields;
import by.epamtc.enrollmentsystem.dao.tables.fields.EnrollmentStatusFields;
import by.epamtc.enrollmentsystem.dao.templates.EnrollmentStatusDAO;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.EnrollmentStatus;
import by.epamtc.enrollmentsystem.dao.composers.builders.entity.EnrollmentStatusBuilder;

import java.util.List;

public class EnrollmentStatusMySQL extends AbstractDAO<EnrollmentStatus> implements EnrollmentStatusDAO {
    @Override
    public List<EnrollmentStatus> getAll() throws DAOException {
        String tableName = TablesNames.enrollment_status;
        return super.getAll(tableName,new EnrollmentStatusBuilder());
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        String tableName = TablesNames.enrollment_status;
        String idField = EnrollmentStatusFields.id;
        String nameField = EnrollmentStatusFields.name;
        return super.getIdByName(tableName,idField,nameField,name);
    }

    @Override
    public EnrollmentStatus getByID(long id) throws DAOException {
        String tableName = TablesNames.enrollment_status;
        String idField = EnrollmentStatusFields.id;
        return super.getByID(tableName,idField,id,new EnrollmentStatusBuilder());
    }

    @Override
    public String getNameById(long id) throws DAOException {
        String tableName = TablesNames.enrollment_status;
        String nameField = EnrollmentStatusFields.name;
        String idField = EnrollmentStatusFields.id;
        return super.getNameById(tableName,idField,nameField,id);
    }
}
