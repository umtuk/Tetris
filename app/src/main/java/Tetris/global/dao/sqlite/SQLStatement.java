package Tetris.global.dao.sqlite;

public class SQLStatement {
        
    private static final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS ";
    private static final String INSERT_INTO = " INSERT INTO ";
    private static final String VALUES = " VALUES ";
    private static final String SELECT = " SELECT ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String UPDATE = " UPDATE ";
    private static final String SET = " SET ";
    private static final String DELETE_FROM = " DELETE FROM ";

    private static String makeListFormat(String[] values) {
        return String.join(",", values);
    }

    private static String makeRoundFormat(String value) {
        return "(" + value + ")";
    }

    private static String makeListNRoundFormat(String[] values) {
        return makeRoundFormat(makeListFormat(values));
    }

    private static String makeAssignFormat(String value) {
        return value + "=?";
    }

    private static String[] makeAssignFormat(String[] values) {
        int n = values.length;
        String[] ret = new String[n];

        for (int i = 0; i < n; i++)
            ret[i] = makeAssignFormat(values[i]);

        return ret;
    }

    private static String makeAssignNListFormat(String[] values) {
        return makeListFormat(makeAssignFormat(values));
    }

    private static String makeUndefinedValueListNRoundFormat(int n) {
        if (n == 0) return "";
        
        String ret = "?";
        for (int i = 1; i < n; i++)
            ret += ",?";
        
        return makeRoundFormat(ret);
    }

    public static String makeCreateFormat(String table, String[] columns, String[] dataTypes, String[][] optional) {
        String columnList = "";
        for (int i = 0; i < columns.length; i++) {
            columnList += columns[i] + " " + dataTypes[i]; 
            for (int j = 0; j < optional[i].length; j++)
                columnList += " " + optional[i][j];
            
            if (i != columns.length - 1)
                columnList += ",";
        }

        return CREATE_TABLE_IF_NOT_EXISTS + table
            + makeRoundFormat(columnList) + ";"
        ;
    }

    public static String makeInsertIntoValuesFormat(String table, String[] columns) {
        return INSERT_INTO + table
            + makeListNRoundFormat(columns)
            + VALUES + makeUndefinedValueListNRoundFormat(columns.length)
        ;
    }

    public static String makeSelectFromFormat(String table) {
        return SELECT  + "*"
            + FROM + table
        ;
    }

    public static String makeSelectFromFormat(String table, String[] columns) {
        return SELECT + makeListFormat(columns)
            + FROM + table
        ;
    }

    public static String makeUpdateFromWhereFormat(String table, String[] columns, String conditional) {
        return UPDATE + table
            + SET + makeAssignNListFormat(columns)
            + WHERE + conditional
        ;
    }

    public static String makeDeleteFromFormat(String table) {
        return DELETE_FROM + table
        ;
    }

    public static String makeDeleteFromWhereFormat(String table, String conditional) {
        return DELETE_FROM + table
            + WHERE + conditional
        ;
    }
}
