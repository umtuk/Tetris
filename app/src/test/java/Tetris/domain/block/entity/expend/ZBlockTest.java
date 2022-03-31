package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class ZBlockTest {
                
    @Test
    void testGetColor() {
        ZBlock block = new ZBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.ZBLOCK));

        block = new ZBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.ZBLOCK));

        block = new ZBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.ZBLOCK));
    }

    @Test
    void testGetShape() {
        ZBlock block = new ZBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {1, 1, 0},
			{0, 1, 1}
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
