package Tetris.domain.block.entity.expend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public class IBlockTest {

    @Test
    void testGetColor() {
        IBlock iBlock = new IBlock(ColorSet.DEFAULT);
        assertTrue(iBlock.getColor() == BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK));

        iBlock = new IBlock(ColorSet.PROTANOPIA);
        assertTrue(iBlock.getColor() == BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.IBLOCK));

        iBlock = new IBlock(ColorSet.TRITANOPIA);
        assertTrue(iBlock.getColor() == BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.IBLOCK));
    }

    @Test
    void testGetShape() {
        IBlock iBlock = new IBlock(ColorSet.DEFAULT);
        int [][] shape = new int[][] {
            {1, 1, 1, 1},
        };
        assertTrue(Arrays.deepEquals(iBlock.getShape(), shape));
    }
}
