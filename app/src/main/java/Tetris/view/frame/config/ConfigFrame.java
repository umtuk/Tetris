package Tetris.view.frame.config;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Tetris.domain.score.service.ScoreService;
import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.Difficulty;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.config.entity.branch.KeyMap;
import Tetris.global.config.service.ConfigService;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;
import Tetris.view.frame.IndexFrame;

import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.awt.*;

public class ConfigFrame extends SimpleJFrame{

    private MainConfig mainConfig = MainConfig.getInstance();
    private ConfigService configService = ConfigService.getInstance();
    private ScoreService scoreService = ScoreService.getInstance();

    private final String title = "설정";

    private final String[] configPanelString = {
        "해상도",
        "난이도",
        "색상",
        "키",
        "기타"
    };
    private final String[][] configLabelString = {
        {},
        {},
        {},
        {
            "블록 한번에 내리기",
            "아래로 블록 이동",
            "왼쪽으로 블록 이동 ",
            "오른쪽으로 블록 이동",
            "일시정지",
            "블록 회전",
        },
        {},
    };

    private final String[][] configButtonString = {
        {
            "800_600",
            "1280_960",
            "1920_1080",
        },
        {
            "하",
            "중",
            "상",
        },
        {
            "일반 모드",
            "색맹 모드",
        },
        {}, // mainConfig에서 가져와야 함
        {
            "설정 초기화",
            "스코어보드 초기화",
            "나가기",
        }
    };

    private final KeyType[] keyTypes = {
        KeyType.UP,
        KeyType.DOWN,
        KeyType.LEFT,
        KeyType.RIGHT,
        KeyType.PAUSE,
        KeyType.ROTATE,
    };
    private final Difficulty[] difficulties = Difficulty.values();
    private final WindowSize[] windowSizes = WindowSize.values();
    private final ColorSet[] colorSets = ColorSet.values();

    private JPanel mainPanel;
    private JPanel centerPanel;

    private JPanel[] configPanels;
    private JLabel[] configLabels;
    private JButton[] configButtons;

    private JPanel[] configLabelNButtonPanels;

    private JLabel titleLabel;

    private JPanel footPanel;

    private JButton exitButton;
    private JButton configInitButton;
    private JButton scoreInitButton;

    private int xPos;
    private int yPos;

    public ConfigFrame(){
        xPos = 0;
        yPos = 0;

        initOthers();
        initConfigButtions();
        initConfigLabels();
        initConfigLabelNButtonPanels();
        initConfigPanels();
        initCenterPanel();
        initMainPanel();

        drawAll();

        addKeyListener(new MainKeyListener());

        setVisible(true);
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(centerPanel);

        add(mainPanel);
    }

