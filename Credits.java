/**
 * This class is used as the credits screen for our game. 
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created which runs the credits screen of our game. Keyboard input
 * is used to detect whether the user wishes to exit the game yet 
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: < 1 hour
 * Comments modified
 * </p>
 * 
 * @author Tsz Fei Wang
 * @version 1.1
 * 
 * Chat-Mod AI Inc.
 * May 31st, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Credits extends JComponent {

   /**
    * private BufferedImage logo  - image of the logo
    * private boolean selected    - whether the user has chosen to exit yet
    * private Color bg            - the color of the background
    * private Color bg2           - the color of the background of the boxes that hold text
    */
   private BufferedImage logo;
   private boolean selected;
   private Color bg;
   private Color bg2;

   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Credits() {
      this.addKeyListener(new KeyHandler());
      try {
         logo = ImageIO.read(new File("logo.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      selected = false; // chosen to exit or not
      bg = new Color(224, 240, 244);
      bg2 = new Color(245, 228, 255);
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
         
         if (key == KeyEvent.VK_ENTER)
            selected = true;
         
         Credits.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      
      g.setColor(bg2);
      g.fillRect(30, 30, 735, 455);
      g.fillRect(30, 505, 735, 445);
      
      g.drawImage(logo, 245, 580, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 120));
      g.drawString("Credits", 220, 150);
      
      g.setFont(new Font("Calibri", Font.BOLD, 54));
      g.drawString("Game made by:", 220, 280);
      g.drawString("Eric Ning and Tsz Fei Wang", 100, 360);
      
      g.setFont(new Font("Calibri", Font.BOLD, 36));
      g.drawString("Thanks for playing CMOD Socializer!", 135, 560);
      g.drawString("Chat-Mod AI Inc.", 280, 930);
      
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Press ‘Enter’ to Exit.", 135, 1020);
   }
   
   /**
    * This method allows the Main class to see if the user has chosen to exit
    * @return Whether is user chose to exit    
    */
   public boolean getSelected() {
      return selected;
   }
}

