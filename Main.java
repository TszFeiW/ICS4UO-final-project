
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
   
   int scene = 0; // starts on splash screen
   JFrame window;
   boolean level2 = false;
   
   public static void main(String[] args) {
      Main m = new Main();
      
      m.window = new JFrame("CMod Socializer");
      m.window.setSize(810, 1080);
      m.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      while (m.scene != -1) { // while the user has not chosen to quit
         switch (m.scene) {
            case 0: 
               SplashScreen ss = new SplashScreen();
               m.window.getContentPane().add(ss);
               ss.setFocusable(true);
               ss.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) { // until the user makes a choice
                  if (ss.getChoice() != -1) break;
               }
               if (ss.getChoice() == 1) { // chooses to exit
                  m.scene = -1;
               }
               else {
                  m.scene = 1;
               }
               m.window.getContentPane().remove(ss);
               break;
            case 1:
               MainMenu mm = new MainMenu(m.level2);
               m.window.getContentPane().add(mm);
               mm.setFocusable(true);
               mm.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) { // until the user makes a choice
                  if (mm.getChoice() != -1) break;
               }
               switch (mm.getChoice()) {
                  case 0:
                     m.scene = 2;
                     break;
                  case 1:
                     m.scene = 3;
                     break;
                  case 2:
                     m.scene = 4;
                     break;
                  case 3:
                     m.scene = -1;
                     break;
               }
               m.window.getContentPane().remove(mm);
               break;
            case 2:
            
               //break;
            case 3: 
               m.level2 = true;
               //break;
            case 4:
            
               //break;
            case 5:
            
               //break;
            case -1:
               m.window.setVisible(false); // exited
               break;
         }
      }
      m.window.dispose();
   }
  
}

