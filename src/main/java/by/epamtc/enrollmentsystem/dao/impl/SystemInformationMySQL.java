package by.epamtc.enrollmentsystem.dao.impl;

import by.epamtc.enrollmentsystem.dao.AbstractDAO;
import by.epamtc.enrollmentsystem.dao.tables.TablesNames;
import by.epamtc.enrollmentsystem.dao.connectionpool.ConnectionPool;
import by.epamtc.enrollmentsystem.dao.templates.SystemInformationTempl;
import by.epamtc.enrollmentsystem.exception.DAOException;
import by.epamtc.enrollmentsystem.model.SystemInformation;
import by.epamtc.enrollmentsystem.dao.tables.fields.SystemInformationFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SystemInformationMySQL extends AbstractDAO<SystemInformation> implements SystemInformationTempl {
    private static final String GET_BY_NAME = "SELECT "+ SystemInformationFields.value +
                                              " FROM " + TablesNames.system_information +
                                              " WHERE " + SystemInformationFields.name +  "= ?";


    @Override
    public List<SystemInformation> getAll() throws DAOException {
        return null;
    }

    @Override
    public int getIdByName(String name) throws DAOException {
        return 0;
    }

    @Override
    public SystemInformation getByID(int id) throws DAOException {
        return null;
    }

    @Override
    public void insertInto(SystemInformation object) throws DAOException {
    }

    @Override
    public SystemInformation getValueByName(String name) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            SystemInformation si = new SystemInformation();
            while(rs.next()) {
                si.setName(name);
                si.setValue(rs.getString(1));
            }
            return si;
        }
        catch (SQLException e){
            throw new DAOException(e.getMessage(),e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(conn,preparedStatement,rs);
        }

    }

    @Override
    public void deleteAll() {

    }


    @Override
    public void updateRowByID(SystemInformation note, int id) {

    }
}