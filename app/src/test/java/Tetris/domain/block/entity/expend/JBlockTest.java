package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class JBlockTest {

    @Test
    void testGetColor() {
        JBlock block = new JBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.JBLOCK));

        block = new JBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.JBLOCK));

        block = new JBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.JBLOCK));
    }

    @Test
    void testGetShape() {
        JBlock block = new JBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {1, 1, 1,},
            {0, 0, 1,},
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
