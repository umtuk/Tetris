package Tetris.view.abstractComponent.container.window.frame;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import Tetris.view.actionListener.KeyListeners;
import java.awt.*;

public class GamePanel {
    public static final int HEIGHT = 20;
	public static final int WIDTH = 10;
	public static final char BORDER_CHAR = 'O';
	
	private JTextPane pane;
	private int[][] board;
	private KeyListeners playerKeyListener;
	private SimpleAttributeSet styleSet;
	private Timer timer;

    int x = 3;
    int y = 0;

    private static final int interval = 1000;

    public GamePanel(){
        pane = new JTextPane();
        pane.setEditable(false);
        pane.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 10),
            BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
        pane.setBorder(border);
        //this.getContentPane().add(pane, BorderLayout.CENTER);
        
    }
}
