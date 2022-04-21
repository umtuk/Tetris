package Tetris.domain.block.entity.item;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.entity.Board;
import Tetris.global.constant.color.BaseColor;

public class BonusScoreItem extends Block {
    public BonusScoreItem() {
        int i = Board.TYPE_BONUS_SCORE;

        shape = new int[][] {
            {i, i, i},
            {i, i, i},
            {i, i, i},
        };
        color = BaseColor.WHITE.getColor();
        isRotatable = true;
        isMovable = true;
    }
}
