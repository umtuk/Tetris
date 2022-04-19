package Tetris.view.actionListener;
import java.awt.event.*;

public class KeyListeners implements KeyListener{
    public void keyTyped(KeyEvent e){
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 37:
                System.out.println("left");
                break;
            case 38:
                System.out.println("up");
                break;
            case 39:
                System.out.println("right");
                break;
            case 40:
                System.out.println("down");
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
