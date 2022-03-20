package Tetris.global.config.entity.branch;

import Tetris.global.config.constant.KeyType;

import java.awt.event.KeyEvent;
import java.util.EnumMap;

public class KeyMap {
    
    private EnumMap<KeyType, KeyEvent> keyMap;

    public KeyMap() {

    }

    public KeyEvent getKeyEvent(KeyType key) {
        return this.keyMap.get(key);
    }
}
