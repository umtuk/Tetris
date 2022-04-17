package Tetris.view.abstractComponent.container.window.frame;

import Tetris.global.config.constant.WindowSize;
import javax.swing.*;
import java.awt.*;

public class SimpleJFrame extends JFrame{
    private JFrame jFrame;
    public int HEIGHT;
    public int WIDTH;
    public SimpleJFrame(){
        HEIGHT = 700;
        WIDTH = 500;

        Dimension Dim = new Dimension(WIDTH, HEIGHT);
        setTitle("Tetris");
        setSize(Dim);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public SimpleJFrame(WindowSize windowSize) {
        setTitle("Tetris");
        this.jFrame.setSize(windowSize.getWidth(), windowSize.getHeight());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }
    public static void main(String[] args){
        new SimpleJFrame();
    }
}
