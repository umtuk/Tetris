package Tetris.view.frame.game;

import Tetris.domain.board.service.BoardService;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.view.abstractComponent.container.panel.BoardJPanel;
import Tetris.view.abstractComponent.container.panel.NextBlockJPanel;
import Tetris.view.abstractComponent.container.panel.ScoreBoardJPanel;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;
import Tetris.view.frame.score.InputNameFrame;
import Tetris.view.frame.score.ScoreBoardFrame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class GameFrame extends SimpleJFrame {

    private final String title = "Tetris";

    private static MainConfig mainConfig = MainConfig.getInstance();

    private BoardService boardService;
    private ScoreService scoreService;

    private final static int minInterval = 50;
    public static int periodInterval = 1000;
    private static int rateOfDecrease = 25;

    private static Timer updateTimer;
    private Timer redrawTimer;

    private BoardJPanel boardJPanel;
    private NextBlockJPanel nextBlockJPanel;
    private ScoreBoardJPanel scoreBoardJPanel;
    
    private JPanel eastPanel;
    private JPanel westPanel;

    public GameFrame() {
        super();

        boardService = BoardService.getInstance();
        scoreService = ScoreService.getInstance();

        addComponents();
        initFrame();

        run(mainConfig.getDifficulty());
    }

    public GameFrame(int mode) {
        super();

        boardService = BoardService.getInstance();
        scoreService = ScoreService.getInstance();

        addComponents();
        initFrame();

        boardService.setItemMode();
        scoreService.setMode(mode);

        run(mainConfig.getDifficulty());
    }

    public void run(Difficulty difficulty) {
        start(difficulty);
    }


    private void initFrame() {

    }

    private void addComponents() {
        WindowSize windowSize = mainConfig.getWindowSize();

        setLayout(new BorderLayout());

        boardJPanel = new BoardJPanel();
        nextBlockJPanel = new NextBlockJPanel();
        scoreBoardJPanel = new ScoreBoardJPanel();

        eastPanel = new JPanel();
        westPanel = new JPanel();

        westPanel.setBackground(Color.GRAY);
        westPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()/4), windowSize.getHeight())); 
        eastPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()/4), windowSize.getHeight()/2));
        boardJPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()/3), (int)(windowSize.getHeight()*0.9)));
        nextBlockJPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()/8), (int)(windowSize.getWidth()/8)));
        scoreBoardJPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()/8), (int)(windowSize.getWidth()/8)));

        eastPanel.setBackground(Color.GRAY);
        eastPanel.add(nextBlockJPanel);
        eastPanel.add(scoreBoardJPanel);

        add(westPanel, BorderLayout.WEST);
        add(boardJPanel, BorderLayout.CENTER); 
        add(eastPanel, BorderLayout.EAST);

        // addKeyListener(this);
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

        if (boardService.isItemMode()) {
            boardService.init();
            boardService.setItemMode();
        }
        else {
            boardService.init();
        }
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
            try {
                update();
            } catch (Exception exception) {
                //TODO: handle exception
            }
        }
        
    }

    private class RepaintCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (boardService.getForceQuit()) {
                updateTimer.stop();
                redrawTimer.stop();

                dispose();
                try {
                    new InputNameFrame();
                } catch (Exception exception) {
                    //TODO: handle exception
                }
            }
            scoreBoardJPanel.updateLabels();
            repaint();
        }
    }

    public static List<Integer> toDelete;

    private void update() throws SQLException {

        if (toDelete != null) {
            boardService.deleteFullLine(toDelete);
            toDelete = null;
        }

        if (boardService.isPause()) {
            return;
        }

        if (boardService.moveBlockDown()) {
            setPeriodInterval();
        }

        toDelete = boardService.getArrayFullLines();
        if (toDelete != null && !toDelete.isEmpty()) {
            int deletedLines = toDelete.size();
            int clock = periodInterval;

            scoreService.updateScore(deletedLines, clock);
            
        }


        if (boardService.isDead()) {
            updateTimer.stop();
            redrawTimer.stop();


            if (boardService.isItemMode()) {
                scoreService.setMode(Score.ITEM_MODE);
            }
            else {
                scoreService.setMode(Score.DEFAULT_MODE);
            }

            
            new InputNameFrame();

            dispose();
        }
    }

    public static void setPeriodInterval() {
        periodInterval -= rateOfDecrease;
        if (periodInterval < minInterval) {
            periodInterval = minInterval;
        }
        updateTimer.setDelay(periodInterval);
    }

    public static void drawDeletedLine(List<Integer> toDeleted) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            GameFrame gameFrame = new GameFrame();
        });
    }
}