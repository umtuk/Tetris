package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;

import Tetris.view.actionListener.ActionEvent;
import Tetris.view.actionListener.MouseListener;
import java.awt.event.*;
import java.awt.*;

public class ConfigFrame extends SimpleJFrame{
    public ConfigFrame(){
        setTitle("configFrame");

        JPanel mainPanel = new JPanel();
        JButton SaveB = new JButton("저장");
        JButton CancleB = new JButton("나가기");
        FlowLayout FL = new FlowLayout();
        
        FL.setAlignment(FlowLayout.CENTER);
        mainPanel.setLayout(FL);
        
        mainPanel.add(SaveB);
        mainPanel.add(CancleB);

        CancleB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args){
        new ConfigFrame();
    }
}