    private void initCenterPanel() {
        int n = configPanelString.length;

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < n; i++) {
            centerPanel.add(configPanels[i]);
        }
    }

    private void initConfigPanels() {
        int n = configPanelString.length;

        int width = mainConfig.getWindowSize().getWidth();
        int height = mainConfig.getWindowSize().getHeight();

        configPanels = new JPanel[n];

        int configLabelIdx = 0;
        for (int i = 0; i < n; i++) {

            configPanels[i] = new JPanel();
            configPanels[i].setBackground(Color.BLACK);
            configPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT));

            configPanels[i].add(configLabels[configLabelIdx++]);
        }

        int configButtonIdx = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < configButtonString[x].length; y++) {
                configPanels[x].add(configButtons[configButtonIdx++]);
            }
        }

        for (int y = 0; y < configLabelString[3].length; y++) {
            configPanels[3].add(configLabelNButtonPanels[y]);
            configButtonIdx++;
        }

        for (int y = 0; y < configButtonString[4].length; y++) {
            configPanels[4].add(configButtons[configButtonIdx++]);
        }
    }

    private void initConfigLabelNButtonPanels() {
        int n = configLabelString[3].length;

        configLabelNButtonPanels = new JPanel[n];

        for (int i = 0; i < n; i++) {
            configLabelNButtonPanels[i] = new JPanel();
            configLabelNButtonPanels[i].setBackground(Color.BLACK);

            configLabelNButtonPanels[i].add(configLabels[i + 5]);
            configLabelNButtonPanels[i].add(configButtons[i + 8]);
        }
    }

    private void initConfigLabels() {
        int n = configPanelString.length;
        int m = configLabelString[3].length;

        configLabels = new JLabel[n + m];

        int i = 0;
        for (int x = 0; x < n; x++) {
            configLabels[i] = new JLabel(configPanelString[x]);
            configLabels[i].setBackground(Color.BLACK);
            configLabels[i].setForeground(Color.WHITE);

            i++;
        }

        for (int y = 0; y < m; y++) {
            configLabels[i] = new JLabel(configLabelString[3][y]);
            configLabels[i].setBackground(Color.BLACK);
            configLabels[i].setForeground(Color.WHITE);

            i++;
        }
    }

    private void initConfigButtions() {
        int n = configButtonString[0].length
            + configButtonString[1].length
            + configButtonString[2].length
            + configButtonString[4].length;
        
        int m = configLabelString[3].length;

        configButtons = new JButton[n + m];

        int i = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < configButtonString[x].length; y++) {
                configButtons[i] = new JButton(configButtonString[x][y]);
                configButtons[i].setBackground(Color.BLACK);
                configButtons[i].setForeground(Color.WHITE);
                configButtons[i].setFocusable(false);

                i++;
            }
        }

        KeyMap keyMap = mainConfig.getKeyMap();
        for (int y = 0; y < m; y++) {
            configButtons[i] = new JButton(KeyEvent.getKeyText(keyMap.get(keyTypes[y])));
            configButtons[i].setBackground(Color.BLACK);
            configButtons[i].setForeground(Color.WHITE);
            configButtons[i].setFocusable(false);

            i++;
        }

        for (int y = 0; y < configButtonString[4].length; y++) {
            configButtons[i] = new JButton(configButtonString[4][y]);
            configButtons[i].setBackground(Color.BLACK);
            configButtons[i].setForeground(Color.WHITE);
            configButtons[i].setFocusable(false);

            i++;
        }
    }

    private void initOthers() {
        titleLabel = new JLabel("설정", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
    }

    private void highlight(JButton jButton) {
        jButton.setBackground(Color.WHITE);
        jButton.setForeground(Color.BLACK);
    }

    private void selectedHighlight(JButton jButton) {
        jButton.setBackground(Color.GRAY);
        jButton.setForeground(Color.BLACK);
    }

    private void initButtonBackground() {
        for (int i = 0; i < configButtons.length; i++) {
            configButtons[i].setBackground(Color.BLACK);
            configButtons[i].setForeground(Color.WHITE);
        }
    }

    private void drawSelectedConfigs() {
        WindowSize windowSize = mainConfig.getWindowSize();
        if (windowSize == WindowSize.W800_H600) {
            selectedHighlight(configButtons[0]);
        }
        else if (windowSize == WindowSize.W1280_H960) {
            selectedHighlight(configButtons[1]);
        }
        else if (windowSize == WindowSize.W1920_H1080) {
            selectedHighlight(configButtons[2]);
        }
        else {
            selectedHighlight(configButtons[0]);
        }

        Difficulty difficulty = mainConfig.getDifficulty();
        if (difficulty == Difficulty.EASY) {
            selectedHighlight(configButtons[3]);
        }
        else if (difficulty == Difficulty.NORMAL) {
            selectedHighlight(configButtons[4]);
        }
        else if (difficulty == Difficulty.HARD) {
            selectedHighlight(configButtons[5]);
        }
        else {
            selectedHighlight(configButtons[4]);
        }

        ColorSet colorSet = mainConfig.getColorSet();
        if (colorSet == ColorSet.DEFAULT) {
            selectedHighlight(configButtons[6]);
        }
        else {
            selectedHighlight(configButtons[7]);
        }
    }

    private void adjustPos() {
        int [] countButton = {
            3, 3, 2, 6, 3
        };
        if (xPos < 0) {
            xPos += 5;
        }
        xPos %= 5;
        if (yPos < 0) {
            yPos += countButton[xPos];;
        }
        yPos %= countButton[xPos];
    }

    private int convertPosTOButtonIndex() {
        int [] countButton = {
            3, 3, 2, 6, 3
        };

        adjustPos();

        int ret = 0;
        for (int x = 0; x < xPos; x++) {
            ret += countButton[x];
        }
        ret += yPos;

        return ret;
    }

    private void drawNowPos() {


        int buttonIndex = convertPosTOButtonIndex();

        highlight(configButtons[buttonIndex]);
    }

    private void drawAll() {
        initButtonBackground();
        drawSelectedConfigs();
        drawNowPos();

    }

    private static boolean isKeyMapConfig = false;
    private static int willUpdateedButtonIdx = 0;

    private void updateKeyMapConfig() {
        configButtons[8].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.UP)));
        configButtons[9].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.DOWN)));
        configButtons[10].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.LEFT)));
        configButtons[11].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.RIGHT)));
        configButtons[12].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.PAUSE)));
        configButtons[13].setText(KeyEvent.getKeyText(mainConfig.getKeyMap().get(KeyType.ROTATE)));
    }
    
    class MainKeyListener implements KeyListener {
        
        KeyMap keyMap = mainConfig.getKeyMap();

        @Override
        public void keyTyped(KeyEvent keyE) {
            // TODO Auto-generated method stub
            
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            int keyEvent = e.getKeyCode();

            int buttonIndex = convertPosTOButtonIndex();

            if (isKeyMapConfig) {
                isKeyMapConfig = false;
                try {
                    switch (willUpdateedButtonIdx) {
                        case 8: configService.setKeyMap(keyEvent, KeyType.UP); break;
                        case 9: configService.setKeyMap(keyEvent, KeyType.DOWN); break;
                        case 10: configService.setKeyMap(keyEvent, KeyType.LEFT); break;
                        case 11: configService.setKeyMap(keyEvent, KeyType.RIGHT); break;
                        case 12: configService.setKeyMap(keyEvent, KeyType.PAUSE); break;
                        case 13: configService.setKeyMap(keyEvent, KeyType.ROTATE); break;
                        default: break;
                    }
                } catch (SQLException exception) {
                        //TODO: handle exception
                }

                updateKeyMapConfig();
            }
            else {
                switch (keyEvent) {
                    case KeyEvent.VK_UP:
                        xPos--;
                        yPos = 0;
                        break;
                    case KeyEvent.VK_DOWN:
                        xPos++;
                        yPos = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        yPos--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        yPos++;
                        break;
                    case KeyEvent.VK_ENTER:
                        try {
                            switch (buttonIndex) {
                                case 0: configService.setWindowSize(WindowSize.W800_H600); dispose(); new ConfigFrame(); break;
                                case 1: configService.setWindowSize(WindowSize.W1280_H960); dispose(); new ConfigFrame(); break;
                                case 2: configService.setWindowSize(WindowSize.W1920_H1080); dispose(); new ConfigFrame(); break;
                                case 3: configService.setDifficulty(Difficulty.EASY); break;
                                case 4: configService.setDifficulty(Difficulty.NORMAL); break;
                                case 5: configService.setDifficulty(Difficulty.HARD); break;
                                case 6: configService.setColorSet(ColorSet.DEFAULT); break;
                                case 7: configService.setColorSet(ColorSet.PROTANOPIA); break;
                                case 8: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 9: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 10: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 11: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 12: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 13: isKeyMapConfig = true; willUpdateedButtonIdx = buttonIndex; break;
                                case 14: configService.setDefaultConfig(); dispose(); new ConfigFrame(); break;
                                case 15: scoreService.deleteAll(); break;
                                case 16: dispose(); new IndexFrame(); break;
                                default: break;
                            }
                        } catch (SQLException exception) {
                            //TODO: handle exception
                        }
                        break;
                    default:
                        break;
                    }
            }
            drawAll();
        }
    
        @Override
        public void keyReleased(KeyEvent keyE) {
            // TODO Auto-generated method stub
            
        }
        
    }

    public static void main(String[] args){
        new ConfigFrame();
    }
}