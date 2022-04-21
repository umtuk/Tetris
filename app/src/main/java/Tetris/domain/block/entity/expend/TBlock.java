package Tetris.domain.block.entity.expend;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.global.config.constant.ColorSet;

public class TBlock extends Block {
    
    public TBlock(ColorSet colorSet) {
        shape = new int[][] {
            {0, 1, 0},
			{1, 1, 1}
        };
        color = BlockColorMap.getColor(colorSet, BlockType.TBLOCK);
        isRotatable = true;
        isMovable = true;
    }
}
