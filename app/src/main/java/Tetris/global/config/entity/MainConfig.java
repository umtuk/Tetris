package Tetris.global.config.entity;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.branch.KeyMap;

public class MainConfig {
    
    private static ColorSet colorSet;
    private static WindowSize windowSize;

    private static KeyMap keyMap;

    public static void setDefault() {
        colorSet = ColorSet.DEFAULT;
        windowSize = WindowSize.W800_H600;

        keyMap.setDefault();
    }

    public static ColorSet getColorSet() {
        return colorSet;
    }

    public static WindowSize getWindowSize() {
        return windowSize;
    }

    public static KeyMap getKeyMap() {
        return keyMap;
    }

    public static void setColorSet(ColorSet _colorSet) {
        colorSet = _colorSet;
    }

    public static void setWindowSize(WindowSize _WindowSize) {
        windowSize = _WindowSize;
    }

    public static void putKeyMap(Integer keyEvent, KeyType keyType) {
        keyMap.put(keyEvent, keyType);
    }

    public static void removeKeyMap(Integer keyEvent) {
        keyMap.remove(keyEvent);
    }
}
