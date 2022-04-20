package Tetris.view.frame.game;

import Tetris.domain.board.service.BoardService;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.WindowSize;
import Tetris.view.abstractComponent.container.panel.BoardJPanel;
import Tetris.view.abstractComponent.container.panel.NextBlockJPanel;
import Tetris.view.abstractComponent.container.panel.ScoreBoardJPanel;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.List;

public class GameFrame extends SimpleJFrame {

    private final String title = "Tetris";

    private BoardService boardService;
    private ScoreService scoreService;


    private final int minInterval = 50;
    public static int periodInterval = 1000;
    private int rateOfDecrease = 25;

    private Timer updateTimer;
    private Timer redrawTimer;

    private boolean isPaused;


    private BoardJPanel boardJPanel;
    private NextBlockJPanel nextBlockJPanel;
    private ScoreBoardJPanel scoreBoardJPanel;
    
    private JPanel eastPanel;
    private JPanel emptyPanel;

    public GameFrame(WindowSize windowSize, Difficulty difficulty) {
        super(windowSize);

        boardService = BoardService.getInstance();
        scoreService = ScoreService.getInstance();

        addComponents();
        initFrame();

        run(difficulty);
    }

    public void run(Difficulty difficulty) {
        start(difficulty);
    }


    private void initFrame() {
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setSize(600, 800);
        setLocationRelativeTo(null);
    }

    private void addComponents() {
        setLayout(new BorderLayout());

        boardJPanel = new BoardJPanel();
        nextBlockJPanel = new NextBlockJPanel();
        scoreBoardJPanel = new ScoreBoardJPanel();

        eastPanel = new JPanel();
        emptyPanel = new JPanel();

        emptyPanel.setPreferredSize(new Dimension(160, 400)); 
        eastPanel.setPreferredSize(new Dimension(160, 400));

        eastPanel.add(nextBlockJPanel);
        eastPanel.add(scoreBoardJPanel);
        eastPanel.add(emptyPanel);

        add(boardJPanel, BorderLayout.CENTER); 
        add(eastPanel, BorderLayout.EAST);
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
            scoreBoardJPanel.updateLabels();
            repaint();
        }
    }

    private void update() {
        if (isPaused) {
            return;
        }

        List<Integer> toDelete = boardService.moveBlockDown();
        if (toDelete != null && !toDelete.isEmpty()) {
            int deletedLines = toDelete.size();
            int clock = periodInterval;

            scoreService.updateScore(deletedLines, clock);
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
