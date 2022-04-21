package Tetris.domain.board.entity;

import Tetris.domain.block.entity.Block;

public class Board {

    public static final int TYPE = 0;
    public static final int COLOR = 1;

    public static final int TYPE_EMPTY = 0;
    public static final int TYPE_STATIC = 1;
    public static final int TYPE_DYNAMIC = 2;

    public static final int TYPE_BOMB = 3;
    public static final int TYPE_BONUS_SCORE = 4;
    public static final int TYPE_DRILL = 5;
    public static final int TYPE_LINE_REMOVER = 6;
    public static final int TYPE_WEIGHT = 7;
    
    private int[][][] board;

    private Block prevBlock;
    private Block nowBlock;

    private int xPos;
    private int yPos;

    public Board() {
        board = new int[24][10][2];
    }

    public int[][][] getBoard() {
        return this.board;
    }

    public Block getPrevBlock() {
        return this.prevBlock;
    }

    public Block getNowBlock() {
        return this.nowBlock;
    }

    public void setPrevBlock(Block block) {
        this.prevBlock = block;
    }

    public void setNowBlock(Block block) {
        this.nowBlock = block;
    }

    public void setBoard(int[][][] board) {
        this.board = board;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
