package Tetris.view.abstractComponent.container.panel;

import java.awt.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.entity.Board;
import Tetris.domain.board.service.BoardService;
import Tetris.global.config.constant.Difficulty;
import Tetris.view.adapter.GameAdapter;

public class BoardJPanel extends JPanel {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;

    private final int minInterval = 50;
    private int periodInterval = 1000;
    private int rateOfDecrease = 25;

    private Timer updateTimer;
    private Timer redrawTimer;

    private boolean isPaused;

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

    private void berforeStart(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            periodInterval = 1500;
            rateOfDecrease = 2;
        }
        else if (difficulty == Difficulty.NORMAL) {
            periodInterval = 1000;
            rateOfDecrease = 5;
        }
        else if (difficulty == Difficulty.HARD) {
            periodInterval = 750;
            rateOfDecrease = 7;
        }

        boardService.init();
        isPaused = false;
    }

    public void start(Difficulty difficulty) {
        berforeStart(difficulty);

        updateTimer = new Timer(periodInterval, new UpdateCycle());
        updateTimer.start();

        redrawTimer = new Timer(25, new RepaintCycle());
        redrawTimer.start();
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


    private class UpdateCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            update();
        }
        
    }

    private class RepaintCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            repaint();
        }
        
    }

    private void update() {
        if (isPaused) {
            return;
        }

        List<Integer> toDelete = boardService.moveBlockDown();
        if (toDelete != null && !toDelete.isEmpty()) {
            // 줄 사라질 때 쓸 이펙트
        }


        periodInterval -= rateOfDecrease;
        if (periodInterval < minInterval) {
            periodInterval = minInterval;
        }
        updateTimer.setDelay(periodInterval);

        if (boardService.isDead()) {
            updateTimer.stop();
            redrawTimer.stop();
            // 게임 종료 창??
        }
    }
}
