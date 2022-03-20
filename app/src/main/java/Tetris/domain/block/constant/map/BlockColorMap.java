package Tetris.domain.block.constant.map;

import java.util.EnumMap;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.color.BaseColor;
import Tetris.global.config.constant.ColorSet;

public class BlockColorMap {
    
    private static EnumMap<ColorSet, EnumMap<BlockType, BaseColor>> BlockColorMap;

    public BlockColorMap() {

    }

    public static int getColor(ColorSet colorSet, BlockType blockType) {
        return BlockColorMap
            .get(colorSet)
            .get(blockType)
            .getColor();
    }
}
