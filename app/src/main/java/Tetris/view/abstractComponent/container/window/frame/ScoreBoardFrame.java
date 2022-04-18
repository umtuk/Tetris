package Tetris.view.abstractComponent.container.window.frame;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;

public class ScoreBoardFrame{
    public JPanel scoreboardP = new JPanel();
    public JButton exitB = new JButton("나가기");
    public ScoreBoardFrame(){}
    public ScoreBoardFrame(int WIDTH, int HEIGHT){
        String[] header = {"순위", "이름", "점수", "난이도"};
        String[][] contents = {
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"박지찬", "80", "170", "쉬움"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"},
            {"엄태욱", "100", "200", "어려움"},
            {"고민혁", "90", "180", "보통"}
        };
        JTable table = new JTable(contents, header);
        JScrollPane JSP = new JScrollPane(table);
        JLabel text = new JLabel("스코어보드", JLabel.CENTER);
        JPanel emptyP1 = new JPanel();
        JPanel emptyP2 = new JPanel();

        emptyP1.setBackground(Color.BLACK);
        emptyP2.setBackground(Color.BLACK);

        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);

        exitB.setForeground(Color.WHITE);
        exitB.setBackground(Color.BLACK);

        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);
        text.setFont(new Font("Serif", Font.BOLD, 30));

        JSP.setBackground(Color.BLACK);
        JSP.setForeground(Color.BLACK);
        JSP.getVerticalScrollBar().setBackground(Color.BLACK);
        JSP.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override    
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
        JSP.setSize((int)(WIDTH*0.6), HEIGHT-50);

        scoreboardP.setLayout(new BorderLayout((int)(WIDTH*0.2), 10));
        scoreboardP.setBackground(Color.BLACK);
        scoreboardP.add(emptyP1, BorderLayout.EAST);
        scoreboardP.add(emptyP2, BorderLayout.WEST);
        scoreboardP.add(text, BorderLayout.NORTH);
        scoreboardP.add(JSP, BorderLayout.CENTER);
        scoreboardP.add(exitB, BorderLayout.SOUTH);
    }
    public static void main(String[] args){
        SimpleJFrame F = new SimpleJFrame();
        ScoreBoardFrame S = new ScoreBoardFrame(F.WIDTH, F.HEIGHT);
        F.add(S.scoreboardP);
        S.scoreboardP.setVisible(true);
    }
}
