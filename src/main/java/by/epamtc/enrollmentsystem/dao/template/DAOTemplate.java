package by.epamtc.enrollmentsystem.dao.template;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;


/*
 * An interface providing basic templates of supported methods
 */
public interface DAOTemplate<T> {

    List<T> getAll() throws DAOException;
    void insertInto(T object) throws DAOException;
    void deleteAll() throws DAOException;
    int getNumberOfRecords() throws DAOException;
    List<T> getEntitiesRange(int from,int offset) throws DAOException;
}
