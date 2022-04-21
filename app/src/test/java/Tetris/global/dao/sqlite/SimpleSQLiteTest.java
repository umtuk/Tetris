package Tetris.global.dao.sqlite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleSQLiteTest {

    private SimpleSQLite simpleSQLite;

    private final String table = "test";
    private final String[] columns = { "column1", "column2" };
    private final String[] dataTypes = { "integer", "varchar(20)" };
    private final String[][] optional = { { "PRIMARY KEY" }, { "NOT NULL" } };
    private final String conditional = "column1=0";

    private final Object[][] beforeInsertParameters = {
        {0, "data0"},
        {1, "data1"},
        {2, "data2"},
    };

    @BeforeAll
    void beforeAll() throws SQLException, ClassNotFoundException {
        simpleSQLite = SimpleSQLite.getInstance();
        simpleSQLite.createTable(table, columns, dataTypes, optional);
        for (Object[] parameters : beforeInsertParameters)
            simpleSQLite.insertInto(table, columns, parameters);
    }

    @Test
    void testCreateTable() throws SQLException {
        simpleSQLite.createTable(table, columns, dataTypes, optional);
    }

    @Test
    void testDeleteFromWhere() throws SQLException {
        simpleSQLite.deleteFromWhere(table, conditional);

        ResultSet rs = simpleSQLite.selectAllFrom(table);
        
        int cnt = 0;
        while (rs.next())
            cnt++;
        assertTrue(cnt == beforeInsertParameters.length - 1);

        simpleSQLite.insertInto(table, columns, beforeInsertParameters[0]);
    }

    @Test
    void testInsertInto() throws SQLException {
        final Object[] parameters = {3, "data3"};
        simpleSQLite.insertInto(table, columns, parameters);

        simpleSQLite.deleteFromWhere(table, "column1=3");
    }

    @Test
    void testSelectAllFrom() throws SQLException {
        ResultSet rs = simpleSQLite.selectAllFrom(table);
        int cnt = 0;
        while (rs.next())
            cnt++;
        assertTrue(cnt == beforeInsertParameters.length);
    }

    @Test
    void testUpdateFromWhere() throws SQLException {
        Object[] parameters = {0, "hello"};
        simpleSQLite.updateFromWhere(table, columns, conditional, parameters);

        ResultSet rs = simpleSQLite.selectAllFromWhere(table, conditional); rs.next();
        assertTrue(rs.getString("column2").equals("hello"));
    }

    @AfterAll
    void afterAll() throws SQLException {
        simpleSQLite.execute(" DROP TABLE " + table);
    }
}
