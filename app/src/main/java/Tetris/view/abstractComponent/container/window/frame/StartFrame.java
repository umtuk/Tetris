package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class StartFrame extends SimpleJFrame{
    public StartFrame(){
        JPanel ButtonP = new JPanel();
        JButton StartB = new JButton("게임시작");
        JButton configB = new JButton("설정");
        JButton ScoreBoardB = new JButton("랭킹");
        JButton ExitB = new JButton("나가기");
        FlowLayout FL = new FlowLayout();

        FL.setAlignment(FlowLayout.CENTER);
        ButtonP.setLayout(FL);

        ButtonP.add(StartB);
        ButtonP.add(configB);
        ButtonP.add(ScoreBoardB);
        ButtonP.add(ExitB);

        ScoreBoardB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new ScoreBoardFrame();
            }
        });
        configB.addActionListener(MouseListener.configFrameMove);
        ExitB.addActionListener(MouseListener.ExitListener);

        add(ButtonP);
        setVisible(true);
    }
    public static void main(String[] args){
        new StartFrame();
    }
}
