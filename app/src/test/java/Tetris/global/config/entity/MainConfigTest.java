package Tetris.global.config.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.KeyEvent;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.branch.KeyMap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainConfigTest {

    private MainConfig mainConfig;

    @BeforeAll
    void beforeAll() {
        mainConfig = MainConfig.getInstance();
    }

    @BeforeEach
    void beforeEach() {
        mainConfig.setDefault();
    }

    @Test
    void testGetColorSet() {
        assertTrue(mainConfig.getColorSet() == ColorSet.DEFAULT);
    }

    @Test
    void testGetKeyMap() {
        KeyMap base = new KeyMap(); base.setDefault();
        KeyMap byMainConfig = mainConfig.getKeyMap();

        Iterator<KeyType> keyTypes = base.getKeyType2EventMap().keySet().iterator();
        Iterator<Integer> keyEvents = base.getKeyEvent2TypeMap().keySet().iterator();

        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();
            assertTrue(base.get(keyType) == byMainConfig.get(keyType));
        }

        while (keyEvents.hasNext()) {
            Integer keyEvent = keyEvents.next();
            assertTrue(base.get(keyEvent) == byMainConfig.get(keyEvent));
        }
    }

    @Test
    void testGetWindowSize() {
        assertTrue(mainConfig.getWindowSize().getWidth() == WindowSize.W800_H600.getWidth());
        assertTrue(mainConfig.getWindowSize().getHeight() == WindowSize.W800_H600.getHeight());
    }

    @Test
    void testUpdateKeyMap() {
        Integer keyEvent = KeyEvent.VK_0;
        KeyType keyType = KeyType.UP;

        mainConfig.updateKeyMap(keyEvent, keyType);

        assertTrue(mainConfig.getKeyMap().get(keyEvent) == keyType);
        assertTrue(mainConfig.getKeyMap().get(keyType) == keyEvent);
    }

    @Test
    void testSetColorSet() {
        ColorSet colorSet = ColorSet.PROTANOPIA;

        mainConfig.setColorSet(colorSet);

        assertTrue(mainConfig.getColorSet() == colorSet);
    }

    @Test
    void testSetDefault() {
        mainConfig.setDefault();

        assertTrue(mainConfig.getColorSet() == ColorSet.DEFAULT);
        assertTrue(mainConfig.getWindowSize() == WindowSize.W800_H600);

        KeyMap base = new KeyMap(); base.setDefault();
        KeyMap byMainConfig = mainConfig.getKeyMap();

        Iterator<KeyType> keyTypes = base.getKeyType2EventMap().keySet().iterator();
        Iterator<Integer> keyEvents = base.getKeyEvent2TypeMap().keySet().iterator();

        while (keyTypes.hasNext()) {
            KeyType keyType = keyTypes.next();
            assertTrue(base.get(keyType) == byMainConfig.get(keyType));
        }

        while (keyEvents.hasNext()) {
            Integer keyEvent = keyEvents.next();
            assertTrue(base.get(keyEvent) == byMainConfig.get(keyEvent));
        }
    }

    @Test
    void testSetWindowSize() {
        WindowSize windowSize = WindowSize.W1280_H960;

        mainConfig.setWindowSize(windowSize);

        assertTrue(mainConfig.getWindowSize().getWidth() == windowSize.getWidth());
        assertTrue(mainConfig.getWindowSize().getHeight() == windowSize.getHeight());
    }
}
