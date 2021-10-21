package by.epamtc.enrollmentsystem.dao;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface DAOTemplate<T> {

    List<T> getAll() throws DAOException;
    Optional<T> getByID(long id) throws DAOException;
    long getIdByName(String name) throws DAOException;
    String getNameById(long id) throws DAOException;
    void insertInto(T object) throws DAOException;
    void updateRowByID(T note) throws DAOException;
    void deleteAll();
    int getNumberOfRecords(String tableName) throws DAOException;
    List<T> getEntitiesRange(int from,int offset) throws DAOException;
}
