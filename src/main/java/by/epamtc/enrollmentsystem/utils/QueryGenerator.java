package by.epamtc.enrollmentsystem.utils;

public class QueryGenerator {//singleton
    private static final String SELECT_ALL = "SELECT * FROM ";
    public static String generateSelectAllQuery(String tableName){
        StringBuilder query = new StringBuilder(SELECT_ALL);
        query.append(tableName);
        return query.toString();
    }
    public static String generateGetByIdPreparedQuery(String tableName,String idFieldName){
        StringBuilder query = new StringBuilder(SELECT_ALL);
        query.append(tableName);
        query.append(" WHERE ");
        query.append(idFieldName);
        query.append("=");
        query.append("?");
        return query.toString();
    }
    public static String getIdByNamePreparedQuery(String tableName,String idField,String nameField){
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(idField);
        query.append(" FROM ");
        query.append(tableName);
        query.append(" WHERE ");
        query.append(nameField);
        query.append("=");
        query.append("?");
        return query.toString();
    }
    public static String getNameByIdQuery(String tableName,String idField,String nameField,long id) {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(nameField);
        query.append(" FROM ");
        query.append(tableName);
        query.append(" WHERE ");
        query.append(idField);
        query.append("=");
        query.append("'");
        query.append(id);
        query.append("'");
        return query.toString();
    }
}
