package Tetris.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import Tetris.domain.block.entity.Block;
import Tetris.domain.block.service.BlockService;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.matrix.IntMatrixUtil;
import Tetris.view.frame.game.GameFrame;

public class BoardService {

    private static BoardService INSTANCE = new BoardService();

    public static BoardService getInstance() {
        return INSTANCE;
    }
    
    private BlockService blockService = BlockService.getInstance();
    private ScoreService scoreService = ScoreService.getInstance();

    private Board board;

    private boolean isPause;

    private boolean isItemMode;

    private boolean forceQuit;

    private int lineCount;
    private final int PER_LINES = 1;

    public boolean isItemMode() {
        return isItemMode;
    }

    public BoardService() {
        board = new Board();
    }

    public boolean isPause() {
        return this.isPause;
    }

    public void switchPause() {
        isPause = true ^ isPause;
    }

    public void onItemMode() {
        isItemMode = true;
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

        return findRealPos(shape, xPos, yPos, center, IntMatrixUtil.countNotZeroValue(shape));
    }

    private void initNowBlockPos() {
        board.setyPos(5);
        board.setxPos(5 - IntMatrixUtil.lengthCenter2Bottom(board.getNowBlock().getShape()));
    }

    private List<Integer> updateBlocks() {
        board.setNowBlock(board.getPrevBlock());
        initNowBlockPos();

        // board.setPrevBlock(blockService.getRandomBlock());

        if (isItemMode && lineCount >= PER_LINES) {
            board.setPrevBlock(blockService.getRandomItem());
            lineCount %= PER_LINES;
        }
        else {
            board.setPrevBlock(blockService.getRandomBlock());
        }


        int[][] shape = board.getNowBlock().getShape();
        if (isItem(shape, Board.TYPE_LINE_REMOVER)) {
            System.out.println("TYPE_LINE_REMOVER");
        }
        else if (isBomb(shape)) {
            System.out.println("Bomb");
        }
        else if (isBonusScore(shape)) {
            System.out.println("BonusScore");
        }
        else if (isDrill(shape)) {
            System.out.println("drill");
        }
        else if (isWeight(shape)) {
            System.out.println("WEIGHT");
        }
        else {
            System.out.println("BAIC BLOCK");
        }


        return getArrayFullLines();
    }


    public int findLineRemover(Block block) {
        int[][] shape = block.getShape();
        int i = 0;
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {

                if (shape[r][c] != Board.TYPE_EMPTY) {
                    if (shape[r][c] == Board.TYPE_LINE_REMOVER) {
                        return i;
                    }
                    i++;
                }
            }
        }

