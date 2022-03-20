package Tetris.domain.block.service;

import Tetris.domain.block.entity.Block;

public class BlockService {
    
    public void rotate(Block block) {
        
    }

    public Block getRandomBlock() {

        return null;
    }

    public int height(Block block) {
        if (block == null)
            return 0;

        return block.getShape().length;
    }

    public int width(Block block) {
        if (block == null)
            return 0;

        return block.getShape()[0].length;
    }
}
