package Tetris.domain.score.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Tetris.domain.score.entity.Score;
import Tetris.global.config.constant.Difficulty;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScoreDaoTest {

    private ScoreDao scoreDao = ScoreDao.getInstance();
    private List<Score> prev;

    @BeforeAll
    public void beforeAll() throws SQLException {
        prev = scoreDao.readAll();
        scoreDao.deleteAll();
        
    }

    @Test
    void testCreate() throws SQLException {
        String username = "user1";
        int scr = 322;
        long timestamp = 3213214124L;
        Difficulty difficulty = Difficulty.HARD;
        int mode = Score.DEFAULT_MODE;

        Score score = new Score(username, scr, timestamp, difficulty, mode);

        scoreDao.create(score);

        List<Score> results = scoreDao.readAll();
        Score result = new Score(Difficulty.EASY);

        for (Score now : results) {
            if (now.getUsername().equals(username)) {
                result = now;
                break;
            }
        }

        assertTrue(result.getUsername().equals(username));
        assertTrue(result.getScore() == scr);
        assertTrue(result.getTimestamp() == timestamp);
        assertTrue(result.getDifficulty() == difficulty);
    }

    @Test
    void testDelete() throws SQLException {
        String username = "user2";
        int scr = 322;
        long timestamp = 3213214124L;
        Difficulty difficulty = Difficulty.HARD;
        int mode = Score.DEFAULT_MODE;

        Score score = new Score(username, scr, timestamp, difficulty, mode);

        scoreDao.create(score);

        List<Score> before = scoreDao.readAll();

        int deleteId = 0;

        for (Score now : before) {
            if (now.getUsername().equals(username)) {
                deleteId = now.getId();
                scoreDao.delete(deleteId);
                break;
            }
        }

        List<Score> after = scoreDao.readAll();

        for (Score now : after) {
            assertTrue(now.getId() != deleteId);
        }
    }

    @Test
    void testDeleteAll() throws SQLException{
        String username = "user3";
        int scr = 322;
        long timestamp = 3213214124L;
        Difficulty difficulty = Difficulty.HARD;
        int mode = Score.DEFAULT_MODE;

        Score score = new Score(username, scr, timestamp, difficulty, mode);

        scoreDao.create(score);

        scoreDao.deleteAll();

        List<Score> result = scoreDao.readAll();

        assertTrue(result.size() == 0);
    }

    @Test
    void testReadAll() throws SQLException {
        String username = "user3";
        int scr = 322;
        long timestamp = 3213214124L;
        Difficulty difficulty = Difficulty.HARD;
        int mode = Score.DEFAULT_MODE;

        Score score = new Score(username, scr, timestamp, difficulty, mode);

        scoreDao.create(score);

        List<Score> result = scoreDao.readAll();

        assertTrue(result.size() != 0);
    }

    @AfterAll
    void afterAll() throws SQLException {
        scoreDao.deleteAll();
        for (Score now : prev)
            scoreDao.create(now);
    }
}
