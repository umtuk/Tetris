package Tetris.domain.block.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockFrequencyMap;
import Tetris.domain.block.entity.Block;
import Tetris.domain.block.entity.expend.*;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;

public class BlockServiceTest {

    private BlockService blockService = BlockService.getInstance();
    private MainConfig mainConfig = MainConfig.getInstance();

    @Test
    void testGetRandomBlock() {
        final int COUNT = 1000;

        Block iBlock = new IBlock(ColorSet.DEFAULT);
        Block jBlock = new JBlock(ColorSet.DEFAULT);
        Block lBlock = new LBlock(ColorSet.DEFAULT);
        Block oBlock = new OBlock(ColorSet.DEFAULT);
        Block sBlock = new SBlock(ColorSet.DEFAULT);
        Block tBlock = new TBlock(ColorSet.DEFAULT);
        Block zBlock = new ZBlock(ColorSet.DEFAULT);

        int iCnt = 0;
        int jCnt = 0;
        int lCnt = 0;
        int oCnt = 0;
        int sCnt = 0;
        int tCnt = 0;
        int zCnt = 0;

        mainConfig.setDifficulty(Difficulty.EASY);
        blockService.setRandomBlock();
        for (int i = 0; i < COUNT; i++) {
            Block derivedBlock = blockService.getRandomBlock();

            if (Arrays.deepEquals(iBlock.getShape(), derivedBlock.getShape()))
                iCnt++;
            else if (Arrays.deepEquals(jBlock.getShape(), derivedBlock.getShape()))
                jCnt++;
            else if (Arrays.deepEquals(lBlock.getShape(), derivedBlock.getShape()))
                lCnt++;
            else if (Arrays.deepEquals(oBlock.getShape(), derivedBlock.getShape()))
                oCnt++;
            else if (Arrays.deepEquals(sBlock.getShape(), derivedBlock.getShape()))
                sCnt++;
            else if (Arrays.deepEquals(tBlock.getShape(), derivedBlock.getShape()))
                tCnt++;
            else if (Arrays.deepEquals(zBlock.getShape(), derivedBlock.getShape()))
                zCnt++;
        }

        int sumOfFitness = blockService.getSumofFitness();
        double percentage = 0.0;
        double criteria = 0.0;

        percentage = (double)COUNT / sumOfFitness;
        criteria = (double)COUNT / 100 * 5;
        assertTrue(Math.abs((double)iCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.IBLOCK)) <= criteria);
        assertTrue(Math.abs((double)jCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.JBLOCK)) <= criteria);
        assertTrue(Math.abs((double)lCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.LBLOCK)) <= criteria);
        assertTrue(Math.abs((double)oCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.OBLOCK)) <= criteria);
        assertTrue(Math.abs((double)sCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.SBLOCK)) <= criteria);
        assertTrue(Math.abs((double)tCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.TBLOCK)) <= criteria);
        assertTrue(Math.abs((double)zCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.ZBLOCK)) <= criteria);
    }

    @Test
    void testHeight() {
        Block block = new JBlock(ColorSet.DEFAULT);
        
        assertTrue(blockService.height(block) == 2);
    }

    @Test
    void testRotate() {
        Block block = new IBlock(ColorSet.DEFAULT);

        int[][] value = {{1, 1, 1, 1},};
        int[][] expected = {
            {1},
            {1},
            {1},
            {1},
        };

        assertTrue(Arrays.deepEquals(block.getShape(), value));

        blockService.rotate(block);
        assertTrue(Arrays.deepEquals(block.getShape(), expected));
    }

    @Test
    void testSetRandomBlock() {
        mainConfig.setDifficulty(Difficulty.EASY);
        blockService.setRandomBlock();

        double[] previousProbability = blockService.getpPeviousProbability();
        int expectedSum = 72;

        assertTrue(expectedSum == blockService.getSumofFitness());
        assertTrue(Math.abs(previousProbability[previousProbability.length - 1] - 1.0) < 1e-5);
    }

    @Test
    void testWidth() {
        Block block = new JBlock(ColorSet.DEFAULT);
        
        assertTrue(blockService.width(block) == 3);
    }
}
