package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class panelexe {
    public static void main(String[] args){
        SimpleJFrame F = new SimpleJFrame();
        startFrame startP = new startFrame(F.WIDTH, F.HEIGHT);
        ScoreBoardFrame scoreP = new ScoreBoardFrame(F.WIDTH, F.HEIGHT);
        ConfigFrame config = new ConfigFrame(F.WIDTH, F.HEIGHT);

        F.add(startP.mainP);
        F.add(scoreP.scoreboardP);
        F.add(config.configP);
        startP.mainP.setVisible(true);
        scoreP.scoreboardP.setVisible(false);
        config.configP.setVisible(false);
        
        startP.scoreboardB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                scoreP.scoreboardP.setVisible(true);
                startP.mainP.setVisible(false);
            }
        });
        startP.configB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                config.configP.setVisible(true);
                startP.mainP.setVisible(false);
            }
        });
        
        scoreP.exitB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                startP.mainP.setVisible(true);
                scoreP.scoreboardP.setVisible(false);
            }
        });
        
        config.exitB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                startP.mainP.setVisible(true);
                config.configP.setVisible(false);
            }
        });
    }
}
