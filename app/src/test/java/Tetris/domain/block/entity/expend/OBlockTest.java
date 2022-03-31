package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class OBlockTest {
        
    @Test
    void testGetColor() {
        OBlock block = new OBlock(ColorSet.DEFAULT);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.OBLOCK));

        block = new OBlock(ColorSet.PROTANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.OBLOCK));

        block = new OBlock(ColorSet.TRITANOPIA);
        assertTrue(block.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.OBLOCK));
    }

    @Test
    void testGetShape() {
        OBlock block = new OBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {1, 1}, 
			{1, 1}
        };
        assertTrue(Arrays.deepEquals(block.getShape(), shape));
    }
}
