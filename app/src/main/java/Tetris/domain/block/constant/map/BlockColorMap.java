package Tetris.domain.block.constant.map;

import java.util.EnumMap;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.color.BaseColor;
import Tetris.global.config.constant.ColorSet;

public class BlockColorMap {
    private static BlockColorMap INSTANCE = new BlockColorMap();

    public static BlockColorMap getInstance() {
        return INSTANCE;
    }
    
    private EnumMap<ColorSet, EnumMap<BlockType, BaseColor>> blockColorMap;

    public BlockColorMap() {
        blockColorMap = new EnumMap<>(ColorSet.class);

        
    }

    public int getColor(ColorSet colorSet, BlockType blockType) {
        return blockColorMap
            .get(colorSet)
            .get(blockType)
            .getColor();
    }
}
