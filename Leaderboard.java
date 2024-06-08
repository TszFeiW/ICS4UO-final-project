import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * This class is used as the leaderboard screen/menu for our game. 
 * 
 * <p>
 * Version 1.0 
 * Time Spent: 2 hour
 * Class was created to display the leaderboard of the game.
 * Still needs comments and better formatting.
 * </p>
 * 
 * <p>
 * Version 1.1
 * Time Spent: 1 hour 
 * Program was modified so that it implements Runnable (fixes a few bugs in the program).
 * Better formatting has also been added to this class. Comments modified.
 * </p>
 *
 * <p>
 * Version 1.2
 * Time Spent: 20 minutes
 * Modifying comments to generate java docs properly
 * </p>
 *
 * <p>
 * Version 1.3
 * Time Spent: 5 minutes
 * Adding a new instance variable for the secondary background colour
 * and changed the colour of one of the boxes.
 * </p>
 *
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.3
 *
 * Chat-Mod AI Inc.
 * June 8th, 2024
 */
public class Leaderboard extends JComponent implements Runnable {

   /** allows for 200 ms delay between key presses */
   private static final long THRESHOLD = 200_000_000L;
   /** keeps track of last time a key has been pressed */
   private long lastPress;
   /** the colour of the background */
   private Color bg;
   /** the colour of the background of the text */
   private Color bg2;
   /** reads user information */
   private BufferedReader br;
   /** stores user data for top 10 scores */
   private String[] data;
   /** stores character pressed by user for input */
   private char ch;
   /** stores whether or not the program is done drawing */
   private boolean doneDrawing;
   /** stores whether or not the user is done with the leaderboard */
   private boolean finished;
   
   /**
    * Constructor of the class so that an instance of the class can be created in Main
    */
   public Leaderboard() {
      this.addKeyListener(new KeyHandler()); // adds the Key Listener
      // initializes instance variables
      bg = new Color(245,228,255);
      bg2 = new Color(224, 240, 244);
      data = new String[10];
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
            Leaderboard.this.repaint();
         }
      }
   }

   public void paintComponent(Graphics g) {
   	// clears the screen
   	g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
   	// title
      g.setColor(Color.black);
   	g.fillRect(185,25,430,80);
   	g.setColor(bg2);
   	g.fillRect(190,30,420,70);
   	g.setColor(Color.black);
   	g.setFont(new Font("Calibri", Font.BOLD, 70));
   	g.drawString("High Scores", 230, 85);
   	
   	// shows highscores
   	g.setColor(Color.black);
   	g.fillRect(20,135,757,730);
   	g.setColor(bg2);
   	g.fillRect(25,140,747,720);
   	g.setColor(Color.black);
   	
      // reading from the highscores.txt file
      try {
         br = new BufferedReader(new FileReader("highscores.txt"));
         for (int i = 0; i < 10; i++) {
      		String line = br.readLine();
            data[i] = line;
         }
         br.close();
      }
      catch (IOException ioe) {  
         System.out.println("IOException Occurred. File(s) may be missing.");
      }
      
      // drawing the highscores menu
   	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
	   for (int i = 1; i <= 10; i++) {
   		String savedData = data[i-1];

   		// stores the name and data into a variable 
   		String name = savedData.substring(0, savedData.indexOf("/")), score = savedData.substring(savedData.indexOf("/")+1);
   		
   		// depending on which highscore #, draws it onto the console at the correct location
   		g.setColor(Color.black);
   		if (i <= 5) {
   		    g.drawString(i + ". " + name, 50, 200 + (i-1) % 5 * 150);
   		    g.drawString(score, 300, 200 + (i-1) % 5 * 150);
   		}
   		else {
   		    g.drawString(i + ". " + name, 410, 200 + (i-1) % 5 * 150);
   		    g.drawString(score, 685, 200 + (i-1) % 5 * 150);
   		}
	   }
   	g.fillRect(384,140,5,720);
      
   	// waits until the user is ready to continue
   	g.setColor(Color.black);
   	g.fillRect(70,900,640,50);
   	g.setColor(bg2);
   	g.fillRect(75,905,630,40);
   	g.setColor(Color.black);
   	g.setFont(new Font("Calibri", 1, 20));
   	g.drawString("Press 'R' to reset highscores, press ENTER to return to the main menu.", 95, 930);
      
   	// if user chooses to reset the highscore file
      if (ch == 'R' || ch == 'r') {
   	   try {
   		   PrintWriter pw = new PrintWriter(new FileWriter("highscores.txt"));
   		   for (int i = 1; i <= 10; i++) {
   		      pw.println("User" + i + "/00000");
   		   }
   		   pw.close();
   	   }
   	   catch (IOException ioe) {
   		   System.out.println("IOException Occurred.");
   	   }
         this.repaint();
   	}
      else if (ch == '\n') {
         finished = true;
      }
   }
   
   /**
    * Method that allows threads to be run (starts a new thread)
    */
   public void run() {
      try {
         while (true) {
            Thread.sleep(200);
            if (finished) break; // until the user wishes to exit
         }
      } catch (InterruptedException ie) {
         System.out.println("InterruptedException has occured.");
      }
   }
}