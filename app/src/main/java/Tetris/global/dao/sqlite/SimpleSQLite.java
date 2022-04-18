package Tetris.global.dao.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleSQLite {
    private static SimpleSQLite INSTANCE;

    static {
        try {
            INSTANCE = new SimpleSQLite();
        } catch (Exception e) {
        }
    }

    public static SimpleSQLite getInstance() {
        return INSTANCE;
    }

    private final SQLStatement sqlStatement = SQLStatement.getInstance();
    private final String url = "jdbc:sqlite:./tetris.db";

    private Connection conn;
    private String sql;

    private Statement stmt;
    private PreparedStatement pstmt;

    public SimpleSQLite() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);

        stmt = conn.createStatement();
    }

    private void pstmtSetInt(int parameterIndex, Integer x) throws SQLException {
        pstmt.setInt(parameterIndex, x);
    }

    private void pstmtSetLong(int parameterIndex, Long x) throws SQLException {
        pstmt.setLong(parameterIndex, x);
    }

    private void pstmtSetString(int parameterIndex, String x) throws SQLException {
        pstmt.setString(parameterIndex, x);
    }

    private void pstmtSet(Object[] parameters) throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] instanceof Integer)
                pstmtSetInt(i + 1, (Integer) parameters[i]);
            else if (parameters[i] instanceof String)
                pstmtSetString(i + 1, (String) parameters[i]);
            else if (parameters[i] instanceof Long)
                pstmtSetLong(i + 1, (Long) parameters[i]);
        }
    }

    public void createTable(String table, String[] columns, String[] dataTypes, String[][] optional) throws SQLException {
        sql = sqlStatement.makeCreateFormat(table, columns, dataTypes, optional);
        stmt.execute(sql);
    }

    public void insertInto(String table, String[] columns, Object[] parameters) throws SQLException {
        sql = sqlStatement.makeInsertIntoValuesFormat(table, columns);
        pstmt = conn.prepareStatement(sql); pstmtSet(parameters);
        pstmt.executeUpdate();
    }

    public ResultSet selectAllFrom(String table) throws SQLException {
        sql = sqlStatement.makeSelectAllFromFormat(table);
        return stmt.executeQuery(sql);
    }

    public ResultSet selectFrom(String table, String[] columns) throws SQLException {
        sql = sqlStatement.makeSelectFromFormat(table, columns);
        return stmt.executeQuery(sql);
    }

    public ResultSet selectAllFromWhere(String table, String conditional) throws SQLException {
        sql = sqlStatement.makeSelectAllFromWhereFormat(table, conditional);
        return stmt.executeQuery(sql);
    }

    public ResultSet selectFromWhere(String table, String[] columns, String conditional) throws SQLException {
        sql = sqlStatement.makeSelectFromWhereFormat(table, columns, conditional);
        return stmt.executeQuery(sql);
    }
    
    public void updateFromWhere(String table, String[] columns, String conditional, Object[] parameters) throws SQLException {
        sql = sqlStatement.makeUpdateFromWhereFormat(table, columns, conditional);
        pstmt = conn.prepareStatement(sql); pstmtSet(parameters);
        pstmt.executeUpdate();
    }

    public void deleteFromWhere(String table, String conditional) throws SQLException {
        sql = sqlStatement.makeDeleteFromWhereFormat(table, conditional);
        stmt.executeUpdate(sql);
    }

    public void execute(String sql) throws SQLException {
        this.sql = sql;
        stmt.execute(this.sql);
    }
}
