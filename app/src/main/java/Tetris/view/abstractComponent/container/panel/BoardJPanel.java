package Tetris.view.abstractComponent.container.panel;

import java.awt.Color;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;

import Tetris.domain.block.entity.Block;
import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.domain.board.service.BoardService;
import Tetris.global.matrix.IntMatrixUtil;
import Tetris.view.adapter.GameAdapter;
import Tetris.view.frame.game.GameFrame;


public class BoardJPanel extends JPanel {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;

    private BoardService boardService = BoardService.getInstance();

    public BoardJPanel() {
        initBoard();
        initPanel();
    }

    private void initBoard() {
        boardService.init();
    }

    private void initPanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400, 800));
        setFocusable(true);
        addKeyListener(new GameAdapter());
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }


    private void drawSquare(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
    }

    private void drawText(Graphics g, int x, int y, Color color, String shape) {
        int fontSize = (int)(squareWidth());
        g.setColor(color);
        g.setFont(new Font("Serif", Font.BOLD, fontSize));
        g.drawString(shape, x * squareWidth(), (int)((y + 1) * squareWidth() * .74));
    }

    public void drawBoard(Graphics g) {
        int[][][] tBoard = boardService.getBoard();

        for (int r = 4; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                Color color = new Color(boardService.getBoard()[r][c][Board.COLOR]);
                String shape;
                if (tBoard[r][c][0] == Board.TYPE_LINE_REMOVER) {
                    shape = "L";
                }
                else  {
                    shape = "O";
                }
                drawText(g, c, (r - 4), color, shape);
                //drawText(g, c, (r - 4), color, "O");
                //drawSquare(g, c * squareWidth(), (r - 4) * squareHeight(), color);
            }
        }
    }

    public void drawNowBlock(Graphics g) {
        int[][] nowBlockPos = boardService.getNowBlockPos();
        Block nowBlock = boardService.getNowBlock();
        int lineRemoverIdx = boardService.findLineRemover(nowBlock);
        int count = IntMatrixUtil.countNotZeroValue(nowBlock.getShape());

        for (int i = 0; i < count; i++) {
            int x = nowBlockPos[i][1];
            int y = nowBlockPos[i][0] - 4;

            Color color = new Color(boardService.getNowBlock().getColor());
            String shape;
            if (boardService.isBomb(nowBlock)) {
                shape = "B";
            }
            else if (boardService.isDrill(nowBlock)) {
                shape = "D";
            }
            else if (i == lineRemoverIdx) {
                shape = "L";
            }
            else {
                shape = "O";
            }

            // drawSquare(g, x * squareWidth(), y * squareHeight(), color);
            //drawText(g, x, y, color, "O");
            drawText(g, x, y, color, shape);
        }
    }

    public void drawDeletedBlock(Graphics g) {

    }

    public void drawDeletedLine(Graphics g) {
        if (GameFrame.toDelete != null) {
            for (int y : GameFrame.toDelete) {
                for (int x = 0; x < 10; x++) {
                    int xPos = x * squareWidth();
                    int yPos = (y - 4) * squareHeight();

                    Color color = new Color(boardService.getBoard()[y][x][Board.COLOR] / 2);

                    drawSquare(g, xPos, yPos, color);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
        drawDeletedLine(g);
        drawNowBlock(g);
    }
}
