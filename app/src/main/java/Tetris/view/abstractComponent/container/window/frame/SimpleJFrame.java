package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.JFrame;

import java.awt.*;

import Tetris.global.config.constant.WindowSize;
import Tetris.global.config.entity.MainConfig;
import Tetris.global.config.service.ConfigService;

public class SimpleJFrame extends JFrame {

    private static ConfigService configService = ConfigService.getInstance();
    private static MainConfig mainConfig = MainConfig.getInstance();

    private final String title = "Tetris";

    public SimpleJFrame() {
        WindowSize windowSize = mainConfig.getWindowSize();

        setTitle(title);

        setSize(windowSize.getWidth(), windowSize.getHeight());
        setResizable(false);
        setBackground(Color.BLACK);
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public SimpleJFrame(WindowSize windowSize) {
        super();

        setSize(windowSize.getWidth(), windowSize.getHeight());
    }

    public static void main(String[] args) {
        SimpleJFrame frame = new SimpleJFrame();
    }
}
