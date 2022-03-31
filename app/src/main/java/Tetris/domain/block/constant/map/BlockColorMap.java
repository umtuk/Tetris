package Tetris.domain.block.constant.map;

import java.util.EnumMap;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.constant.color.BaseColor;

public class BlockColorMap {
    
    private static EnumMap<ColorSet, EnumMap<BlockType, BaseColor>> blockColorMap;
    
    private static EnumMap<BlockType, BaseColor> defaultBlockColor;
    private static EnumMap<BlockType, BaseColor> protanopiaBlockColor;
    private static EnumMap<BlockType, BaseColor> tritanopiaBlockColor;

    static {
        blockColorMap = new EnumMap<>(ColorSet.class);

        defaultBlockColor = new EnumMap<>(BlockType.class);
        protanopiaBlockColor = new EnumMap<>(BlockType.class);
        tritanopiaBlockColor = new EnumMap<>(BlockType.class);

        defaultBlockColor.put(BlockType.IBLOCK, BaseColor.CYAN);
        defaultBlockColor.put(BlockType.JBLOCK, BaseColor.BLUE);
        defaultBlockColor.put(BlockType.LBLOCK, BaseColor.ORANGE);
        defaultBlockColor.put(BlockType.OBLOCK, BaseColor.YELLOW);
        defaultBlockColor.put(BlockType.SBLOCK, BaseColor.GREEN);
        defaultBlockColor.put(BlockType.TBLOCK, BaseColor.PURPLE);
        defaultBlockColor.put(BlockType.ZBLOCK, BaseColor.RED);

        protanopiaBlockColor.put(BlockType.IBLOCK, BaseColor.SKY_BLUE);
        protanopiaBlockColor.put(BlockType.JBLOCK, BaseColor.BLUE);
        protanopiaBlockColor.put(BlockType.LBLOCK, BaseColor.DW_ORANGE);
        protanopiaBlockColor.put(BlockType.OBLOCK, BaseColor.DW_YELLOW);
        protanopiaBlockColor.put(BlockType.SBLOCK, BaseColor.BLUISH_GREEN);
        protanopiaBlockColor.put(BlockType.TBLOCK, BaseColor.REDDISH_PURPLE);
        protanopiaBlockColor.put(BlockType.ZBLOCK, BaseColor.VERMILION);
        
        tritanopiaBlockColor.put(BlockType.IBLOCK, BaseColor.SKY_BLUE);
        tritanopiaBlockColor.put(BlockType.JBLOCK, BaseColor.BLUE);
        tritanopiaBlockColor.put(BlockType.LBLOCK, BaseColor.DW_ORANGE);
        tritanopiaBlockColor.put(BlockType.OBLOCK, BaseColor.DW_YELLOW);
        tritanopiaBlockColor.put(BlockType.SBLOCK, BaseColor.BLUISH_GREEN);
        tritanopiaBlockColor.put(BlockType.TBLOCK, BaseColor.REDDISH_PURPLE);
        tritanopiaBlockColor.put(BlockType.ZBLOCK, BaseColor.VERMILION);

        blockColorMap.put(ColorSet.DEFAULT, defaultBlockColor);
        blockColorMap.put(ColorSet.PROTANOPIA, protanopiaBlockColor);
        blockColorMap.put(ColorSet.TRITANOPIA, tritanopiaBlockColor);
    }

    public static int getColor(ColorSet colorSet, BlockType blockType) {
        return blockColorMap
            .get(colorSet)
            .get(blockType)
            .getColor();
    }
}
