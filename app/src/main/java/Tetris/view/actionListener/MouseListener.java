package Tetris.view.actionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Tetris.view.abstractComponent.container.window.frame.*;

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
            new ConfigFrame();
        }
    };
    public static ActionListener ScoreBoardListener = new ActionListener(){
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e){
            new ScoreBoardFrame();
        }
    };
}