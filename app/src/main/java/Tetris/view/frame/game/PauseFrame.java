package Tetris.view.frame.game;

import Tetris.domain.board.service.BoardService;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class PauseFrame extends SimpleJFrame implements KeyListener{

    private MainConfig mainConfig = MainConfig.getInstance();
    private BoardService boardService = BoardService.getInstance();

    private JPanel PausePanel;
    private JButton continueButton;
    private JButton exitButton;
    private LineBorder lineBorder;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel eastPanel;
    private JPanel westPanel;
    public int button_idx = 10000;

    public void addActionListener(){
        continueButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                boardService.switchPause();
            }
        });
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                boardService.switchPause();
                boardService.setForceQuit();
            }
        });
    }
    public void initComponents(){
        PausePanel = new JPanel();
        continueButton = new JButton("계속하기");
        exitButton = new JButton("나가기");
        lineBorder = new LineBorder(Color.WHITE);
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();
    }
    public void setComponents(){
        WindowSize windowSize = mainConfig.getWindowSize();

        setLayout(new BorderLayout());
        northPanel.setBackground(Color.BLACK);
        northPanel.setPreferredSize(new Dimension(windowSize.getWidth(), (int)(windowSize.getHeight()*0.35)));
        southPanel.setBackground(Color.BLACK);
        southPanel.setPreferredSize(new Dimension(windowSize.getWidth(), (int)(windowSize.getHeight()*0.35)));
        eastPanel.setBackground(Color.BLACK);
        eastPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()*0.35), 100));
        westPanel.setBackground(Color.BLACK);
        westPanel.setPreferredSize(new Dimension((int)(windowSize.getWidth()*0.35), 100));
        PausePanel.setBackground(Color.BLACK);
        PausePanel.setPreferredSize(new Dimension((int)windowSize.getWidth()/5, 100));
        
        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(Color.BLACK);
        continueButton.setBorder(lineBorder);
        continueButton.setFocusable(false);

        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(lineBorder);
        exitButton.setFocusable(false);
    }
    public void addComponents(){
        PausePanel.add(continueButton);
        PausePanel.add(exitButton);

        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(PausePanel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);

        addKeyListener(this);
    }
    public PauseFrame(){
        initComponents();
        setComponents();
        addComponents();
        addActionListener();
        setVisible(true);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                buttonClick(button_idx);
                break;
            case KeyEvent.VK_LEFT:
                button_idx--;
                highlight(button_idx);
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_RIGHT:
                button_idx++;
                highlight(button_idx);
                break;
            case KeyEvent.VK_DOWN:
                break;
            default:
                break;
        }
    }
    private void buttonClick(int idx) {
        switch(idx%2){
            case 0:
                continueButton.doClick();
                break;
            case 1:
                exitButton.doClick();
                break;
            default:
                break;
        }
    }
    private void highlight(int idx) {
        switch(idx%2){
            case 0:
                continueButton.setBackground(Color.WHITE);
                continueButton.setForeground(Color.BLACK);
                exitButton.setBackground(Color.BLACK);
                exitButton.setForeground(Color.WHITE);
                break;
            case 1:
                continueButton.setBackground(Color.BLACK);
                continueButton.setForeground(Color.WHITE);
                exitButton.setBackground(Color.WHITE);
                exitButton.setForeground(Color.BLACK);
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    public static void main(String[] args){
        new PauseFrame();
    }
}