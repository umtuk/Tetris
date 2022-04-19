package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class panelexe {
    public static void main(String[] args){
        SimpleJFrame F = new SimpleJFrame();
        IndexFrame startP = new IndexFrame(F.WIDTH, F.HEIGHT);
        ScoreBoardPanel scoreP = new ScoreBoardPanel(F.WIDTH, F.HEIGHT);
        ConfigPanel config = new ConfigPanel(F.WIDTH, F.HEIGHT);

        F.add(startP.mainP);
        F.add(config.configP);
        F.add(scoreP.scoreboardP);
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
