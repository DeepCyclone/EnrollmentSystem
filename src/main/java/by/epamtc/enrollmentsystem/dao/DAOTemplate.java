package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;

public interface DAOTemplate<T> {

//    List<T> getAll(String tableName, EntityBuilder<T> entityBuilder) throws DAOException;
    List<T> getAll() throws DAOException;
//    T getByID(String tableName,String idField,int id,EntityBuilder<T> entityBuilder) throws DAOException;
    T getByID(long id) throws DAOException;
//    int getIdByName(String tableName,String idField,String nameField,String nameValue) throws DAOException;
    int getIdByName(String name) throws DAOException;
    String getNameById(long id) throws DAOException;
    void insertInto(T object) throws DAOException;
    void updateRowByID(T note,long id) throws DAOException;
    void deleteAll();
}
