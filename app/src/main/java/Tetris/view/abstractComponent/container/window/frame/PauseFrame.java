package Tetris.view.abstractComponent.container.window.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseFrame extends JFrame{
    JFrame pauseFrame = new JFrame("일시정지");
    JPanel buttonPanel = new JPanel();
    JButton continueB = new JButton("계속하기");
    JButton exitB = new JButton("나가기");
    Dimension Dim = new Dimension(300, 300);

    public PauseFrame(){
        setSize(Dim);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setBackground(Color.BLACK);

        continueB.setBackground(Color.BLACK);
        continueB.setForeground(Color.WHITE);
        exitB.setBackground(Color.BLACK);
        exitB.setForeground(Color.WHITE);

        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new GridLayout(0, 1, 50, 50));
        buttonPanel.add(continueB);
        buttonPanel.add(exitB);

        continueB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                System.out.println("continue");
            }
        });
        exitB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("exit");
                new IndexFrame();
                dispose();
            }
        });
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args){
        new PauseFrame();
    }
}
