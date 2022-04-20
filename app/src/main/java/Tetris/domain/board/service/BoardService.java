package Tetris.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import Tetris.domain.block.entity.Block;
import Tetris.domain.block.service.BlockService;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.global.matrix.IntMatrixUtil;

public class BoardService {

    private static BoardService INSTANCE = new BoardService();

    public static BoardService getInstance() {
        return INSTANCE;
    }
    
    private BlockService blockService = BlockService.getInstance();

    private Board board;

    public BoardService() {
        board = new Board();
    }

    private int[][] findRealPos(int[][] matrix, int xPos, int yPos, int[] center, int count) {

        int[][] ret = new int[count][2];

        int[][] notZeroValue = IntMatrixUtil.findAllNotZeroValuePos(matrix, count);

        for (int i = 0; i < count; i++) {
            ret[i][0] = xPos + (notZeroValue[i][0] - center[0]);
            ret[i][1] = yPos + (notZeroValue[i][1] - center[1]);
        }

        return ret;
    }

    private int[][] findNowBlockPos() {
        Block block = board.getNowBlock();
        int[][] shape = block.getShape();

        int xPos = board.getxPos();
        int yPos = board.getyPos();

        int[] center = IntMatrixUtil.findNearestCenter(shape);

        return findRealPos(shape, xPos, yPos, center, 4);
    }

    private void initNowBlockPos() {
        board.setyPos(5);
        board.setxPos(5 - IntMatrixUtil.lengthCenter2Bottom(board.getNowBlock().getShape()));
    }

    private List<Integer> updateBlocks() {
        board.setNowBlock(board.getPrevBlock());
        initNowBlockPos();

        board.setPrevBlock(blockService.getRandomBlock());
        return deleteFullLine();
    }

    private void convertBlockToBoard() {
        int[][][] tBoard = board.getBoard();
        int[][] blockPosNShape = findNowBlockPos();
        int blockColor = board.getNowBlock().getColor();

        for (int i = 0; i < 4; i++) {
            int xPos = blockPosNShape[i][0];
            int yPos = blockPosNShape[i][1];

            tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_STATIC;
            tBoard[xPos][yPos][Board.COLOR] = blockColor;
        }
    }


    public int[][][] getBoard() {
        return board.getBoard();
    }

    public int[][] getNowBlockPos() {
        return findNowBlockPos();
    }

    public Block getNowBlock() {
        return board.getNowBlock();
    }

    public void setNowBlock(Block block) {
        board.setNowBlock(block);
    }

    public int getxPos() {
        return board.getxPos();
    }

    public int getyPos() {
        return board.getyPos();
    }

    public void setxPos(int xPos) {
        board.setxPos(xPos);
    }

    public void setyPos (int yPos) {
        board.setyPos(yPos);
    }

    public Block getPrevBlock() {
        return board.getPrevBlock();
    }


    public void moveBlockRight() {
        try {
            int[][] nowBlockPos = findNowBlockPos();

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] + 1][Board.TYPE] != Board.TYPE_EMPTY) {
                    return;
                }
            }

            board.setyPos(board.getyPos() + 1);
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public void moveBlockLeft() {
        try {
            int[][] nowBlockPos = findNowBlockPos();

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] - 1][Board.TYPE] != Board.TYPE_EMPTY) {
                    return;
                }
            }

            board.setyPos(board.getyPos() - 1);
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public List<Integer> moveBlockDown() {
        try {
            int[][] nowBlockPos = findNowBlockPos();

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                    convertBlockToBoard();
                    return updateBlocks();
                }
            }
            
            board.setxPos(board.getxPos() + 1);
        } catch (IndexOutOfBoundsException e) {
            convertBlockToBoard();
            return updateBlocks();
        }

        return null;
    }

    public List<Integer> moveBlockAtOnce() {
        try {
            for (int a = 0; a < 24; a++) {
                int[][] nowBlockPos = findNowBlockPos();

                for (int i = 0; i < 4; i++) {
                    if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                        convertBlockToBoard();
                        return updateBlocks();
                        
                    }
                }
                
                board.setxPos(board.getxPos() + 1);
            }
        } catch (IndexOutOfBoundsException e) {
            convertBlockToBoard();
            return updateBlocks();
        }

        return null;
    }

    private boolean isEmpty(int[][] pos) {
        int[][] matrix = board.getNowBlock().getShape();
        int xPos = board.getxPos();
        int yPos = board.getyPos();
        int[] center = IntMatrixUtil.findNearestCenter(matrix);

        int[][] realPos = findRealPos(pos, xPos, yPos, center, 4);

        int[][][] tBoard = board.getBoard();

        try {
            for (int i = 0; i < 4; i++) {
                int x = realPos[i][0];
                int y = realPos[i][1];
    
                if (tBoard[x][y][Board.TYPE] != Board.TYPE_EMPTY) {
                    return false;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    public void rotate() {
        int[][] beforeShape = board.getNowBlock().getShape();
        int[][] afterShape = IntMatrixUtil.rotateClockwise(board.getNowBlock().getShape());

        int prevXPos = board.getxPos();
        int prevYPos = board.getyPos();

        board.getNowBlock().setShape(afterShape);
        for (int xVal = 0; xVal >= -1; xVal--) {
            for (int yVal = 0; yVal <= 2; yVal++) {
                board.setxPos(prevXPos + xVal); board.setyPos(prevYPos + yVal);
                if (isEmpty(afterShape)) {
                    board.getNowBlock().setShape(afterShape);
                    return;
                }
    
                board.setyPos(prevYPos - yVal);
                if (isEmpty(afterShape)) {
                    board.getNowBlock().setShape(afterShape);
                    return;
                }
            }
        }

        board.setxPos(prevXPos); board.setyPos(prevYPos);
        board.getNowBlock().setShape(beforeShape);
    }


    public boolean isDead() {
        final int[][] test = board.getBoard()[3];
        for (int x = 0; x < 10; x++) {
            if (test[x][Board.TYPE] != Board.TYPE_EMPTY)
                return true;
        }

        return false;
    }

    private void moveBoardLineDown(int[][][] board, int deletedRow) {
        for (int i = deletedRow; i > 0; i--) {
            board[i] = board[i - 1];
        }

        board[0] = new int[10][2];
        for (int i = 0; i < 10; i++) {
            board[0][i][Board.TYPE] = Board.TYPE_EMPTY;
            board[0][i][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
        }
    }

    public List<Integer> getArrayFullLines() {
        List<Integer> toDelete = new ArrayList<>();
        int[][][] tBoard = board.getBoard();

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (tBoard[r][c][Board.TYPE] != Board.TYPE_STATIC)
                    break;
                    if (c == 9) {
                        toDelete.add(r);
                    }
            }
        }

        return toDelete;
    }

    public int countFullLine() {

        List<Integer> toDelete = getArrayFullLines();

        int count = toDelete.size();

        return count;
    }

    public List<Integer> deleteFullLine() {
        List<Integer> toDelete = getArrayFullLines();

        for (Integer i : toDelete) {
            moveBoardLineDown(board.getBoard(), i);
        }

        return toDelete;
    }

    public void init() {
        int[][][] tBoard = board.getBoard();

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                tBoard[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        board.setPrevBlock(blockService.getRandomBlock());
        updateBlocks();
    }
}