package Tetris.domain.block.constant.map;

import java.util.EnumMap;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.config.constant.Difficulty;

public class BlockFrequencyMap {
    
    private static EnumMap<Difficulty, EnumMap<BlockType, Integer>> blockFrequencyMap;

    private static EnumMap<BlockType, Integer> easyFrequencyMap;
    private static EnumMap<BlockType, Integer> normalFrequencyMap;
    private static EnumMap<BlockType, Integer> hardFrequencyMap;

    static {
        blockFrequencyMap = new EnumMap<>(Difficulty.class);

        easyFrequencyMap = new EnumMap<>(BlockType.class);
        normalFrequencyMap = new EnumMap<>(BlockType.class);
        hardFrequencyMap = new EnumMap<>(BlockType.class);

        easyFrequencyMap.put(BlockType.IBLOCK, 12);
        easyFrequencyMap.put(BlockType.JBLOCK, 10);
        easyFrequencyMap.put(BlockType.LBLOCK, 10);
        easyFrequencyMap.put(BlockType.OBLOCK, 10);
        easyFrequencyMap.put(BlockType.SBLOCK, 10);
        easyFrequencyMap.put(BlockType.TBLOCK, 10);
        easyFrequencyMap.put(BlockType.ZBLOCK, 10);

        normalFrequencyMap.put(BlockType.IBLOCK, 10);
        normalFrequencyMap.put(BlockType.JBLOCK, 10);
        normalFrequencyMap.put(BlockType.LBLOCK, 10);
        normalFrequencyMap.put(BlockType.OBLOCK, 10);
        normalFrequencyMap.put(BlockType.SBLOCK, 10);
        normalFrequencyMap.put(BlockType.TBLOCK, 10);
        normalFrequencyMap.put(BlockType.ZBLOCK, 10);

        hardFrequencyMap.put(BlockType.IBLOCK, 8);
        hardFrequencyMap.put(BlockType.JBLOCK, 10);
        hardFrequencyMap.put(BlockType.LBLOCK, 10);
        hardFrequencyMap.put(BlockType.OBLOCK, 10);
        hardFrequencyMap.put(BlockType.SBLOCK, 10);
        hardFrequencyMap.put(BlockType.TBLOCK, 10);
        hardFrequencyMap.put(BlockType.ZBLOCK, 10);

        blockFrequencyMap.put(Difficulty.EASY, easyFrequencyMap);
        blockFrequencyMap.put(Difficulty.NORMAL, normalFrequencyMap);
        blockFrequencyMap.put(Difficulty.HARD, hardFrequencyMap);
    }

    public static int getFrequency(Difficulty difficulty, BlockType blockType) {
        return blockFrequencyMap.get(difficulty).get(blockType);
    }
}
