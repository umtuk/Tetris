package Tetris.view.actionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyListener{
    public void keyTyped(KeyEvent e){
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
}
