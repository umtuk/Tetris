package Tetris.view.actionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyListeners implements KeyListener{
    public void keyTyped(KeyEvent e){
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 37){
            //왼쪽
        }else if(e.getKeyCode() == 38){
            //위
        }else if(e.getKeyCode() == 39){
            //오른쪽
        }else if(e.getKeyCode() == 40){
            //아래
        }
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public void highlight(JButton B, JButton another){
        B.setBackground(Color.WHITE);
        B.setForeground(Color.BLACK);
        another.setBackground(Color.BLACK);
        another.setForeground(Color.WHITE);
    }
}
