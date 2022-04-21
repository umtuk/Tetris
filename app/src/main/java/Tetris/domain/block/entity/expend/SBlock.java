package Tetris.domain.block.entity.expend;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.global.config.constant.ColorSet;

public class SBlock extends Block {
    
    public SBlock(ColorSet colorSet) {
        shape = new int[][] {
            {0, 1, 1},
			{1, 1, 0}
        };
        color = BlockColorMap.getColor(colorSet, BlockType.SBLOCK);
        isRotatable = true;
        isMovable = true;
    }
}
