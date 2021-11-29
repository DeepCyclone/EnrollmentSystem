package by.epamtc.enrollmentsystem.service.util;
/*
 * Helper utility that generates basic queries
 */
public class QueryGenerator {
    private static final String SELECT_ALL = "SELECT * FROM ";
    private static final String DELETE_ALL = "DELETE * FROM ";

    public static String generateSelectAllQuery(String tableName){
        StringBuilder query = new StringBuilder(SELECT_ALL);
        query.append(tableName);
        return query.toString();
    }

    public static String generateGetByIdPreparedQuery(String tableName,String idFieldName){
        StringBuilder query = new StringBuilder(SELECT_ALL).append(tableName).append(" WHERE ").append(idFieldName).append("= ?");
        return query.toString();
    }

    public static String getByNamePreparedQuery(String tableName,String nameField){
        StringBuilder query = new StringBuilder(SELECT_ALL).append(tableName).append(" WHERE ").append(nameField).append("= ?");
        return query.toString();
    }

}
