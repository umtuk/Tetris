package Tetris.global.config.entity.branch;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.KeyEvent;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Tetris.global.config.constant.KeyType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KeyMapTest {

    private KeyMap keyMap;

    private Map<Integer, KeyType> keyEvent2TypeMapDefault;
    private EnumMap<KeyType, Integer> keyType2EventMapDefault;

    @BeforeAll
    void beforeAll() {
        keyMap = new KeyMap();

        keyEvent2TypeMapDefault = new HashMap<>();
        keyType2EventMapDefault = new EnumMap<>(KeyType.class);

        keyEvent2TypeMapDefault.put(KeyEvent.VK_UP, KeyType.UP);
        keyEvent2TypeMapDefault.put(KeyEvent.VK_DOWN, KeyType.DOWN);
        keyEvent2TypeMapDefault.put(KeyEvent.VK_LEFT, KeyType.LEFT);
        keyEvent2TypeMapDefault.put(KeyEvent.VK_RIGHT, KeyType.RIGHT);

        keyEvent2TypeMapDefault.put(KeyEvent.VK_ENTER, KeyType.OK);
        keyEvent2TypeMapDefault.put(KeyEvent.VK_ESCAPE, KeyType.PAUSE);

        keyEvent2TypeMapDefault.put(KeyEvent.VK_R, KeyType.ROTATE);


        keyType2EventMapDefault.put(KeyType.UP, KeyEvent.VK_UP);
        keyType2EventMapDefault.put(KeyType.DOWN, KeyEvent.VK_DOWN);
        keyType2EventMapDefault.put(KeyType.LEFT, KeyEvent.VK_LEFT);
        keyType2EventMapDefault.put(KeyType.RIGHT, KeyEvent.VK_RIGHT);

        keyType2EventMapDefault.put(KeyType.OK, KeyEvent.VK_ENTER);
        keyType2EventMapDefault.put(KeyType.PAUSE, KeyEvent.VK_ESCAPE);

        keyType2EventMapDefault.put(KeyType.ROTATE, KeyEvent.VK_R);
    }

    @BeforeEach
    void beforeEach() {
        keyMap.setDefault();
    }

    @Test
    void testGet() {
        Iterator<KeyType> keyTypes = keyType2EventMapDefault.keySet().iterator();

        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();
            assertTrue(keyMap.get(keyType) == keyType2EventMapDefault.get(keyType));
        }
    }

    @Test
    void testUpdate() {
        Integer keyEvent = KeyEvent.VK_0;
        KeyType keyType = KeyType.UP;

        keyMap.update(keyEvent, keyType);

        assertTrue(keyMap.get(keyEvent) == keyType);
        assertTrue(keyMap.get(keyType) == keyEvent);
    }

    @Test
    void testGet2() {
        Iterator<Integer> keyEvents = keyEvent2TypeMapDefault.keySet().iterator();

        while (keyEvents.hasNext()) {
            Integer keyEvent = keyEvents.next();
            assertTrue(keyMap.get(keyEvent) == keyEvent2TypeMapDefault.get(keyEvent));
        }
    }

    @Test
    void testSetDefault() {
        keyMap.setDefault();

        Iterator<KeyType> keyTypes = keyType2EventMapDefault.keySet().iterator();

        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();
            assertTrue(keyMap.get(keyType) == keyType2EventMapDefault.get(keyType));
        }


        Iterator<Integer> keyEvents = keyEvent2TypeMapDefault.keySet().iterator();

        while (keyEvents.hasNext()) {
            Integer keyEvent = keyEvents.next();
            assertTrue(keyMap.get(keyEvent) == keyEvent2TypeMapDefault.get(keyEvent));
        }    
    }
}
