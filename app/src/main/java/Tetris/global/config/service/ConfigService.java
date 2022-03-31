package Tetris.global.config.service;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.dao.ConfigDao;
import Tetris.global.config.entity.MainConfig;

import java.sql.SQLException;

public class ConfigService {
    private static ConfigService INSTANCE = new ConfigService();

    public static ConfigService getInstance() {
        return INSTANCE;
    }

    private MainConfig mainConfig;
    private ConfigDao configDao;

    public ConfigService() {
        mainConfig = MainConfig.getInstance();
        configDao = ConfigDao.getInstance();
    }
    
    public void setWindowSize(WindowSize windowSize) throws SQLException {
        mainConfig.setWindowSize(windowSize);
        configDao.update();
    }

    public void setColorSet(ColorSet colorSet) throws SQLException {
        mainConfig.setColorSet(colorSet);
        configDao.update();
    }

    public void setKeyMap(Integer keyEvent, KeyType keyType) throws SQLException {
        mainConfig.updateKeyMap(keyEvent, keyType);
        configDao.update();
    }

    public void setDefaultConfig() throws SQLException {
        mainConfig.setDefault();
        configDao.update();
    }

    public void clearScoreBoard() {
        // Todo
    }
}
