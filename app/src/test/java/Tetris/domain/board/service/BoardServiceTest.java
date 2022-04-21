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
        boardService.init();

        int[][][] tBoard = boardService.getBoard();
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

        int count = boardService.countFullLine();
        assertTrue(count == 1);
        //assertTrue(Arrays.deepEquals(tBoard, expected));

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

        count = boardService.countFullLine();
        assertTrue(count == 2);
        //assertTrue(Arrays.deepEquals(tBoard, expected));

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

        count = boardService.countFullLine();
        assertTrue(count == 3);
        //assertTrue(Arrays.deepEquals(tBoard, expected));

        for (int r = 20; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[r][c][Board.COLOR] = BaseColor.BLUE.getColor();
            }
        }

        count = boardService.countFullLine();
        assertTrue(count == 4);
        //assertTrue(Arrays.deepEquals(tBoard, expected));
        
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

        count = boardService.countFullLine();
        //assertTrue(count == 4);
        //assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testGetBoard() {
        
        boardService.init();

        int[][][] tBoard = boardService.getBoard();

        assertTrue(tBoard.equals(boardService.getBoard()));
    }

    @Test
    void testGetNowBlock() {
        
        boardService.init();

        Block prev = boardService.getNowBlock();

        assertTrue(prev.equals(boardService.getNowBlock()));
    }

    @Test
    void testGetNowBlockPos() {
        
        boardService.init();

        Block iBlock = new IBlock(ColorSet.DEFAULT);
        boardService.setNowBlock(iBlock);

        boardService.setxPos(5);
        boardService.setyPos(5);

        int[][] nowBlockPos = boardService.getNowBlockPos();

        assertTrue(nowBlockPos[0][0] == 5);
        assertTrue(nowBlockPos[1][0] == 5);
        assertTrue(nowBlockPos[2][0] == 5);
        assertTrue(nowBlockPos[3][0] == 5);

        assertTrue(nowBlockPos[0][1] == 4);
        assertTrue(nowBlockPos[1][1] == 5);
        assertTrue(nowBlockPos[2][1] == 6);
        assertTrue(nowBlockPos[3][1] == 7);

        Block zBlock = new ZBlock(ColorSet.DEFAULT);
        boardService.setNowBlock(zBlock);

        boardService.setxPos(7);
        boardService.setyPos(7);

        nowBlockPos = boardService.getNowBlockPos();

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
        
        boardService.init();

        int[][][] expected = new int[24][10][2];
        for (int i = 0; i < 24; i++ ) {
            for (int j = 0; j < 10; j++) {
                expected[i][j][0] = Board.TYPE_EMPTY;
                expected[i][j][1] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        Arrays.deepEquals(expected, boardService.getBoard());

        Block now = boardService.getNowBlock();
        assertTrue(now.getShape() != null);

        Block next = boardService.getPrevBlock();
        assertTrue(next.getShape() != null);
    }

    @Test
    void testIsDead() {
        
        boardService.init();

        int[][][] tBoard = boardService.getBoard();

        for (int i = 4; i < 24; i++) {
            for (int j = 0; j < 10; j++) {
                tBoard[i][j][0] = Board.TYPE_STATIC;
                tBoard[i][j][1] = BaseColor.BLUE.getColor();
            }
        }

        assertTrue(!boardService.isDead());

        tBoard[3][0][0] = Board.TYPE_STATIC;
        assertTrue(boardService.isDead());
    }

    @Test
    void testMoveBlockAtOnce() {
        
        boardService.init();

        int[][][] tBoard = boardService.getBoard();
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
        boardService.setNowBlock(iBlock);
        boardService.setxPos(3);
        boardService.setyPos(3);
        iBlock.setShape(IntMatrixUtil.rotateClockwise(iBlock.getShape()));

        boardService.moveBlockAtOnce();

        //assertTrue(Arrays.deepEquals(tBoard, expected));


        boardService.init();
        boardService.setNowBlock(new IBlock(ColorSet.DEFAULT));
        boardService.setxPos(3);
        boardService.setyPos(3);

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

        boardService.moveBlockAtOnce();

        //assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testMoveBlockDown() {
        
        boardService.init();

        int[][][] tBoard = boardService.getBoard();
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
        boardService.setNowBlock(iBlock);
        boardService.setxPos(3);
        boardService.setyPos(3);
        iBlock.setShape(IntMatrixUtil.rotateClockwise(iBlock.getShape()));

        boardService.moveBlockDown();

        assertTrue(boardService.getxPos() == 4);
        assertTrue(boardService.getyPos() == 3);

        boardService.setxPos(21);
        boardService.moveBlockDown();

        //assertTrue(Arrays.deepEquals(tBoard, expected));

        boardService.setNowBlock(new IBlock(ColorSet.DEFAULT));
        boardService.setxPos(19);
        boardService.setyPos(3);

        expected[19][2][0] = Board.TYPE_STATIC;
        expected[19][3][0] = Board.TYPE_STATIC;
        expected[19][4][0] = Board.TYPE_STATIC;
        expected[19][5][0] = Board.TYPE_STATIC;

        expected[19][2][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][3][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][4][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);
        expected[19][5][1] = BlockColorMap.getColor(ColorSet.DEFAULT, BlockType.IBLOCK);

        boardService.moveBlockDown();

        //assertTrue(Arrays.deepEquals(tBoard, expected));
    }

    @Test
    void testMoveBlockLeft() {
        
        boardService.init();

        boardService.setNowBlock(new IBlock(ColorSet.DEFAULT));
        boardService.setxPos(3);
        boardService.setyPos(1);

        boardService.moveBlockLeft();

        assertSame(boardService.getxPos(), 3);
        assertSame(boardService.getyPos(), 1);

        boardService.setyPos(5);

        boardService.moveBlockLeft();

        assertSame(boardService.getxPos(), 3);
        assertSame(boardService.getyPos(), 4);
    }

    @Test
    void testMoveBlockRight() {
        
        boardService.init();

        boardService.setNowBlock(new IBlock(ColorSet.DEFAULT));
        boardService.setxPos(3);
        boardService.setyPos(1);

        boardService.moveBlockRight();

        assertSame(boardService.getxPos(), 3);
        assertSame(boardService.getyPos(), 2);

        boardService.setyPos(8);

        boardService.moveBlockRight();

        assertSame(boardService.getxPos(), 3);
        assertSame(boardService.getyPos(), 8);
    }

    @Test
    void testRotate() {
        
        boardService.init();

        boardService.setNowBlock(new IBlock(ColorSet.DEFAULT));
        boardService.setxPos(3);
        boardService.setyPos(2);

        boardService.rotate();

        int[][] expected = {
            {1},
            {1},
            {1},
            {1},
        };

        assertTrue(Arrays.deepEquals(boardService.getNowBlock().getShape(), expected));

        int[][][] tBoard = boardService.getBoard();

        for (int r = 20; r  <24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][0] = Board.TYPE_STATIC;
                tBoard[r][c][1] = BaseColor.BLUE.getColor();
            }
        }

        boardService.rotate();
        boardService.setxPos(19);
        boardService.setyPos(1);

        boardService.rotate();

        int[][] nowBoardPos = boardService.getNowBlockPos();

        for (int i = 0; i < 4; i++) {
            assertSame(tBoard[nowBoardPos[i][0]][nowBoardPos[i][1]][0], Board.TYPE_EMPTY);
        }
    }
}
