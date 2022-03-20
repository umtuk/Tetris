package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.JFrame;

import Tetris.global.config.constant.WindowSize;

public class SimpleJFrame {
    
    private JFrame jFrame;

    public SimpleJFrame(WindowSize windowSize) {
        this.jFrame.setSize(windowSize.getWidth(), windowSize.getHeight());
    }
}
