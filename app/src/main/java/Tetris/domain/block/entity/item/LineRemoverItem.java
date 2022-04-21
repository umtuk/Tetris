package Tetris.domain.block.entity.item;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.global.config.constant.ColorSet;

public class LineRemoverItem extends Block {
    public LineRemoverItem(ColorSet colorSet) {
        shape = new int[][] {
            {1, 1, 1, 1},
        };
        color = BlockColorMap.getColor(colorSet, BlockType.IBLOCK);
        isRotatable = true;
        isMovable = true;
    }
}
