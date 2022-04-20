package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.JFrame;

import Tetris.global.config.constant.WindowSize;

public class SimpleJFrame extends JFrame {

    public SimpleJFrame(WindowSize windowSize) {
        setSize(windowSize.getWidth(), windowSize.getHeight());
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}
