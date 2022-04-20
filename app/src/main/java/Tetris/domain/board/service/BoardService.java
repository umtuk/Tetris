package Tetris.domain.board.service;

import java.lang.reflect.Array;
import java.security.cert.PKIXBuilderParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.domain.block.entity.expend.IBlock;
import Tetris.domain.block.entity.expend.JBlock;
import Tetris.domain.block.entity.expend.ZBlock;
import Tetris.domain.block.service.BlockService;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.constant.color.BaseColor;
import Tetris.global.matrix.IntMatrixUtil;

public class BoardService {

    private static BoardService INSTANCE = new BoardService();

    public static BoardService getInstance() {
        return INSTANCE;
    }
    
    private BlockService blockService = BlockService.getInstance();

    private int[][] findRealPos(int[][] matrix, int xPos, int yPos, int[] center, int count) {

        int[][] ret = new int[count][2];

        int[][] notZeroValue = IntMatrixUtil.findAllNotZeroValuePos(matrix, count);

        for (int i = 0; i < count; i++) {
            ret[i][0] = xPos + (notZeroValue[i][0] - center[0]);
            ret[i][1] = yPos + (notZeroValue[i][1] - center[1]);
        }

        return ret;
    }

    private int[][] findNowBlockPos(Board board) {
        Block block = board.getNowBlock();
        int[][] shape = block.getShape();

        int xPos = board.getxPos();
        int yPos = board.getyPos();

        int[] center = IntMatrixUtil.findNearestCenter(shape);

        return findRealPos(shape, xPos, yPos, center, 4);
    }

    private void initNowBlockPos(Board board) {
        board.setyPos(5);
        board.setxPos(20 + IntMatrixUtil.lengthCenter2Bottom(board.getNowBlock().getShape()));
    }

    private void updateBlocks(Board board) {
        board.setNowBlock(board.getPrevBlock());
        initNowBlockPos(board);

        board.setPrevBlock(blockService.getRandomBlock());
    }

    private void convertBlockToBoard(Board board) {
        int[][][] tBoard = board.getBoard();
        int[][] blockPosNShape = findNowBlockPos(board);
        int blockColor = board.getNowBlock().getColor();

        for (int i = 0; i < 4; i++) {
            System.out.println("(" + blockPosNShape[i][0] + ", " + blockPosNShape[i][1] + ")");
        }

        for (int i = 0; i < 4; i++) {
            int xPos = blockPosNShape[i][0];
            int yPos = blockPosNShape[i][1];

            tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_STATIC;
            tBoard[xPos][yPos][Board.COLOR] = blockColor;
        }
    }


    public int[][][] getBoard(Board board) {
        return board.getBoard();
    }

    public int[][] getNowBlockPos(Board board) {
        return findNowBlockPos(board);
    }

    public Block getNowBlock(Board board) {
        return board.getNowBlock();
    }


    public void moveBlockRight(Board board) {
        try {
            int[][] nowBlockPos = findNowBlockPos(board);

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] + 1][Board.TYPE] != Board.TYPE_EMPTY) {
                    return;
                }
            }

            board.setyPos(board.getyPos() + 1);
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public void moveBlockLeft(Board board) {
        try {
            int[][] nowBlockPos = findNowBlockPos(board);

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] - 1][Board.TYPE] != Board.TYPE_EMPTY) {
                    return;
                }
            }

            board.setyPos(board.getyPos() - 1);
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

    public void moveBlockDown(Board board) {
        try {
            int[][] nowBlockPos = findNowBlockPos(board);

            for (int i = 0; i < 4; i++) {
                if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                    convertBlockToBoard(board);
                    updateBlocks(board);
                    return;
                }
            }
            
            board.setxPos(board.getxPos() + 1);
        } catch (IndexOutOfBoundsException e) {
            convertBlockToBoard(board);
            updateBlocks(board);
        }
    }

    public void moveBlockAtOnce(Board board) {
        try {
            for (int a = 0; a < 24; a++) {
                int[][] nowBlockPos = findNowBlockPos(board);

                for (int i = 0; i < 4; i++) {
                    if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                        convertBlockToBoard(board);
                        updateBlocks(board);
                        return;
                    }
                }
                
                board.setxPos(board.getxPos() + 1);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("!!!");
            convertBlockToBoard(board);
            updateBlocks(board);
        }
    }

    private boolean isEmpty(Board board, int[][] pos) {
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

    public void rotate(Board board) {
        int[][] afterShape = IntMatrixUtil.rotateClockwise(board.getNowBlock().getShape());

        int prevXPos = board.getxPos();
        int prevYPos = board.getyPos();

        for (int xVal = 0; xVal >= -3; xVal--) {
            for (int yVal = 0; yVal <= 2; yVal++) {
                board.setxPos(prevXPos + xVal); board.setyPos(prevYPos + yVal);
                if (isEmpty(board, afterShape)) {
                    board.getNowBlock().setShape(afterShape);
                    return;
                }

                board.setyPos(prevYPos - yVal);
                if (isEmpty(board, afterShape)) {
                    board.getNowBlock().setShape(afterShape);
                    return;
                }
            }
        }

        board.setxPos(prevXPos); board.setyPos(prevYPos);
    }


    public boolean isDead(Board board) {
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

    public int countFullLine(Board board) {
        int[][][] tBoard = board.getBoard();

        List<Integer> toDelete = new ArrayList<>();

        int count = 0;

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (tBoard[r][c][Board.TYPE] != Board.TYPE_STATIC)
                    break;
                    if (c == 9) {
                        count++;
                        toDelete.add(r);
                    }
            }
        }

        for (Integer i : toDelete) {
            moveBoardLineDown(board.getBoard(), i);
        }

        return count;
    }

    public void init(Board board) {
        int[][][] tBoard = board.getBoard();

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                tBoard[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        board.setPrevBlock(blockService.getRandomBlock());
        updateBlocks(board);
    }

    public static void main(String[] args) {
        BoardService boardService = BoardService.getInstance();

        Board board = new Board();
        boardService.init(board);

        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(3);
        board.setyPos(2);

        boardService.rotate(board);

        int[][][] tBoard = board.getBoard();

        for (int r = 20; r  <24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][0] = Board.TYPE_STATIC;
                tBoard[r][c][1] = BaseColor.BLUE.getColor();
            }
        }

        boardService.rotate(board);
        board.setxPos(19);
        board.setyPos(1);
        int[][] nowBoardPos = boardService.getNowBlockPos(board);

        for (int i = 0; i < 4; i++) {
            System.out.println("(" + nowBoardPos[i][0] + " , " + nowBoardPos[i][1] + ")");
        }

        boardService.rotate(board);

        nowBoardPos = boardService.getNowBlockPos(board);
        for (int i = 0; i < 4; i++) {
            System.out.println("(" + nowBoardPos[i][0] + " , " + nowBoardPos[i][1] + ")");
        }
    }

}
