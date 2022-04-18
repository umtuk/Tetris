package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class startFrame{
    public JPanel mainP = new JPanel();
    JPanel buttonP = new JPanel();
    JPanel emptyP1 = new JPanel();
    JPanel emptyP2 = new JPanel();
    JPanel emptyP3 = new JPanel();
    JButton startB = new JButton("게임시작");
    JButton scoreboardB = new JButton("스코어보드");
    JButton configB = new JButton("설정");
    JButton exitB = new JButton("나가기");
    LineBorder LB = new LineBorder(Color.WHITE);
    
    public startFrame(){}
    public startFrame(int W, int H){
        emptyP3 = new JPanel(){
        Image background = new ImageIcon(startFrame.class.getResource("tetris_image.jpg")).getImage();
            public void paint(Graphics g){
                g.drawImage(background, (int)(W/2)-210, 100, null);
            }
        };

        buttonP.setSize((int)(W/5), (int)(H/7*3));
        buttonP.setLocation((int)(W/5*2), (int)(H/7*4));
        buttonP.setBackground(new Color(255, 0, 0, 0));
        buttonP.setLayout(new GridLayout(4, 1, 0, 10));
        startB.setBackground(Color.BLACK);
        startB.setForeground(Color.WHITE);
        startB.setBorder(LB);
        scoreboardB.setForeground(Color.WHITE);
        scoreboardB.setBackground(Color.black);
        scoreboardB.setBorder(LB);
        configB.setBackground(Color.BLACK);
        configB.setForeground(Color.WHITE);
        configB.setBorder(LB);
        exitB.setBackground(Color.BLACK);
        exitB.setForeground(Color.WHITE);
        exitB.setBorder(LB);
        buttonP.add(startB);
        buttonP.add(scoreboardB);
        buttonP.add(configB);
        buttonP.add(exitB);

        emptyP1.setBackground(new Color(255, 0, 0, 0));
        emptyP2.setBackground(new Color(255, 0, 0, 0));
        emptyP3.setPreferredSize(new Dimension(0, (int)(H*0.5)));

        mainP.setLayout(new BorderLayout(150, 0));
        mainP.setSize(W, H);
        mainP.setBackground(Color.BLACK);
        mainP.add(buttonP, BorderLayout.CENTER);
        mainP.add(emptyP1, BorderLayout.EAST);
        mainP.add(emptyP2, BorderLayout.WEST);
        mainP.add(emptyP3, BorderLayout.NORTH);

        exitB.addActionListener(MouseListener.ExitListener);
    }
    
    public static void main(String[] args){
        new startFrame(500, 700);
    }
}
