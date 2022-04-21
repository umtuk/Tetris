package Tetris.global.dao.sqlite;

public class SQLStatement {
    private static SQLStatement INSTANCE = new SQLStatement();

    public static SQLStatement getInstance() {
        return INSTANCE;
    }
        
    private final String CREATE_TABLE_IF_NOT_EXISTS = " CREATE TABLE IF NOT EXISTS ";
    private final String INSERT_INTO = " INSERT INTO ";
    private final String VALUES = " VALUES ";
    private final String SELECT = " SELECT ";
    private final String FROM = " FROM ";
    private final String WHERE = " WHERE ";
    private final String UPDATE = " UPDATE ";
    private final String SET = " SET ";
    private final String DELETE_FROM = " DELETE FROM ";

    private String makeListFormat(String[] values) {
        return String.join(",", values);
    }

    private String makeRoundFormat(String value) {
        return "(" + value + ")";
    }

    private String makeListNRoundFormat(String[] values) {
        return makeRoundFormat(makeListFormat(values));
    }

    private String makeAssignFormat(String value) {
        return value + "=?";
    }

    private String[] makeAssignFormat(String[] values) {
        int n = values.length;
        String[] ret = new String[n];

        for (int i = 0; i < n; i++)
            ret[i] = makeAssignFormat(values[i]);

        return ret;
    }

    private String makeAssignNListFormat(String[] values) {
        return makeListFormat(makeAssignFormat(values));
    }

    private String makeUndefinedValueListNRoundFormat(int n) {
        if (n == 0) return "";
        
        String ret = "?";
        for (int i = 1; i < n; i++)
            ret += ",?";
        
        return makeRoundFormat(ret);
    }

    public String makeCreateFormat(String table, String[] columns, String[] dataTypes, String[][] optional) {
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

    public String makeInsertIntoValuesFormat(String table, String[] columns) {
        return INSERT_INTO + table
            + makeListNRoundFormat(columns)
            + VALUES + makeUndefinedValueListNRoundFormat(columns.length)
        ;
    }

    public String makeSelectAllFromFormat(String table) {
        return SELECT  + "*"
            + FROM + table
        ;
    }

    public String makeSelectFromFormat(String table, String[] columns) {
        return SELECT + makeListFormat(columns)
            + FROM + table
        ;
    }

    public String makeSelectAllFromWhereFormat(String table, String conditional) {
        return SELECT  + "*"
            + FROM + table
            + WHERE + conditional
        ;
    }

    public String makeSelectFromWhereFormat(String table, String[] columns, String conditional) {
        return SELECT + makeListFormat(columns)
            + FROM + table
            + WHERE + conditional
        ;
    }

    public String makeUpdateFromWhereFormat(String table, String[] columns, String conditional) {
        return UPDATE + table
            + SET + makeAssignNListFormat(columns)
            + WHERE + conditional
        ;
    }

    public String makeDeleteFromFormat(String table) {
        return DELETE_FROM + table
        ;
    }

    public String makeDeleteFromWhereFormat(String table, String conditional) {
        return DELETE_FROM + table
            + WHERE + conditional
        ;
    }
}
