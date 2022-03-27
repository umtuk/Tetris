package Tetris.global.dao.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleSQLite {
    private final String url = "jdbc:sqlite:./tetris.db";

    protected Connection conn;
    protected String sql;

    protected Statement stmt;
    protected PreparedStatement pstmt;

    public SimpleSQLite() throws SQLException {
        conn = DriverManager.getConnection(url);

        stmt = conn.createStatement();
    }
}
