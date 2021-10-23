package by.epamtc.enrollmentsystem.dao.interfaces;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface DAOTemplate<T> {

    List<T> getAll() throws DAOException;
    void insertInto(T object) throws DAOException;
    void deleteAll();
    int getNumberOfRecords() throws DAOException;
    List<T> getEntitiesRange(int from,int offset) throws DAOException;
}
