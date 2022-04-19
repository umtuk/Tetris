package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.WindowSize;
import java.awt.*;

public class ConfigPanel{
    public JPanel configP = new JPanel();
    public JButton initB = new JButton("초기화");
    public JButton saveB = new JButton("저장");
    public JButton exitB = new JButton("나가기");
    public ConfigPanel(){}
    public ConfigPanel(int WIDTH, int HEIGHT){
        JPanel con = new JPanel();
        JLabel text = new JLabel("설정", JLabel.CENTER);
        JPanel buttonP = new JPanel();
        JLabel windowSizeL = new JLabel("해상도");
        JLabel colorSetL = new JLabel("모드");
        JLabel difficultyL = new JLabel("난이도");
        /*
        JLabel up = new JLabel("위");
        JLabel down = new JLabel("아래");
        JLabel left = new JLabel("왼쪽");
        JLabel right = new JLabel("오른쪽");
        JLabel ok = new JLabel("확인");
        JLabel pause = new JLabel("일시정지");
        JLabel rotate = new JLabel("회전");
        */
        WindowSize[] windowSize = WindowSize.values();
        ColorSet[] colorSets = ColorSet.values();

        JComboBox<String> windowSizeCB = new JComboBox(windowSize);
        JComboBox<String> colorSetCB = new JComboBox(colorSets);
        //JComboBox difficultyCB = new JComboBox();

        windowSizeCB.setBackground(Color.BLACK);
        colorSetCB.setBackground(Color.BLACK);
        windowSizeCB.setForeground(Color.WHITE);
        colorSetCB.setForeground(Color.WHITE);

        windowSizeL.setForeground(Color.WHITE);
        colorSetL.setForeground(Color.WHITE);
        difficultyL.setForeground(Color.WHITE);

        con.setLayout(new GridLayout(0, 2, 20, 30));
        con.setBackground(Color.BLACK);
        con.add(windowSizeL);
        con.add(windowSizeCB);
        con.add(colorSetL);
        con.add(colorSetCB);
        con.add(difficultyL);
        //configSP.add(difficultyCB);
        /*
        keymap
        */

        buttonP.setBackground(Color.BLACK);
        buttonP.add(initB);
        buttonP.add(saveB);
        buttonP.add(exitB);

        initB.setForeground(Color.WHITE);
        initB.setBackground(Color.BLACK);
        saveB.setForeground(Color.WHITE);
        saveB.setBackground(Color.BLACK);
        exitB.setForeground(Color.WHITE);
        exitB.setBackground(Color.BLACK);

        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);
        text.setFont(new Font("Serif", Font.BOLD, 30));

        configP.setLayout(new BorderLayout());
        configP.add(text, BorderLayout.NORTH);
        configP.add(con, BorderLayout.WEST);
        configP.add(buttonP, BorderLayout.SOUTH);
        configP.setBackground(Color.BLACK);
    }

    public static void main(String[] args){
        new ConfigPanel();
    }
}