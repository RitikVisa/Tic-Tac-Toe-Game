
package com.mygame;

import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MyGame extends JFrame implements ActionListener {
    JLabel heading, clockLabel;
    Font font= new Font("",Font.BOLD,25);
    JPanel mainPanel;
    JButton btns[]= new JButton[9];
    
    //game instance variables
    
    int gamechances[]= {2,2,2,2,2,2,2,2,2};
   // int gamechances[]= {0,0,2,1,1,1,2,2,2};
    int activePlayer= 0;
    int winner= 2;
    
    int wps[][]= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    
    boolean gameOver= false;
    
    MyGame(){
        System.out.print("creating a game instance");
        setTitle("Tic Tac Toe");
        setSize(550,550);
        ImageIcon icon = new ImageIcon("src/img/tictacicon.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        createGUI();
        
        //now we are adding a JPanel
        
    }
  

    private void createGUI() {
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        // we have to set heading in the north section of the frame
        heading= new JLabel("Tic Tac Toe");
        
        heading.setFont(font);
        heading.setForeground(Color.white);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(heading,BorderLayout.NORTH);
        
        
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setForeground(Color.white);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);
        //creating a thread for clock
        
        Thread t = new Thread(){
            @Override
            public void run(){
                try{
                    while(true){
                        String datetime= new Date().toLocaleString();
                        clockLabel.setText(datetime);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        t.start();
        
        // creating new JPanel
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        
        for(int i=1;i<=9;i++){
            JButton btn= new JButton();
//            btn.setIcon(new ImageIcon("src/img/x.png"));
            btn.setFont(font);
            btn.setBackground(Color.decode("#90caf9"));
            mainPanel.add(btn);
            btns[i-1]= btn;
            btn.addActionListener(this);
            
            btn.setName(String.valueOf(i-1));
            
            
        }
         
        
        this.add(mainPanel,BorderLayout.CENTER);
    }
    @Override
    
    public void actionPerformed(ActionEvent e){
       JButton currentbutton= (JButton) e.getSource();
       String str = currentbutton.getName();
       int name= Integer.parseInt(str);
       
       if(gameOver== true){
           JOptionPane.showMessageDialog(this,"Game is already over");
           return;
       }
       
       
       
       if(gamechances[name]==2){
           if(activePlayer==1){
               currentbutton.setIcon(new ImageIcon("src/img/x.png"));
                gamechances[name]= activePlayer;
               activePlayer=0;
              
           }else{
               currentbutton.setIcon(new ImageIcon("src/img/ooo.png"));
                gamechances[name]= activePlayer;
               activePlayer=1;
              
           }
           
          
            //finding a winner logic
            //int gamechances[]= {0,0,0,1,1,2,2,2,2};
            //int wps[][]= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
            
           for(int []temp : wps){
           if((gamechances[temp[0]]==gamechances[temp[1]])&& (gamechances[temp[1]]==gamechances[temp[2]]) && (gamechances[temp[2]]!=2)){
               winner= gamechances[temp[0]];
                gameOver= true;
                String a="";
                if(winner==0){
                    a="O";
                }else{
                    a= "X" ;
                }
               JOptionPane.showMessageDialog(null,"Player "+ a +" has won the game ");
               int i = JOptionPane.showConfirmDialog(this, "do you want to play more..");
               System.out.println(i);
               
             
               if(i==0){
                   this.setVisible(false);
                   new MyGame();
               }else if(i==1){
                   System.exit(321);
               }
               else{
                   
                  
               }
                System.out.println(i);
                   break;
           }
       }
        //draw logic starts here
        int c=0;
        for(int x : gamechances){
            if(x==2){
                c++;
                break;
            }
        }
       
         if(c==0 && gameOver==false){
            JOptionPane.showMessageDialog(null," Its a draw ");
            int i=JOptionPane.showConfirmDialog(this,"play more?? ");
            
            if(i==0){
                
                this.setVisible(false);
                new MyGame();
                
            }else if(i==1){
                
                System.exit(0000);
                
            }else{
                
            }
            
           gameOver= true;
        }
          
       }
       else{
           
           JOptionPane.showMessageDialog(this,"This Position Already Occupied");
    }
       
       //find winner logic
      
       
}
}
     
  