        return -1;
    }

    private void convertBlockToBoard() {
        int[][][] tBoard = board.getBoard();
        int[][] blockPosNShape = findNowBlockPos();
        Block nowBlock = board.getNowBlock();
        int blockColor = nowBlock.getColor();

        int lineRemoverIdx = findLineRemover(nowBlock);

        if (isBomb(nowBlock)) {
            for (int r = -1; r <= 1; r++) {
                for (int c = -1; c <= 1; c++) {
                    try {
                        int xPos = blockPosNShape[0][0];
                        int yPos = blockPosNShape[0][1];

                        tBoard[xPos + r][yPos + c][Board.TYPE] = Board.TYPE_EMPTY;
                        tBoard[xPos + r][yPos + c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        else if (isBonusScore(nowBlock)) {
            scoreService.updateScore(5, GameFrame.periodInterval);
            for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlock.getShape()); i++) {
                int xPos = blockPosNShape[i][0];
                int yPos = blockPosNShape[i][1];
    
                tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_STATIC;
                tBoard[xPos][yPos][Board.COLOR] = blockColor;
            }
        }
        else {
            for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlock.getShape()); i++) {
                int xPos = blockPosNShape[i][0];
                int yPos = blockPosNShape[i][1];

                if (i == lineRemoverIdx) {
                    tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_LINE_REMOVER;
                }
                else {
                    tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_STATIC;
                }
                tBoard[xPos][yPos][Board.COLOR] = blockColor;
            }
        }
//        for (int i = 0; i < 4; i++) {
//            int xPos = blockPosNShape[i][0];
//            int yPos = blockPosNShape[i][1];
//
//            tBoard[xPos][yPos][Board.TYPE] = Board.TYPE_STATIC;
//            tBoard[xPos][yPos][Board.COLOR] = blockColor;
//        }

        lineCount += countFullLine();
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
        if (board.getNowBlock().isMovable()) {
            try {
                int[][] nowBlockPos = findNowBlockPos();

                if (isDrill(board.getNowBlock())) {
                    for (int i = 0; i < IntMatrixUtil.countNotZeroValue(board.getNowBlock().getShape()); i++) {
                        if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] + 1][Board.TYPE] != Board.TYPE_EMPTY) {
                                continue;
                        }
                    }
                    removePos(nowBlockPos);
                }
                else {
                    for (int i = 0; i < IntMatrixUtil.countNotZeroValue(board.getNowBlock().getShape()); i++) {
                        if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] + 1][Board.TYPE] != Board.TYPE_EMPTY) {
                            return;
                        }
                    }
                }
    
                board.setyPos(board.getyPos() + 1);
            } catch (IndexOutOfBoundsException e) {
                
            }
        }
    }

    public void moveBlockLeft() {
        if (board.getNowBlock().isMovable()) {
            try {
                int[][] nowBlockPos = findNowBlockPos();
                int[][] nowBlockShape = board.getNowBlock().getShape();
                
                if (isDrill(board.getNowBlock())) {
                    for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlockShape); i++) {
                        if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] - 1][Board.TYPE] != Board.TYPE_EMPTY) {
                                continue;
                        }
                    }
                    removePos(nowBlockPos);
                }
                else {
                    for (int i = 0; i < IntMatrixUtil.countNotZeroValue(board.getNowBlock().getShape()); i++) {
                        if (board.getBoard()[nowBlockPos[i][0]][nowBlockPos[i][1] - 1][Board.TYPE] != Board.TYPE_EMPTY) {
                            return;
                        }
                    }
                }
    
                board.setyPos(board.getyPos() - 1);
            } catch (IndexOutOfBoundsException e) {
                
            }
        }
    }

    private void removePos(int[][] pos) {
        int[][][] tBoard = board.getBoard();

        for (int i = 0; i < pos.length; i++) {
            int x = pos[i][0];
            int y = pos[i][1];

            tBoard[x][y][0] = Board.TYPE_EMPTY;
            tBoard[x][y][1] = BoardColorMap.getColor(BoardComponent.EMPTY);
        }
    }

    private boolean isItem(int[][] shape, int i) {
        for (int r = 0; r < shape.length; r++) {
            for (int c = 0; c < shape[r].length; c++) {
                if (shape[r][c] == i)
                    return true;
            }
        }

        return false;
    }

    private boolean isBomb(int[][] shape) {
        return isItem(shape, Board.TYPE_BOMB);
    }

    private boolean isBonusScore(int[][] shape) {
        return isItem(shape, Board.TYPE_BONUS_SCORE);
    }

    private boolean isDrill(int[][] shape) {
        return isItem(shape, Board.TYPE_DRILL);
    }

    private boolean isWeight(int[][] shape) {
        return isItem(shape, Board.TYPE_WEIGHT);
    }

    public boolean isBomb(Block block) {
        return isBomb(block.getShape());
    }

    public boolean isBonusScore(Block block) {
        return isBonusScore(block.getShape());
    }

    public boolean isDrill(Block block) {
        return isDrill(block.getShape());
    }

    public boolean isWeight(Block block) {
        return isWeight(block.getShape());
    }

    public boolean isLineRemover(Block block) {
        return isItem(block.getShape(), Board.TYPE_LINE_REMOVER);
    }


    public boolean moveBlockDown() {

        int[][] nowBlockShape = board.getNowBlock().getShape();
        int[][] nowBlockPos = findNowBlockPos();

        try {

            if (isWeight(nowBlockShape)) {
                for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlockShape); i++) {
                    if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                            board.getNowBlock().setMovable(false);
                    }
                }
                removePos(nowBlockPos);
            } 
            else if (isDrill(nowBlockShape)) {
                for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlockShape); i++) {
                    if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                            continue;
                    }
                }
                removePos(nowBlockPos);
            } 
            else {   
                for (int i = 0; i < IntMatrixUtil.countNotZeroValue(nowBlockShape); i++) {
                    if (board.getBoard()[nowBlockPos[i][0] + 1][nowBlockPos[i][1]][Board.TYPE] != Board.TYPE_EMPTY) {
                            convertBlockToBoard();
                            updateBlocks();
                            return true;
                    }
                }
            }
            
            board.setxPos(board.getxPos() + 1);
            scoreService.updateScore(0, GameFrame.periodInterval);
        } catch (IndexOutOfBoundsException e) {
            if (isWeight(nowBlockShape)) {
                removePos(nowBlockPos);
                updateBlocks();
            }
            else if (isDrill(nowBlockShape)) {
                removePos(nowBlockPos);
                updateBlocks();
            }
            else {
                convertBlockToBoard();
                updateBlocks();
            }

            return true;
        }

        return false;
    }

    public boolean moveBlockAtOnce() {

        while (!moveBlockDown()) {}

        return true;
    }

    private boolean isEmpty(int[][] pos) {
        int[][] matrix = board.getNowBlock().getShape();
        int xPos = board.getxPos();
        int yPos = board.getyPos();
        int[] center = IntMatrixUtil.findNearestCenter(matrix);

        int[][] realPos = findRealPos(pos, xPos, yPos, center, IntMatrixUtil.countNotZeroValue(matrix));

        int[][][] tBoard = board.getBoard();

        try {
            for (int i = 0; i < IntMatrixUtil.countNotZeroValue(matrix); i++) {
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
        if (board.getNowBlock().isRotatable()) {
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
                if (tBoard[r][c][Board.TYPE] == Board.TYPE_LINE_REMOVER) {
                    toDelete.add(r);
                }
            }
        }

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                if (tBoard[r][c][Board.TYPE] != Board.TYPE_STATIC)
                    break;
                    if (c == 9) {
                        toDelete.add(r);
                    }
            }
        }

        System.out.println("getArrayFullLines");
        for (int asdf : toDelete) {
            System.out.print(asdf + " ");
        }
        System.out.println();

        return toDelete;
    }

    public int countFullLine() {

        List<Integer> toDelete = getArrayFullLines();

        int count = toDelete.size();

        System.out.println("conutFullLine: " + count);

        return count;
    }

    public void deleteFullLine(List<Integer> toDelete) {
        for (Integer i : toDelete) {
            moveBoardLineDown(board.getBoard(), i);
        }
    }

    public void init() {
        int[][][] tBoard = board.getBoard();

        for (int r = 0; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                tBoard[r][c][Board.TYPE] = Board.TYPE_EMPTY;
                tBoard[r][c][Board.COLOR] = BoardColorMap.getColor(BoardComponent.EMPTY);
            }
        }

        isPause = false;
        isItemMode = false;
        forceQuit = false;

        lineCount = 0;

        board.setPrevBlock(blockService.getRandomBlock());
        updateBlocks();
    }

    public void setForceQuit() {
        forceQuit = true;
    }

    public void setItemMode() {
        isItemMode = true;
    }

    public boolean getForceQuit() {
        return forceQuit;
    }
}