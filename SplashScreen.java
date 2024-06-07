/**
 * This class is used as the Splash Screen class for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 3 hours
 * Class was created which runs the splash screen of our game. Keyboard input is used
 * to detect whether the user wishes to continue or wishes to leave the game.  
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 2 hours
 * Class was modified such that it is animated in the beginning.
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
 * @author Eric Ning, Tsz Fei Wang
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

public class SplashScreen extends JComponent implements Runnable {

   /**
    * private BufferedImage logo       - image of the logo
    * private BufferedImage logo2      - image of the logo without blue border
    * private BufferedImage logoLarge  - image of the logo but larger
    * private BufferedImage play       - image of the non-selected play button
    * private BufferedImage play2      - image of the selected play button
    * private BufferedImage exit       - image of the non-selected exit button
    * private BufferedImage exit2      - image of the selected exit button
    * private int selected             - the current button that is selected (numbered 0 to 3)
    * private int choice               - the selected button
    * private int time                 - timer variable to deal with animation
    * private Color bg                 - the color of the background
    */
   private BufferedImage logo;
   private BufferedImage logo2;
   private BufferedImage logoLarge;
   private BufferedImage play;
   private BufferedImage play2;
   private BufferedImage exit;
   private BufferedImage exit2;
   private int selected;
   private int choice;
   private int time;
   private Color bg;

   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public SplashScreen() {
      this.addKeyListener(new KeyHandler()); // adding KeyListener
      
      try {
         // importing images
         logo = ImageIO.read(new File("logo.png"));
         logo2 = ImageIO.read(new File("logo2.png"));
         logoLarge = ImageIO.read(new File("logoLarge.png"));
         play = ImageIO.read(new File("playButton.png"));
         play2 = ImageIO.read(new File("playButton2.png"));
         exit = ImageIO.read(new File("exitButton.png"));
         exit2 = ImageIO.read(new File("exitButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      
      choice = -1; // the user's choice
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
         if (time <= 718) return; // no input when animated part is not done
         
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_DOWN) // down arrow key
            selected = 1;
         else if (key == KeyEvent.VK_UP) // up arrow key
            selected = 0;
         else if (key == KeyEvent.VK_ENTER) // selected an option
            choice = selected;
         
         SplashScreen.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (time <= 360) { // starting animation with loading logo
         Graphics2D g2 = (Graphics2D) g; // need setStroke method only in Graphics2D
         g2.setColor(bg);
         g2.fillRect(0, 0, 810, 1020);
         g2.drawImage(logo2, 85, 50, this);
         
         g2.setStroke(new BasicStroke(25));
         g2.setColor(new Color(103, 157, 255));
         g2.drawArc(111, 73, 594, 594, time, 100);
      
         try { Thread.sleep(10); } catch (InterruptedException ie) {}
         time++;
         this.repaint();
      }
      else if (time <= 615) { // company name fading in
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
         g.drawImage(logoLarge, 85, 50, this);
         g.setColor(new Color(0, 0, 0, time-360));
         
         g.setFont(new Font("Calibri", Font.BOLD, 50));
         g.drawString("Chat-Mod AI Inc.", 235, 750);
         
         try { Thread.sleep(10); } catch (InterruptedException ie) {}
         time++;
         this.repaint();
      }
      else if (time <= 718) { // logo and company name moving downwards animation
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
         g.drawImage(logoLarge, 85, 50+10*(time-615), this);
         g.setColor(Color.black);
         
         g.setFont(new Font("Calibri", Font.BOLD, 50));
         g.drawString("Chat-Mod AI Inc.", 235, 750+10*(time-615));
         
         try { Thread.sleep(10); } catch (InterruptedException ie) {}
         time++;
         this.repaint();
      }
      else { // menu displayed
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
   
         g.drawImage(logo, 245, 100, this);
         
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 50));
         g.drawString("Chat-Mod AI Inc.", 235, 520);
         
         if (selected == 0) { // currently on the play button (changes colour)
            g.drawImage(play2, 255, 600, this);
            g.drawImage(exit, 255, 720, this);
         }
         else { // currently on the exit button (changes colour)
            g.drawImage(play, 255, 600, this);
            g.drawImage(exit2, 255, 720, this);
         }
         
         // instructions to continue
         g.setFont(new Font("Calibri", Font.BOLD, 64));
         g.drawString("Use Arrow Keys and press", 50, 860);
         g.drawString("‘Enter’ to Continue.", 135, 930);
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
            Thread.sleep(500);
            if (choice != -1) break; // until the user has made a choice
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}