/**
 * This class is used as the instructions screen for our game.
 * @version 1.1
 * May 24th, 2024
 * Time Spent: 1 hour
 * @author Tsz Fei Wang
 *
 * Modifications: Location of images changed slightly
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
    * private BufferedImage generalInstructions - image of the general instructions for the game
    * private BufferedImage instructionsL1      - image of the instructions for level 1
    * private BufferedImage instructionsL2      - image of the instructions for level 2
    * private Color bg                          - the color of the background
    * private int choice                        - the selected 
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
      this.addKeyListener(new KeyHandler());
      try {
         generalInstructions = ImageIO.read(new File("generalInstructions.png"));
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
         if (currScreen == 3) finished = true;
            
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
      
      if(currScreen == 0) g.drawImage(generalInstructions, -10, 0, this);
      else if(currScreen == 1) g.drawImage(instructionsL1, -10, -50, this);
      else if(currScreen == 2) g.drawImage(instructionsL2, -10, -50, this);
   }
   
   /**
    * This method allows the Main class to access whether the user is done reading or not
    * @return Whether the user has finished reading all instructions
    */
   public boolean getFinished() {
      return finished;
   }
   
   /**
    * This method allows the Main class to access the current page of the instructions 
    * @return The choice that the user made in the menu
    */
   public int getCurrScreen() {
      return currScreen;
   }

}