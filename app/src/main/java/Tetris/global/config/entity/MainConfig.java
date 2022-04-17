package Tetris.global.config.entity;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.branch.KeyMap;

public class MainConfig {
    private static MainConfig INSTANCE = new MainConfig();

    public static MainConfig getInstance() {
        return INSTANCE;
    }
    
    private ColorSet colorSet;
    private WindowSize windowSize;
    private Difficulty difficulty;

    private KeyMap keyMap;

    public void setDefault() {
        colorSet = ColorSet.DEFAULT;
        windowSize = WindowSize.W800_H600;
        difficulty = Difficulty.NORMAL;

        keyMap.setDefault();
    }

    public MainConfig() {
        keyMap = new KeyMap();
        setDefault();
    }

    public ColorSet getColorSet() {
        return colorSet;
    }

    public WindowSize getWindowSize() {
        return windowSize;
    }

    public KeyMap getKeyMap() {
        return keyMap;
    }

    public void setColorSet(ColorSet colorSet) {
        this.colorSet = colorSet;
    }

    public void setWindowSize(WindowSize windowSize) {
        this.windowSize = windowSize;
    }

    public void updateKeyMap(Integer keyEvent, KeyType keyType) {
        keyMap.update(keyEvent, keyType);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}


