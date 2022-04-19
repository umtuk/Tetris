package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import java.awt.*;
import Tetris.view.actionListener.KeyListeners;

public class panelexe{
    static KeyListeners KL = new KeyListeners();
    public static void main(String[] args){
        SimpleJFrame Frame = new SimpleJFrame();
        Frame.addKeyListener(KL);
    }
}
