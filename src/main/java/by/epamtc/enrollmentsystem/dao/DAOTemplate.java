package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.services.composers.builders.entity.EntityBuilder;
import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;

public interface DAOTemplate<T> {

    public List<T> getAll(String tableName, EntityBuilder<T> entityBuilder) throws DAOException;
    public T getByID(String tableName,String idField,int id,EntityBuilder<T> entityBuilder) throws DAOException;
    public int getIdByName(String tableName,String idField,String nameField,String nameValue) throws DAOException;
    public void insertInto(T object) throws DAOException;
    public void updateRowByID(T note,int id) throws DAOException;
    public void deleteAll();
}
