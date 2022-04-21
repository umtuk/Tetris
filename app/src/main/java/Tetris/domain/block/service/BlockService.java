package Tetris.domain.block.service;

import java.util.Arrays;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockFrequencyMap;
import Tetris.domain.block.entity.Block;
import Tetris.domain.block.entity.expend.*;
import Tetris.domain.block.entity.item.BombItem;
import Tetris.domain.block.entity.item.BonusScoreItem;
import Tetris.domain.block.entity.item.DrillItem;
import Tetris.domain.block.entity.item.WeightItem;
import Tetris.domain.board.entity.Board;
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

        sumOfFitness = 0;
        previousProbability = new double[blockTypes.length];

        for (int i = 0; i < blockTypes.length; i++)
            sumOfFitness += BlockFrequencyMap.getFrequency(diff, blockTypes[i]);

        previousProbability[0] = (double)(BlockFrequencyMap.getFrequency(diff, blockTypes[0])) / (double)(sumOfFitness);
        for (int i = 1; i < blockTypes.length; i++)
            previousProbability[i] = previousProbability[i - 1] + (double)(BlockFrequencyMap.getFrequency(diff, blockTypes[i])) / (double)(sumOfFitness);
    }
    
    public void rotate(Block block) {
        if (block.isRotatable()) {
            int[][] res = IntMatrixUtil.rotateClockwise(block.getShape());
            block.setShape(res);
        }
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

    private void addLineRemover(Block block, int t) {
        int[][] shape = block.getShape();

        for (int r = 0 ; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] != 0) {
                    if (t == 0) {
                        shape[r][c] = Board.TYPE_LINE_REMOVER;
                        return;
                    }
                    t--;
                }
            }
        }
    }

    public Block getRandomItem() {
        Block block;
        int rand;
        int randomNumber = (int)(Math.random() * 5);
        switch (randomNumber) {
            case 0: return new BombItem();
            case 1: return new BonusScoreItem();
            case 2: return new DrillItem();
            case 3: 
                block = getRandomBlock();
                rand = (int)(Math.random() * 4);
                addLineRemover(block, rand);
                System.out.println("getRandomItem:: LineRemover");
                return block;
            case 4: return new WeightItem();
            default:
                block = getRandomBlock();
                rand = (int)(Math.random() * 4);
                addLineRemover(block, rand);
                System.out.println("getRandomItem:: LineRemover");
                for (int r = 0; r < block.getShape().length; r++) {
                    for (int c = 0; c < block.getShape()[r].length; c++) {
                        System.out.print(block.getShape()[r][c] + " ");
                    }
                    System.out.println();
                }
                return block;
        }
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

    public boolean isMovable(Block block) {
        return block.isMovable();
    }
}
