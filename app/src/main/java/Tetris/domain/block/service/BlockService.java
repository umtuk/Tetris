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
}
