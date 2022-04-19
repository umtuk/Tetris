package Tetris.view.abstractComponent.container.window.frame;

import Tetris.global.config.constant.WindowSize;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimpleJFrame extends JFrame{
    static SimpleJFrame F;
    public JFrame jFrame;
    public int HEIGHT = 700;
    public int WIDTH = 500;
    IndexFrame startP = new IndexFrame(this.WIDTH, this.HEIGHT);
    ScoreBoardPanel scoreP = new ScoreBoardPanel(this.WIDTH, this.HEIGHT);
    ConfigPanel config = new ConfigPanel(this.WIDTH, this.HEIGHT);
    public SimpleJFrame(){
        Dimension Dim = new Dimension(WIDTH, HEIGHT);
        setTitle("Tetris");
        setSize(Dim);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        System.out.println("프래임 생성 및 설정");
        
        this.add(startP.mainP);
        this.add(config.configP);
        this.add(scoreP.scoreboardP);
        System.out.println("패널 추가");
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
    public SimpleJFrame(WindowSize windowSize) {
        setTitle("Tetris");
        this.jFrame.setSize(windowSize.getWidth(), windowSize.getHeight());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }
    public static void main(String[] args){
    }
}
