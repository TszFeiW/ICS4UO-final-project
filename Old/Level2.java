import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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
 * + calculations of score + diversity of transition screens.
 * </p>
 *
 * <p>
 * Version 1.2
 * Time Spent: 2 hours
 * Class modified so that there are more scenarios included, along with a tentative
 * scoring system and now works properly. Still need the end screen with the total
 * score along with a little better formatting of text.
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: 1 hour
 * The end screen is mostly functional, along with scoring system working and text
 * format working. Still need to add a leaderboard option however. Currently
 * working on having an abstract class Level which is the superclass of Level2.
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
 * Time Spent: 1 hour
 * Adding a method to store the score onto a text file for the leaderboard.
 * Also changed some arrays to ArrayLists to avoid some issues.
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
 * Time Spent: 4 hours
 * Completely revamping the background of the game, as well as adding WASD + F key
 * input to use the character that the user will control in the game. Added collision
 * detection to prevent the user from going across the trees in the background. Scoring
 * system also has been changed.
 * </p>
 *
 * <p>
 * Version 1.11
 * Time Spent: 2.5 hours
 * Coordinates of some drawings adjusted after making JFrame window smaller.
 * Background image more detailed, collision detection with trees fixed, 
 * messages displaying fixed, character is now animated as well, new graphics
 * to accompany the game to make the level look better.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.11
 * 
 * Chat-Mod AI Inc.
 * June 12th, 2024
 */
public class Level2 extends Level {

