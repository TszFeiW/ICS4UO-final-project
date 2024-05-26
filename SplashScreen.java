/**
 * This class is used as the splash screen for our game.
 * @version 1.0
 * May 24th, 2024
 * Time Spent: 3 hours
 * @author Eric Ning, Tsz Fei Wang
 *
 * Modifications: Class was created which runs the splash screen of our game. Keyboard input is used
 *                to detect whether the user wishes to continue or wishes to leave the game. 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class SplashScreen extends JComponent {

   /**
    * private BufferedImage logo  - image of the logo
    * private BufferedImage play  - image of the non-selected play button
    * private BufferedImage play2 - image of the selected play button
    * private BufferedImage exit  - image of the non-selected exit button
    * private BufferedImage exit2 - image of the selected exit button
    * private int selected        - the current button that is selected (numbered 0 to 3)
    * private int choice          - the selected button
    * private Color bg            - the color of the background
    */
   private BufferedImage logo;
   private BufferedImage play;
   private BufferedImage play2;
   private BufferedImage exit;
   private BufferedImage exit2;
   private int selected;
   private int choice = -1;
   private Color bg;

   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public SplashScreen() {
      this.addKeyListener(new KeyHandler());
      try {
         logo = ImageIO.read(new File("logo.png"));
         play = ImageIO.read(new File("playButton.png"));
         play2 = ImageIO.read(new File("playButton2.png"));
         exit = ImageIO.read(new File("exitButton.png"));
         exit2 = ImageIO.read(new File("exitButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      selected = 0; // on the first button
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
         
         if (key == KeyEvent.VK_DOWN)
            selected = 1;
         else if (key == KeyEvent.VK_UP)
            selected = 0;
         else if (key == KeyEvent.VK_ENTER)
            choice = selected;
         
         SplashScreen.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);

      g.drawImage(logo, 245, 100, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 50));
      g.drawString("Chat-Mod AI Inc.", 235, 520);
      
      if (selected == 0) {
         g.drawImage(play2, 255, 600, this);
         g.drawImage(exit, 255, 720, this);
      }
      else {
         g.drawImage(play, 255, 600, this);
         g.drawImage(exit2, 255, 720, this);
      }
      
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 900);
      g.drawString("‘Enter’ to Continue.", 135, 970);
   }
   
   /**
    * This method allows the Main class to access the choice that the user made 
    * @return The choice that the user made in the menu
    */
   public int getChoice() {
      return choice;
   }
}

