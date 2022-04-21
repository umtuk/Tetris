package Tetris.global.config.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.dao.ConfigDao;
import Tetris.global.config.entity.MainConfig;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigServiceTest {

    private ConfigService configService;
    private ConfigDao configDao;
    private MainConfig mainConfig = MainConfig.getInstance();

    @BeforeAll
    void beforeAll() throws SQLException {
        configService = ConfigService.getInstance();
        configDao = ConfigDao.getInstance();
    }

    @Test
    void testSetColorSet() throws SQLException {
        configService.setColorSet(ColorSet.PROTANOPIA);

        assertTrue(mainConfig.getColorSet() == ColorSet.PROTANOPIA);
        ResultSet rs = configDao.read();

        rs.next();
        assertTrue(rs.getInt("colorSet") == 1);
    }

    @Test
    void testSetDefaultConfig() throws SQLException {
        configService.setDefaultConfig();

        assertTrue(mainConfig.getDifficulty() == Difficulty.NORMAL);
        assertTrue(mainConfig.getColorSet() == ColorSet.DEFAULT);
        assertTrue(mainConfig.getWindowSize() == WindowSize.W800_H600);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_UP) == KeyType.UP);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_DOWN) == KeyType.DOWN);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_LEFT) == KeyType.LEFT);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_RIGHT) == KeyType.RIGHT);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_ENTER) == KeyType.OK);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_ESCAPE) == KeyType.PAUSE);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_R) == KeyType.ROTATE);

        assertTrue(mainConfig.getKeyMap().get(KeyType.UP) == KeyEvent.VK_UP);
        assertTrue(mainConfig.getKeyMap().get(KeyType.DOWN) == KeyEvent.VK_DOWN);
        assertTrue(mainConfig.getKeyMap().get(KeyType.LEFT) == KeyEvent.VK_LEFT);
        assertTrue(mainConfig.getKeyMap().get(KeyType.RIGHT) == KeyEvent.VK_RIGHT);

        assertTrue(mainConfig.getKeyMap().get(KeyType.OK) == KeyEvent.VK_ENTER);
        assertTrue(mainConfig.getKeyMap().get(KeyType.PAUSE) == KeyEvent.VK_ESCAPE);

        assertTrue(mainConfig.getKeyMap().get(KeyType.ROTATE) == KeyEvent.VK_R);

        ResultSet rs = configDao.read();
        configDao.setMainConfig(rs);

        assertTrue(mainConfig.getDifficulty() == Difficulty.NORMAL);
        assertTrue(mainConfig.getColorSet() == ColorSet.DEFAULT);
        assertTrue(mainConfig.getWindowSize() == WindowSize.W800_H600);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_UP) == KeyType.UP);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_DOWN) == KeyType.DOWN);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_LEFT) == KeyType.LEFT);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_RIGHT) == KeyType.RIGHT);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_ENTER) == KeyType.OK);
        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_ESCAPE) == KeyType.PAUSE);

        assertTrue(mainConfig.getKeyMap().get(KeyEvent.VK_R) == KeyType.ROTATE);

        assertTrue(mainConfig.getKeyMap().get(KeyType.UP) == KeyEvent.VK_UP);
        assertTrue(mainConfig.getKeyMap().get(KeyType.DOWN) == KeyEvent.VK_DOWN);
        assertTrue(mainConfig.getKeyMap().get(KeyType.LEFT) == KeyEvent.VK_LEFT);
        assertTrue(mainConfig.getKeyMap().get(KeyType.RIGHT) == KeyEvent.VK_RIGHT);

        assertTrue(mainConfig.getKeyMap().get(KeyType.OK) == KeyEvent.VK_ENTER);
        assertTrue(mainConfig.getKeyMap().get(KeyType.PAUSE) == KeyEvent.VK_ESCAPE);

        assertTrue(mainConfig.getKeyMap().get(KeyType.ROTATE) == KeyEvent.VK_R);
    }

    @Test
    void testSetKeyMap() throws SQLException {
        KeyType keyType = KeyType.OK;
        int keyEvent = KeyEvent.VK_0;

        configService.setKeyMap(keyEvent, keyType);

        assertTrue(mainConfig.getKeyMap().get(keyEvent) == keyType);
        assertTrue(mainConfig.getKeyMap().get(keyType) == keyEvent);

        ResultSet rs = configDao.read();
        configDao.setMainConfig(rs);

        assertTrue(mainConfig.getKeyMap().get(keyEvent) == keyType);
        assertTrue(mainConfig.getKeyMap().get(keyType) == keyEvent);
    }

    @Test
    void testSetWindowSize() throws SQLException {
        WindowSize windowSize = WindowSize.W1920_H1080;

        configService.setWindowSize(windowSize);

        assertTrue(mainConfig.getWindowSize() == windowSize);

        ResultSet rs = configDao.read();
        configDao.setMainConfig(rs);

        assertTrue(mainConfig.getWindowSize() == windowSize);
    }
}
