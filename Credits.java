/**
 * This class is used as the credits screen for our game. 
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created which runs the credits screen of our game. Keyboard input
 * is used to detect whether the user wishes to exit the game yet .
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: < 1 hour
 * Comments modified.
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
 * Time Spent: 1 hour
 * Added an animation to end the credits screen.
 * </p>
 *
 * <p>
 * Version 1.4
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * @author Tsz Fei Wang
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

public class Credits extends JComponent implements Runnable {

   /**
    * private BufferedImage logo  - image of the logo
    * private boolean selected    - whether the user has chosen to exit yet
    * private boolean finished    - whether the animation has finished
    * private Color bg            - the color of the background
    * private Color bg2           - the color of the background of the boxes that hold text
    * private int counter         - counter variable for animation
    */
   private BufferedImage logo;
   private boolean selected;
   private boolean finished;
   private Color bg;
   private Color bg2;
   private int counter;

   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Credits() {
      this.addKeyListener(new KeyHandler()); // adding KeyListener
      
      try {
         // importing logo image
         logo = ImageIO.read(new File("logo.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      
      // initializing other instance variables
      selected = false; // chosen to exit or not
      bg = new Color(224, 240, 244);
      bg2 = new Color(245, 228, 255);
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
         
         if (key == KeyEvent.VK_ENTER) // pressed enter: user has chosen to exit the game entirely
            selected = true;
         
         Credits.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      // draws credits screen
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
      g.setColor(bg2);
      g.fillRect(30, 30, 735, 455);
      g.fillRect(30, 505, 735, 445);
      
      g.drawImage(logo, 245, 580, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 120));
      g.drawString("Credits", 220, 150);
      
      g.setFont(new Font("Calibri", Font.BOLD, 54));
      g.drawString("Game made by:", 220, 260);
      g.drawString("Eric Ning and Tsz Fei Wang", 100, 320);
      
      g.setFont(new Font("Calibri", Font.BOLD, 36));
      g.drawString("Thanks for playing CMOD Socializer!", 135, 560);
      g.drawString("Chat-Mod AI Inc.", 280, 930);
      
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Press ‘Enter’ to Exit.", 135, 400);
      
      if (selected && counter <= 398) { // if the user has chosen to exit and animation is ongoing
         // adds thicker and thicker black borders until the screen is completely black
         g.setColor(Color.black);
         g.fillRect(0, 0, counter, 980);
         g.fillRect(0, 0, 795, counter);
         g.fillRect(795-counter, 0, counter, 980);
         g.fillRect(0, 980-counter, 795, counter);
         counter++;
         try {Thread.sleep(20);} catch (InterruptedException ie) {}
         this.repaint();
      }
      else if (selected) { // animation finished 
         finished = true;
         g.setColor(Color.black);
         g.fillRect(0, 0, 810, 1020);
      }
   }
   
   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(500);
            if (finished) break; // until the credits screen is complete 
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}

