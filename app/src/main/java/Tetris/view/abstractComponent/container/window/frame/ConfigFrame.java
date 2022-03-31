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

        configPanel(ConfigPanel);
        buttonPanel(ButtonPanel);

        setVisible(true);
    }

    public void configPanel(JPanel ConfigPanel){
        ConfigPanel.setSize(new Dimension(50, 40));
        ConfigPanel.setLayout(new GridLayout(0, 2, 10, 20));
        WindowSize[] windowSize = WindowSize.values();
        ColorSet[] colorSets = ColorSet.values();
        
        JComboBox<String> WindowSizeBox = new JComboBox(windowSize);
        JComboBox<String> ColorSetBox = new JComboBox(colorSets);

        JLabel lb_WindowSize = new JLabel("창 크기");
        JLabel lb_ColorSet = new JLabel("색 모드");

        ConfigPanel.add(lb_WindowSize, BorderLayout.WEST);
        ConfigPanel.add(WindowSizeBox, BorderLayout.WEST);

        ConfigPanel.add(lb_ColorSet, BorderLayout.WEST);
        ConfigPanel.add(ColorSetBox, BorderLayout.WEST);

        add(ConfigPanel, BorderLayout.WEST);
    }

    public void buttonPanel(JPanel buttonPanel){

        JButton SaveB = new JButton("저장");
        JButton CancleB = new JButton("나가기");

        buttonPanel.add(SaveB);
        buttonPanel.add(CancleB);

        CancleB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        add(buttonPanel, BorderLayout.EAST);
    }

    public static void main(String[] args){
        new ConfigFrame();
    }
}