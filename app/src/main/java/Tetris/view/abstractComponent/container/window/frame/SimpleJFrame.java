package Tetris.view.abstractComponent.container.window.frame;

import Tetris.global.config.constant.WindowSize;
import javax.swing.*;
import java.awt.*;

public class SimpleJFrame extends JFrame{
    private JFrame jFrame;
    public int HEIGHT = 300;
    public int WIDTH = 500;
    public SimpleJFrame(){
        setTitle("Tetris");
        setPreferredSize(new Dimension(500, 300));
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public SimpleJFrame(WindowSize windowSize) {
        //제목, 크기
        setTitle("Tetris");
        this.jFrame.setSize(windowSize.getWidth(), windowSize.getHeight());

        //우측 상단에 X버튼을 누르면 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
