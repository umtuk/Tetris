package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class SBlockTest {
        
    @Test
    void testGetColor() {
        SBlock block = new SBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.SBLOCK));

        block = new SBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.SBLOCK));

        block = new SBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.SBLOCK));
    }

    @Test
    void testGetShape() {
        SBlock block = new SBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {0, 1, 1},
			{1, 1, 0}
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
