package Tetris.domain.score.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Tetris.global.config.constant.Difficulty;

public class ScoreTest {

    private final String username = "username";
    private final int scr = 320;
    private final Long timestamp = System.currentTimeMillis();
    private final Difficulty difficulty = Difficulty.EASY;

    private Score score = new Score(username, scr, timestamp, difficulty);

    @Test
    void testGetDifficulty() {
        assertTrue(score.getDifficulty() == difficulty);
    }

    @Test
    void testGetScore() {
        assertTrue(score.getScore() == scr);
    }

    @Test
    void testGetTimestamp() {
        assertTrue(score.getTimestamp() == timestamp);
    }

    @Test
    void testGetUsername() {
        assertTrue(username.equals(score.getUsername()));
    }

    @Test
    void testSetScore() {
        final int expected = 225;
        score.setScore(expected);
        assertTrue(score.getScore() == expected);
    }

    @Test
    void testSetTimestamp() {
        final long expected = System.currentTimeMillis();
        score.setTimestamp(expected);
        assertTrue(score.getTimestamp() == expected);
    }

    @Test
    void testSetUsername() {
        final String expected = "namename";
        score.setUsername("namename");
        assertTrue(expected.equals(score.getUsername()));
    }
}
