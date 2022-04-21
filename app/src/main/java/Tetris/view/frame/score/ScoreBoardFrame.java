package Tetris.view.frame.score;

import java.awt.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.Difficulty;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;
import Tetris.view.frame.IndexFrame;

import java.awt.event.KeyListener;

public class ScoreBoardFrame extends SimpleJFrame implements KeyListener {

    private static ScoreService scoreService = ScoreService.getInstance();

    private final String[] HEADERS = {
        "이름", "점수", "난이도", "날짜", "모드"
    };
    private final String TITLE = "스코어보드";
    private final String EXIT = "나가기";

    private String[][] contents;
    private DefaultTableModel dtm;
    private MyRenderer myRenderer;
    
    private JButton exitButton;
    private JTable scoreTable;
    private JLabel titleLabel;

    private JScrollPane scrollableTablePanel;

    private JPanel eastPanel;
    private JPanel westPanel;

    private JPanel scoreBoardPanel;

    public ScoreBoardFrame() throws SQLException {
        super();

        setContents();

        initComponents();
        addComponentsActionListener();
        initPanel();
        addComponents();
    }

    public ScoreBoardFrame(Score score) throws SQLException {
        scoreService.insertScore();

        setContents();

        initComponents(score);
        addComponentsActionListener();
        initPanel();
        addComponents();
    }
    private void initComponents() {
        scoreBoardPanel = new JPanel();
        scoreBoardPanel.setBackground(Color.BLACK);

        exitButton = new JButton(EXIT);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);

        scoreTable = new JTable(contents, HEADERS);
        scoreTable.getTableHeader().setBackground(Color.BLACK);
        scoreTable.getTableHeader().setForeground(Color.WHITE);
        scoreTable.setBackground(Color.BLACK);
        scoreTable.setForeground(Color.WHITE);
        scoreTable.setFocusable(false);

        titleLabel = new JLabel(TITLE);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        eastPanel = new JPanel();
        eastPanel.setBackground(Color.BLACK);

        westPanel = new JPanel();
        westPanel.setBackground(Color.BLACK);

        scrollableTablePanel = new JScrollPane(scoreTable);
        scrollableTablePanel.setBackground(Color.BLACK);
        scrollableTablePanel.setForeground(Color.WHITE);
        scrollableTablePanel.getVerticalScrollBar()
            .setBackground(Color.BLACK);
        scrollableTablePanel.getVerticalScrollBar()
            .setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = Color.WHITE;
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }

                @Override    
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }

                private JButton createZeroButton() {
                    JButton jbutton = new JButton();
                    jbutton.setPreferredSize(new Dimension(0, 0));
                    jbutton.setMinimumSize(new Dimension(0, 0));
                    jbutton.setMaximumSize(new Dimension(0, 0));
                    return jbutton;
                }
            });
        scrollableTablePanel.setSize((int)(WIDTH * 0.6), HEIGHT - 50);
        scrollableTablePanel.setFocusable(false);
        scrollableTablePanel.getVerticalScrollBar().setFocusable(false);

        setFocusable(true);
    }
    private void initComponents(Score score) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dtm = new DefaultTableModel(contents, HEADERS);
        myRenderer = new MyRenderer(simpleDateFormat.format(score.getTimestamp()));

        scoreBoardPanel = new JPanel();
        scoreBoardPanel.setBackground(Color.BLACK);

        exitButton = new JButton(EXIT);
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);

        scoreTable = new JTable(dtm);
        scoreTable.getTableHeader().setBackground(Color.BLACK);
        scoreTable.getTableHeader().setForeground(Color.WHITE);
        scoreTable.setBackground(Color.BLACK);
        scoreTable.setForeground(Color.WHITE);
        scoreTable.setFocusable(false);
        scoreTable.setDefaultRenderer(Object.class, myRenderer);

        titleLabel = new JLabel(TITLE);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        eastPanel = new JPanel();
        eastPanel.setBackground(Color.BLACK);

        westPanel = new JPanel();
        westPanel.setBackground(Color.BLACK);

        scrollableTablePanel = new JScrollPane(scoreTable);
        scrollableTablePanel.setBackground(Color.BLACK);
        scrollableTablePanel.setForeground(Color.WHITE);
        scrollableTablePanel.getVerticalScrollBar()
            .setBackground(Color.BLACK);
        scrollableTablePanel.getVerticalScrollBar()
            .setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = Color.WHITE;
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }

                @Override    
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }

                private JButton createZeroButton() {
                    JButton jbutton = new JButton();
                    jbutton.setPreferredSize(new Dimension(0, 0));
                    jbutton.setMinimumSize(new Dimension(0, 0));
                    jbutton.setMaximumSize(new Dimension(0, 0));
                    return jbutton;
                }
            });
        scrollableTablePanel.setSize((int)(WIDTH * 0.6), HEIGHT - 50);
        scrollableTablePanel.setFocusable(false);
        scrollableTablePanel.getVerticalScrollBar().setFocusable(false);

        setFocusable(true);
    }

    private void initPanel() {
        setLayout(new BorderLayout((int)(WIDTH * 0.2), 10));
        setBackground(Color.BLACK);
    }

    private void addComponentsActionListener() {
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new IndexFrame();
            }
        });

        exitButton.addKeyListener(this);
    }

    private void addComponents() {
        scoreBoardPanel.setLayout(new BorderLayout((int)(WIDTH*0.2), 10));
        scoreBoardPanel.add(eastPanel, BorderLayout.EAST);
        scoreBoardPanel.add(westPanel, BorderLayout.WEST);
        scoreBoardPanel.add(titleLabel, BorderLayout.NORTH);
        scoreBoardPanel.add(scrollableTablePanel, BorderLayout.CENTER);
        scoreBoardPanel.add(exitButton, BorderLayout.SOUTH);

        add(scoreBoardPanel);

        addKeyListener(this);
        setVisible(true);
    }

    public void setContents() throws SQLException {
        List<Score> scores = scoreService.readAll();
        
        contents = new String[scores.size()][5];

        int i = 0;
        for (Score score : scores) {
            String username = score.getUsername();
            int scr = score.getScore();
            Difficulty difficulty = score.getDifficulty();
            long timestamp = score.getTimestamp();
            int mode = score.getMode();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            contents[i][0] = username;
            contents[i][1] = scr + "";
            
            if (difficulty == Difficulty.EASY) {
                contents[i][2] = "EASY";
            }
            else if (difficulty == Difficulty.NORMAL) {
                contents[i][2] = "NORMAL";
            }
            else if (difficulty == Difficulty.HARD) {
                contents[i][2] = "HARD";
            }

            contents[i][3] = simpleDateFormat.format(timestamp);

            if (mode == Score.DEFAULT_MODE) {
                contents[i][4] = "일반 모드";
            }
            else if (mode == Score.ITEM_MODE) {
                contents[i][4] = "아이템 모드";
            }

            i++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                exitButton.doClick();
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_DOWN:
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String[] args) {

        try {
            ScoreBoardFrame frame = new ScoreBoardFrame();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}