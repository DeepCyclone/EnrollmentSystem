package by.epamtc.enrollmentsystem.dao.templates;

import by.epamtc.enrollmentsystem.exception.DAOException;

import java.util.List;

public interface DAOTemplate<T> {

    public List<T> getAll() throws DAOException;
    public T getByID(int id) throws DAOException;
    public void insertInto(T object) throws DAOException;
    public void updateRowByID(T note,int id) throws DAOException;
    public void deleteAll();
}
