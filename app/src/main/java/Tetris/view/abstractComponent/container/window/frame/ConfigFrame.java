package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.KeyType;
import Tetris.global.config.constant.WindowSize;
import Tetris.view.actionListener.ActionEvent;
import Tetris.view.actionListener.MouseListener;
import java.awt.event.*;
import java.awt.*;

public class ConfigFrame extends SimpleJFrame{
    public ConfigFrame(){
        setTitle("설정 창");

        JPanel ButtonPanel = new JPanel();
        JPanel ConfigPanel = new JPanel();
        JPanel mainP = new JPanel();

        mainPanel(mainP, ConfigPanel, ButtonPanel);
        configPanel(ConfigPanel);
        buttonPanel(ButtonPanel);

        setVisible(true);
    }

    public void mainPanel(JPanel mainP, JPanel ConfigPanel, JPanel ButtonPanel){
        mainP.setSize(new Dimension(500, 300));
        mainP.setBackground(Color.BLACK);
        mainP.setLayout(new GridLayout(0, 1, 10, 10));
        mainP.add(ConfigPanel);
        mainP.add(ButtonPanel);
        add(mainP);
    }
    public void configPanel(JPanel ConfigPanel){
        ConfigPanel.setSize(new Dimension(10, 200));
        ConfigPanel.setLayout(new GridLayout(0, 2, 10, 20));
        WindowSize[] windowSize = WindowSize.values();
        ColorSet[] colorSets = ColorSet.values();
        
        JComboBox<String> WindowSizeBox = new JComboBox(windowSize);
        JComboBox<String> ColorSetBox = new JComboBox(colorSets);

        JLabel lb_WindowSize = new JLabel("창 크기");
        JLabel lb_ColorSet = new JLabel("색 모드");

        lb_WindowSize.setHorizontalAlignment(JLabel.CENTER);
        lb_ColorSet.setHorizontalAlignment(JLabel.CENTER);

        ConfigPanel.add(lb_WindowSize);
        ConfigPanel.add(WindowSizeBox);

        ConfigPanel.add(lb_ColorSet);
        ConfigPanel.add(ColorSetBox);
        ConfigPanel.setLayout(new GridLayout(2, 0, 0, 10));
    }

    public void buttonPanel(JPanel buttonPanel){
        
        JButton SaveB = new JButton("저장");
        JButton CancleB = new JButton("나가기");
        JButton InitB = new JButton("초기화");

        buttonPanel.add(InitB);
        buttonPanel.add(SaveB);
        buttonPanel.add(CancleB);

        CancleB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args){
        new ConfigFrame();
    }
}