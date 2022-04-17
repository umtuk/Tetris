package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class startFrame extends SimpleJFrame{
    public JPanel mainP = new JPanel();
    public startFrame(){
        int WIDTHs = WIDTH;
        JPanel buttonP = new JPanel();
        JPanel emptyP1 = new JPanel();
        JPanel emptyP2 = new JPanel();
        JPanel emptyP3 = new JPanel(){
            Image background = new ImageIcon(startFrame.class.getResource("tetris_image.jpg")).getImage();
            public void paint(Graphics g){
                g.drawImage(background, (int)(WIDTHs/2)-210, 100, null);
            }
        };
        JButton startB = new JButton("게임시작");
        JButton scoreboardB = new JButton("스코어보드");
        JButton configB = new JButton("설정");
        JButton exitB = new JButton("나가기");
        LineBorder LB = new LineBorder(Color.WHITE);
        

        buttonP.setSize(100, 300);
        buttonP.setLocation(200, 400);
        buttonP.setBackground(new Color(255, 0, 0, 0));
        buttonP.setLayout(new GridLayout(4, 1, 10, 10));
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
        emptyP3.setPreferredSize(new Dimension(0, (int)(HEIGHT*0.5)));

        mainP.setLayout(new BorderLayout(150, 0));
        mainP.setSize(500, 700);
        mainP.setBackground(Color.BLACK);
        mainP.add(buttonP, BorderLayout.CENTER);
        mainP.add(emptyP1, BorderLayout.EAST);
        mainP.add(emptyP2, BorderLayout.WEST);
        mainP.add(emptyP3, BorderLayout.NORTH);

        scoreboardB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new ScoreBoardFrame();
            }
        });
        configB.addActionListener(MouseListener.configFrameMove);
        exitB.addActionListener(MouseListener.ExitListener);

        add(mainP);
    }
    
    public static void main(String[] args){
        new startFrame();
    }
}
