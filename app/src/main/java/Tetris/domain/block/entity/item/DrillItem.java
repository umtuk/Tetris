package Tetris.domain.block.entity.item;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.entity.Board;
import Tetris.global.constant.color.BaseColor;

public class DrillItem extends Block {
    public DrillItem() {
        int i = Board.TYPE_DRILL;

        shape = new int[][] {
            { i},
            {i},
        };
        color = BaseColor.WHITE.getColor();
        isRotatable = false;
        isMovable = true;
    }
}
