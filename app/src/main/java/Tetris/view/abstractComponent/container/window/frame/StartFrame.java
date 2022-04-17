package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Tetris.view.actionListener.MouseListener;

public class StartFrame extends SimpleJFrame{
    public void buttonPanel(JPanel ButtonP){
        ButtonP.setSize(100, 150);
        JButton StartB = new JButton("게임시작");
        JButton configB = new JButton("설정");
        JButton ScoreBoardB = new JButton("랭킹");
        JButton ExitB = new JButton("나가기");

        ButtonP.setLayout(new GridLayout(4, 1, 0, 10));
        
        ButtonP.add(StartB);
        ButtonP.add(configB);
        ButtonP.add(ScoreBoardB);
        ButtonP.add(ExitB);

        ButtonP.setBackground(new Color(255, 0, 0, 0));

        ScoreBoardB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new ScoreBoardFrame();
            }
        });
        configB.addActionListener(MouseListener.configFrameMove);
        ExitB.addActionListener(MouseListener.ExitListener);
    }
    public StartFrame(){
        JPanel ButtonP = new JPanel();
        buttonPanel(ButtonP);
        add(ButtonP);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new StartFrame();
    }
}
