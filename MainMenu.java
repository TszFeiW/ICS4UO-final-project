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
 * Time Spent: < 1 hour
 * Comments modified
 * </p>
 * 
 * <p>
 * Version 1.2
 * Time Spent: < 1 hour
 * Coordinates of some drawings adjusted so that it fits on school monitor
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.2
 * 
 * Chat-Mod AI Inc.
 * June 3rd, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class MainMenu extends JComponent {
   /**
    * private BufferedImage instructions         - image of the non-selected instructions button
    * private BufferedImage instructions2        - image of the selected instructions button
    * private BufferedImage firstLevel           - image of the non-selected level 1 button
    * private BufferedImage firstLevel2          - image of the selected level 1 button
    * private BufferedImage secondLevel          - image of the non-selected non-unlocked level 2 button
    * private BufferedImage secondLevel2         - image of the selected non-unlocked level 2 button
    * private BufferedImage secondLevelUnlocked  - image of the non-selected unlocked level 2 button
    * private BufferedImage secondLevelUnlocked2 - image of the selected unlocked level 2 button
    * private BufferedImage quit                 - image of the non-selected quit game button
    * private BufferedImage quit2                - image of the selected quit game button
    * private int selected                       - the current button that is selected (numbered 0 to 3)
    * private int choice                         - the selected button
    * private Color bg                           - the color of the background
    * private boolean allowed                    - whether the user can choose level 2 or not
    * private boolean warning                    - whether the user chose a non-unlocked level 2
    */
   private BufferedImage instructions;
   private BufferedImage instructions2;
   private BufferedImage firstLevel;
   private BufferedImage firstLevel2;
   private BufferedImage secondLevel;
   private BufferedImage secondLevel2;
   private BufferedImage secondLevelUnlocked;
   private BufferedImage secondLevelUnlocked2;
   private BufferedImage quit;
   private BufferedImage quit2;
   private int selected = 0;
   private int choice = -1;
   private Color bg;
   private boolean allowed;
   private boolean warning;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    * @param boolean level2 Whether the second level can currently be chosen or not
    */
   public MainMenu(boolean level2) {
      this.addKeyListener(new KeyHandler());
      try {
         instructions = ImageIO.read(new File("instructionsButton.png"));
         instructions2 = ImageIO.read(new File("instructionsButton2.png"));
         firstLevel = ImageIO.read(new File("level1Button.png"));
         firstLevel2 = ImageIO.read(new File("level1Button2.png"));
         secondLevel = ImageIO.read(new File("level2Button.png"));
         secondLevel2 = ImageIO.read(new File("level2Button2.png"));
         secondLevelUnlocked = ImageIO.read(new File("level2unlocked.png"));
         secondLevelUnlocked2 = ImageIO.read(new File("level2unlocked2.png"));
         quit = ImageIO.read(new File("quitGameButton.png"));
         quit2 = ImageIO.read(new File("quitGameButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
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
       * @param KeyEvent e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_DOWN)
            selected = Math.min(selected+1, 3);
         else if (key == KeyEvent.VK_UP)
            selected = Math.max(selected-1, 0);
         else if (key == KeyEvent.VK_ENTER && selected == 2 && !allowed)
            warning = true;
         else if (key == KeyEvent.VK_ENTER && (selected != 2 || allowed))
            choice = selected;
         
         MainMenu.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 100));
      g.drawString("CMOD Socializer", 65, 150);
      
      g.drawImage(instructions, 205, 250, this);
      g.drawImage(firstLevel, 205, 400, this);
      g.drawImage(allowed ? secondLevelUnlocked : secondLevel, 205, 550, this);
      g.drawImage(quit, 205, 700, this);
      
      if (selected == 0)
         g.drawImage(instructions2, 205, 250, this);
      else if (selected == 1)
         g.drawImage(firstLevel2, 205, 400, this);
      else if (selected == 2)
         g.drawImage(allowed ? secondLevelUnlocked2 : secondLevel2, 205, 550, this);
      else 
         g.drawImage(quit2, 205, 700, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 860);
      g.drawString("‘Enter’ to Continue.", 135, 930);
      
      if (warning) {
         g.setFont(new Font("Calibri", Font.BOLD, 48));
         g.setColor(new Color(162, 210, 255));
         g.fillRect(50, 480, 710, 120);
         g.setColor(Color.black);
         g.drawString("Cannot Choose Level 2 Currently", 85, 550);
      }
      
      warning = false;
   }
   
   /**
    * This method allows the Main class to access the choice that the user made 
    * @return The choice that the user made in the menu
    */
   public int getChoice() {
      return choice;
   }
}