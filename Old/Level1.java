import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
 * Time Spent: 5 minutes
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
 * Time Spent: 10 minutes
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
 * <p>
 * Version 1.7
 * Time Spent: 20 minutes
 * Modifying comments to generate java docs properly
 * </p>
 *
 * <p>
 * Version 1.8
 * Time Spent: 10 minutes
 * Changing the graphics of the instructions and community guidelines
 * </p>
 *
 * <p>
 * Version 1.9
 * Time Spent: 10 minutes
 * Modifying the file path for importing files after organizing folders
 * </p>
 *
 * <p>
 * Version 1.10
 * Time Spent: 35 minutes
 * Changing the coordinates of the graphics on the level. Adding 
 * instructions to the username screen. Username prompted only
 * the first time that Level 1 is run, otherwise uses previous username.
 * </p>
 *
 * <p>
 * Version 1.11
 * Time Spent: 1 hour
 * Changing the coordinates of some of the graphics in the level. New 
 * images for characters on the sides, also randomized.
 * </p>
 *
 * <p>
 * Version 1.12
 * Time Spent: 20 minutes
 * Coordinates of some drawings adjusted after making JFrame window smaller
 * </p>
 *
 * @author Tsz Fei Wang, Eric Ning
 * @version 1.12
 * 
 * Chat-Mod AI Inc.
 * June 12th, 2024
 */
public class Level1 extends Level {

