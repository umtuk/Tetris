package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

//import Tetris.view.actionListener.KeyListeners;
import Tetris.view.actionListener.MouseListener;

public class IndexFrame extends SimpleJFrame implements KeyListener{
    public JPanel mainP = new JPanel();
    JPanel buttonP = new JPanel();
    JPanel emptyP1 = new JPanel();
    JPanel emptyP2 = new JPanel();
    JPanel emptyP3 = new JPanel();
    String[] Array = {"게임시작", "스코어보드", "설정", "나가기"};
    JButton startB = new JButton("게임시작");
    JButton scoreboardB = new JButton("스코어보드");
    JButton configB = new JButton("설정");
    JButton exitB = new JButton("나가기");
    LineBorder LB = new LineBorder(Color.WHITE);
    int button_idx = 10000;
    
    public IndexFrame(){
        int W = WIDTH;
        int H = HEIGHT;
        emptyP3 = new JPanel(){
        Image background = new ImageIcon(IndexFrame.class.getResource("tetris_image.jpg")).getImage();
            public void paint(Graphics g){
                g.drawImage(background, (int)(W/2)-210, 100, null);
            }
        };

        buttonP.setSize((int)(W/5), (int)(H/7*3));
        buttonP.setLocation((int)(W/5*2), (int)(H/7*4));
        buttonP.setBackground(new Color(255, 0, 0, 0));
        buttonP.setLayout(new GridLayout(4, 1, 0, 10));
        startB.setBackground(Color.BLACK);
        startB.setForeground(Color.WHITE);
        startB.setBorder(LB);
        scoreboardB.setForeground(Color.WHITE);
        scoreboardB.setBackground(Color.black);
        scoreboardB.setBorder(LB);
        configB.setBackground(Color.BLACK);
        configB.setForeground(Color.WHITE);
        configB.setBorder(LB);
        exitB.setBackground(Color.BLACK);
        exitB.setForeground(Color.WHITE);
        exitB.setBorder(LB);
        buttonP.add(startB);
        buttonP.add(scoreboardB);
        buttonP.add(configB);
        buttonP.add(exitB);

        emptyP1.setBackground(new Color(255, 0, 0, 0));
        emptyP2.setBackground(new Color(255, 0, 0, 0));
        emptyP3.setPreferredSize(new Dimension(0, (int)(H*0.5)));

        mainP.setLayout(new BorderLayout(150, 0));
        mainP.setSize(W, H);
        mainP.setBackground(Color.BLACK);
        mainP.add(buttonP, BorderLayout.CENTER);
        mainP.add(emptyP1, BorderLayout.EAST);
        mainP.add(emptyP2, BorderLayout.WEST);
        mainP.add(emptyP3, BorderLayout.NORTH);

        exitB.addActionListener(MouseListener.ExitListener);

        scoreboardB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new ScoreBoardPanel();
            }
        });
        configB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new ConfigPanel();
            }
        });
        
        addKeyListener(this);
        add(mainP);
        setVisible(true);
    }
    public void highlight(int idx){
        switch(idx%4){
            case 0:
                startB.setBackground(Color.WHITE);
                startB.setForeground(Color.BLACK);
                exitB.setBackground(Color.BLACK);
                exitB.setForeground(Color.WHITE);
                scoreboardB.setBackground(Color.BLACK);
                scoreboardB.setForeground(Color.WHITE);
                break;
            case 1:
                scoreboardB.setBackground(Color.WHITE);
                scoreboardB.setForeground(Color.BLACK);
                startB.setBackground(Color.BLACK);
                startB.setForeground(Color.WHITE);
                configB.setBackground(Color.BLACK);
                configB.setForeground(Color.WHITE);
                break;
            case 2:
                configB.setBackground(Color.WHITE);
                configB.setForeground(Color.BLACK);
                scoreboardB.setBackground(Color.BLACK);
                scoreboardB.setForeground(Color.WHITE);
                exitB.setBackground(Color.BLACK);
                exitB.setForeground(Color.WHITE);
                break;
            case 3:
                exitB.setBackground(Color.WHITE);
                exitB.setForeground(Color.BLACK);
                configB.setBackground(Color.BLACK);
                configB.setForeground(Color.WHITE);
                startB.setBackground(Color.BLACK);
                startB.setForeground(Color.WHITE);
                break;
            default:
                break;
        }
    }
    public void pressButton(int idx){
        switch(idx%4){

        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        switch(e.getKeyCode()){
            case 10:
                pressButton(button_idx);
                System.out.println("enterKey");
                break;
            case 37:
                System.out.println("left");
                break;
            case 38:
                System.out.println("up");
                button_idx--;
                highlight(button_idx);
                break;
            case 39:
                System.out.println("right");
                break;
            case 40:
                System.out.println("down");
                button_idx++;
                highlight(button_idx);
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }
    
    public static void main(String[] args){
        IndexFrame a = new IndexFrame();
        a.startB.setBackground(Color.WHITE);
        a.startB.setForeground(Color.BLACK);
    }
}
