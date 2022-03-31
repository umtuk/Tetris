package Tetris.domain.block.constant.map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.global.config.constant.Difficulty;

public class BlockFrequencyMapTest {

    @Test
    void testGetFrequency() {
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.IBLOCK) == 12);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.JBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.LBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.OBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.SBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.TBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.ZBLOCK) == 10);
    
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.IBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.JBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.LBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.OBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.SBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.TBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.NORMAL, BlockType.ZBLOCK) == 10);

        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.IBLOCK) == 8);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.JBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.LBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.OBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.SBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.TBLOCK) == 10);
        assertTrue(BlockFrequencyMap.getFrequency(Difficulty.HARD, BlockType.ZBLOCK) == 10);
    }
}