   /** image containing the instructions for the level */
   private BufferedImage instructionsL1;
   /** image of the enter username scene */
   private BufferedImage user; 
   /** image of the person's stool in front of the desk */
   private BufferedImage stool;
   /** image of the "Level 1" text header */
   private BufferedImage level1text;
   /** image of the person's computer desk */
   private BufferedImage desk;
   /** image of the person */
   private BufferedImage person;
   /** image of person sitting at computer desk */
   private BufferedImage personComputer;
   /** image of the computer zoomed in */
   private BufferedImage computer;
   /** image of people on the computer screen */
   private BufferedImage[] computerPeople;
   /** image of the transition screen between two blocks of messages */
   private BufferedImage transition;
   /** the current scene in the level being displayed */
   private int currScene;
   /** counter variable to deal with animation */
   private int counter;
   /** keeps track of number of messages being displayed */
   private int numDisplayed;
   /** stores the user's input */
   private char ch;
   /** text in the currently displayed messages */
   private String[] messageTextDisplayed;
   /** the user that sent each message in currently displayed messages */
   private int[] messageUserDisplayed;
   /** contains the text of all the messages */
   private ArrayList<String> messageText;
   /** contains the corresponding user of all the messages */
   private ArrayList<Integer> messageUser;
   /** which computer person to draw on the left hand side of the screen */
   private int computerPerson1; 
   /** which computer person to draw on the right hand side of the screen */
   private int computerPerson2;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    * @param username The user's username, stored from previous run of Level 1 if it happened (otherwise empty)
    * @param level1 Whether the user has ran level 1 before yet
    */
   public Level1(String username, boolean level1) {
      super(username, new Color(245,228,255)); // calls the constructor of the Level class
      this.addKeyListener(new KeyHandler()); // adds the Key Listener
      
      try {
         // importing images 
         computerPeople = new BufferedImage[6];
         
    	   ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
         instructionsL1 = ImageIO.read(classLoader.getResourceAsStream("images/instructionsL1.png"));
         user = ImageIO.read(classLoader.getResourceAsStream("images/enter username.jpg"));
         stool = ImageIO.read(classLoader.getResourceAsStream("images/stool.png"));
         level1text = ImageIO.read(classLoader.getResourceAsStream("images/level1text.png"));
         desk = ImageIO.read(classLoader.getResourceAsStream("images/desk.png"));
         person = ImageIO.read(classLoader.getResourceAsStream("images/person.png"));
         personComputer = ImageIO.read(classLoader.getResourceAsStream("images/personComputer.png"));
         computer = ImageIO.read(classLoader.getResourceAsStream("images/computer.png"));
         transition = ImageIO.read(classLoader.getResourceAsStream("images/transition.png"));
         
         computerPeople[0] = ImageIO.read(classLoader.getResourceAsStream("images/man1.png"));
         computerPeople[1] = ImageIO.read(classLoader.getResourceAsStream("images/man2.png"));
         computerPeople[2] = ImageIO.read(classLoader.getResourceAsStream("images/man3.png"));
         computerPeople[3] = ImageIO.read(classLoader.getResourceAsStream("images/woman1.png"));
         computerPeople[4] = ImageIO.read(classLoader.getResourceAsStream("images/woman2.png"));
         computerPeople[5] = ImageIO.read(classLoader.getResourceAsStream("images/woman3.png"));
         computerPerson1 = (int)(Math.random()*3); // either 0, 1, or 2
         computerPerson2 = (int)(Math.random()*3)+3; // either 3, 4, or 5
         
         // initializing other instance variables
         ch = '\u0000';
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new ArrayList<String>();
         messageUser = new ArrayList<Integer>();
         if (level1) currScene = 1;
         
         // reading the information into the arrays inside the pre-created level1.txt file
         BufferedReader br = new BufferedReader(new FileReader("textfiles/level1.txt"));
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
         br.close();
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
       * @param e An event that shows that a keyboard input as been made
       */
      public void keyPressed(KeyEvent e) {
         long current = System.nanoTime();
         
         if (lastPress <= 0L || current - lastPress >= THRESHOLD) {
            ch = e.getKeyChar();
            lastPress = current;
            Level1.this.repaint();
         }
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (currScene == 0) { // taking username
         // draws background
         g.setColor(bg);
         g.fillRect(0, 0, 810, 950);
         g.drawImage(user, 0, -200, this);
         g.setColor(new Color(255, 209, 235));
         g.fillRect(50, 690, 695, 140);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 48));     
         g.drawString("Type letters and numbers then", 90, 745); 
         g.drawString("press 'Enter' to submit", 165, 805);
         
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
               g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 56));
               g.drawString(username, 160, 500); // draws the current username on screen
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
         else if(username.length() >= 14) { // too long
            displayWarning("You cannot exceed fourteen characters.", g);
         }
         else { // otherwise it is valid, so add to username
            username += c;
            
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 56));             
            g.drawString(username, 160, 500); // draws the current username on screen
            
            // resets the character to avoid one key endlessly being added
            ch = '\u0000';
         }
      }
      else if (currScene == 1) { // instructions
         displayInstructions(g);
         
         if (ch == '\n') { // user chooses to continue
            ch = '\u0000';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) { // community guidelines
         displayCommunityGuidelines(g);
         
         if (ch == '\n') { // user chooses to continue
            ch = '\u0000';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 3) { // no user input required, basic animation
         if (counter < 100) { // person moving left
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 950);
            g.drawImage(level1text, 100, 50, this);
            g.drawImage(desk, 30, 250, this);
            g.drawImage(stool, 135, 580, this);
            g.drawImage(person, 650-counter, 450, this);
            g.setColor(Color.white);
            g.fillRect(842-counter, 450, 1, 400);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 355) { // screen fades to black
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 950);
            g.drawImage(level1text, 100, 50, this);
            g.drawImage(desk, 30, 250, this);
            g.drawImage(stool, 135, 580, this);
            g.drawImage(person, 550, 450, this);
            g.setColor(new Color(0, 0, 0, counter-100));
            g.fillRect(0, 0, 810, 950);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 610) { // screen fades back in, person sitting at computer
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 950);
            g.drawImage(personComputer, 10, 220, this);
            g.setColor(new Color(0, 0, 0, 610-counter));
            g.fillRect(0, 0, 810, 950);
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
            g.fillRect(0, 0, 810, 950);
            g.drawImage(computer, 2, 150, this);
            g.setColor(new Color(150, 75, 0));
            g.fillRect(0, 790, 810, 220);
            g.setColor(new Color(224, 240, 244));
            g.fillRect(20, 170, 750, 420);
            g.setColor(new Color(254, 189, 225, counter*2));
            g.fillRect(315, 305, 300, 100);
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24)); 
            
            // draws the two people on the ends of the screen
            g.drawImage(computerPeople[computerPerson1], 28, 363, this);
            g.drawImage(computerPeople[computerPerson2], 658, 363, this);   
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 199) { // username being displayed
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 950);
            g.drawImage(computer, 2, 150, this);
            g.setColor(new Color(150, 75, 0));
            g.fillRect(0, 790, 810, 220);
            g.setColor(new Color(254, 189, 225));
            g.fillRect(315, 305, 300, 100);
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));     
            g.drawString("Hello! My name is", 340, 345);        
            g.drawString(username, 340, 380);
            g.setColor(new Color(162, 210, 255, (counter-100)*2));
            g.fillRect(21, 170, 751, 422);
            
            // draws the two people on the ends of the screen
            g.drawImage(computerPeople[computerPerson1], 28, 363, this);
            g.drawImage(computerPeople[computerPerson2], 658, 363, this);
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
               if (counter-200 >= 0 && counter-200 < messageUser.size() && messageUser.get(counter-200) == -1)
                   displayTransition(g);
               return;
            }
            
            int nextMessage = counter-200; // index of next message in array
            if (messageUser.get(nextMessage) == -1) { // between scenarios show transition
               displayTransition(g);
               computerPerson1 = (int)(Math.random()*3);
               computerPerson2 = (int)(Math.random()*3)+3;
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
    * @param message The warning message
    * @param g An object which is a painting tool
    */
   public void displayWarning(String message, Graphics g) {
      g.setFont(new Font("Calibri", Font.BOLD, 40));
      g.setColor(new Color(162, 210, 255));
      g.fillRect(50, 30, 710, 120);
      g.setColor(Color.black);
      g.drawString(message, 80, 100);
      g.setColor(Color.black);
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 56));             
      g.drawString(username, 160, 500);
   }
   
   /**
    * Utility method to display the instructions of the level
    * @param g An object which is a painting tool
    */
   public void displayInstructions(Graphics g) {
      // background
      g.setColor(bg);
      g.fillRect(0, 0, 810, 950);
   
      // title         
      g.setColor(new Color(255, 209, 235));
      g.fillRect(30, 30, 737, 160);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 80));
      g.drawString("Level 1", 280, 138);
      
      // text
      g.drawImage(instructionsL1, 44, 205, this);
      
      // instructions to continue
      g.setColor(new Color(255, 209, 235));
      g.fillRect(50, 800, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 92, 865);    
   }
   
   /**
    * Utility method to display the background of the level
    * @param g An object which is a painting tool
    */
   public void displayBackground(Graphics g) {
      g.setColor(new Color(224, 240, 244));
      g.fillRect(0, 0, 810, 950);
      g.drawImage(computer, 2, 150, this);
      g.setColor(new Color(150, 75, 0));
      g.fillRect(0, 790, 810, 220);
      
      g.setColor(new Color(254, 189, 225));
      g.fillRect(315, 305, 300, 100);
      g.setColor(Color.black);
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));     
      g.drawString("Hello! My name is", 340, 345);        
      g.drawString(username, 340, 380);
      g.setColor(new Color(162, 210, 255, 200));
      g.fillRect(21, 170, 751, 422);
      g.drawImage(computerPeople[computerPerson1], 28, 363, this);
      g.drawImage(computerPeople[computerPerson2], 658, 363, this);
      
      // press enter to continue message
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 810, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 875);
   }
   
   /**
    * Utility method to display the messages in the level
    * @param g An object which is a painting tool
    */
   public void displayMessages(Graphics g) {
      g.setFont(new Font("Calibri", Font.BOLD, 20));
      for (int i = 0; i < numDisplayed; i++) {
         if (messageUserDisplayed[i] == 0) {
            g.setColor(Color.red);
            g.fillRect(45, 190+i*100, 700, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 55, 220+i*100);
         }
         else if (messageUserDisplayed[i] == 1) {
            g.setColor(new Color(254, 189, 225));
            g.fillRect(365, 190+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 375, 220+i*100);
         }
         else if (messageUserDisplayed[i] == 2) {
            g.setColor(new Color(103, 157, 255));
            g.fillRect(30, 190+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 220+i*100);
         }
         else {
            g.setColor(new Color(126, 217, 87));
            g.fillRect(30, 190+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 220+i*100);
         }
      }
   }
   
   /**
    * Utility method to display the transition between scenarios in the level
    * @param g An object which is a painting tool
    */
   public void displayTransition(Graphics g) {
      g.drawImage(transition, 48, 300, this);
      // press enter to continue message
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 810, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 875); 
      numDisplayed = 0;

   }
   
   /**
    * This method allows the Main class to access the user's username for level 2
    * @return The String containing the username
    */
   public String getUsername() {
      return username;
   }
   
   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(200);
            if (finished) break; // until the level is complete
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}
