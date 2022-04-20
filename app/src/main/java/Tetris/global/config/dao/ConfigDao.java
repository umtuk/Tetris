package Tetris.global.config.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.dao.sqlite.SimpleSQLite;

public class ConfigDao {
    private static ConfigDao INSTANCE;

    static {
        try {
            INSTANCE = new ConfigDao();
        } catch (SQLException e) {
        }
    }

    public static ConfigDao getInstance() {
        return INSTANCE;
    }

    private final SimpleSQLite simpleSQLite;
    private MainConfig mainConfig;

    private EnumMap<KeyType, String> keyType2Column;

    private EnumMap<ColorSet, Integer> colorSet2Integer;
    private Map<Integer, ColorSet> integer2ColorSet;
    
    private Map<String, WindowSize> string2WindowSize;
    private EnumMap<WindowSize, String> windowSize2String;

    private EnumMap<Difficulty, Integer> difficulty2Integer;
    private Map<Integer, Difficulty> integer2Difficulty;

    private final String table = "Config";
    private final String[] columns = {
        "id",

        "colorSet",

        "windowSize",

        "keyUp",
        "keyDown",
        "keyleft",
        "keyRight",
        "keyOk",
        "keyPause",
        "keyRotate",

        "difficulty"
    };
    private final String[] dataTypes = {
        "integer",

        "integer",

        "varchar(20)",

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

        {"NOT NULL",},
    };

    private Object[] parameters;

    private void putIntegerNColorSet(Integer i, ColorSet colorSet) {
        integer2ColorSet.put(i, colorSet);
        colorSet2Integer.put(colorSet, i);
    }

    private void putStringNWindowSize(String s, WindowSize windowSize) {
        string2WindowSize.put(s, windowSize);
        windowSize2String.put(windowSize, s);
    }

    private void putIntegerNDifficulty(Integer i, Difficulty difficulty) {
        integer2Difficulty.put(i, difficulty);
        difficulty2Integer.put(difficulty, i);
    }

    private void initMaps() {
        keyType2Column = new EnumMap<>(KeyType.class);

        colorSet2Integer = new EnumMap<>(ColorSet.class);
        integer2ColorSet = new HashMap<>();

        windowSize2String = new EnumMap<>(WindowSize.class);
        string2WindowSize = new HashMap<>();

        difficulty2Integer = new EnumMap<>(Difficulty.class);
        integer2Difficulty = new HashMap<>();

        keyType2Column.put(KeyType.UP, columns[3]);
        keyType2Column.put(KeyType.DOWN, columns[4]);
        keyType2Column.put(KeyType.LEFT, columns[5]);
        keyType2Column.put(KeyType.RIGHT, columns[6]);
        keyType2Column.put(KeyType.OK, columns[7]);
        keyType2Column.put(KeyType.PAUSE, columns[8]);
        keyType2Column.put(KeyType.ROTATE, columns[9]);

        putIntegerNColorSet(0, ColorSet.DEFAULT);
        putIntegerNColorSet(1, ColorSet.PROTANOPIA);
        putIntegerNColorSet(2, ColorSet.TRITANOPIA);

        putStringNWindowSize("W800_H600", WindowSize.W800_H600);
        putStringNWindowSize("W1280_H960", WindowSize.W1280_H960);
        putStringNWindowSize("W1920_H1080", WindowSize.W1920_H1080);
        
        putIntegerNDifficulty(0, Difficulty.EASY);
        putIntegerNDifficulty(1, Difficulty.NORMAL);
        putIntegerNDifficulty(2, Difficulty.HARD);
    }

    public ConfigDao() throws SQLException {
        simpleSQLite = SimpleSQLite.getInstance();
        mainConfig = MainConfig.getInstance();

        initMaps();

        parameters = new Object[11]; setParametersByMainConfig();
        simpleSQLite.createTable(table, columns, dataTypes, optional);

        setMainConfig(read());
    }

    public void setParametersByMainConfig() throws SQLException {

        parameters[0] = 0; // id

        parameters[1] = colorSet2Integer.get(mainConfig.getColorSet()); // colorSet

        parameters[2] = windowSize2String.get(mainConfig.getWindowSize()); // windowSize

        parameters[3] = mainConfig.getKeyMap().get(KeyType.UP); // keyUp
        parameters[4] = mainConfig.getKeyMap().get(KeyType.DOWN); // keyDown
        parameters[5] = mainConfig.getKeyMap().get(KeyType.LEFT); // keyLeft
        parameters[6] = mainConfig.getKeyMap().get(KeyType.RIGHT); // keyRight
        parameters[7] = mainConfig.getKeyMap().get(KeyType.OK); // keyOk
        parameters[8] = mainConfig.getKeyMap().get(KeyType.PAUSE); // keyPause
        parameters[9] = mainConfig.getKeyMap().get(KeyType.ROTATE); // keyRotate

        parameters[10] = difficulty2Integer.get(mainConfig.getDifficulty()); // difficulty
    }

    public void createDefaultIfNotExists() throws SQLException {
        ResultSet rs = simpleSQLite.selectAllFromWhere(table, "id=0");

        if (rs.isClosed()) {
            mainConfig.setDefault();
            setParametersByMainConfig();
            simpleSQLite.insertInto(table, columns, parameters);
        }
    }

    public ResultSet read() throws SQLException {
        return simpleSQLite.selectAllFromWhere(table, "id=0");
    }

    public void setMainConfig(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst())
            rs.next();

        mainConfig.setColorSet(integer2ColorSet.get(rs.getInt("colorSet")));
        mainConfig.setWindowSize(string2WindowSize.get(rs.getString("windowSize")));

        Iterator<KeyType> keyTypes = keyType2Column.keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            mainConfig.updateKeyMap(rs.getInt(keyType2Column.get(keyType)), keyType);
        }

        mainConfig.setDifficulty(integer2Difficulty.get(rs.getInt("difficulty")));
    }
    
    public void update() throws SQLException {
        setParametersByMainConfig();
        simpleSQLite.updateFromWhere(table, columns, "id=0", parameters);
    }

    public void updateDefault() throws SQLException {
        mainConfig.setDefault();
        update();
    }

    public EnumMap<KeyType, String> getKeyType2Column() {
        return keyType2Column;
    }

    public Map<Integer, ColorSet> getInteger2ColorSet() {
        return integer2ColorSet;
    }

    public Map<String, WindowSize> getString2WindowSize() {
        return string2WindowSize;
    }

    public Map<Integer, Difficulty> getInteger2Difficulty() {
        return integer2Difficulty;
    }
}
