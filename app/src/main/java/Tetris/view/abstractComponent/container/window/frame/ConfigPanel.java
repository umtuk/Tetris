package Tetris.view.abstractComponent.container.window.frame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Tetris.global.config.constant.ColorSet;
import Tetris.global.config.constant.WindowSize;
import java.awt.event.KeyListener;
import java.awt.*;

public class ConfigPanel extends SimpleJFrame{
    public JPanel configP = new JPanel();
    static public JPanel con = new JPanel();
    public JButton initB = new JButton("초기화");
    public JButton saveB = new JButton("저장");
    public JButton exitB = new JButton("나가기");
    public configButton Listener = new configButton();
    JLabel text = new JLabel("설정", JLabel.CENTER);
    JPanel buttonP = new JPanel();
    JLabel windowSizeL = new JLabel("해상도");
    JLabel colorSetL = new JLabel("모드");
    JLabel difficultyL = new JLabel("난이도");
    JLabel upL = new JLabel("위");
    JLabel downL = new JLabel("아래");
    JLabel leftL = new JLabel("왼쪽");
    JLabel rightL = new JLabel("오른쪽");
    JLabel okL = new JLabel("확인");
    JLabel pauseL = new JLabel("일시정지");
    JLabel rotateL = new JLabel("회전");
    JButton up_key = new JButton("up");
    JButton down_key = new JButton("down");
    JButton left_key = new JButton("left");
    JButton right_key = new JButton("right");
    JButton ok_key = new JButton("enter");
    JButton pause_key = new JButton("esc");
    JButton rotate_key = new JButton("r");
    WindowSize[] windowSize = WindowSize.values();
    ColorSet[] colorSets = ColorSet.values();
    JComboBox<String> windowSizeCB = new JComboBox(windowSize);
    JComboBox<String> colorSetCB = new JComboBox(colorSets);
    JComboBox difficultyCB = new JComboBox();
    public void setColor(){
        configP.setBackground(Color.BLACK);

        text.setForeground(Color.WHITE);
        text.setBackground(Color.BLACK);

        windowSizeCB.setBackground(Color.BLACK);
        colorSetCB.setBackground(Color.BLACK);
        windowSizeCB.setForeground(Color.WHITE);
        colorSetCB.setForeground(Color.WHITE);
        up_key.setForeground(Color.WHITE);
        down_key.setForeground(Color.WHITE);
        left_key.setForeground(Color.WHITE);
        right_key.setForeground(Color.WHITE);
        ok_key.setForeground(Color.WHITE);
        pause_key.setForeground(Color.WHITE);
        rotate_key.setForeground(Color.WHITE);

        up_key.setBackground(Color.BLACK);
        down_key.setBackground(Color.BLACK);
        left_key.setBackground(Color.BLACK);
        right_key.setBackground(Color.BLACK);
        ok_key.setBackground(Color.BLACK);
        pause_key.setBackground(Color.BLACK);
        rotate_key.setBackground(Color.BLACK);

        windowSizeL.setForeground(Color.WHITE);
        colorSetL.setForeground(Color.WHITE);
        difficultyL.setForeground(Color.WHITE);
        upL.setForeground(Color.WHITE);
        downL.setForeground(Color.WHITE);
        leftL.setForeground(Color.WHITE);
        rightL.setForeground(Color.WHITE);
        okL.setForeground(Color.WHITE);
        pauseL.setForeground(Color.WHITE);
        rotateL.setForeground(Color.WHITE);

        initB.setForeground(Color.WHITE);
        initB.setBackground(Color.BLACK);
        saveB.setForeground(Color.WHITE);
        saveB.setBackground(Color.BLACK);
        exitB.setForeground(Color.WHITE);
        exitB.setBackground(Color.BLACK);
    }
    public void setconfigP(){
        con.setLayout(new GridLayout(0, 2, 20, 30));
        con.setBackground(Color.BLACK);
        con.add(windowSizeL);
        con.add(windowSizeCB);
        con.add(colorSetL);
        con.add(colorSetCB);
        //con.add(difficultyL);
        //configSP.add(difficultyCB);
        //keymap
        con.add(upL);
        con.add(up_key);
        con.add(downL);
        con.add(down_key);
        con.add(leftL);
        con.add(left_key);
        con.add(rightL);
        con.add(right_key);
        con.add(okL);
        con.add(ok_key);
        con.add(pauseL);
        con.add(pause_key);
        con.add(rotateL);
        con.add(rotate_key);
    }
    public void setButtonP(){
        buttonP.setBackground(Color.BLACK);
        buttonP.add(initB);
        buttonP.add(saveB);
        buttonP.add(exitB);
        
        exitB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new IndexFrame();
            }
        });


    }
    public void setButtonListener(){
        up_key.addActionListener(Listener);
        down_key.addActionListener(Listener);
        left_key.addActionListener(Listener);
        right_key.addActionListener(Listener);
        ok_key.addActionListener(Listener);
        pause_key.addActionListener(Listener);
        rotate_key.addActionListener(Listener);
    }
    public void init(){
        setColor();
        setconfigP();
        setButtonP();
        setButtonListener();
        text.setFont(new Font("Serif", Font.BOLD, 30));
        up_key.setFocusable(false);
        down_key.setFocusable(false);
        left_key.setFocusable(false);
        right_key.setFocusable(false);
        ok_key.setFocusable(false);
        pause_key.setFocusable(false);
        rotate_key.setFocusable(false);
        configP.setLayout(new BorderLayout());
        configP.add(text, BorderLayout.NORTH);
        configP.add(con, BorderLayout.WEST);
        configP.add(buttonP, BorderLayout.SOUTH);
        addKeyListener(Listener);
        add(configP);
        setVisible(true);
    }
    public ConfigPanel(){
        init();
    }
    class configButton implements KeyListener, ActionListener{
        int button_idx = -1;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==up_key){
                up_key.setBackground(Color.WHITE);
                button_idx = 1;
            }else if(e.getSource()==down_key){
                down_key.setBackground(Color.WHITE);
                button_idx = 2;
            }else if(e.getSource()==left_key){
                left_key.setBackground(Color.WHITE);
                button_idx = 3;
            }else if(e.getSource()==right_key){
                right_key.setBackground(Color.WHITE);
                button_idx = 4;
            }else if(e.getSource()==ok_key){
                ok_key.setBackground(Color.WHITE);
                button_idx = 5;
            }else if(e.getSource()==pause_key){
                pause_key.setBackground(Color.WHITE);
                button_idx = 6;
            }else if(e.getSource()==rotate_key){
                rotate_key.setBackground(Color.WHITE);
                button_idx = 7;
            }else{
                button_idx = -1;
            }
        }
    
        @Override
        public void keyTyped(KeyEvent keyE) {
            // TODO Auto-generated method stub
            
        }
    
        @Override
        public void keyPressed(KeyEvent keyE) {
            // TODO Auto-generated method stub
            System.out.println("ladk;fj;asldkfj;aslkfja;sdkljf;asdkljf;asdkljf");
            System.out.println("Button_idx: "+button_idx);
            switch(button_idx){
                case 1:
                    up_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    up_key.setBackground(Color.BLACK);
                    break;
                case 2:
                    down_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    down_key.setBackground(Color.BLACK);
                    break;
                case 3:
                    left_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    left_key.setBackground(Color.BLACK);
                    break;
                case 4:
                    right_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    right_key.setBackground(Color.BLACK);
                    break;
                case 5:
                    ok_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    ok_key.setBackground(Color.BLACK);
                    break;
                case 6:
                    pause_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    pause_key.setBackground(Color.BLACK);
                    break;
                case 7:
                    rotate_key.setText(KeyEvent.getKeyText(keyE.getKeyCode()));
                    rotate_key.setBackground(Color.BLACK);
                    break;
                default:
                    break;

            }
        }
    
        @Override
        public void keyReleased(KeyEvent keyE) {
            // TODO Auto-generated method stub
            
        }
        
    }

    public static void main(String[] args){
        new ConfigPanel();
    }

}