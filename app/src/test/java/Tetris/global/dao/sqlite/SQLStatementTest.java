package Tetris.global.dao.sqlite;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SQLStatementTest {

    private final SQLStatement sqlStatement = SQLStatement.getInstance();

    private String table = "table";
    private String[] columns = { "column1", "column2" };
    private String[] dataTypes = { "integer", "varchar(20)" };
    private String[][] optional = { { "PRIMARY KEY" }, { "NOT NULL" } };
    private String conditional = "id=0";

    @Test
    void testMakeCreateFormat() {
        String sql = sqlStatement.makeCreateFormat(table, columns, dataTypes, optional);
        String expected = " CREATE TABLE IF NOT EXISTS table(column1 integer PRIMARY KEY,column2 varchar(20) NOT NULL);";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeDeleteFromFormat() {
        String sql = sqlStatement.makeDeleteFromFormat(table);
        String expected = " DELETE FROM table";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeDeleteFromWhereFormat() {
        String sql = sqlStatement.makeDeleteFromWhereFormat(table, conditional);
        String expected = " DELETE FROM table WHERE id=0";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeInsertIntoValuesFormat() {
        String sql = sqlStatement.makeInsertIntoValuesFormat(table, columns);
        String expected = " INSERT INTO table(column1,column2) VALUES (?,?)";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeSelectAllFromFormat() {
        String sql = sqlStatement.makeSelectAllFromFormat(table);
        String expected = " SELECT * FROM table";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeSelectFromFormat() {
        String sql = sqlStatement.makeSelectFromFormat(table, columns);
        String expected = " SELECT column1,column2 FROM table";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeSelectAllFromWhereFormat() {
        String sql = sqlStatement.makeSelectAllFromWhereFormat(table, conditional);
        String expected = " SELECT * FROM table WHERE id=0";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeSelectFromWhereFormat() {
        String sql = sqlStatement.makeSelectFromWhereFormat(table, columns, conditional);
        String expected = " SELECT column1,column2 FROM table WHERE id=0";

        assertTrue(sql.equals(expected));
    }

    @Test
    void testMakeUpdateFromWhereFormat() {
        String sql = sqlStatement.makeUpdateFromWhereFormat(table, columns, conditional);
        String expected = " UPDATE table SET column1=?,column2=? WHERE id=0";

        assertTrue(sql.equals(expected));
    }
}
