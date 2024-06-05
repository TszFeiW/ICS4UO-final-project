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
 * <p>
 * Version 1.1
 * Time Spent: 4 hours
 * Class modified so that the formatting looks better and transition screens added
 * User input also added. However, need one more scenario + working functionality
 * + calculations of score + diversity of transition screens
 *
 * <p>
 * Version 1.2
 * Time Spent: 2 hours
 * Class modified so that there are more scenarios included, along with a tentative
 * scoring system and now works properly. Still need the end screen with the total
 * score along with a little better formatting of text
 *
 * <p>
 * Version 1.3
 * Time Spent: 1 hour
 * The end screen is mostly functional, along with scoring system working and text
 * format working. Still need to add a leaderboard option however. Currently
 * working on having an abstract class Level which is the superclass of Level2
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.3
 * 
 * Chat-Mod AI Inc.
 * June 5th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Level2 extends Level {

   /**
    * private BufferedImage instructionsL2  - image containing the instructions for the level
    * private BufferedImage user            - image of the enter username scene
    * private BufferedImage level2text      - image of the "Level 2" text header
    * private BufferedImage computer        - image of the computer zoomed in
    * private BufferedImage computerPeople  - image of people inside the computer screen
    * private BufferedImage transition2     - image of the transition screen between two blocks of messages
    * private String username               - the user's username
    * private Color bg                      - the color of the background
    * private boolean finished              - whether or not the level is complete
    * private int currScene                 - the current scene in the level being displayed
    * private int counter                   - counter variable to deal with animation
    * private int numDisplayed              - keeps track of number of messages being displayed
    * private int selected                  - the selected option out of the 4 choices in the game
    * private char ch                       - stores the user's input
    * private String[] messageTextDisplayed - text in the currently displayed messages
    * private int[] messageUserDisplayed    - the user that sent each message in currently displayed messages
    * private String[] messageText          - contains the text of all the messages
    * private int[] messageUser             - contains the corresponding user of all the messages
    */
   private BufferedImage instructionsL2;
   private BufferedImage user; 
   private BufferedImage level2text;
   private BufferedImage computer;
   private BufferedImage computerPeople;
   private BufferedImage transition2;
   private BufferedImage results; 
   private String username = ""; 
   private Color bg; 
   private boolean game;
   private boolean seeAllOptions;
   private boolean finished;
   private int currScene;
   private int counter;
   private int numDisplayed;
   private int selected;
   private int nextCounter;
   private int scenario;
   private int score;
   private int timer;
   private int totalTime;
   private int ch = '\\';
   private String[] messageTextDisplayed;
   private int[] messageUserDisplayed;
   private String[] messageText;
   private int[] messageUser;
   private String[][] nextMessage;
   private String[][] scores;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Level2(String username) {
      this.addKeyListener(new KeyHandler());
      try {
         instructionsL2 = ImageIO.read(new File("instructionsL2.png"));
         level2text = ImageIO.read(new File("level2text.png"));
         computer = ImageIO.read(new File("computer.png"));
         computerPeople = ImageIO.read(new File("computerPeople2.png"));
         transition2 = ImageIO.read(new File("transition2.png"));
         results = ImageIO.read(new File("level2Results.png")); 
         
         bg = new Color(245,228,255);
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new String[96];
         messageUser = new int[96];
         nextMessage = new String[5][4];
         scores = new String[5][4];
         this.username = username;
         
         // reading the information into the arrays inside the pre-created level2.txt file
         BufferedReader br = new BufferedReader(new FileReader("level2.txt"));
         for (int i = 0; i < 96; i++) { // assumes that file is functional
            String line = br.readLine();
            if (line == null) break;
            else if (line.charAt(0) == '/') {
               messageText[i] = line.substring(1);
               messageUser[i] = -10;
            }
            else if (line.equals("-")) {
               messageText[i] = "";
               messageUser[i] = -5;
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
         br = new BufferedReader(new FileReader("level2dat.txt"));
         for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            nextMessage[i] = line.split(" ");
         }
         br = new BufferedReader(new FileReader("level2scores.txt"));
         for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            scores[i] = line.split(" ");
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
         ch = e.getKeyCode();
         if (ch == KeyEvent.VK_DOWN && selected != 2 && selected != 3) {
            selected += 2;
         }
         else if (ch == KeyEvent.VK_UP && selected != 0 && selected != 1) {
            selected -= 2;
         }
         else if (ch == KeyEvent.VK_LEFT && selected != 0 && selected != 2) {
            selected--;
         }
         else if (ch == KeyEvent.VK_RIGHT && selected != 1 && selected != 3) {
            selected++;
         }
         Level2.this.repaint();
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param Graphics g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (currScene == 0) { // starting screen
         g.setColor(new Color(224, 240, 244));
         g.fillRect(0, 0, 810, 1080);
         g.drawImage(computer, 0, 220, this);
         g.setColor(new Color(150, 75, 0));
         g.fillRect(0, 860, 810, 220);
         g.setColor(Color.black);
         g.setColor(new Color(162, 210, 255, 200));
         g.fillRect(20, 240, 750, 420);
         g.drawImage(level2text, 95, 380, this);
         
         // press enter to continue message
         g.setColor(new Color(254, 189, 225));
         g.fillRect(50, 880, 700, 90);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 64));     
         g.drawString("Press Enter to Continue", 95, 945);
         
         if (ch == KeyEvent.VK_ENTER) {
            ch = '\\';
            currScene++;
            this.repaint();
         } 
      }
      else if (currScene == 1) { // instructions
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1080);
         g.drawImage(instructionsL2, -10, -70, this);
         if (ch == KeyEvent.VK_ENTER) {
            ch = '\\';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) { // no user input required, basic animation
         if (counter < 127) { // screen fades to black
            g.setColor(bg);
            g.fillRect(0, 0, 810, 1080);
            g.drawImage(instructionsL2, -10, -70, this);
            g.setColor(new Color(0, 0, 0, counter*2));
            g.fillRect(0, 0, 810, 1080);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 254) { // screen fades back in, game appears
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
            
            g.setColor(new Color(0, 0, 0, 506-counter*2));
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
         if (counter < 96) { // messages appear here
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
            if (game) {
               timer++;
            }
            else {
               totalTime += timer; // amount time selecting answer
               timer = 0;
            }
            if (ch == KeyEvent.VK_ENTER) { // next message displays
               ch = '\\';
               counter++;
               if (counter == 96) {
                  this.repaint();
                  return;
               }
               
               if (game && scenario < 5) {
                  if (selected == 3) {
                     seeAllOptions = true;
                     // score does not change
                  }
                  else {
                     counter = Integer.parseInt(nextMessage[scenario][selected]) - 1;
                     score += Math.max(Integer.parseInt(scores[scenario][selected]) - timer, -1000);
                  }
                  scenario++;
                  this.repaint();
                  return;
               }
            }
            else if (!game) { // display same messages
               displayMessages(g);
               return;
            }
            
            int nextMessage = counter; // index of next message in array
            if (messageUser[nextMessage] == -1) {
               displayTransition(g);
               seeAllOptions = false;
            }
            else if (messageUser[nextMessage] == -5 && seeAllOptions) {
               numDisplayed = 0;
               this.repaint();
            }
            else if (messageUser[nextMessage] == -5) {
               counter = nextCounter;
               if (counter != 96)
                  displayTransition(g);
               else
                  this.repaint();
            }
            else if (messageUser[nextMessage] == -10) {
               game = true;
               nextCounter = Integer.parseInt(messageText[nextMessage]);
               g.setFont(new Font("Calibri", Font.BOLD, 28));
               g.setColor(new Color(254, 189, 225));
               g.fillRect(170, 260, 450, 60);
               g.setColor(Color.black); 
               g.drawString("What action should CMod AI take?", 200, 300);
               
               if (selected == 0) g.setColor(new Color(253, 95, 95));
               else g.setColor(new Color(255, 128, 128));
               g.fillRect(70, 400, 300, 60);
               
               if (selected == 1) g.setColor(new Color(253, 95, 95));
               else g.setColor(new Color(255, 128, 128));
               g.fillRect(430, 400, 300, 60);
               
               if (selected == 2) g.setColor(new Color(253, 95, 95));
               else g.setColor(new Color(255, 128, 128));
               g.fillRect(70, 550, 300, 60);
               
               if (selected == 3) g.setColor(new Color(253, 95, 95));
               else g.setColor(new Color(255, 128, 128));
               g.fillRect(430, 550, 300, 60);
               
               g.setColor(Color.black);
               g.drawString("No Offence Made", 115, 440);
               g.drawString("Temporary Mute", 480, 440);
               g.drawString("Permanent Ban", 125, 590);
               g.drawString("(Skip) View Scenarios", 455, 590);
               numDisplayed = 0;
               this.repaint();
            }
            else if (numDisplayed == 4) { // displays all 4 messages
               for (int i = 0; i < 3; i++) {
                  messageTextDisplayed[i] = messageTextDisplayed[i+1];
                  messageUserDisplayed[i] = messageUserDisplayed[i+1];
               }
               messageTextDisplayed[3] = messageText[nextMessage];
               messageUserDisplayed[3] = messageUser[nextMessage];
               selected = 0;
               game = false;
            }
            else {
               messageTextDisplayed[numDisplayed] = messageText[nextMessage];
               messageUserDisplayed[numDisplayed] = messageUser[nextMessage];
               numDisplayed++;
               selected = 0;
               game = false;
            }
            
            if (messageUser[nextMessage] != -10)
               displayMessages(g);
         }
         else { // level 2 is complete
            g.setFont(new Font("Calibri", Font.BOLD, 28));
            g.drawImage(results, 0, 0, this);
            g.drawString(username, 375, 500); 
            g.drawString(""+score, 425, 550);
            g.drawString(""+totalTime, 425, 600);
            
            if (ch == KeyEvent.VK_ENTER) {
               finished = true;
            }
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
      selected = 0;
      game = false;
   }
   
   public void displayTransition(Graphics g) {
      g.drawImage(transition2, 95, 380, this);
      // press enter to continue message
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 880, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 945); 
      numDisplayed = 0;
      selected = 0;
      game = false;
      seeAllOptions = false;
   }
   
   /**
    * This method allows the Main class to access whether the user is done reading or not
    * @return Whether the user has finished reading all instructions
    */
   public boolean getFinished() {
      return finished;
   }
}