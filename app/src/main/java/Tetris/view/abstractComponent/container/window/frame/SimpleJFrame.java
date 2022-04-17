package Tetris.view.abstractComponent.container.window.frame;

import Tetris.global.config.constant.WindowSize;
import javax.swing.*;
import java.awt.*;

public class SimpleJFrame extends JFrame{
    private JFrame jFrame;
    private Image background = new ImageIcon(SimpleJFrame.class.getResource("start_image.png")).getImage();
    public int HEIGHT = 700;
    public int WIDTH = 500;
    public SimpleJFrame(){
        setTitle("Tetris");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(12, 25, 53));
        setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
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
