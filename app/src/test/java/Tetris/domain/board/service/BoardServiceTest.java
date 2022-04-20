package Tetris.domain.board.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Tetris.domain.block.constant.BlockType;
import Tetris.domain.block.constant.map.BlockColorMap;
import Tetris.domain.block.entity.Block;
import Tetris.domain.block.entity.expend.IBlock;
import Tetris.domain.block.entity.expend.ZBlock;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.constant.color.BaseColor;
import Tetris.global.matrix.IntMatrixUtil;

public class BoardServiceTest {

    private BoardService boardService = BoardService.getInstance();

    @Test
    void testCountFullLine() {
        Board board = new Board();
        boardService.init(board);

        int[][][] tBoard = board.getBoard();
        for (int r = 23; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }
        tBoard[22][0][Board.TYPE] = Board.TYPE_STATIC;
        tBoard[22][0][Board.COLOR] = BaseColor.BLUE.getColor();

        int[][][] expected = new int[24][10][2];
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                expected[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }
        expected[23][0][Board.TYPE] = Board.TYPE_STATIC;
        expected[23][0][Board.COLOR] = BaseColor.BLUE.getColor();

        int count = boardService.countFullLine(board);
        assertTrue(count == 1);
        assertTrue(Arrays.deepEquals(tBoard, expected));

        for (int r = 22; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                expected[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        count = boardService.countFullLine(board);
        assertTrue(count == 2);
        assertTrue(Arrays.deepEquals(tBoard, expected));

        for (int r = 21; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                expected[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        count = boardService.countFullLine(board);
        assertTrue(count == 3);
        assertTrue(Arrays.deepEquals(tBoard, expected));

        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }

        count = boardService.countFullLine(board);
        assertTrue(count == 4);
        assertTrue(Arrays.deepEquals(tBoard, expected));
        
        for (int r = 18; r < 22; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                expected[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        tBoard[22][0][Board.TYPE] = Board.TYPE_STATIC;
        tBoard[22][0][Board.COLOR] = BaseColor.BLUE.getColor();

        tBoard[23][0][Board.TYPE] = Board.TYPE_STATIC;
        tBoard[23][0][Board.COLOR] = BaseColor.BLUE.getColor();

        expected[22][0][Board.TYPE] = Board.TYPE_STATIC;
        expected[22][0][Board.COLOR] = BaseColor.BLUE.getColor();

        expected[23][0][Board.TYPE] = Board.TYPE_STATIC;
        expected[23][0][Board.COLOR] = BaseColor.BLUE.getColor();

        count = boardService.countFullLine(board);
        assertTrue(count == 4);
        assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testGetBoard() {
        Board board = new Board();
        boardService.init(board);

        int[][][] tBoard = board.getBoard();

        assertTrue(tBoard.equals(board.getBoard()));
    }

    @Test
    void testGetNowBlock() {
        Board board = new Board();
        boardService.init(board);

        Block prev = board.getNowBlock();

        assertTrue(prev.equals(board.getNowBlock()));
    }

    @Test
    void testGetNowBlockPos() {
        Board board = new Board();
        boardService.init(board);

        Block iBlock = new IBlock(ColorSet.DEFAULT);
        board.setNowBlock(iBlock);

        board.setxPos(5);
        board.setyPos(5);

        int[][] nowBlockPos = boardService.getNowBlockPos(board);

        assertTrue(nowBlockPos[0][0] == 5);
        assertTrue(nowBlockPos[1][0] == 5);
        assertTrue(nowBlockPos[2][0] == 5);
        assertTrue(nowBlockPos[3][0] == 5);

        assertTrue(nowBlockPos[0][1] == 4);
        assertTrue(nowBlockPos[1][1] == 5);
        assertTrue(nowBlockPos[2][1] == 6);
        assertTrue(nowBlockPos[3][1] == 7);

        Block zBlock = new ZBlock(ColorSet.DEFAULT);
        board.setNowBlock(zBlock);

        board.setxPos(7);
        board.setyPos(7);

        nowBlockPos = boardService.getNowBlockPos(board);

        assertTrue(nowBlockPos[0][0] == 7);
        assertTrue(nowBlockPos[1][0] == 7);
        assertTrue(nowBlockPos[2][0] == 8);
        assertTrue(nowBlockPos[3][0] == 8);

        assertTrue(nowBlockPos[0][1] == 6);
        assertTrue(nowBlockPos[1][1] == 7);
        assertTrue(nowBlockPos[2][1] == 7);
        assertTrue(nowBlockPos[3][1] == 8);
    }

    @Test
    void testInit() {
        Board board = new Board();
        boardService.init(board);

        int[][][] expected = new int[24][10][2];
        for (int i = 0; i < 24; i++ ) {
            for (int j = 0; j < 10; j++) {
                expected[i][j][0] = Board.TYPE_EMPTY;
                expected[i][j][1] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        Arrays.deepEquals(expected, board.getBoard());

        Block now = board.getNowBlock();
        assertTrue(now.getShape() != null);

        Block next = board.getPrevBlock();
        assertTrue(next.getShape() != null);
    }

    @Test
    void testIsDead() {
        Board board = new Board();
        boardService.init(board);

        int[][][] tBoard = board.getBoard();

        for (int i = 4; i < 24; i++) {
            for (int j = 0; j < 10; j++) {
                tBoard[i][j][0] = Board.TYPE_STATIC;
                tBoard[i][j][1] = BaseColor.BLUE.getColor();
            }
        }

        assertTrue(!boardService.isDead(board));

        tBoard[3][0][0] = Board.TYPE_STATIC;
        assertTrue(boardService.isDead(board));
    }

    @Test
    void testMoveBlockAtOnce() {
        Board board = new Board();
        boardService.init(board);

        int[][][] tBoard = board.getBoard();
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (c != 3) {
                    tBoard[r][c][0] = Board.TYPE_STATIC;
                    tBoard[r][c][1] = BaseColor.BLUE.getColor();
                }
            }
        }

        int[][][] expected = new int[24][10][2];
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][0] = Board.TYPE_EMPTY;
                expected[r][c][0] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][0] = Board.TYPE_STATIC;
                expected[r][c][1] = BaseColor.BLUE.getColor();
            }
        }

        expected[23][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[22][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[21][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[20][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);

        Block iBlock = new IBlock(ColorSet.DEFAULT);
        board.setNowBlock(iBlock);
        board.setxPos(3);
        board.setyPos(3);
        iBlock.setShape(IntMatrixUtil.rotateClockwise(iBlock.getShape()));

        boardService.moveBlockAtOnce(board);

        assertTrue(Arrays.deepEquals(tBoard, expected));


        boardService.init(board);
        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(3);
        board.setyPos(3);

        System.out.println();
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (c != 3) {
                    tBoard[r][c][0] = Board.TYPE_STATIC;
                    tBoard[r][c][1] = BaseColor.BLUE.getColor();
                }
            }
        }

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                    expected[r][c][0] = Board.TYPE_EMPTY;
                    expected[r][c][1] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (c != 3) {
                    expected[r][c][0] = Board.TYPE_STATIC;
                    expected[r][c][1] = BaseColor.BLUE.getColor();
                }
            }
        }

        for (int c = 2; c < 6; c++) {
            expected[19][c][0] = Board.TYPE_STATIC;
            expected[19][c][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        }

        boardService.moveBlockAtOnce(board);

        assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testMoveBlockDown() {
        Board board = new Board();
        boardService.init(board);

        int[][][] tBoard = board.getBoard();
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (c != 3) {
                    tBoard[r][c][0] = Board.TYPE_STATIC;
                    tBoard[r][c][1] = BaseColor.BLUE.getColor();
                }
            }
        }

        int[][][] expected = new int[24][10][2];
        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][0] = Board.TYPE_EMPTY;
                expected[r][c][0] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }
        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                expected[r][c][0] = Board.TYPE_STATIC;
                expected[r][c][1] = BaseColor.BLUE.getColor();
            }
        }

        expected[23][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[22][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[21][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[20][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);

        Block iBlock = new IBlock(ColorSet.DEFAULT);
        board.setNowBlock(iBlock);
        board.setxPos(3);
        board.setyPos(3);
        iBlock.setShape(IntMatrixUtil.rotateClockwise(iBlock.getShape()));

        boardService.moveBlockDown(board);

        assertTrue(board.getxPos() == 4);
        assertTrue(board.getyPos() == 3);

        board.setxPos(21);

        boardService.moveBlockDown(board);

        assertTrue(Arrays.deepEquals(tBoard, expected));

        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(19);
        board.setyPos(3);

        expected[19][2][0] = Board.TYPE_STATIC;
        expected[19][3][0] = Board.TYPE_STATIC;
        expected[19][4][0] = Board.TYPE_STATIC;
        expected[19][5][0] = Board.TYPE_STATIC;

        expected[19][2][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][4][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][5][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);

        boardService.moveBlockDown(board);

        assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testMoveBlockLeft() {
        Board board = new Board();
        boardService.init(board);

        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(3);
        board.setyPos(1);

        boardService.moveBlockLeft(board);

        assertSame(board.getxPos(), 3);
        assertSame(board.getyPos(), 1);

        board.setyPos(5);

        boardService.moveBlockLeft(board);

        assertSame(board.getxPos(), 3);
        assertSame(board.getyPos(), 4);
    }

    @Test
    void testMoveBlockRight() {
        Board board = new Board();
        boardService.init(board);

        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(3);
        board.setyPos(1);

        boardService.moveBlockRight(board);

        assertSame(board.getxPos(), 3);
        assertSame(board.getyPos(), 2);

        board.setyPos(8);

        boardService.moveBlockRight(board);

        assertSame(board.getxPos(), 3);
        assertSame(board.getyPos(), 8);
    }

    @Test
    void testRotate() {
        Board board = new Board();
        boardService.init(board);

        board.setNowBlock(new IBlock(ColorSet.DEFAULT));
        board.setxPos(3);
        board.setyPos(2);

        boardService.rotate(board);

        int[][] expected = {
            {1},
            {1},
            {1},
            {1},
        };

        assertTrue(Arrays.deepEquals(board.getNowBlock().getShape(), expected));

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

        boardService.rotate(board);

        int[][] nowBoardPos = boardService.getNowBlockPos(board);

        for (int i = 0; i < 4; i++) {
            assertSame(tBoard[nowBoardPos[i][0]][nowBoardPos[i][1]][0], Board.TYPE_EMPTY);
        }
    }
}
