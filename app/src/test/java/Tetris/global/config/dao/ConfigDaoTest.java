package Tetris.global.config.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.config.entity.branch.KeyMap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConfigDaoTest {

    private final ConfigDao configDao = ConfigDao.getInstance();
    private MainConfig mainConfig = MainConfig.getInstance();
    private ResultSet prev;

    @BeforeAll
    public void beforeAll() throws SQLException {
        prev = configDao.read();
        configDao.createDefaultIfNotExists();
        configDao.setMainConfig(configDao.read());
    }

    @Test
    void testCreateDefaultIfNotExists() throws SQLException {
        configDao.createDefaultIfNotExists();

        ResultSet rs = configDao.read(); rs.next();

        mainConfig.setDefault();

        assertTrue(configDao.getInteger2ColorSet().get(rs.getInt("colorSet")) == mainConfig.getColorSet());
        assertTrue(configDao.getString2WindowSize().get(rs.getString("windowSize")) == mainConfig.getWindowSize());

        Iterator<KeyType> keyTypes = configDao.getKeyType2Column().keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            //assertSame(rs.getInt(configDao.getKeyType2Column().get(keyType)), mainConfig.getKeyMap().get(keyType));
        }

        assertTrue(configDao.getInteger2Difficulty().get(rs.getInt("difficulty")) == mainConfig.getDifficulty());
    }

    @Test
    void testRead() throws SQLException {
        ResultSet rs = configDao.read(); rs.next();

        assertNotNull(rs.getInt("id"));
        assertNotNull(rs.getInt("colorSet"));
        assertNotNull(rs.getString("windowSize"));

        KeyMap keyMap = new KeyMap(); keyMap.setDefault();
        Iterator<KeyType> keyTypes = configDao.getKeyType2Column().keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            assertNotNull(rs.getInt(configDao.getKeyType2Column().get(keyType)));
        }

        assertNotNull(rs.getInt("difficulty"));
    }

    @Test
    void testSetMainConfig() throws SQLException {
        ResultSet rs = configDao.read();

        configDao.setMainConfig(rs);

        assertTrue(configDao.getInteger2ColorSet().get(rs.getInt("colorSet")) == mainConfig.getColorSet());
        assertTrue(configDao.getString2WindowSize().get(rs.getString("windowSize")) == mainConfig.getWindowSize());

        Iterator<KeyType> keyTypes = configDao.getKeyType2Column().keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            assertTrue(rs.getInt(configDao.getKeyType2Column().get(keyType)) == mainConfig.getKeyMap().get(keyType));
        }

        assertTrue(configDao.getInteger2Difficulty().get(rs.getInt("difficulty")) == mainConfig.getDifficulty());
    }

    @Test
    void testSetParametersByMainConfig() throws SQLException {
        configDao.setParametersByMainConfig();

        ResultSet rs = configDao.read(); rs.next();

        assertTrue(configDao.getInteger2ColorSet().get(rs.getInt("colorSet")) == mainConfig.getColorSet());
        assertTrue(configDao.getString2WindowSize().get(rs.getString("windowSize")) == mainConfig.getWindowSize());

        Iterator<KeyType> keyTypes = configDao.getKeyType2Column().keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            assertTrue(rs.getInt(configDao.getKeyType2Column().get(keyType)) == mainConfig.getKeyMap().get(keyType));
        }

        assertTrue(configDao.getInteger2Difficulty().get(rs.getInt("difficulty")) == mainConfig.getDifficulty());
    }

    @Test
    void testUpdate() throws SQLException {
        Integer keyEvent = KeyEvent.VK_0;
        KeyType keyType = KeyType.UP;
        Difficulty difficulty = Difficulty.HARD;

        mainConfig.setColorSet(ColorSet.PROTANOPIA);
        mainConfig.setWindowSize(WindowSize.W1280_H960);
        mainConfig.getKeyMap().update(keyEvent, keyType);
        mainConfig.setDifficulty(difficulty);

        configDao.update();
        ResultSet rs = configDao.read(); rs.next();

        assertTrue(configDao.getInteger2ColorSet().get(rs.getInt("colorSet")) == ColorSet.PROTANOPIA);
        assertTrue(configDao.getString2WindowSize().get(rs.getString("windowSize")) == WindowSize.W1280_H960);
        assertTrue(rs.getInt(configDao.getKeyType2Column().get(keyType)) == keyEvent);
        assertTrue(configDao.getInteger2Difficulty().get(rs.getInt("difficulty")) == Difficulty.HARD);
    }

    @Test
    void testUpdateDefault() throws SQLException {
        configDao.updateDefault();

        ResultSet rs = configDao.read(); rs.next();
        mainConfig.setDefault();

        assertTrue(configDao.getInteger2ColorSet().get(rs.getInt("colorSet")) == mainConfig.getColorSet());
        assertTrue(configDao.getString2WindowSize().get(rs.getString("windowSize")) == mainConfig.getWindowSize());

        Iterator<KeyType> keyTypes = configDao.getKeyType2Column().keySet().iterator();
        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();

            assertTrue(rs.getInt(configDao.getKeyType2Column().get(keyType)) == mainConfig.getKeyMap().get(keyType));
        }

        assertTrue(configDao.getInteger2Difficulty().get(rs.getInt("difficulty")) == mainConfig.getDifficulty());
    }

    @AfterAll
    void afterAll() throws SQLException {
        if (prev.isClosed())
            configDao.createDefaultIfNotExists();
        else
            configDao.setMainConfig(prev);
    }
}
