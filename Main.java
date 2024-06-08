import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

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
 * Time Spent: 15 minutes
 * Class was modified to provide functionality for the credits and level 1 screen.
 * </p>
 *
 * <p>
 * Version 1.2
 * Time Spent: 5 minutes
 * Class was modified so username from level 1 could be accessed
 * Window size modified so that it fits on the school monitors.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: 5 minutes
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
 * <p>
 * Version 1.5
 * Time Spent: 20 minutes
 * Modifying comments to generate java docs properly
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.5
 * 
 * Chat-Mod AI Inc.
 * June 7th, 2024
 */
public class Main extends Thread {
   
   /** JFrame window which has all the graphics drawn onto it */
   private JFrame window;
   /** the user's username */
   private String username;
   /** the current scene number */
   private int scene;
   /** whether the user can access level 2 or not */
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
      Thread thread = new Thread(ss); // creates thread object
      window.getContentPane().add(ss); // adds splash screen to JFrame window
      ss.setFocusable(true);
      ss.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
      if (ss.getChoice() == 1) // chooses to exit
         scene = 7;
      else // chooses to proceed to main menu
         scene = 1;
      
      cleanWindow();
   }
   
   /**
    * This method displays the main menu of the game
    */
   public void mainMenu() {
      MainMenu mm = new MainMenu(level2);
      Thread thread = new Thread(mm); // creates thread object
      window.getContentPane().add(mm); // adds main menu to JFrame window
      mm.setFocusable(true);
      mm.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
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
      
      cleanWindow();
   }
   
   /**
    * This method displays the instructions of the game
    */
   public void instructions() {
      Instructions i = new Instructions();
      Thread thread = new Thread(i); // creates thread object
      window.getContentPane().add(i); // adds instructions to JFrame window
      i.setFocusable(true);
      i.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
      cleanWindow();
      scene = 1;
   }
   
   /**
    * This method displays Level 1 of the game
    */
   public void level1() {
      Level1 l1 = new Level1();
      Thread thread = new Thread(l1); // creates thread object
      window.getContentPane().add(l1); // adds level 1 to JFrame window
      l1.setFocusable(true);
      l1.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
      username = l1.getUsername();
      level2 = true;
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays Level 2 of the game
    */
   public void level2() {
      Level2 l2 = new Level2(username);
      Thread thread = new Thread(l2); // creates thread object
      window.getContentPane().add(l2); // adds level 2 to JFrame window
      l2.setFocusable(true);
      l2.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays the leaderboard screen of the game
    */
   public void leaderboard() {
      Leaderboard l = new Leaderboard();
      Thread thread = new Thread(l); // creates thread object
      window.getContentPane().add(l); // adds leaderboard to JFrame window
      l.setFocusable(true);
      l.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
            
      scene = 1;
      cleanWindow();
   }
   
   /**
    * This method displays the credits screen of the game
    */
   public void credits() {
      Credits c = new Credits();
      Thread thread = new Thread(c); // creates thread object
      window.getContentPane().add(c); // adds credits screen to JFrame window
      c.setFocusable(true);
      c.requestFocusInWindow();
      window.setVisible(true);
      try {
         thread.start();
         thread.join();
      } 
      catch (Exception e) { System.out.println("Exception has occured."); }
      
      scene = 7;
      cleanWindow();
   }
   
   /**
    * This method ends and exits the game
    */
   public void exit() {
      window.setVisible(false);
      window.dispose(); // closes window
      scene = -1;
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
    * @param args The command-line arguments for Java
    */
   public static void main(String[] args) {
      Main m = new Main();
      
      while (m.getScene() != -1) { // while the program is still running
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
            case 7:
               m.exit();
               break;
         }
      }
   }
  
}

