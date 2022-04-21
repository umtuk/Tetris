package Tetris.view.abstractComponent.container.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Tetris.domain.board.constant.BoardComponent;
import Tetris.domain.board.constant.map.BoardColorMap;
import Tetris.domain.board.service.BoardService;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;

public class ScoreBoardJPanel extends JPanel {
    
    private JLabel name;
    private JLabel score;

    private ScoreService scoreService;
    private BoardService boardService;
    private LineBorder lineBorder;

    public ScoreBoardJPanel() {
        initObjects();
        initPanel();
        initLabels();
        addComponents();
    }

    private void initObjects() {
        scoreService = ScoreService.getInstance();
        boardService = BoardService.getInstance();
        scoreService.init();
        lineBorder = new LineBorder(Color.WHITE);
    }

    private void initPanel() {
        setBorder(lineBorder);
        setBackground(new Color(BoardColorMap.getColor(BoardComponent.EMPTY)));
        setPreferredSize(new Dimension(160, 100));
    }

    private void initLabels() {
        name = new JLabel("스코어");
        name.setPreferredSize(new Dimension(160, 50));
        name.setBackground(new Color(BoardColorMap.getColor(BoardComponent.WALL)));
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Serif", Font.BOLD, 30));
        name.setHorizontalAlignment(JLabel.CENTER);

        score = new JLabel(0 + "");
        score.setPreferredSize(new Dimension(160, 50));
        score.setBackground(new Color(BoardColorMap.getColor(BoardComponent.WALL)));
        score.setForeground(Color.WHITE);
        score.setFont(new Font("Serif", Font.BOLD, 20));
        score.setHorizontalAlignment(JLabel.CENTER);
    }

    private void addComponents() {
        add(name, BorderLayout.NORTH);
        add(score, BorderLayout.SOUTH);
    }

    public void updateLabels() {
        score.setText(scoreService.getScore().getScore() + "");
    }

    public void initScore() {
        scoreService.init();
    }

    public void setMode(int mode) {
        scoreService.setMode(mode);
    }
}