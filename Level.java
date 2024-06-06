/**
 * This abstract class is used as a template for our Level1 and Level2 classes
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created as a template for the levels in our game
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 1 hour
 * Class modified so that it is fully commented and functional
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.1
 * 
 * Chat-Mod AI Inc.
 * June 5th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

abstract class Level extends JComponent {
   
   /*
    * protected String username  - the user's username
    * protected Color bg         - the color of the background
    * protected boolean finished - whether the level has been completed or not
    */
   protected String username = ""; 
   protected Color bg;
   protected boolean finished;
   
   /**
    * Constructor of the class so that Level1 and Level2 can use super()
    * and extend this superclass and inherit the variables and methods
    * @param String username The user's username from level 1
    * @param Color bg The color of the background
    */
   public Level(String username, Color bg) {
      this.username = username;
      this.bg = bg;
      finished = false; // level has not been finished at the time of initialization
   }
   
   /**
    * Utility method to display the background of the level
    * @param Graphics g An object which is a painting tool
    */
   abstract void displayBackground(Graphics g);
   
   /**
    * Utility method to display the messages in the level
    * @param Graphics g An object which is a painting tool
    */
   abstract void displayMessages(Graphics g);
   
   /**
    * Utility method to display the transition between scenarios in the level
    * @param Graphics g An object which is a painting tool
    */
   abstract void displayTransition(Graphics g);
   
   /**
    * This method allows the Main class to access whether the user is done the level or not
    * @return Whether the user has finished the level
    */
   public boolean getFinished() {
      return finished;
   }
}