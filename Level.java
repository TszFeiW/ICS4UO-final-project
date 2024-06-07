/**
 * This abstract class is used as a template for our Level1 and Level2 classes.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 1 hour
 * Class was created as a template for the levels in our game.
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 1 hour
 * Class modified so that it is fully commented and functional.
 * </p>
 * 
 * <p>
 * Version 1.2
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.2
 * 
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

abstract class Level extends JComponent implements Runnable {
   
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
      // initializing instance variables
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
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(500);
            if (finished) break; // until the level is complete
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}