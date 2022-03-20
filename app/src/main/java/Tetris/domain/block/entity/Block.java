package Tetris.domain.block.entity;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.color.BaseColor;
import Tetris.global.config.constant.ColorSet;

public abstract class Block {
    
    private int[][] shape;
    private int color;

    public Block() {
        this.shape = new int[][] {
            {0, 0},
            {0, 0}
        };
        this.color = BaseColor.WHITE.getColor();
    }

    public Block(ColorSet colorSet) {
        this.shape = new int[][] {
            {0, 0},
            {0, 0}
        };
        this.color = BlockColorMap.getColor(colorSet, BlockType.IBLOCK);
    }

    public int[][] getShape() {
        return this.shape;
    }

    public int getColor() {
        return this.color;
    }
}
