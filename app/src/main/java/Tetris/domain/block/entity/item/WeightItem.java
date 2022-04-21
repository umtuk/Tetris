package Tetris.domain.block.entity.item;

import java.awt.Color;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.entity.Board;
import Tetris.global.constant.color.BaseColor;

public class WeightItem extends Block {
    public WeightItem() {
        int i = Board.TYPE_WEIGHT;

        shape = new int[][] {
            {0, i, i, 0},
            {i, i, i, i},
        };
        color = BaseColor.WHITE.getColor();
        isRotatable = false;
        isMovable = true;
    }
}
