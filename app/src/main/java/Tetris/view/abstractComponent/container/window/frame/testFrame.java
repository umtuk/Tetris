package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Tetris.view.actionListener.MouseListener;

public class testFrame extends JFrame{
    public void buttonPanel(JPanel ButtonP){
        String Button_list[] = {"게임시작", "설정", "스코어보드", "나가기"};
        JButton[] buttons = new JButton[Button_list.length];
        GridBagConstraints[] gbc = new GridBagConstraints[Button_list.length];
        GridBagLayout gbl = new GridBagLayout();
        ButtonP.setLayout(gbl);

        for(int i=0;i<Button_list.length;i++){
            buttons[i] = new JButton(Button_list[i]);
            gbc[i].gridx = i;
            gbc[i].gridy = i;

            ButtonP.add(buttons[i], gbc[i]);
        }
        
        buttons[1].addActionListener(MouseListener.configFrameMove);
        buttons[2].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new ScoreBoardFrame();
            }
        });
        buttons[3].addActionListener(MouseListener.ExitListener);
    }
    public testFrame(){
        setTitle("testFrame");
        JPanel ButtonP = new JPanel();
        buttonPanel(ButtonP);
        setContentPane(ButtonP);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new testFrame();
    }
}
