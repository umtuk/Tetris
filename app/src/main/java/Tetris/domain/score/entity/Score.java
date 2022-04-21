package Tetris.domain.score.entity;

import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;

public class Score {

    private static MainConfig mainConfig = MainConfig.getInstance();
    
    private int id;
    
    private String username;
    private int score;
    private long timestamp;
    private  Difficulty difficulty;
    private int mode;

    public final static int DEFAULT_MODE = 0;
    public final static int ITEM_MODE = 1;

    public Score(String username, int score, int mode) {
        this();

        this.username = username;
        this.score = score;
    }

    public Score(String username, int score, long timestamp, Difficulty difficulty, int mode) {
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.mode = mode;
    }

    public Score(int id, String username, int score, long timestamp, Difficulty difficulty, int mode) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.mode = mode;
    }

    public Score(Difficulty difficulty) {
        this.difficulty = mainConfig.getDifficulty();
        this.timestamp = System.currentTimeMillis();
    }

    public Score() {
        this(mainConfig.getDifficulty());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
