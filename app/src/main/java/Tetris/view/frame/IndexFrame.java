package Tetris.view.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

import Tetris.domain.board.service.BoardService;
import Tetris.domain.score.entity.Score;
import Tetris.global.config.entity.MainConfig;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;
import Tetris.view.actionListener.MouseListener;
import Tetris.view.frame.config.ConfigFrame;
import Tetris.view.frame.game.GameFrame;
import Tetris.view.frame.score.ScoreBoardFrame;

public class IndexFrame extends SimpleJFrame implements KeyListener {

    private static MainConfig mainConfig = MainConfig.getInstance();

    private final String[] BUTTON_STRINGS = {
        "일반 모드", "아이템 모드", "스코어보드", "설정", "나가기"
    };

    private JPanel indexPanel;

    private JPanel imagePanel;
    private JPanel buttonPanel;

    private JPanel eastPanel;
    private JPanel westPanel;

    private JButton gameButton;
    private JButton itemModeButton;
    private JButton scoreBoardButton;
    private JButton configButton;
    private JButton exitButton;

    private LineBorder lineBorder = new LineBorder(Color.WHITE);

    private int buttonIndex;

    public IndexFrame() {
        super();

        buttonIndex = 10000;

        initComponents();
        addComponentsActionListener();
        addComponents();

        setVisible(true);
    }

    private void initComponents() {
        int indexFrameWidth = mainConfig.getWindowSize().getWidth();
        int indexFrameHeight = mainConfig.getWindowSize().getHeight();

        indexPanel = new JPanel();
        indexPanel.setSize(indexFrameWidth, indexFrameHeight);
        indexPanel.setBackground(Color.BLACK);

        imagePanel = new JPanel() {
            Image background = new ImageIcon(IndexFrame.class
                                        .getResource("tetris_image.jpg"))
                                        .getImage();

            public void paint(Graphics g) {
                g.drawImage(background, 
                    (int)(indexFrameWidth / 2) - 210, 100, null);
            }
        };
        imagePanel.setPreferredSize(new Dimension(0, (int)(indexFrameHeight * 0.5)));

        buttonPanel = new JPanel();
        buttonPanel.setSize((int)(indexFrameWidth / 5), (int)(indexFrameHeight / 7 * 3));
        buttonPanel.setLocation((int)(indexFrameWidth / 5 * 2), (int)(indexFrameHeight / 7 * 4));
        buttonPanel.setBackground(new Color(255, 0, 0, 0));

        gameButton = new JButton(BUTTON_STRINGS[0]);
        gameButton.setBackground(Color.WHITE);
        gameButton.setForeground(Color.BLACK);
        gameButton.setBorder(lineBorder);
        gameButton.setFocusable(false);

        itemModeButton = new JButton(BUTTON_STRINGS[1]);
        itemModeButton.setBackground(Color.BLACK);
        itemModeButton.setForeground(Color.WHITE);
        itemModeButton.setBorder(lineBorder);
        itemModeButton.setFocusable(false);

        scoreBoardButton = new JButton(BUTTON_STRINGS[2]);
        scoreBoardButton.setForeground(Color.WHITE);
        scoreBoardButton.setBackground(Color.black);
        scoreBoardButton.setBorder(lineBorder);
        scoreBoardButton.setFocusable(false);

        configButton = new JButton(BUTTON_STRINGS[3]);
        configButton.setBackground(Color.BLACK);
        configButton.setForeground(Color.WHITE);
        configButton.setBorder(lineBorder);
        configButton.setFocusable(false);

        exitButton = new JButton(BUTTON_STRINGS[4]);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(lineBorder);
        exitButton.setFocusable(false);

        eastPanel = new JPanel();
        eastPanel.setBackground(new Color(255, 0, 0, 0));

        westPanel = new JPanel();
        westPanel.setBackground(new Color(255, 0, 0, 0));
    }

    private void addComponentsActionListener() {
        exitButton.addActionListener(MouseListener.ExitListener);

        gameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();

                EventQueue.invokeLater(() -> {

                    new GameFrame(Score.DEFAULT_MODE);
                });
            }
        });
        itemModeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                EventQueue.invokeLater(() -> {
                    
                    new GameFrame(Score.ITEM_MODE);
                });
            }
        });
        scoreBoardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                try {
                    new ScoreBoardFrame();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        });

        configButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new ConfigFrame();
            }
        });
        
        addKeyListener(this);
    }
    
    private void initFrame() {

    }

    private void addComponents() {
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10));
        buttonPanel.add(gameButton);
        buttonPanel.add(itemModeButton);
        buttonPanel.add(scoreBoardButton);
        buttonPanel.add(configButton);
        buttonPanel.add(exitButton);
        
        indexPanel.setLayout(new BorderLayout(150, 0));
        indexPanel.add(imagePanel, BorderLayout.NORTH);
        indexPanel.add(buttonPanel, BorderLayout.CENTER);
        indexPanel.add(eastPanel, BorderLayout.EAST);
        indexPanel.add(westPanel, BorderLayout.WEST);

        add(indexPanel);
    }

    public void highlight(int idx) {
        switch(idx % 5) {
            case 0:
                gameButton.setBackground(Color.WHITE);
                gameButton.setForeground(Color.BLACK);
                exitButton.setBackground(Color.BLACK);
                exitButton.setForeground(Color.WHITE);
                itemModeButton.setBackground(Color.BLACK);
                itemModeButton.setForeground(Color.WHITE);
                break;
            case 1:
                itemModeButton.setBackground(Color.WHITE);
                itemModeButton.setForeground(Color.BLACK);
                gameButton.setBackground(Color.BLACK);
                gameButton.setForeground(Color.WHITE);
                scoreBoardButton.setBackground(Color.BLACK);
                scoreBoardButton.setForeground(Color.WHITE);
                break;
            case 2:
                scoreBoardButton.setBackground(Color.WHITE);
                scoreBoardButton.setForeground(Color.BLACK);
                itemModeButton.setBackground(Color.BLACK);
                itemModeButton.setForeground(Color.WHITE);
                configButton.setBackground(Color.BLACK);
                configButton.setForeground(Color.WHITE);
                break;
            case 3:
                configButton.setBackground(Color.WHITE);
                configButton.setForeground(Color.BLACK);
                scoreBoardButton.setBackground(Color.BLACK);
                scoreBoardButton.setForeground(Color.WHITE);
                exitButton.setBackground(Color.BLACK);
                exitButton.setForeground(Color.WHITE);
                break;
            case 4:
                exitButton.setBackground(Color.WHITE);
                exitButton.setForeground(Color.BLACK);
                configButton.setBackground(Color.BLACK);
                configButton.setForeground(Color.WHITE);
                gameButton.setBackground(Color.BLACK);
                gameButton.setForeground(Color.WHITE);
                break;
            default:
                break;
        }
    }

    public void pressButton(int idx){
        switch(idx % 5){
            case 0:
                gameButton.doClick();
                break;
            case 1:
                itemModeButton.doClick();
                break;
            case 2:
                scoreBoardButton.doClick();
                break;
            case 3:
                configButton.doClick();
                break;
            case 4:
                exitButton.doClick();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                pressButton(buttonIndex);
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                buttonIndex--;
                highlight(buttonIndex);
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_DOWN:
                buttonIndex++;
                highlight(buttonIndex);
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

    public static void main(String[] args) {
        new IndexFrame();
    }
}