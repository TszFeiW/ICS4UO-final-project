import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * This class is used to display the Main Menu for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 4 hours
 * Class was created which runs the main menu of our game. Keyboard input is used
 * to detect which scene/level they would like to proceed to. The user is still
 * able to exit the game. The user cannot proceed to level 2 prior to level 1.
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 10 minutes
 * Comments modified.
 * </p>
 * 
 * <p>
 * Version 1.2
 * Time Spent: 5 minutes
 * Coordinates of some drawings adjusted so that it fits on school monitor.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: 5 minutes
 * Coordinates of some drawings adjusted again so it doesn't go out of the screen.
 * </p>
 *
 * <p>
 * Version 1.4
 * Time Spent: 15 minutes
 * Adding a leaderboard button as well.
 * </p>
 *
 * <p>
 * Version 1.5
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * <p>
 * Version 1.6
 * Time Spent: 20 minutes
 * Modifying comments to generate java docs properly.
 * </p>
 *
 * <p>
 * Version 1.7
 * Time Spent: 5 minutes
 * Adjusting the location of the buttons and text slightly.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.6
 * 
 * Chat-Mod AI Inc.
 * June 7th, 2024
 */
public class MainMenu extends JComponent implements Runnable {

   /** allows for 50 ms delay between key presses */
   private static final long THRESHOLD = 50_000_000L;
   /** keeps track of last time a key has been pressed */
   private long lastPress;
   /** image of the non-selected instructions button */
   private BufferedImage instructions;
   /** image of the selected instructions button */
   private BufferedImage instructions2;
   /** image of the non-selected level 1 button */
   private BufferedImage firstLevel;
   /** image of the selected level 1 button */
   private BufferedImage firstLevel2;
   /** image of the non-selected non-unlocked level 2 button */
   private BufferedImage secondLevel;
   /** image of the selected non-unlocked level 2 button */
   private BufferedImage secondLevel2;
   /** image of the non-selected unlocked level 2 button */
   private BufferedImage secondLevelUnlocked;
   /** image of the selected unlocked level 2 button */
   private BufferedImage secondLevelUnlocked2;
   /** image of the non-selected leaderboard button */
   private BufferedImage leaderboard;
   /** image of the selected leaderboard button */
   private BufferedImage leaderboard2;
   /** image of the non-selected quit game button */
   private BufferedImage quit;
   /** image of the selected quit game button */
   private BufferedImage quit2;
   /** the current button that is selected (numbered 0 to 3) */
   private int selected;
   /** the selected button */
   private int choice;
   /** the color of the background */
   private Color bg;
   /** whether the user can choose level 2 or not */
   private boolean allowed;
   /** whether the user chose a non-unlocked level 2 */
   private boolean warning;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    * @param level2 Whether the second level can currently be chosen or not
    */
   public MainMenu(boolean level2) {
      this.addKeyListener(new KeyHandler()); // adding KeyListener
      
      try {
         // importing images
         instructions = ImageIO.read(new File("instructionsButton.png"));
         instructions2 = ImageIO.read(new File("instructionsButton2.png"));
         firstLevel = ImageIO.read(new File("level1Button.png"));
         firstLevel2 = ImageIO.read(new File("level1Button2.png"));
         secondLevel = ImageIO.read(new File("level2Button.png"));
         secondLevel2 = ImageIO.read(new File("level2Button2.png"));
         secondLevelUnlocked = ImageIO.read(new File("level2unlocked.png"));
         secondLevelUnlocked2 = ImageIO.read(new File("level2unlocked2.png"));
         leaderboard = ImageIO.read(new File("leaderboardButton.png"));
         leaderboard2 = ImageIO.read(new File("leaderboardButton2.png"));
         quit = ImageIO.read(new File("quitGameButton.png"));
         quit2 = ImageIO.read(new File("quitGameButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      
      // initializing other instance variables
      choice = -1; // the user's choice
      selected = 0; // on the first button
      allowed = level2; // whether level 2 can be selected
      bg = new Color(245, 228, 255);
   }
   
   /**
    * This private class extends KeyAdapter so the drawing can detect key inputs
    */
   private class KeyHandler extends KeyAdapter {
      /**
       * This method allows the actual game to run (main method)   
       * @param e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         long current = System.nanoTime();
         
         if (lastPress <= 0L || current - lastPress >= THRESHOLD) {
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_DOWN) // down arrow key
               selected = Math.min(selected+1, 4);
            else if (key == KeyEvent.VK_UP) // up arrow key
               selected = Math.max(selected-1, 0);
            else if (key == KeyEvent.VK_ENTER && selected == 2 && !allowed) // choosing level 2 but not unlocked
               warning = true;
            else if (key == KeyEvent.VK_ENTER && (selected != 2 || allowed)) // choosing an unlocked option
               choice = selected;
            
            lastPress = current;
            MainMenu.this.repaint();
         }
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      // background
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
      // title
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 100));
      g.drawString("CMOD Socializer", 50, 150);
      
      // drawing default buttons
      g.drawImage(instructions, 205, 200, this);
      g.drawImage(firstLevel, 205, 320, this);
      g.drawImage(allowed ? secondLevelUnlocked : secondLevel, 205, 440, this);
      g.drawImage(leaderboard, 205, 560, this);
      g.drawImage(quit, 205, 680, this);
      
      // drawing selected button differently
      if (selected == 0)
         g.drawImage(instructions2, 205, 200, this);
      else if (selected == 1)
         g.drawImage(firstLevel2, 205, 320, this);
      else if (selected == 2)
         g.drawImage(allowed ? secondLevelUnlocked2 : secondLevel2, 205, 440, this);
      else if (selected == 3)
         g.drawImage(leaderboard2, 205, 560, this);
      else 
         g.drawImage(quit2, 205, 680, this);
      
      // instructions to continue
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 860);
      g.drawString("‘Enter’ to Continue.", 135, 930);
      
      if (warning) { // displays warning message if you try to choose level 2 when level 1 is incomplete
         g.setFont(new Font("Calibri", Font.BOLD, 48));
         g.setColor(new Color(162, 210, 255));
         g.fillRect(50, 427, 710, 120);
         g.setColor(Color.black);
         g.drawString("Cannot Choose Level 2 Currently", 85, 497);
         warning = false;
      }
   }
   
   /**
    * This method allows the Main class to access the choice that the user made 
    * @return The choice that the user made in the menu
    */
   public int getChoice() {
      return choice;
   }
   
   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(200);
            if (choice != -1) break; // until the user has made a choice in the menu
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}