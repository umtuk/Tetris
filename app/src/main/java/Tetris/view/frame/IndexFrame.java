package Tetris.view.frame;

import java.awt.*;
import javax.swing.*;

import Tetris.global.config.constant.WindowSize;
import Tetris.view.abstractComponent.container.panel.BoardJPanel;
import Tetris.view.abstractComponent.container.window.frame.SimpleJFrame;

public class IndexFrame extends SimpleJFrame {

    private final String title = "Tetris";

    public IndexFrame(WindowSize windowSize) {
        super(windowSize);

        addComponents();
        initFrame();
    }
    
    private void initFrame() {
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.BLACK);
        setSize(300, 600);
    }

    private void addComponents() {
        BoardJPanel boardJPanel = new BoardJPanel(); add(boardJPanel); 
    }
}
