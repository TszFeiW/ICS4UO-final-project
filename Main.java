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
 * Class was modified to provide functionality for the credits and level 1 screen.
 * </p>
 *
 * <p>
 * Version 1.2
 * Time Spent: < 1 hour
 * Class was modified so username from level 1 could be accessed
 * Window size modified so that it fits on the school monitors.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: < 1 hour
 * Class was modified to add the leaderboard to the game.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.3
 * 
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
   
   int scene = 0; // starts on splash screen
   JFrame window;
   boolean level2 = false;
   String username = "";
   
   /**
    * This method allows the actual game to run (main method)   
    * @param String[] args The command-line arguments for Java
    */
   public static void main(String[] args) {
      Main m = new Main();
      
      m.window = new JFrame("CMod Socializer");
      m.window.setSize(810, 1020);
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
               m.level2 = true; // temporaroryoryryry
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
                  case 4:
                     m.scene = 6;
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
               m.username = l1.getUsername();
               m.window.getContentPane().remove(l1);
               m.level2 = true;
               m.scene = 1;
               break;
            case 4:
               Level2 l2 = new Level2(m.username);
               m.window.getContentPane().add(l2);
               l2.setFocusable(true);
               l2.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) {
                  if (l2.getFinished()) break;
               }
               m.window.getContentPane().remove(l2);
               m.scene = 1;
               break;
            case 5:
               Leaderboard l = new Leaderboard();
               m.window.getContentPane().add(l);
               l.setFocusable(true);
               l.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) {
                  if (l.getFinished()) break;
               }
               m.window.getContentPane().remove(l);
               m.scene = 1;
               break;
            case 6:
               Credits c = new Credits();
               m.window.getContentPane().add(c);
               c.setFocusable(true);
               c.requestFocusInWindow();
               m.window.setVisible(true);
               while (true) { // until the user makes a choice
                  if (c.getFinished()) break;
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

