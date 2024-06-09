import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * This class is used as the instructions screen for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created which runs the instructions of our game. Keyboard input is used
 * to detect whether the user wishes to continue or wishes to leave the game. 
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 10 minutes
 * Location of images changed slightly, Comments modified.
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
 * <p>
 * Version 1.6
 * Time Spent: 30 minutes
 * Changing the graphics of the instructions
 * </p>
 *
 * @author Tsz Fei Wang, Eric Ning
 * @version 1.6
 * 
 * Chat-Mod AI Inc.
 * June 9th, 2024
 */
public class Instructions extends JComponent implements Runnable {

   /** allows for 200 ms delay between key presses */
   private static final long THRESHOLD = 200_000_000L;
   /** keeps track of last time a key has been pressed */
   private long lastPress;
   /** image of the general instructions for the game */
   private BufferedImage generalInstructions;
   /** image of the instructions for level 1 */
   private BufferedImage instructionsL1;
   /** image of the instructions for level 2 */
   private BufferedImage instructionsL2;
   /** the color of the background */
   private Color bg;
   /** the color of the background of the title */
   private Color bg2;
   /** whether this scene of the game is finished */
   private boolean finished;
   /** which instructions panel it is on */
   private int currScreen;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Instructions() {
      this.addKeyListener(new KeyHandler()); // adding KeyListener
      
      try {
         // importing images
         generalInstructions = ImageIO.read(new File("generalInstructions.png"));
         instructionsL1 = ImageIO.read(new File("instructionsL1.png"));
         instructionsL2 = ImageIO.read(new File("instructionsL2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      
      // initializing other instance variables
      bg = new Color(245, 228, 255);
      bg2 = new Color(255, 209, 235);
   }
   
   /**
    * This private class extends KeyAdapter so the drawing can detect key inputs
    */
   private class KeyHandler extends KeyAdapter {
      /**
       * This method allows for user key input to be detected
       * @param e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         long current = System.nanoTime();
         
         if (lastPress <= 0L || current - lastPress >= THRESHOLD) {
         
            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_ENTER) currScreen++; // pressed enter to next screen
            
            lastPress = current;
            Instructions.this.repaint();
         }
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
      if(currScreen == 0) {
         // title         
         g.setColor(bg2);
         g.fillRect(30, 30, 737, 160);
         
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 80));
         g.drawString("General Instructions", 57, 138);
         
         // text
         g.drawImage(generalInstructions, 80, 215, this);
         
         // instructions to continue
         g.setColor(bg2);
         g.fillRect(50, 840, 700, 90);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 64));     
         g.drawString("Press Enter to Continue", 92, 905); 
      }
      else if(currScreen == 1) {
         // title         
         g.setColor(bg2);
         g.fillRect(30, 30, 737, 160);
         
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 80));
         g.drawString("Level 1", 280, 138);
         
         // text
         g.drawImage(instructionsL1, 17, 205, this);
         
         // instructions to continue
         g.setColor(bg2);
         g.fillRect(50, 840, 700, 90);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 64));     
         g.drawString("Press Enter to Continue", 92, 905); 
      }
      else if(currScreen == 2) {
         // title         
         g.setColor(bg2);
         g.fillRect(30, 30, 737, 160);
         
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 80));
         g.drawString("Level 2", 280, 138);
         
         // text
         g.drawImage(instructionsL2, 8, 205, this);
         
         // instructions to continue
         g.setColor(bg2);
         g.fillRect(50, 840, 700, 90);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 64));     
         g.drawString("Press Enter to Continue", 92, 905); 
      }
   }

   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(200);
            if (currScreen == 3) break; // until the game has passed the last instructions screen
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}