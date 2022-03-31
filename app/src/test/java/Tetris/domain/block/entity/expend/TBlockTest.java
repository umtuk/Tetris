package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class TBlockTest {
            
    @Test
    void testGetColor() {
        TBlock block = new TBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.TBLOCK));

        block = new TBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.TBLOCK));

        block = new TBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.TBLOCK));
    }

    @Test
    void testGetShape() {
        TBlock block = new TBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {0, 1, 0},
			{1, 1, 1}
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
