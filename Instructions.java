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
 * Time Spent: < 1 hour
 * Location of images changed slightly, Comments modified.
 * </p>
 * 
 * <p>
 * Version 1.2
 * Time Spent: < 1 hour
 * Coordinates of some drawings adjusted so that it fits on school monitor.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: < 1 hour
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
 * @author Tsz Fei Wang, Eric Ning
 * @version 1.4
 * 
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Instructions extends JComponent implements Runnable {

   /**
    * private BufferedImage generalInstructions - image of the general instructions for the game
    * private BufferedImage instructionsL1      - image of the instructions for level 1
    * private BufferedImage instructionsL2      - image of the instructions for level 2
    * private Color bg                          - the color of the background
    * private boolean finished                  - whether this scene of the game is finished
    * private int currScreen                    - which instructions panel it is on 
    */
   private BufferedImage generalInstructions;
   private BufferedImage instructionsL1;
   private BufferedImage instructionsL2;
   private Color bg;
   private boolean finished;
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
      
      // initializing other instance variable
      bg = new Color(245, 228, 255);
   }
   
   /**
    * This private class extends KeyAdapter so the drawing can detect key inputs
    */
   private class KeyHandler extends KeyAdapter {
      /**
       * This method allows for user key input to be detected
       * @param KeyEvent e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_ENTER) currScreen++; // pressed enter to next screen
            
         Instructions.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
      if(currScreen == 0) g.drawImage(generalInstructions, -10, -20, this);
      else if(currScreen == 1) g.drawImage(instructionsL1, -10, -70, this);
      else if(currScreen == 2) g.drawImage(instructionsL2, -10, -70, this);
   }

   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(500);
            if (currScreen == 3) break; // until the game has passed the last instructions screen
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}