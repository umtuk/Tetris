package Tetris.domain.score.service;

import java.sql.SQLException;
import java.util.List;

import Tetris.domain.score.dao.ScoreDao;
import Tetris.domain.score.entity.Score;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;

public class ScoreService {
    
    private static ScoreService INSTANCE = new ScoreService();

    public static ScoreService getInstance() {
        return INSTANCE;
    }
    
    private final ScoreDao scoreDao;
    private  MainConfig mainConfig;

    private Score score;

    public ScoreService() {
        scoreDao = ScoreDao.getInstance();
        mainConfig = MainConfig.getInstance();
        score = new Score();
    }

    public Score getScore() {
        return score;
    }

    public List<Score> readAll() throws SQLException {
        return scoreDao.readAll();
    }

    public void updateScore(int deletedLines, double clock) {
        int updated = score.getScore();
        Difficulty diff = mainConfig.getDifficulty();
        double weight = 0;
        if (diff == Difficulty.EASY) {
            weight = 1500.0;
        }
        else if (diff == Difficulty.NORMAL) {
            weight = 2000.0;
        }
        else if (diff == Difficulty.HARD) {
            weight = 2500.0;
        }
        else {
            weight = 1.0;
        }

        updated += (int)(weight * deletedLines / clock);
        score.setScore(updated);
    }

    public void setUsername(String username) {
        score.setUsername(username);
    }

    public void insertScore() throws SQLException {
        scoreDao.create(score);
    }

    public void init() {
        score = new Score();
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
