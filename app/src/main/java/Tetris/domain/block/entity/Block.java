package Tetris.domain.block.entity;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public abstract class Block {
    
    protected int[][] shape;
    protected int color;

    public Block() {

    }

    public Block(ColorSet colorSet) {
        shape = new int[][] {
            {0, 0},
            {0, 0}
        };
        color = BlockColorMap.getColor(colorSet, BlockType.IBLOCK);
    }

    public int[][] getShape() {
        return shape;
    }

    public int getColor() {
        return color;
    }
}
