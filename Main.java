/**
 * This class is used as the Driver class for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 2 hours
 * Class was created which runs separate parts of the game in the correct order.
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: < 1 hour
 * Class was modified to provide functionality for the credits and level 1 screen
 * </p>
 * 
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.1
 * 
 * Chat-Mod AI Inc.
 * May 31st, 2024
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
   
   int scene = 0; // starts on splash screen
   JFrame window;
   boolean level2 = false;
   
   /**
    * This method allows the actual game to run (main method)   
    * @param String[] args The command-line arguments for Java
    */
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
                     m.scene = 5;
                     break;
               }
               m.window.getContentPane().remove(mm);
               break;
            case 2:
               Instructions i = new Instructions();
               m.window.getContentPane().add(i);
               i.setFocusable(true);
               i.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) {
                  if (i.getFinished()) break;
               }
               m.window.getContentPane().remove(i);
               m.scene = 1;
               break;
            case 3: 
               Level1 l1 = new Level1();
               m.window.getContentPane().add(l1);
               l1.setFocusable(true);
               l1.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) {
                  if (l1.getFinished()) break;
               }
               m.window.getContentPane().remove(l1);
               m.level2 = true;
               m.scene = 1;
               break;
            case 4:
            
               //break;
            case 5:
               Credits c = new Credits();
               m.window.getContentPane().add(c);
               c.setFocusable(true);
               c.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) { // until the user makes a choice
                  if (c.getSelected()) break;
               }
               m.window.getContentPane().remove(c);
               m.scene = -1;
               break;
            case -1:
               m.window.setVisible(false); // exited
               break;
         }
      }
      m.window.dispose();
   }
  
}

