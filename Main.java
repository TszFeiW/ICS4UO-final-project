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
 * <p>
 * Version 1.4
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.4
 * 
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Main extends Thread {
   
   /**
    * private JFrame window   - JFrame window which has all the graphics drawn onto it
    * private String username - the user's username
    * private int scene       - the current scene number
    * private boolean level2  - whether the user can access level 2 or not
    */
   private JFrame window;
   private String username;
   private int scene;
   private boolean level2;
   
   /**
    * Constructor so the main method can access the methods of this class
    */
   public Main() {
      // setting up the window
      window = new JFrame("CMod Socializer");
      window.setSize(810, 1020);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // initializing other instance variables
      username = "";
      scene = 0; // starts on the splash screen
      level2 = false;
   }
   
   /**
    * This utility method cleans the window by clearing it
    */
   public void cleanWindow() {
      window.getContentPane().removeAll();
      window.revalidate();
      window.repaint();
   }
   
   /**
    * This method displays the splash screen of the game
    */
   public void splashScreen() {
      SplashScreen ss = new SplashScreen();
      Thread thread = new Thread(ss);
      window.getContentPane().add(ss);
      ss.setFocusable(true);
      ss.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      if (ss.getChoice() == 1) // chooses to exit
         scene = -1;
      else
         scene = 1;
      
      window.getContentPane().remove(ss);
      level2 = true; // temporaroryoryryry
      cleanWindow();
   }
   
   /**
    * This method displays the main menu of the game
    */
   public void mainMenu() {
      MainMenu mm = new MainMenu(level2);
      Thread thread = new Thread(mm);
      window.getContentPane().add(mm);
      mm.setFocusable(true);
      mm.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      switch (mm.getChoice()) {
         case 0:
            scene = 2; // instructions 
            break;
         case 1:
            scene = 3; // level 1
            break;
         case 2:
            scene = 4; // level 2
            break;
         case 3:
            scene = 5; // leaderboard
            break;
         case 4:
            scene = 6; // credits
      }
      window.getContentPane().remove(mm);
      cleanWindow();
   }
   
   /**
    * This method displays the instructions of the game
    */
   public void instructions() {
      Instructions i = new Instructions();
      Thread thread = new Thread(i);
      window.getContentPane().add(i);
      i.setFocusable(true);
      i.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      cleanWindow();
      scene = 1;
   }
   
   /**
    * This method displays Level 1 of the game
    */
   public void level1() {
      Level1 l1 = new Level1();
      Thread thread = new Thread(l1);
      window.getContentPane().add(l1);
      l1.setFocusable(true);
      l1.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      username = l1.getUsername();
      window.getContentPane().remove(l1);
      level2 = true;
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays Level 2 of the game
    */
   public void level2() {
      Level2 l2 = new Level2(username);
      Thread thread = new Thread(l2);
      window.getContentPane().add(l2);
      l2.setFocusable(true);
      l2.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      window.getContentPane().remove(l2);
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays the leaderboard screen of the game
    */
   public void leaderboard() {
      Leaderboard l = new Leaderboard();
      Thread thread = new Thread(l);
      window.getContentPane().add(l);
      l.setFocusable(true);
      l.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      window.getContentPane().remove(l);
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays the credits screen of the game
    */
   public void credits() {
      Credits c = new Credits();
      Thread thread = new Thread(c);
      window.getContentPane().add(c);
      c.setFocusable(true);
      c.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { }
      
      window.getContentPane().remove(c);
      scene = -1;
      cleanWindow();
   }
   
   /**
    * This method ends and exits the game
    */
   public void exit() {
      window.setVisible(false);
      window.dispose();
   }
   
   /**
    * This method allows the main method to access the scene number for the next screen
    * @return The scene number
    */
   public int getScene() {
      return scene;
   }

   /**
    * This method allows the actual game to run (main method)   
    * @param String[] args The command-line arguments for Java
    */
   public static void main(String[] args) {
      Main m = new Main();
      
      while (m.getScene() != -1) { // while the user has not chosen to quit
         switch (m.getScene()) {
            case 0: 
               m.splashScreen();
               break;
            case 1:
               m.mainMenu();
               break;
            case 2:
               m.instructions();
               break;
            case 3: 
               m.level1();
               break;
            case 4:
               m.level2();
               break;
            case 5:
               m.leaderboard();
               break;
            case 6:
               m.credits();
               break;
            case -1:
               m.exit();
               break;
         }
      }
   }
  
}

