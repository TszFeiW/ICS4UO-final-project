/**
 * This class is used to display Level 1 (Deficiencies level) for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 8 hours
 * Class was created to play level 1 of the game.
 * Formatting of messages not perfect, along with transition screen missing.
 * </p>
 *
 * <p>
 * Version 1.1
 * Time Spent: 2 hours
 * Class was modified so format of messages work.
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
 * Changing Level1 to extend an abstract class Level as well as removing.
 * JOptionPane import since it is not being used anymore.
 * </p>
 *
 * <p>
 * Version 1.4
 * Time Spent: 1 hour
 * Fully commenting this class and changing coordinates of some
 * drawings adjusted again so it doesn't go out of the screen.
 * </p>
 *
 * <p>
 * Version 1.5
 * Time Spent: < 1 hour
 * Modifying class to use an ArrayList to avoid certain issues.
 * </p>
 *
 * <p>
 * Version 1.6
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Comments modified.
 * </p>
 *
 * @author Tsz Fei Wang, Eric Ning
 * @version 1.6
 * 
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Level1 extends Level {

   /**
    * private BufferedImage instructionsL1      - image containing the instructions for the level
    * private BufferedImage communityGuidelines - image of the community guidelines for online conduct
    * private BufferedImage user                - image of the enter username scene
    * private BufferedImage stool               - image of the person's stool in front of the desk
    * private BufferedImage level1text          - image of the "Level 1" text header
    * private BufferedImage desk                - image of the person's computer desk
    * private BufferedImage person              - image of the person 
    * private BufferedImage personComputer      - image of person sitting at computer desk
    * private BufferedImage computer            - image of the computer zoomed in
    * private BufferedImage computerPeople      - image of people inside the computer screen
    * private BufferedImage transition          - image of the transition screen between two blocks of messages
    * private boolean finished                  - whether or not the level is complete
    * private int currScene                     - the current scene in the level being displayed
    * private int counter                       - counter variable to deal with animation
    * private int numDisplayed                  - keeps track of number of messages being displayed
    * private char ch                           - stores the user's input
    * private String[] messageTextDisplayed     - text in the currently displayed messages
    * private int[] messageUserDisplayed        - the user that sent each message in currently displayed messages
    * private ArrayList<String> messageText     - contains the text of all the messages
    * private ArrayList<Integer> messageUser    - contains the corresponding user of all the messages
    */
   private BufferedImage instructionsL1;
   private BufferedImage communityGuidelines;
   private BufferedImage user; 
   private BufferedImage stool;
   private BufferedImage level1text;
   private BufferedImage desk;
   private BufferedImage person;
   private BufferedImage personComputer;
   private BufferedImage computer;
   private BufferedImage computerPeople;
   private BufferedImage transition;
   private boolean finished;
   private int currScene;
   private int counter;
   private int numDisplayed;
   private char ch;
   private String[] messageTextDisplayed;
   private int[] messageUserDisplayed;
   private ArrayList<String> messageText;
   private ArrayList<Integer> messageUser;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Level1() {
      super("", new Color(245,228,255)); // calls the constructor of the Level class
      this.addKeyListener(new KeyHandler()); // adds the Key Listener
      
      try {
         // importing images 
         instructionsL1 = ImageIO.read(new File("instructionsL1.png"));
         communityGuidelines = ImageIO.read(new File("community guidelines.png"));
         user = ImageIO.read(new File("enter username.jpg"));
         stool = ImageIO.read(new File("stool.png"));
         level1text = ImageIO.read(new File("level1text.png"));
         desk = ImageIO.read(new File("desk.png"));
         person = ImageIO.read(new File("person.png"));
         personComputer = ImageIO.read(new File("personComputer.png"));
         computer = ImageIO.read(new File("computer.png"));
         computerPeople = ImageIO.read(new File("computerPeople.png"));
         transition = ImageIO.read(new File("transition.png"));
         
         // initializing other instance variables
         ch = '\u0000';
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new ArrayList<String>();
         messageUser = new ArrayList<Integer>();
         
         // reading the information into the arrays inside the pre-created level1.txt file
         BufferedReader br = new BufferedReader(new FileReader("level1.txt"));
         while (true) {
            String line = br.readLine();
            if (line == null) break; // end of file
            else if (line.equals(".")) { // transition between scenarios
               messageText.add("");
               messageUser.add(-1);
            }
            else { // normal message
               messageText.add(line.substring(0, line.indexOf('|'))); // text portion of the data
               messageUser.add(line.charAt(line.length()-1) - '0'); // message user portion of the data
            }
         }
      }
      catch (IOException ioe) {  
         System.out.println("IOException Occurred. File(s) may be missing.");
      }
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
         ch = e.getKeyChar();
         Level1.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (currScene == 0) { // taking username
         // draws background
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
         g.drawImage(user, 0, -100, this);
         
         try {Thread.sleep(50);} catch (InterruptedException ie) {} // adds delay so less spam can be done
         char c = ch;
         
         if (c == '\u0000') return; // nothing happens
         else if (c == '\n' && username.length() > 1) { // username entered
            currScene = 1; // next scene in level
            ch = '\u0000'; // resets the character to avoid one key endlessly being added
            this.repaint();
         }
         else if (c == 8) { // backspace pressed
            if (username.length() != 0) { 
               username = username.substring(0, username.length() - 1); // deletes the rightmost character
               g.setColor(Color.black);
               g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
               g.drawString(username, 110, 600); // draws the current username on screen
               // resets the character to avoid one key endlessly being added
               ch = '\u0000';
            }
         }
         else if (!Character.isLetterOrDigit(c) && c != '\n') { // not alphanumerical
            displayWarning("Please use alphanumerical characters.", g);
         }
         else if (c == '\n' && username.length() < 2) { // too short
            displayWarning("This username you chose is too short.", g);
         }
         else if(username.length() >= 20) { // too long
            displayWarning("You cannot exceed twenty characters.", g);
         }
         else { // otherwise it is valid, so add to username
            username += c;
            
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));             
            g.drawString(username, 110, 600); // draws the current username on screen
            
            // resets the character to avoid one key endlessly being added
            ch = '\u0000';
         }
      }
      else if (currScene == 1) { // instructions
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
         g.drawImage(instructionsL1, -10, -70, this);
         if (ch == '\n') { // user chooses to continue
            ch = '\u0000';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) { // community guidelines
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1020);
         g.drawImage(communityGuidelines, -10, -40, this);
         if (ch == '\n') { // user chooses to continue
            ch = '\u0000';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 3) { // no user input required, basic animation
         if (counter < 100) { // person moving left
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1020);
            g.drawImage(level1text, 100, 150, this);
            g.drawImage(desk, 30, 350, this);
            g.drawImage(stool, 135, 680, this);
            g.drawImage(person, 650-counter, 550, this);
            g.setColor(Color.white);
            g.fillRect(842-counter, 550, 1, 400);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 355) { // screen fades to black
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1020);
            g.drawImage(level1text, 100, 150, this);
            g.drawImage(desk, 30, 350, this);
            g.drawImage(stool, 135, 680, this);
            g.drawImage(person, 550, 550, this);
            g.setColor(new Color(0, 0, 0, counter-100));
            g.fillRect(0, 0, 810, 1020);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 610) { // screen fades back in, person sitting at computer
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1020);
            g.drawImage(personComputer, 10, 320, this);
            g.setColor(new Color(0, 0, 0, 610-counter));
            g.fillRect(0, 0, 810, 1020);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else { // goes to next scene of this level
            try {Thread.sleep(100);} catch (InterruptedException ie) {}
            currScene++;
            counter = 0;
            ch = '\u0000';
            this.repaint();
         }
      }
      else { // actual messages being displayed in this scene of the level
         if (counter < 125) { // text box fading in
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 1020);
            g.drawImage(computer, 0, 220, this);
            g.setColor(new Color(150, 75, 0));
            g.fillRect(0, 860, 810, 220);
            g.setColor(new Color(224, 240, 244));
            g.fillRect(20, 240, 750, 420);
            g.drawImage(computerPeople, 23, 243, this);
            g.setColor(new Color(254, 189, 225, counter*2));
            g.fillRect(225, 375, 300, 100);
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));     
            try {Thread.sleep(50);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 199) { // username being displayed
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 1020);
            g.drawImage(computer, 0, 220, this);
            g.setColor(new Color(150, 75, 0));
            g.fillRect(0, 860, 810, 220);
            g.drawImage(computerPeople, 23, 243, this);
            g.setColor(new Color(254, 189, 225));
            g.fillRect(225, 375, 300, 100);
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));     
            g.drawString("Hello! My name is", 240, 415);        
            g.drawString(username, 240, 450);
            g.setColor(new Color(162, 210, 255, (counter-100)*2));
            g.fillRect(20, 240, 750, 420);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            ch = '\u0000';
            this.repaint();
         }
         else if (counter < 227) { // messages appear here 
            displayBackground(g);
            
            try {Thread.sleep(50);} catch (InterruptedException ie) {}
            if (ch == '\n') { // next message displays
               ch = '\u0000';
               counter++;
               if (counter == 227) { // end of messages
                  this.repaint();
                  return;
               }
            } 
            else { // display same messages
               displayMessages(g);
               return;
            }
            
            int nextMessage = counter-200; // index of next message in array
            if (messageUser.get(nextMessage) == -1) { // between scenarios show transition
               displayTransition(g);
            }
            else if (numDisplayed == 4) { // displays all 4 messages
               // gets rid of oldest message and shifts everything down and replaces it
               for (int i = 0; i < 3; i++) {
                  messageTextDisplayed[i] = messageTextDisplayed[i+1];
                  messageUserDisplayed[i] = messageUserDisplayed[i+1];
               }
               messageTextDisplayed[3] = messageText.get(nextMessage);
               messageUserDisplayed[3] = messageUser.get(nextMessage);
            }
            else {
               // adds a new message to the screen
               messageTextDisplayed[numDisplayed] = messageText.get(nextMessage);
               messageUserDisplayed[numDisplayed] = messageUser.get(nextMessage);
               numDisplayed++;
            }
            displayMessages(g); // displays the messages onto the screen
         }
         else { // level 1 is complete
            finished = true;
         }
      }
   }
   
   /**
    * Utility method to display warning messages (reuses code)
    * @param String message The warning message
    * @param Graphics g An object which is a painting tool
    */
   public void displayWarning(String message, Graphics g) {
      g.setFont(new Font("Calibri", Font.BOLD, 40));
      g.setColor(new Color(162, 210, 255));
      g.fillRect(50, 80, 710, 120);
      g.setColor(Color.black);
      g.drawString(message, 85, 150);
      g.setColor(Color.black);
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));             
      g.drawString(username, 110, 600);
   }
   
   /**
    * Utility method to display the background of the level
    * @param Graphics g An object which is a painting tool
    */
   public void displayBackground(Graphics g) {
      g.setColor(new Color(224, 240, 244));
      g.fillRect(0, 0, 810, 1020);
      g.drawImage(computer, 0, 220, this);
      g.setColor(new Color(150, 75, 0));
      g.fillRect(0, 860, 810, 220);
      g.drawImage(computerPeople, 23, 243, this);
      g.setColor(new Color(254, 189, 225));
      g.fillRect(225, 375, 300, 100);
      g.setColor(Color.black);
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));     
      g.drawString("Hello! My name is", 240, 415);        
      g.drawString(username, 240, 450);
      g.setColor(new Color(162, 210, 255, 200));
      g.fillRect(20, 240, 750, 420);
      
      // press enter to continue message
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 880, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 945);
   }
   
   /**
    * Utility method to display the messages in the level
    * @param Graphics g An object which is a painting tool
    */
   public void displayMessages(Graphics g) {
      g.setFont(new Font("Calibri", Font.BOLD, 20));
      for (int i = 0; i < numDisplayed; i++) {
         if (messageUserDisplayed[i] == 0) {
            g.setColor(Color.red);
            g.fillRect(45, 260+i*100, 700, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 55, 290+i*100);
         }
         else if (messageUserDisplayed[i] == 1) {
            g.setColor(new Color(254, 189, 225));
            g.fillRect(365, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 375, 290+i*100);
         }
         else if (messageUserDisplayed[i] == 2) {
            g.setColor(new Color(103, 157, 255));
            g.fillRect(30, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 290+i*100);
         }
         else {
            g.setColor(new Color(126, 217, 87));
            g.fillRect(30, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 290+i*100);
         }
      }
   }
   
   /**
    * Utility method to display the transition between scenarios in the level
    * @param Graphics g An object which is a painting tool
    */
   public void displayTransition(Graphics g) {
      g.drawImage(transition, 55, 180, this);
      // press enter to continue message
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 880, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 945); 
      numDisplayed = 0;

   }
   
   /**
    * This method allows the Main class to access the user's username for level 2
    * @return The String containing the username
    */
   public String getUsername() {
      return username;
   }
}