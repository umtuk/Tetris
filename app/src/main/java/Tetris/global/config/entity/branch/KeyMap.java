package Tetris.global.config.entity.branch;

import Tetris.global.config.constant.KeyType;

import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class KeyMap {
    
    private Map<Integer, KeyType> keyEvent2TypeMap;
    private EnumMap<KeyType, Integer> keyType2EventMap;

    public KeyMap() {
        keyEvent2TypeMap = new HashMap<>();
        keyType2EventMap = new EnumMap<>(KeyType.class);
    }

    public void update(Integer keyEvent, KeyType keyType) {
        Integer prevKeyEvent = get(keyType);

        keyEvent2TypeMap.remove(prevKeyEvent);

        keyEvent2TypeMap.put(keyEvent, keyType);
        keyType2EventMap.put(keyType, keyEvent);
    }

    public KeyType get(Integer keyEvent) {
        return keyEvent2TypeMap.get(keyEvent);
    }

    public Integer get (KeyType keyType) {
        return keyType2EventMap.get(keyType);
    }

    public void setDefault() {
        update(KeyEvent.VK_UP, KeyType.UP);
        update(KeyEvent.VK_DOWN, KeyType.DOWN);
        update(KeyEvent.VK_LEFT, KeyType.LEFT);
        update(KeyEvent.VK_RIGHT, KeyType.RIGHT);

        update(KeyEvent.VK_ENTER, KeyType.OK);
        update(KeyEvent.VK_ESCAPE, KeyType.PAUSE);

        update(KeyEvent.VK_R, KeyType.ROTATE);
    }

    public Map<Integer, KeyType> getKeyEvent2TypeMap() {
        return keyEvent2TypeMap;
    }

    public EnumMap<KeyType, Integer> getKeyType2EventMap() {
        return keyType2EventMap;
    }
}
