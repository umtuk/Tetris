package Tetris.view.frame.game;

import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.WindowSize;
import Tetris.view.abstractComponent.container.panel.BoardJPanel;
import Tetris.view.abstractComponent.container.panel.NextBlockJPanel;
import Tetris.view.abstractComponent.container.panel.ScoreBoardJPanel;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends SimpleJFrame {

    private final String title = "Tetris";

    private BoardJPanel boardJPanel;
    private NextBlockJPanel nextBlockJPanel;
    private ScoreBoardJPanel scoreBoardJPanel;
    
    private JPanel eastPanel;
    private JPanel emptyPanel;

    public GameFrame(WindowSize windowSize, Difficulty difficulty) {
        super(windowSize);

        addComponents();
        initFrame();

        run(difficulty);
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
        boardJPanel = new BoardJPanel(); add(boardJPanel, BorderLayout.WEST); 
        nextBlockJPanel = new NextBlockJPanel();
        scoreBoardJPanel = new ScoreBoardJPanel();

        eastPanel = new JPanel();
        emptyPanel = new JPanel(); 

        eastPanel.setLayout(new GridLayout());

        eastPanel.add(nextBlockJPanel);
        eastPanel.add(scoreBoardJPanel);
        eastPanel.add(emptyPanel);
        add(eastPanel, BorderLayout.EAST);
    }

    public void run(Difficulty difficulty) {
        boardJPanel.start(difficulty);
    }
}
