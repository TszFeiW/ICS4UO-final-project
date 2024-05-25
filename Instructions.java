/**
 * This class is used as the splash screen for our game.
 * @version 1
 * May 24th, 2024
 * Time Spent: 1 hour
 * @author Tsz Fei Wang
 *
 * Modifications: Class was created which runs the instructions of our game. Keyboard input is used
 *                to detect whether the user wishes to continue or wishes to leave the game. 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Instructions extends JComponent {

   /**
    * private BufferedImage logo  - image of the logo
    * private instructionsL1      - image of the instructions for level 1
    * private instructionsL2      - image of the instructions for level 2
    * private Color bg            - the color of the background
    * private int choice          - the selected 
    * private int currScreen      - which instructions panel it is on 
    */
   private BufferedImage logo;
   private BufferedImage instructionsL1;
   private BufferedImage instructionsL2;
   private Color bg;
   private int currScreen;
   private int choice = 2; 
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Instructions() {
      this.addKeyListener(new KeyHandler());
      try {
         logo = ImageIO.read(new File("logo.png"));
         instructionsL1 = ImageIO.read(new File("instructionsL1.png"));
         instructionsL2 = ImageIO.read(new File("instructionsL2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      bg = new Color(245, 228, 255);
   }
   
   /**
    * This private class extends KeyAdapter so the drawing can detect key inputs
    */
   private class KeyHandler extends KeyAdapter {
      /**
       * This method allows the actual game to run (main method)   
       * @param KeyEvent e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_ENTER) currScreen++;
            
         Instructions.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      
      if(currScreen == 0) g.drawImage(instructionsL1, 0, 0, this);
      else if(currScreen == 1) { g.drawImage(instructionsL2, 0, 0, this); }
      else if(currScreen == 2) { }
   }
   
   /**
    * This method allows the Main class to access the choice that the user made 
    * @return The choice that the user made in the menu
    */
   public int getChoice() {
      return choice;
   }
   
   /**
    * This method allows the Main class to access the current page of the instructions 
    * @return The choice that the user made in the menu
    */
   public int getCurrScreen() {
      return currScreen;
   }

}

