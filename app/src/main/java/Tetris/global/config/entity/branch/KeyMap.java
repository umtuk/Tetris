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

        keyEvent2TypeMap.put(KeyEvent.VK_UP, KeyType.UP);
        keyEvent2TypeMap.put(KeyEvent.VK_DOWN, KeyType.DOWN);
        keyEvent2TypeMap.put(KeyEvent.VK_LEFT, KeyType.LEFT);
        keyEvent2TypeMap.put(KeyEvent.VK_RIGHT, KeyType.RIGHT);

        keyEvent2TypeMap.put(KeyEvent.VK_ENTER, KeyType.OK);
        keyEvent2TypeMap.put(KeyEvent.VK_ESCAPE, KeyType.PAUSE);

        keyEvent2TypeMap.put(KeyEvent.VK_R, KeyType.ROTATE);


        keyType2EventMap.put(KeyType.UP, KeyEvent.VK_UP);
        keyType2EventMap.put(KeyType.DOWN, KeyEvent.VK_DOWN);
        keyType2EventMap.put(KeyType.LEFT, KeyEvent.VK_LEFT);
        keyType2EventMap.put(KeyType.RIGHT, KeyEvent.VK_RIGHT);

        keyType2EventMap.put(KeyType.OK, KeyEvent.VK_ENTER);
        keyType2EventMap.put(KeyType.PAUSE, KeyEvent.VK_ESCAPE);

        keyType2EventMap.put(KeyType.ROTATE, KeyEvent.VK_R);
    }

    public void put(Integer keyEvent, KeyType keyType) {
        keyEvent2TypeMap.put(keyEvent, keyType);
        keyType2EventMap.put(keyType, keyEvent);
    }

    public void remove(Integer keyEvent) {
        keyEvent2TypeMap.remove(keyEvent);
    }

    public KeyType get(Integer keyEvent) {
        return keyEvent2TypeMap.get(keyEvent);
    }

    public Integer get (KeyType keyType) {
        return keyType2EventMap.get(keyType);
    }
}
