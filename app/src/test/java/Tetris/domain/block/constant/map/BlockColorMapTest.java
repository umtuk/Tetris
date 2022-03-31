package Tetris.domain.block.constant.map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.constant.color.BaseColor;

public class BlockColorMapTest {

    @Test
    void testGetColor() {
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK) == BaseColor.CYAN.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.JBLOCK) == BaseColor.BLUE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.LBLOCK) == BaseColor.ORANGE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.OBLOCK) == BaseColor.YELLOW.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.SBLOCK) == BaseColor.GREEN.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.TBLOCK) == BaseColor.PURPLE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.ZBLOCK) == BaseColor.RED.getColor());

        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.IBLOCK) == BaseColor.SKY_BLUE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.JBLOCK) == BaseColor.BLUE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.LBLOCK) == BaseColor.DW_ORANGE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.OBLOCK) == BaseColor.DW_YELLOW.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.SBLOCK) == BaseColor.BLUISH_GREEN.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.TBLOCK) == BaseColor.REDDISH_PURPLE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.PROTANOPIA, BlockType.ZBLOCK) == BaseColor.VERMILION.getColor());
        
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.IBLOCK) == BaseColor.SKY_BLUE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.JBLOCK) == BaseColor.BLUE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.LBLOCK) == BaseColor.DW_ORANGE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.OBLOCK) == BaseColor.DW_YELLOW.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.SBLOCK) == BaseColor.BLUISH_GREEN.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.TBLOCK) == BaseColor.REDDISH_PURPLE.getColor());
        assertTrue(BlockColorMap.getColor(ColorSet.TRITANOPIA, BlockType.ZBLOCK) == BaseColor.VERMILION.getColor());
    }
}
