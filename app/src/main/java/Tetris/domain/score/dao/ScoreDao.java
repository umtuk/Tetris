package Tetris.domain.score.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Tetris.domain.score.entity.Score;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.dao.sqlite.SimpleSQLite;

public class ScoreDao {
    
    private static ScoreDao INSTANCE;

    static {
        try {
            INSTANCE = new ScoreDao();
        } catch (SQLException e) {
            
        }
    }

    public static ScoreDao getInstance() {
        return INSTANCE;
    }

    private final String table = "Score";
    private final String[] columns = {
        "id",

        "username",
        "score",
        "timestamp",
        "difficulty",
    };

    private final String[] createColumns = {
        "username",
        "score",
        "timestamp",
        "difficulty",
    };

    private final String[] dataTypes = {
        "integer",

        "varchar(20)",
        "integer",
        "integer",
        "integer",
    };

    private final String[][] optional = {
        {"PRIMARY KEY"},

        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
        {"NOT NULL",},
    };

    private final SimpleSQLite simpleSQLite;

    private Object[] parameters;

    private EnumMap<Difficulty, Integer> difficulty2Integer;
    private Map<Integer, Difficulty> integer2Difficulty;

    public ScoreDao() throws SQLException {
        simpleSQLite = SimpleSQLite.getInstance();

        parameters = new Object[4];
        initMaps();
        simpleSQLite.createTable(table, columns, dataTypes, optional);
    }

    private void putIntegerNDifficulty(Integer i, Difficulty difficulty) {
        integer2Difficulty.put(i, difficulty);
        difficulty2Integer.put(difficulty, i);
    }

    private void initMaps() {
        difficulty2Integer = new EnumMap<>(Difficulty.class);
        integer2Difficulty = new HashMap<>();

        putIntegerNDifficulty(0, Difficulty.EASY);
        putIntegerNDifficulty(1, Difficulty.NORMAL);
        putIntegerNDifficulty(2, Difficulty.HARD);
    }

    private void setParameters(Score score) {
        parameters[0] = score.getUsername();
        parameters[1] = (Integer)score.getScore();
        parameters[2] = (Long)score.getTimestamp();
        parameters[3] = difficulty2Integer.get(score.getDifficulty());
    }

    private List<Score> toEntity(ResultSet rs) throws SQLException {

        List<Score> ret = new ArrayList<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            int score = rs.getInt("score");
            long timestamp = rs.getLong("timestamp");
            Difficulty difficulty = integer2Difficulty.get(rs.getInt("difficulty"));

            ret.add(new Score(id, username, score, timestamp, difficulty));
        }

        return ret;
    }

    public List<Score> readAll() throws SQLException {
        ResultSet rs = simpleSQLite.selectAllFrom(table);
        return toEntity(rs);
    }

    public void create(Score score) throws SQLException {
        setParameters(score);
        simpleSQLite.insertInto(table, createColumns, parameters);
    }

    public void delete(int id) throws SQLException {
        simpleSQLite.deleteFromWhere(table, "id=" + id);
    }

    public void deleteAll() throws SQLException {
        simpleSQLite.deleteFromWhere(table, "TRUE");
    }
}
