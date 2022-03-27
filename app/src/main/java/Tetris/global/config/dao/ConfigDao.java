package Tetris.global.config.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.dao.sqlite.SQLStatement;
import Tetris.global.dao.sqlite.SimpleSQLite;

public class ConfigDao extends SimpleSQLite {

    private static EnumMap<KeyType, String> keyString = new EnumMap<>(KeyType.class);
    private static EnumMap<ColorSet, Integer> colorSetInt = new EnumMap<>(ColorSet.class);

    private static final String table = "Config";
    private static final String[] columns = {
        "id",

        "colorSet",

        "windowSizeW",
        "windowSizeH",

        keyString.get(KeyType.UP),
        keyString.get(KeyType.DOWN),
        keyString.get(KeyType.LEFT),
        keyString.get(KeyType.RIGHT),
        keyString.get(KeyType.OK),
        keyString.get(KeyType.PAUSE),
        keyString.get(KeyType.ROTATE),
    };
    private final String[] dataTypes = {
        "integer",

        "integer",

        "integer",
        "integer",

        "integer",
        "integer",
        "integer",
        "integer",
        "integer",
        "integer",
        "integer",
    };

    private final String[][] optional = {
        {"PRIMARY KEY",},

        {"NOT NULL",},
        {"NOT NULL",},

        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
    };

    private final String conditional = "id = 0";

    public ConfigDao() throws SQLException {
        super();

        keyString.put(KeyType.UP, "keyUp");
        keyString.put(KeyType.DOWN, "keyDown");
        keyString.put(KeyType.LEFT, "keyLeft");
        keyString.put(KeyType.RIGHT, "keyRight");
        keyString.put(KeyType.OK, "keyOk");
        keyString.put(KeyType.PAUSE, "keyPause");
        keyString.put(KeyType.ROTATE, "keyRotate");

        colorSetInt.put(ColorSet.DEFAULT, 0);
        colorSetInt.put(ColorSet.PROTANOPIA, 1);
        colorSetInt.put(ColorSet.TRITANOPIA, 2);
        
        sql = SQLStatement.makeCreateFormat(table, columns, dataTypes, optional);
        stmt.execute(sql);
    }

    public void insertInto() throws SQLException {
        sql = SQLStatement.makeInsertIntoValuesFormat(table, columns);

        pstmt.setInt(1, 0);

        pstmt.setInt(2, colorSetInt.get(MainConfig.getColorSet()));

        pstmt.setInt(3, MainConfig.getWindowSize().getWidth());
        pstmt.setInt(4, MainConfig.getWindowSize().getHeight());

        pstmt.setInt(5, MainConfig.getKeyMap().get(KeyType.UP));
        pstmt.setInt(6, MainConfig.getKeyMap().get(KeyType.DOWN));
        pstmt.setInt(7, MainConfig.getKeyMap().get(KeyType.LEFT));
        pstmt.setInt(8, MainConfig.getKeyMap().get(KeyType.RIGHT));
        pstmt.setInt(9, MainConfig.getKeyMap().get(KeyType.OK));
        pstmt.setInt(10, MainConfig.getKeyMap().get(KeyType.PAUSE));
        pstmt.setInt(11, MainConfig.getKeyMap().get(KeyType.ROTATE));
    
        pstmt.executeUpdate();
    }

    public ResultSet selectFrom() throws SQLException {
        sql = SQLStatement.makeSelectFromFormat(table);

        return stmt.executeQuery(sql);
    }
    
    public void updateFromWhere() throws SQLException {
        sql = SQLStatement.makeUpdateFromWhereFormat(table, columns, conditional);
        pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, 0);

        pstmt.setInt(2, colorSetInt.get(MainConfig.getColorSet()));

        pstmt.setInt(3, MainConfig.getWindowSize().getWidth());
        pstmt.setInt(4, MainConfig.getWindowSize().getHeight());

        pstmt.setInt(5, MainConfig.getKeyMap().get(KeyType.UP));
        pstmt.setInt(6, MainConfig.getKeyMap().get(KeyType.DOWN));
        pstmt.setInt(7, MainConfig.getKeyMap().get(KeyType.LEFT));
        pstmt.setInt(8, MainConfig.getKeyMap().get(KeyType.RIGHT));
        pstmt.setInt(9, MainConfig.getKeyMap().get(KeyType.OK));
        pstmt.setInt(10, MainConfig.getKeyMap().get(KeyType.PAUSE));
        pstmt.setInt(11, MainConfig.getKeyMap().get(KeyType.ROTATE));

        pstmt.executeUpdate();
    }

    public void deleteFromWhere(int id) throws SQLException {
        sql = SQLStatement.makeDeleteFromWhereFormat(table, "id=" + id);

        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }
}