   /** image containing the instructions for the level */
   private BufferedImage instructionsL2;
   /** image of the "Level 2" text header */
   private BufferedImage level2text;
   /** image of background in the Level 2 game */
   private BufferedImage level2background;
   /** image of the F key being able to be pressed */
   private BufferedImage fKey;
   /** image of user's character to control */
   private BufferedImage character;
   /** image of user's character to control with left foot raised */
   private BufferedImage characterLeftFootRaised;
   /** image of user's character to control with right foot raised */
   private BufferedImage characterRightFootRaised;
   /** image of the first person to interact with */
   private BufferedImage person1;
   /** image of the second person to interact with */
   private BufferedImage person2;
   /** image of the third person to interact with */
   private BufferedImage person3;
   /** image of the fourth person to interact with */
   private BufferedImage person4;
   /** image of the fifth person to interact with */
   private BufferedImage person5;
   /** image of the computer screen for the start of the level*/
   private BufferedImage computer;
   /** image of the results screen at the end of the level */
   private BufferedImage results; 
   /** whether or not the user is selecting a response in the game */
   private boolean game;
   /** whether or not the user chose to skip the question to see all answers */
   private boolean seeAllOptions;
   /** the current scene in the level being displayed */
   private int currScene;
   /** counter variable to deal with animation and messages being displayed */
   private int counter;
   /** secondary counter variable for an animated arrow */
   private int arrow;
   /** secondary counter variable for the animated character */
   private int footRaised;
   /** keeps track of number of messages being displayed */
   private int numDisplayed;
   /** the selected option out of the 4 choices in the game */
   private int selected;
   /** the next message after the user made a choice */ 
   private int nextCounter;
   /** the scenario number (0 to 4) in the game */
   private int scenario;
   /** the current option being displayed if seeAllOptions is true */
   private int currentOption;
   /** the user's score for the level */
   private int score;
   /** timer to keep track of how long the user has been selecting a response */
   private int timer;
   /** total timer for the results screen at the end of the level */
   private int totalTime;
   /** stores the user's input */
   private int ch;
   /** the x-coordinate of the user's character */
   private int x;
   /** the y-coordinate of the user's character */
   private int y;
   /** the location of the people the user interacts with */
   private int[][] location;
   /** text in the currently displayed messages */
   private String[] messageTextDisplayed;
   /** the user that sent each message in currently displayed messages */
   private int[] messageUserDisplayed;
   /** contains the text of all the messages */
   private ArrayList<String> messageText;
   /** contains the corresponding user of all the message */
   private ArrayList<Integer> messageUser;
   /** 2D array that stores the next message to display for each response in each scenario */
   private String[][] nextMessage;
   /** 2D array that stores the base score value of the responses for each scenario */
   private String[][] scores;
   /** Whether there are currently messages appearing on screen from the scenario */
   private boolean messages;
   /** Whether the user chose the correct option */
   private boolean correct;
   /** Whether the user chose the okay option */
   private boolean okay;
   /** Whether the user chose the incorrect option */
   private boolean incorrect;
   /** Whether the user has not made any mistakes */
   private boolean perfectGame;
   /** Whether the user ran out of time to answer the question */
   private boolean outOfTime;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    * @param username The user's username from level 1
    */
   public Level2(String username) {
      super(username, new Color(245,228,255)); // calls the constructor of the Level class
      this.addKeyListener(new KeyHandler()); // adds the Key Listener
      
      try {
         // importing images
    	   ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
         instructionsL2 = ImageIO.read(classLoader.getResourceAsStream("images/instructionsL2.png"));
         level2text = ImageIO.read(classLoader.getResourceAsStream("images/level2text.png"));
         level2background = ImageIO.read(classLoader.getResourceAsStream("images/level2background.png"));
         fKey = ImageIO.read(classLoader.getResourceAsStream("images/fKey.png"));
         character = ImageIO.read(classLoader.getResourceAsStream("images/character.png"));
         characterLeftFootRaised = ImageIO.read(classLoader.getResourceAsStream("images/characterLeftFootRaised.png"));
         characterRightFootRaised = ImageIO.read(classLoader.getResourceAsStream("images/characterRightFootRaised.png"));
         person1 = ImageIO.read(classLoader.getResourceAsStream("images/person1.png"));
         person2 = ImageIO.read(classLoader.getResourceAsStream("images/person2.png"));
         person3 = ImageIO.read(classLoader.getResourceAsStream("images/person3.png"));
         person4 = ImageIO.read(classLoader.getResourceAsStream("images/person4.png"));
         person5 = ImageIO.read(classLoader.getResourceAsStream("images/person5.png"));
         computer = ImageIO.read(classLoader.getResourceAsStream("images/computer.png"));
         results = ImageIO.read(classLoader.getResourceAsStream("images/level2Results.png")); 

         // initializing other instance variables
         location = new int[][]{{20, 810}, {300, 150}, {300, 350}, {600, 150}, {470, 460}};
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new ArrayList<String>();
         messageUser = new ArrayList<Integer>();
         nextMessage = new String[5][4];
         scores = new String[5][4];
         ch = '\\';
         x = 700;
         y = 810;
         perfectGame = true;
         
         // reading the information into the arrays inside the pre-created level2.txt file
         BufferedReader br = new BufferedReader(new FileReader("textfiles/level2.txt"));
         while (true) { // assumes that file is functional
            String line = br.readLine();
            if (line == null) break; // end of file
            else if (line.charAt(0) == '/') { // user must make a choice here
               messageText.add(line.substring(1));
               messageUser.add(-10);
            }
            else if (line.equals("-")) { // between different possible choices
               messageText.add("");
               messageUser.add(-5);
            }
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
         
         // reads data to determine next line after user makes a choice
         br = new BufferedReader(new FileReader("textfiles/level2dat.txt"));
         for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            nextMessage[i] = line.split(" ");
         }
         br.close();
         
         // reads data to determine the score gained or lost for each possible choice
         br = new BufferedReader(new FileReader("textfiles/level2scores.txt"));
         for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            scores[i] = line.split(" ");
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
            ch = e.getKeyCode();
            if (ch == KeyEvent.VK_DOWN && selected != 2 && selected != 3) { // down arrow key
               selected += 2;
            }
            else if (ch == KeyEvent.VK_UP && selected != 0 && selected != 1) { // up arrow key
               selected -= 2;
            }
            else if (ch == KeyEvent.VK_LEFT && selected != 0 && selected != 2) { // left arrow key
               selected--;
            }
            else if (ch == KeyEvent.VK_RIGHT && selected != 1 && selected != 3) { // right arrow key
               selected++;
            }
            lastPress = current;
            Level2.this.repaint();
         }
      }
   }
   
   /**
    * This method is capable of actually drawing onto the JFrame window.
    * @param g An object which is a painting tool
    */
   public void paintComponent(Graphics g) {
      if (currScene == 0) { // starting screen
         g.setColor(new Color(224, 240, 244));
         g.fillRect(0, 0, 810, 950);
         g.drawImage(computer, 2, 150, this);
         g.setColor(new Color(150, 75, 0));
         g.fillRect(0, 790, 810, 220);
         g.setColor(Color.black);
         g.setColor(new Color(162, 210, 255, 200));
         g.fillRect(21, 170, 751, 422);
         g.drawImage(level2text, 95, 310, this);
         
         // press enter to continue message
         g.setColor(new Color(254, 189, 225));
         g.fillRect(50, 810, 700, 90);
         g.setColor(Color.black);
         g.setFont(new Font("Calibri", Font.BOLD, 64));     
         g.drawString("Press Enter to Continue", 95, 875);
         
         if (ch == KeyEvent.VK_ENTER) { // user chooses to continue
            ch = '\\';
            currScene++;
            this.repaint();
         } 
      }
      else if (currScene == 1) { // instructions
         displayInstructions(g);
         
         if (ch == KeyEvent.VK_ENTER) { // user chooses to continue
            ch = '\\';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) { // community guidelines
         displayCommunityGuidelines(g);
         
         if (ch == KeyEvent.VK_ENTER) { // user chooses to continue
            ch = '\u0000';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 3) { // no user input required, basic animation
         if (counter < 127) { // screen fades to black
            displayCommunityGuidelines(g);
            g.setColor(new Color(0, 0, 0, counter*2));
            g.fillRect(0, 0, 810, 950);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 254) { // screen fades back in, game appears
            displayBackground(g);
            g.setColor(new Color(0, 0, 0, 506-counter*2));
            g.fillRect(0, 0, 810, 950);
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
            displayBackground(g);
            if (!game && !messages)
               arrow++;
            
            try {Thread.sleep(50);} catch (InterruptedException ie) {}
            if (game) { // if the user is currently trying to make a response
               timer++;
               g.setColor(bg);
               g.fillRect(35, 130, 110, 90);
               g.setFont(new Font("Calibri", Font.BOLD, 80));
               g.setColor(Color.black);
               g.drawString(""+(60 - timer/16), 50, 200); // displays timer
               if (60 - timer/16 == 0) {
                  outOfTime = true;
                  seeAllOptions = true;
                  // adds the next message to display
                  counter++;
                  messageTextDisplayed[0] = messageText.get(counter);
                  messageUserDisplayed[0] = messageUser.get(counter);
                  numDisplayed++;
                  selected = 0;
                  scenario++;
                  game = false; // no longer making a response
                  this.repaint();
                  return;
               }
            }
            else {
               totalTime += timer; // amount time selecting answer
               timer = 0;
            }
            
            if (ch == KeyEvent.VK_ENTER && messages) { // next message displays
               ch = '\\';
               counter++;
               if (counter == 96) { // end of messages
                  this.repaint();
                  return;
               }
               
               if (game && scenario < 5) { // if the user is currently trying to make a response
                  if (selected == 3) { // chose to skip
                     seeAllOptions = true;
                     perfectGame = false;
                     // score does not change
                  }
                  else {
                     counter = Integer.parseInt(nextMessage[scenario][selected]) - 1; // next message
                     score += Math.max(Integer.parseInt(scores[scenario][selected]) - timer*5, -1000); // adds to score
                     if (scores[scenario][selected].equals("3000")) // correct answer
                        correct = true;
                     else if (scores[scenario][selected].equals("1000")) { // okay answer
                        okay = true;
                        perfectGame = false;
                     }
                     else { // wrong answer
                        incorrect = true;
                        perfectGame = false;
                     }
                  }
                  // adds the next message to display
                  messageTextDisplayed[0] = messageText.get(counter);
                  messageUserDisplayed[0] = messageUser.get(counter);
                  numDisplayed++;
                  selected = 0;
                  currentOption = 0;
                  scenario++;
                  game = false; // no longer making a response
                  this.repaint();
                  return;
               }
            }
            else if (!game && messages) { // display same messages
               displayMessages(g);
               if (counter >= 0 && counter < messageUser.size() && messageUser.get(counter) == -1) {
                  displayBackground(g);
                  messages = false;
                  numDisplayed = 0;
               }
               return;
            }
            else if (ch == KeyEvent.VK_W && !game && !messages) { // user moving up
               ch = '\\';
               if (y - 10 >= 20 && !(y == 460 && x <= 570 && x >= 330) && !(y == 340 && x <= 320 && x >= 120) && !(y == 300 && x <= 740 && x >= 610) && !(y == 140 && x >= 440)) { // not hitting any objects or border
                  y -= 10;
                  if (footRaised == 1) // left foot
                     footRaised = 2; // now right foot
                  else 
                     footRaised = 1; // now left foot
                  displayBackground(g);
               }
               this.repaint();
               return;
            }
            else if (ch == KeyEvent.VK_A && !game && !messages) { // user moving left
               ch = '\\';
               if (x - 10 >= 0 && !(x == 580 && y <= 450 && y >= 310) && !(x == 440 && y <= 300)) { // not hitting any objects or border
                  x -= 10;
                  if (footRaised == 1) // left foot
                     footRaised = 2; // now right foot
                  else 
                     footRaised = 1; // now left foot
                  displayBackground(g);
               }
               this.repaint();
               return;
            }
            else if (ch == KeyEvent.VK_S && !game && !messages) { // user moving down
               ch = '\\';
               if (y + 10 <= 880 && !(y == 170 && x <= 320 && x >= 130) && !(y == 300 && x >= 440 && x <= 570) && !(y == 140 && x >= 610)) { // not hitting any objects or border
                  y += 10;
                  if (footRaised == 1) // left foot
                     footRaised = 2; // now right foot
                  else 
                     footRaised = 1; // now left foot
                  displayBackground(g);
               }
               this.repaint();
               return;
            }
            else if (ch == KeyEvent.VK_D && !game && !messages) { // user moving right
               ch = '\\';
               if (x + 10 <= 740 && !(x == 320 && y <= 450 && y >= 290) && !(x == 120 && y <= 330 && y >= 180) && !(x == 320 && y <= 170) && !(x == 600 && y <= 290 && y >= 150)) { // not hitting any objects or border
                  x += 10;
                  if (footRaised == 1) // left foot
                     footRaised = 2; // now right foot
                  else 
                     footRaised = 1; // now left foot
                  displayBackground(g);
               }
               this.repaint();
               return;
            }
            else if (ch == KeyEvent.VK_F && !game && !messages) { // user interacting with another character
               ch = '\\';
               if (x >= location[scenario][0] - 50 && x <= location[scenario][0] + 50 && y >= location[scenario][1] - 100 && y <= location[scenario][1] + 100 && !game) { // user in a valid position from the character
                   messages = true; // now displaying messages
                   counter++; // next message
                   displayBackground(g);
               }
               else {
                  this.repaint();
                  return;
               }      
            }
            else if (!messages) {
               footRaised = 0; // no foot being raised anymore
               try {Thread.sleep(5);} catch (InterruptedException ie) {}
               this.repaint();
               return;
            }
            
            int nextMessage = counter; // index of next message in array
            if (messageUser.get(nextMessage) == -1) { // between scenarios show transition
               displayBackground(g);
               // resets variables
               outOfTime = false;
               messages = false;
               correct = false;
               okay = false;
               incorrect = false;
               seeAllOptions = false;
               numDisplayed = 0;
               this.repaint();
               return;
            }
            else if (messageUser.get(nextMessage) == -5 && seeAllOptions) { // user wants to see all options
               numDisplayed = 1; // begins to show next option (resets current screen)
               counter++;
               currentOption++;
               messageTextDisplayed[0] = messageText.get(nextMessage+1);
               messageUserDisplayed[0] = messageUser.get(nextMessage+1);
            }
            else if (messageUser.get(nextMessage) == -5) { // user does not want to see all options, current option done
               counter = nextCounter; // goes to next message
               correct = false;
               okay = false;
               incorrect = false;
               if (counter != 96) { // if there are still more messages/scenarios
                  displayBackground(g);
                  messages = false;
                  numDisplayed = 0;
               }
               this.repaint();
               return;
            }
            else if (messageUser.get(nextMessage) == -10) { // end of messages, asks user to select from options
               displayOptions(g);
            }
            else if (numDisplayed == 4) { // displays all 4 messages
               // gets rid of oldest message and shifts everything down and replaces it
               for (int i = 0; i < 3; i++) {
                  messageTextDisplayed[i] = messageTextDisplayed[i+1];
                  messageUserDisplayed[i] = messageUserDisplayed[i+1];
               }
               messageTextDisplayed[3] = messageText.get(nextMessage);
               messageUserDisplayed[3] = messageUser.get(nextMessage);
               selected = 0;
            }
            else { // adds the next message
               messageTextDisplayed[numDisplayed] = messageText.get(nextMessage);
               messageUserDisplayed[numDisplayed] = messageUser.get(nextMessage);
               numDisplayed++;
               selected = 0;
            }
            
            if (messageUser.get(nextMessage) != -10) // if not a transition screen then display messages
               displayMessages(g);
         }
         else { // level 2 is complete
            int overallScore = score + (perfectGame ? 5000 : 0); // max score theoretically is 20 000
            g.setFont(new Font("Calibri", Font.BOLD, 28));
            g.drawImage(results, -3, -80, this);
            g.drawString(username, 415, 422); 
            g.drawString(""+overallScore, 415, 465);
            g.drawString(""+(totalTime/16), 415, 510);
            
            if (overallScore >= 11000) { // great score
               g.setColor(Color.green);
               g.fillOval(595, 25, 175, 175);
               g.setColor(Color.black);
               int[] x = {645, 630, 665, 740, 725, 665};
               int[] y = {115, 130, 165, 90, 75, 135};
               g.fillPolygon(x, y, 6);
            }
            else if (overallScore >= 7000) { // sufficient score
               g.setColor(new Color(200, 150, 50));
               g.fillOval(595, 25, 175, 175);
               g.setColor(Color.black);
               g.fillRect(610, 97, 145, 30); 
            }
            else { // insufficient score
               g.setColor(Color.red);
               g.fillOval(595, 25, 175, 175);
               g.setColor(Color.black);
               int[] x = {700, 740, 725, 685, 645, 630, 670, 630, 645, 685, 725, 740};
               int[] y = {117, 77, 62, 102, 62, 77, 117, 157, 172, 132, 172, 157};
               g.fillPolygon(x, y, 12);
            }
            
            if (ch == KeyEvent.VK_ENTER) { // user chooses to exit
               addHighscore();
               finished = true;
            }
         }
      }
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
      g.drawString("Level 2", 280, 138);
      
      // text
      g.drawImage(instructionsL2, 23, 205, this);
      
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
      g.drawImage(level2background, 0, 0, this);
      if (footRaised == 0) // no foot raised
         g.drawImage(character, x, y, this);
      else if (footRaised == 1) // left foot raised
         g.drawImage(characterLeftFootRaised, x, y, this);
      else // right foot raised
         g.drawImage(characterRightFootRaised, x, y, this);
         
      // other "people" in the level
      g.drawImage(person1, location[0][0], location[0][1], this);
      g.drawImage(person2, location[1][0], location[1][1], this);
      g.drawImage(person3, location[2][0], location[2][1], this);
      g.drawImage(person4, location[3][0], location[3][1], this);
      g.drawImage(person5, location[4][0], location[4][1], this);
      
      // instructions just in case
      g.setFont(new Font("Calibri", Font.BOLD, 36));
      g.setColor(Color.white);
      g.drawString("WASD to move, F to interact with next character", 30, 50);
      
      if (scenario >= 5) return; // no more scenarios
      
      // for animated arrow
      int xShift = location[scenario][0], yShift = location[scenario][1];
      
      arrow %= 5;
      if (arrow == 0 || arrow == 4) yShift -= 0;
      else if (arrow == 1 || arrow == 3) yShift -= 5;
      else yShift -= 10;
      
      g.setColor(Color.red);
      int[] xArr = {xShift+23, xShift+46, xShift+35, xShift+35, xShift+11, xShift+11, xShift};
      int[] yArr = {yShift-10, yShift-33, yShift-33, yShift-66, yShift-66, yShift-33, yShift-33};
      g.fillPolygon(xArr, yArr, 7); // drawing the arrow
      
      if (x >= location[scenario][0] - 50 && x <= location[scenario][0] + 50 && y >= location[scenario][1] - 100 && y <= location[scenario][1] + 100 && !game && !messages) { // user in a valid position from the character
         g.drawImage(fKey, location[scenario][0] + 70, location[scenario][1] - 75, this); // displays an F key when the user can use F
      }
   }
   
   /**
    * Utility method to display the messages in the level
    * @param g An object which is a painting tool
    */
   public void displayMessages(Graphics g) {
      messages = true;
      g.setColor(new Color(245, 228, 255));
      g.fillRect(15, 240, 760, 415);
      g.setFont(new Font("Calibri", Font.BOLD, 20));
      for (int i = 0; i < numDisplayed; i++) {
         // depending on the user who sent the message and formats the message nicely
         if (messageUserDisplayed[i] == 0) { // CMod AI message
            g.setColor(Color.red);
            g.fillRect(45, 260+i*100, 700, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 55, 290+i*100);
         }
         else if (messageUserDisplayed[i] == 1) { // pink user message
            g.setColor(new Color(254, 189, 225));
            g.fillRect(365, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 375, 290+i*100);
         }
         else if (messageUserDisplayed[i] == 2) { // blue user message
            g.setColor(new Color(103, 157, 255));
            g.fillRect(30, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 290+i*100);
         }
         else if (messageUserDisplayed[i] == 3) { // pink user left message
            g.setColor(new Color(254, 189, 225));
            g.fillRect(245, 260+i*100, 300, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 290, 290+i*100);
         }
         else { // blue user left message
            g.setColor(new Color(103, 157, 255));
            g.fillRect(245, 260+i*100, 300, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 290, 290+i*100);
         }
      }
      
      if (correct || (seeAllOptions && scores[scenario-1][currentOption].equals("3000"))) { // check mark (correct)
         g.setColor(Color.green);
         g.fillOval(595, 25, 175, 175);
         g.setColor(Color.black);
         int[] x = {645, 630, 665, 740, 725, 665};
         int[] y = {115, 130, 165, 90, 75, 135};
         g.fillPolygon(x, y, 6);
      }
      else if (okay || (seeAllOptions && scores[scenario-1][currentOption].equals("1000"))) { // horizontal line (okay)
         g.setColor(new Color(200, 150, 50));
         g.fillOval(595, 25, 175, 175);
         g.setColor(Color.black);
         g.fillRect(610, 97, 145, 30); 
      }
      else if (incorrect || (seeAllOptions && scores[scenario-1][currentOption].equals("-1000"))) { // x (incorrect)
         g.setColor(Color.red);
         g.fillOval(595, 25, 175, 175);
         g.setColor(Color.black);
         int[] x = {700, 740, 725, 685, 645, 630, 670, 630, 645, 685, 725, 740};
         int[] y = {117, 77, 62, 102, 62, 77, 117, 157, 172, 132, 172, 157};
         g.fillPolygon(x, y, 12);
      }
      
      // instructions to continue
      g.setColor(Color.black);
      g.fillRect(45, 765, 710, 100);
      g.setColor(new Color(254, 189, 225));
      g.fillRect(50, 770, 700, 90);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));     
      g.drawString("Press Enter to Continue", 95, 835);
      
      if (outOfTime) { // user ran out of time so the choices are automatically shown
         g.setFont(new Font("Calibri", Font.BOLD, 48));
         g.setColor(new Color(162, 210, 255));
         g.fillRect(48, 100, 702, 120);
         g.setColor(Color.black);
         g.drawString("You have ran out of time!", 135, 175);
      }
      
      selected = 0;
      game = false;
   }
   
   /**
    * Utility method to display the options the user has to select from
    * @param g An object which is a painting tool
    */
   public void displayOptions(Graphics g) {
      g.setColor(new Color(245, 228, 255));
      g.fillRect(15, 240, 760, 415);
      game = true;
      nextCounter = Integer.parseInt(messageText.get(counter));
      
      g.setFont(new Font("Calibri", Font.BOLD, 28));
      g.setColor(new Color(254, 189, 225));
      g.fillRect(170, 260, 450, 60);
      g.setColor(Color.black); 
      g.drawString("What action should CMod AI take?", 200, 300);
      
      // selected option in the menu
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
      
      // displays possible options
      g.setColor(Color.black);
      g.drawString("No Offence Made", 115, 440);
      g.drawString("Temporary Mute", 480, 440);
      g.drawString("Permanent Ban", 125, 590);
      g.drawString("(Skip) View Scenarios", 455, 590);
      numDisplayed = 0;
      
      // instructions to continue
      g.setColor(Color.black);
      g.fillRect(35, 695, 730, 170);
      g.setColor(new Color(254, 189, 225));
      g.fillRect(40, 700, 720, 160);
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 760);
      g.drawString("'Enter' to Continue.", 135, 830);
      
      this.repaint();
   }
   
   /**
    * Utility method to add the user's score to the highscores
    * If the score is lower than the top 10, then it will not be saved
    */
   public void addHighscore() {
   	int idx = -1; // the user's position on the leaderboard
      int overallScore = score + (perfectGame ? 5000 : 0); // user's overall score
   	String[][] arr = new String[10][2]; // leaderboard data
   	
   	try {
   	   BufferedReader br = new BufferedReader(new FileReader("textfiles/highscores.txt"));
   	   for (int i = 0; i < 10; i++) {
      		String line = br.readLine();
      		
      		// splits the data based on the slash (username and score split)
      		arr[i] = line.split("/", -1);
      		
      		// finding the correct index of the score
      		if (Integer.parseInt(arr[i][1]) < overallScore && idx == -1) {
      		    idx = i;
      		}
   	   }
   	   br.close();
   	}
      catch (IOException ioe) {  
         System.out.println("IOException Occurred. File may be missing.");
      }
   	
   	// do not add the highscore if it is not in the top 10
   	if (idx == -1) {
   	    return;
   	}
      
   	String temp = "" + overallScore;
   	while (temp.length() < 5) { // pads the score so that it is 5 digits long
   	    temp = "0" + temp;
   	}
   	try {
   	   PrintWriter pw = new PrintWriter(new FileWriter("textfiles/highscores.txt")); // writes to the highscores.txt file
   	   for (int i = 0; i < 10; i++) {
   		   if (i < idx) pw.println(arr[i][0] + "/" + arr[i][1]); // scores higher than the new score
   		   else if (i == idx) pw.println(username + "/" + temp); // new position for score
   		   else pw.println(arr[i-1][0] + "/" + arr[i-1][1]); // new position for score already placed, so everything shifted by 1
   	   }
   	   pw.close();
      }
      catch (IOException ioe) {  
         System.out.println("IOException Occurred.");
      }
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
