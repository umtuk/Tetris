package Tetris.domain.score.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import Tetris.domain.score.dao.ScoreDao;
import Tetris.domain.score.entity.Score;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;

public class ScoreServiceTest {

    private final ScoreService scoreService = ScoreService.getInstance();
    private final MainConfig mainConfig = MainConfig.getInstance();
    private final ScoreDao scoreDao = ScoreDao.getInstance();

    @Test
    void testCreateScore() {
        long currentMillis = System.currentTimeMillis();
        Score score = scoreService.createScore();

        assertTrue(score.getDifficulty() == mainConfig.getDifficulty());
        assertTrue(Math.abs(currentMillis - score.getTimestamp()) < 1000);
    }

    @Test
    void testInsertScore() throws SQLException {
        String username = "user1";
        int scr = 322342;
        long timestamp = 3213214124L;
        Difficulty difficulty = Difficulty.HARD;

        Score score = new Score(username, scr, timestamp, difficulty);

        scoreService.insertScore(score);

        List<Score> result = scoreDao.readAll();

        boolean isInserted = false;
        for (Score now : result) {
            if (username.equals(now.getUsername()) && scr == now.getScore() && timestamp == now.getTimestamp() && difficulty == now.getDifficulty()) {
                isInserted = true;
                scoreDao.delete(now.getId());
                break;
            }
        }

        assertTrue(isInserted);
    }

    @Test
    void testReadAll() throws SQLException {
        List<Score> result = scoreService.readAll();
    }

    @Test
    void testSetUsername() {
        Score score = new Score(Difficulty.EASY);
        String expected = "HELLO";
        scoreService.setUsername(score, expected);
        assertTrue(score.getUsername().equals(expected));
    }

    @Test
    void testUpdateScore() {
        Score score = new Score(mainConfig.getDifficulty());
        int prevScore = score.getScore();

        scoreService.updateScore(score, 1, 2);

        assertTrue(prevScore < score.getScore());
    }
}
