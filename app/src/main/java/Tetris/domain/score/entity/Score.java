package Tetris.domain.score.entity;

import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;

public class Score {

    private static MainConfig mainConfig = MainConfig.getInstance();
    
    private int id;
    
    private String username;
    private int score;
    private long timestamp;
    private final Difficulty difficulty;

    public Score(String username, int score, long timestamp, Difficulty difficulty) {
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
    }

    public Score(int id, String username, int score, long timestamp, Difficulty difficulty) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
    }

    public Score(Difficulty difficulty) {
        this.difficulty = difficulty;
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
}
