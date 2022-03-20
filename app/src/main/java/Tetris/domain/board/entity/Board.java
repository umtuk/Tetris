package Tetris.domain.board.entity;

import Tetris.domain.block.entity.Block;

public class Board {
    
    private int[][] board;

    private Block prevBlock;
    private Block nowBlock;

    public Board() {

    }

    public int[][] getBoard() {
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
}
