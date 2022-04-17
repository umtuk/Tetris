package Tetris.domain.block.service;

import java.util.Arrays;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockFrequencyMap;
import Tetris.domain.block.entity.Block;
import Tetris.domain.block.entity.expend.*;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.matrix.IntMatrixUtil;

public class BlockService {

    private static BlockService INSTANCE;

    static {
        INSTANCE = new BlockService();
    }

    public static BlockService getInstance() {
        return INSTANCE;
    }

    private MainConfig mainConfig;
    
    private int sumOfFitness;
    private double[] previousProbability;

    public BlockService()  {
        mainConfig = MainConfig.getInstance();
        setRandomBlock();
    }
    
    public void setRandomBlock() {
        Difficulty diff = mainConfig.getDifficulty();
        BlockType[] blockTypes = BlockType.values();

        System.out.println(diff);

        for (int i = 0; i < blockTypes.length; i++)
            System.out.println(blockTypes[i]);

        sumOfFitness = 0;
        previousProbability = new double[blockTypes.length];

        for (int i = 0; i < blockTypes.length; i++)
            sumOfFitness += BlockFrequencyMap.getFrequency(diff, blockTypes[i]);

        previousProbability[0] = (double)(BlockFrequencyMap.getFrequency(diff, blockTypes[0])) / (double)(sumOfFitness);
        for (int i = 1; i < blockTypes.length; i++)
            previousProbability[i] = previousProbability[i - 1] + (double)(BlockFrequencyMap.getFrequency(diff, blockTypes[i])) / (double)(sumOfFitness);
    }
    
    public void rotate(Block block) {
        int[][] res = IntMatrixUtil.rotateClockwise(block.getShape());
        block.setShape(res);
    }

    public Block getRandomBlock() {
        BlockType[] blockTypes = BlockType.values();
        double randomNumber = Math.random();

        for (int i = 0; i < blockTypes.length; i++)
            if (randomNumber < previousProbability[i])
                if (blockTypes[i] == BlockType.IBLOCK)
                    return new IBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.JBLOCK)
                    return new JBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.LBLOCK)
                    return new LBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.OBLOCK)
                    return new OBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.SBLOCK)
                    return new SBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.TBLOCK)
                    return new TBlock(mainConfig.getColorSet());
                else if (blockTypes[i] == BlockType.ZBLOCK)
                    return new ZBlock(mainConfig.getColorSet());

        return null;
    }

    public int height(Block block) {
        if (block == null)
            return 0;

        return block.getShape().length;
    }

    public int width(Block block) {
        if (block == null)
            return 0;

        return block.getShape()[0].length;
    }

    public int getSumofFitness() {
        return sumOfFitness;
    }

    public double[] getpPeviousProbability() {
        return previousProbability;
    }

    public static void main(String[] args) {
        BlockService blockService = BlockService.getInstance();
        MainConfig mainConfig = MainConfig.getInstance();

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

        System.out.println("iCnt: " + iCnt);
        System.out.println("jCnt: " + jCnt);
        System.out.println("lCnt: " + lCnt);
        System.out.println("oCnt: " + oCnt);
        System.out.println("sCnt: " + sCnt);
        System.out.println("tCnt: " + tCnt);
        System.out.println("zCnt: " + zCnt);

        int sumOfFitness = blockService.getSumofFitness();
        double percentage = 0.0;

        percentage = (double)COUNT / sumOfFitness;
        System.out.println("perct: " + percentage);
        System.out.println(("5%: " + (double)COUNT / 100 * 5));
        System.out.println(Math.abs((double)iCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.IBLOCK)));
        System.out.println(Math.abs((double)jCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.JBLOCK)));
        System.out.println(Math.abs((double)lCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.LBLOCK)));
        System.out.println(Math.abs((double)oCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.OBLOCK)));
        System.out.println(Math.abs((double)sCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.SBLOCK)));
        System.out.println(Math.abs((double)tCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.TBLOCK)));
        System.out.println(Math.abs((double)zCnt - percentage * (double)BlockFrequencyMap.getFrequency(Difficulty.EASY, BlockType.ZBLOCK)));
    }
}
