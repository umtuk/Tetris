package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class LBlockTest {
    
    @Test
    void testGetColor() {
        LBlock block = new LBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.LBLOCK));

        block = new LBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.LBLOCK));

        block = new LBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.LBLOCK));
    }

    @Test
    void testGetShape() {
        LBlock block = new LBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {1, 1, 1},
			{1, 0, 0}
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
