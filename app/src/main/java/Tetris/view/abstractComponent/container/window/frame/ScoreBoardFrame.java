package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScoreBoardFrame extends SimpleJFrame{
    public ScoreBoardFrame(){
        String[] header = {"Name", "EndTime", "Score"};
        String[][] contents = {
            {"엄태욱", "100", "200"},
            {"고민혁", "90", "180"},
            {"박지찬", "80", "170"}
        };

        JPanel buttonPanel = new JPanel();
        JTable table = new JTable(contents, header);
        JScrollPane JSP = new JScrollPane(table);
        JButton exitB = new JButton("나가기");
        FlowLayout FL = new FlowLayout();
        
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(exitB);

        exitB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        buttonPanel.setLocation(200, 200);
        buttonPanel.setSize(200, 200);
        JSP.setLocation(0, 0);
        JSP.setSize(300, 160);
        add(buttonPanel);
        add(JSP);

        setVisible(true);
    }

    public static void main(String[] args){
        new ScoreBoardFrame();
    }
}
