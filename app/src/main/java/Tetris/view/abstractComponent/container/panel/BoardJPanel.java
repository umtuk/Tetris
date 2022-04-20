package Tetris.view.abstractComponent.container.panel;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.domain.board.service.BoardService;
import Tetris.view.adapter.GameAdapter;

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
        setBackground(new Color(BoardColorMap.getColor(BoardComponent.EMPTY)));
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

    public void drawBoard(Graphics g) {
        for (int r = 4; r < 24; r++) {
            for (int c = 0; c < 10; c++) {
                Color color = new Color(boardService.getBoard()[r][c][Board.COLOR]);
                drawSquare(g, c * squareWidth(), (r - 4) * squareHeight(), color);
            }
        }
    }

    public void drawNowBlock(Graphics g) {
        int[][] nowBlockPos = boardService.getNowBlockPos();

        for (int i = 0; i < 4; i++) {
            int x = nowBlockPos[i][1];
            int y = nowBlockPos[i][0] - 4;

            Color color = new Color(boardService.getNowBlock().getColor());

            drawSquare(g, x * squareWidth(), y * squareHeight(), color);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
        drawNowBlock(g);
    }
}
