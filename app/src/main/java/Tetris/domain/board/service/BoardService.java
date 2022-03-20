package Tetris.domain.board.service;

import Tetris.domain.block.service.BlockService;
import Tetris.domain.board.entity.Board;

public class BoardService {
    
    private BlockService blockService;

    public void setBlocks(Board board) {
        board.setNowBlock(board.getPrevBlock());
        board.setPrevBlock(blockService.getRandomBlock());
    }
}
