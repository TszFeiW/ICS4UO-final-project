import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * This abstract class is used as a template for our Level1 and Level2 classes.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created as a template for the levels in our game.
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 1 hour
 * Class modified so that it is fully commented and functional.
 * </p>
 * 
 * <p>
 * Version 1.2
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: 20 minutes
 * Modifying comments to generate java docs properly
 * </p>
 *
 * <p>
 * Version 1.4
 * Time Spent: 20 minutes
 * Changing the graphics of the instructions and community guidelines
 * </p>
 *
 * <p>
 * Version 1.5
 * Time Spent: 10 minutes
 * Modifying the file path for importing files after organizing folders
 * </p>
 *
 * <p>
 * Version 1.6
 * Time Spent: 1 minute
 * Level 2 no longer has a transition method, so no longer abstract in Level
 * </p>
 *
 * <p>
 * Version 1.7
 * Time Spent: 5 minutes
 * Coordinates of some drawings adjusted after making JFrame window smaller
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.7
 * 
 * Chat-Mod AI Inc.
 * June 12th, 2024
 */
abstract class Level extends JComponent implements Runnable {
   
   /** allows for 50 ms delay between key presses */
   protected static final long THRESHOLD = 50_000_000L;
   /** image of the community guidelines for online conduct */
   protected BufferedImage communityGuidelines;
   /** keeps track of last time a key has been pressed */
   protected long lastPress;
   /** the user's username */
   protected String username = ""; 
   /** the color of the background */
   protected Color bg;
   /** whether the level has been completed or not */
   protected boolean finished;
   
   /**
    * Constructor of the class so that Level1 and Level2 can use super()
    * and extend this superclass and inherit the variables and methods
    * @param username The user's username from level 1
    * @param bg The color of the background
    */
   public Level(String username, Color bg) {
      // initializing instance variables
      this.username = username;
      this.bg = bg;
      try {
         // importing images 
    	   ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
         communityGuidelines = ImageIO.read(classLoader.getResourceAsStream("images/communityGuidelines.png"));
      }
      catch (IOException ioe) {  
         System.out.println("IOException Occurred. File(s) may be missing.");
      }
      finished = false; // level has not been finished at the time of initialization
   }
   
   /**
    * Utility method to display the instructions of the level
    * @param g An object which is a painting tool
    */
   abstract void displayInstructions(Graphics g);
   
   /**
    * Utility method to display the background of the level
    * @param g An object which is a painting tool
    */
   abstract void displayBackground(Graphics g);
   
   /**
    * Utility method to display the messages in the level
    * @param g An object which is a painting tool
    */
   abstract void displayMessages(Graphics g);
   
   /**
    * Utility method to display the community guidelines in the scenarios for the game
    * @param g An object which is a painting tool
    */
   public void displayCommunityGuidelines(Graphics g) {
      // background
      g.setColor(bg);
      g.fillRect(0, 0, 810, 950);
   
      // title         
      g.setColor(new Color(255, 209, 235));
      g.fillRect(30, 30, 737, 160);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 70));
      g.drawString("Community Guidelines", 55, 138);
      
      // text
      g.drawImage(communityGuidelines, 50, 205, this);
      
      // instructions to continue
      g.setColor(new Color(255, 209, 235));
      g.fillRect(50, 800, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 92, 865); 
   }
   
   
}