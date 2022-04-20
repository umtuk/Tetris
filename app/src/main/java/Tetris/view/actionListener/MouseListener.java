package Tetris.view.actionListener;
import java.awt.event.*;

public class MouseListener{
    public static ActionListener ExitListener = new ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.exit(0);
        }
    };
    public static ActionListener configFrameMove = new ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            
        }
    };
    public static ActionListener ScoreBoardListener = new ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e){
            
        }
    };
}