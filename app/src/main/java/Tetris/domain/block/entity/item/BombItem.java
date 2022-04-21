package Tetris.domain.block.entity.item;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.entity.Board;
import Tetris.global.constant.color.BaseColor;

public class BombItem extends Block {
    public BombItem() {
        int i = Board.TYPE_BOMB;

        shape = new int[][] {
            {i},
        };
        color = BaseColor.WHITE.getColor();
        isRotatable = true;
        isMovable = true;
    }
}
