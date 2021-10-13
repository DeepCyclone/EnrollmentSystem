package by.epamtc.enrollmentsystem.utils;

public class SQLGenerator {
    private static final String SELECT_ALL = "SELECT * FROM ";
    public static String generateSelectAllQuery(String tableName){
        StringBuilder query = new StringBuilder(SELECT_ALL);
        query.append(tableName);
        return query.toString();
    }
    public static String generateGetByIdQuery(String tableName,String idFieldName,int id){
        StringBuilder query = new StringBuilder(SELECT_ALL);
        query.append(tableName);
        query.append(" WHERE ");
        query.append(idFieldName);
        query.append("=");
        query.append(id);
        return query.toString();
    }
    public static String getIdByNameQuery(String tableName,String idField,String nameField,String nameValue){
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(idField);
        query.append(" FROM ");
        query.append(tableName);
        query.append(" WHERE ");
        query.append(nameField);
        query.append("=");
        query.append("'");
        query.append(nameValue);
        query.append("'");
        return query.toString();
    }
}
