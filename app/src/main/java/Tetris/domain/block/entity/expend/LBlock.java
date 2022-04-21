package Tetris.domain.block.entity.expend;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.global.config.constant.ColorSet;

public class LBlock extends Block{

     public LBlock(ColorSet colorSet) {
        shape = new int[][] {
            {1, 1, 1},
			{1, 0, 0}
        };
        color = BlockColorMap.getColor(colorSet, BlockType.LBLOCK);
        isRotatable = true;
        isMovable = true;
     }
}
