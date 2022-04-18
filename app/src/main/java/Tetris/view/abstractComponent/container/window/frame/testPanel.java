package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import Tetris.view.actionListener.MouseListener;

public class testPanel{
    public JPanel mainP = new JPanel();
    public testPanel(){
        mainP.setBackground(Color.BLACK);
    }
    
    public static void main(String[] args){
        new testPanel();
    }
}
