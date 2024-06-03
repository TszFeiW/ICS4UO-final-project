/**
 * This class is used to display Level 2 (Levelling Up Level) for our game.
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 2 hours
 * Class was created to play level 2 of the game.
 * Formatting of messages not perfect, transition screens not formatted properly,
 * no user input in the actual game portion yet
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.0
 * 
 * Chat-Mod AI Inc.
 * June 2nd, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Level2 extends JComponent {

   /**
    * private BufferedImage instructionsL2  - image containing the instructions for the level
    * private BufferedImage user            - image of the enter username scene
    * private BufferedImage level1text      - image of the "Level 1" text header
    * private BufferedImage computer        - image of the computer zoomed in
    * private BufferedImage computerPeople  - image of people inside the computer screen
    * private BufferedImage transition      - image of the transition screen between two blocks of messages
    * private String username               - the user's username
    * private Color bg                      - the color of the background
    * private boolean finished              - whether or not the level is complete
    * private int currScene                 - the current scene in the level being displayed
    * private int counter                   - counter variable to deal with animation
    * private int numDisplayed              - keeps track of number of messages being displayed
    * private char ch                       - stores the user's input
    * private String[] messageTextDisplayed - text in the currently displayed messages
    * private int[] messageUserDisplayed    - the user that sent each message in currently displayed messages
    * private String[] messageText          - contains the text of all the messages
    * private int[] messageUser             - contains the corresponding user of all the messages
    */
   private BufferedImage instructionsL2;
   private BufferedImage user; 
   private BufferedImage level1text;
   private BufferedImage computer;
   private BufferedImage computerPeople;
   private BufferedImage transition;
   private String username = ""; 
   private Color bg; 
   private boolean finished = false;
   private int currScene;
   private int counter;
   private int numDisplayed;
   private char ch = '\\';
   private String[] messageTextDisplayed;
   private int[] messageUserDisplayed;
   private String[] messageText;
   private int[] messageUser;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Level2(String username) {
      this.addKeyListener(new KeyHandler());
      try {
         instructionsL2 = ImageIO.read(new File("instructionsL2.png"));
         level1text = ImageIO.read(new File("level1text.png"));
         computer = ImageIO.read(new File("computer.png"));
         computerPeople = ImageIO.read(new File("computerPeople.png"));
         transition = ImageIO.read(new File("transition.png"));
         bg = new Color(245,228,255);
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new String[47];
         messageUser = new int[47];
         currScene = 1;
         this.username = username;
         
         // reading the information into the arrays inside the pre-created level2.txt file
         BufferedReader br = new BufferedReader(new FileReader("level2.txt"));
         for (int i = 0; i < 47; i++) { // assumes that file is functional
            String line = br.readLine();
            if (line == null) break;
            else if (line.equals("/")) {
               messageText[i] = "";
               messageUser[i] = -10;
            }
            else if (line.equals(".")) {
               messageText[i] = "";
               messageUser[i] = -1;
            }
            else {
               messageText[i] = line.substring(0, line.indexOf('|'));
               messageUser[i] = line.charAt(line.length()-1) - '0';
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
         Level2.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (currScene == 1) { // instructions
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1080);
         g.drawImage(instructionsL2, -10, -50, this);
         if (ch == '\n') {
            ch = '\\';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) { // no user input required, basic animation
         if (counter < 255) { // screen fades to black
            g.setColor(bg);
            g.fillRect(0, 0, 810, 1080);
            g.drawImage(instructionsL2, -10, -50, this);
            g.setColor(new Color(0, 0, 0, counter));
            g.fillRect(0, 0, 810, 1080);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 510) { // screen fades back in, game appears
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 1080);
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
            
            g.setColor(new Color(0, 0, 0, 510-counter));
            g.fillRect(0, 0, 810, 1080);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else { // goes to next scene of this level
            try {Thread.sleep(100);} catch (InterruptedException ie) {}
            currScene++;
            counter = -1;
            ch = '\\';
            this.repaint();
         }
      }
      else { // actual messages being displayed in this scene of the level
         if (counter < 47) { // messages appear here
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 1080);
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
            
            try {Thread.sleep(50);} catch (InterruptedException ie) {}
            if (ch == '\n') { // next message displays
               ch = '\\';
               counter++;
               if (counter == 47) {
                  this.repaint();
                  return;
               }
            } 
            else { // display same messages
               displayMessages(g);
               return;
            }
            
            int nextMessage = counter; // index of next message in array
            if (messageUser[nextMessage] == -1) {
               g.drawImage(transition, -10, -20, this);
               // press enter to continue message
               g.setColor(new Color(254, 189, 225));
               g.fillRect(50, 880, 700, 90);
               g.setColor(Color.black);
               g.setFont(new Font("Calibri", Font.BOLD, 64));     
               g.drawString("Press Enter to Continue", 95, 945); 
               numDisplayed = 0;
            }
            else if (messageUser[nextMessage] == -10) {
            
            }
            else if (numDisplayed == 4) { // displays all 4 messages
               for (int i = 0; i < 3; i++) {
                  messageTextDisplayed[i] = messageTextDisplayed[i+1];
                  messageUserDisplayed[i] = messageUserDisplayed[i+1];
               }
               messageTextDisplayed[3] = messageText[nextMessage];
               messageUserDisplayed[3] = messageUser[nextMessage];
            }
            else {
               messageTextDisplayed[numDisplayed] = messageText[nextMessage];
               messageUserDisplayed[numDisplayed] = messageUser[nextMessage];
               numDisplayed++;
            }
            displayMessages(g);
         }
         else { // level 2 is complete
            finished = true;
         }
      }
   }
   
   /**
    * Utility method to display the messages in the level
    * @param Graphics g An object which is a painting tool
    */
   public void displayMessages(Graphics g) {
      Font calibri = new Font("Calibri", Font.BOLD, 20);
      g.setFont(calibri);
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
         else if (messageUserDisplayed[i] == 3) {
            g.setColor(new Color(254, 189, 225));
            g.fillRect(245, 260+i*100, 300, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 290, 290+i*100);
         }
         else {
            g.setColor(new Color(103, 157, 255));
            g.fillRect(245, 260+i*100, 300, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 290, 290+i*100);
         }
      }
   }
   
   /**
    * This method allows the Main class to access whether the user is done reading or not
    * @return Whether the user has finished reading all instructions
    */
   public boolean getFinished() {
      return finished;
   }
}