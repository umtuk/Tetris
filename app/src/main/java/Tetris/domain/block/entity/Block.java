package Tetris.domain.block.entity;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.global.config.constant.ColorSet;

public abstract class Block {
    
    protected int[][] shape;
    protected int color;

    protected boolean isRotatable;
    protected boolean isMovable;

    public Block() {

    }

    public Block(ColorSet colorSet) {
        shape = new int[][] {
            {0, 0},
            {0, 0}
        };
        color = BlockColorMap.getColor(colorSet, BlockType.IBLOCK);

        isRotatable = true;
        isMovable = true;
    }

    public int[][] getShape() {
        return shape;
    }

    public int getColor() {
        return color;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public boolean isRotatable() {
        return isRotatable;
    }

    public void setMovable(boolean b) {
        isMovable = b;
    }

    public void setRotatable(boolean b) {
        isRotatable = b;
    }
}
