package Tetris.view.abstractComponent.container.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;

public class ScoreBoardJPanel extends JPanel {
    
    private JLabel name;
    private JLabel score;

    private Score scoreObject;

    public ScoreBoardJPanel() {
        initObjects();
        initPanel();
        initLabels();
        addComponents();
    }

    private void initObjects() {
        scoreObject = new Score();
    }

    private void initPanel() {
        setBackground(new Color(BoardColorMap.getColor(BoardComponent.EMPTY)));
        //setPreferredSize(new Dimension(160, 160));
        setSize(160, 160);
    }

    private void initLabels() {
        name = new JLabel("스코어");
        name.setPreferredSize(new Dimension(160, 80));
        name.setBackground(new Color(BoardColorMap.getColor(BoardComponent.WALL)));

        score = new JLabel(0 + "");
        score.setPreferredSize(new Dimension(160, 80));
        score.setBackground(new Color(BoardColorMap.getColor(BoardComponent.WALL)));
    }

    private void addComponents() {
        add(name, BorderLayout.NORTH);
        add(score, BorderLayout.SOUTH);
    }

    public void updateLabels() {
        score.setText(scoreObject.getScore() + "");
    }
}
