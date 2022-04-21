package Tetris.view.frame.score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Tetris.domain.score.entity.Score;
import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;


public class InputNameFrame extends SimpleJFrame implements ActionListener, KeyListener {


    private JLabel title;

    private JPanel scorePanel;

    private JLabel scoreLabel;
    private JLabel difficultyLabel;
    private JLabel timestameLabel;
    private JLabel modeLabel;

    private JLabel name;
    private JTextField input;
    private JPanel namePanel;
    private JPanel emptyPanel;
    private JPanel emptyPanel1;
    private JPanel emptyPanel2;

    private ScoreService scoreService = ScoreService.getInstance();
    private MainConfig mainConfig = MainConfig.getInstance();
    private WindowSize windowSize = mainConfig.getWindowSize();

    public InputNameFrame(){
        initScorePanel();
        initComponents();
        setComponents();
        addComponents();
        addActionListener();
        addKeyListener(this);
        setVisible(true);
    }

    private void initScorePanel() {
        Score score = scoreService.getScore();

        scorePanel = new JPanel();
        emptyPanel1 = new JPanel();
        emptyPanel2 = new JPanel();
        emptyPanel1.setBackground(Color.BLACK);
        emptyPanel2.setBackground(Color.BLACK);
        emptyPanel1.setPreferredSize(new Dimension((int)(windowSize.getWidth()*0.35), 100));
        emptyPanel2.setPreferredSize(new Dimension((int)(windowSize.getWidth()*0.35), 100));
        
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        scorePanel.setPreferredSize(new Dimension(windowSize.getWidth(), 100));
        scoreLabel = new JLabel("점수: " + score.getScore() + "점");
        difficultyLabel = new JLabel("난이도: " + score.getDifficulty());
        timestameLabel = new JLabel("게임시간: " + simpleDateFormat.format(score.getTimestamp()));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBackground(Color.BLACK);
        String mode;
        int cpr = score.getMode();

        if (cpr == Score.DEFAULT_MODE) {
            mode = "일반 모드";
        }
        else {
            mode = "아이템 모드";
        }
        modeLabel = new JLabel("모드: " + mode);

        scorePanel.setBackground(Color.GRAY);

        scorePanel.add(scoreLabel);
        scorePanel.add(difficultyLabel);
        scorePanel.add(timestameLabel);
        scorePanel.add(modeLabel);
    }

    private void initComponents(){
        title = new JLabel("게임 오버");
        title.setPreferredSize(new Dimension(windowSize.getWidth(), (int)(windowSize.getHeight()/3)));
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        name = new JLabel("이름을 입력하시오");
        input = new JTextField(15);
        input.setFocusable(false);
        namePanel = new JPanel();
        namePanel.setFocusable(false);
        emptyPanel = new JPanel();

        emptyPanel.setFocusable(false);
    }

    private void setComponents(){
        name.setBackground(new Color(255, 0, 0, 0));
        name.setForeground(Color.WHITE);

        title.setBackground(Color.BLACK);
        title.setForeground(Color.WHITE);

        namePanel.setBackground(Color.BLACK);
        namePanel.setPreferredSize(new Dimension(windowSize.getWidth(), (int)(windowSize.getHeight()*0.6)));

        emptyPanel.setPreferredSize(new Dimension(mainConfig.getWindowSize().getWidth(), mainConfig.getWindowSize().getHeight()/2));
        emptyPanel.setBackground(Color.BLACK);

        setLayout(new BorderLayout());
    }

    private void addComponents(){
        emptyPanel.setLayout(new BorderLayout());
        emptyPanel.add(title, BorderLayout.NORTH);
        emptyPanel.add(scorePanel, BorderLayout.CENTER);
        emptyPanel.add(emptyPanel1, BorderLayout.EAST);
        emptyPanel.add(emptyPanel2, BorderLayout.WEST);
        //emptyPanel.setAlignmentX(CENTER_ALIGNMENT);

        namePanel.add(name);
        namePanel.add(input);

        add(emptyPanel, BorderLayout.NORTH);
        add(namePanel, BorderLayout.CENTER);
    }

    private void addActionListener(){
        input.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(input.getText());
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(input.getText());
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        String inputText = input.getText();
        int length = inputText.length();

        switch (keyEvent) {
            case KeyEvent.VK_ENTER: 
                dispose();
                scoreService.setUsername(inputText);
                try {
                    new ScoreBoardFrame(scoreService.getScore());
                } catch (Exception a){
                    //TODO: handle exception
                }
                break;
            case KeyEvent.VK_CAPS_LOCK: break;
            case KeyEvent.VK_SHIFT: break;
            case KeyEvent.VK_BACK_SPACE: 
                if (length != 0)
                    input.setText(inputText.substring(0, inputText.length() - 1)); 
                break;
            default: input.setText(inputText + e.getKeyChar()); break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args){

        Score score = new Score();
        //score.setMode(Score.DEFAULT_MODE);
        score.setScore(123);

        ScoreService scoreService = ScoreService.getInstance();
        scoreService.init();
        scoreService.setScore(score);

        new InputNameFrame();
    }
}