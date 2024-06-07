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
 * @author Eric Ning, Tsz Fei Wang
 * @version 1.1
 *
 * Chat-Mod AI Inc.
 * June 6th, 2024
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Leaderboard extends JComponent implements Runnable {

   private Color bg;
   private BufferedReader br;
   private String[] data;
   private char ch;
   private boolean doneDrawing;
   private boolean finished;
   
   public Leaderboard() {
      this.addKeyListener(new KeyHandler()); // adds the Key Listener
      bg = new Color(245,228,255);
      data = new String[10];
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
         Leaderboard.this.repaint();
      }
   }

   public void paintComponent(Graphics g) {
   	// clears the screen
   	g.setColor(bg);
      g.fillRect(0, 0, 810, 1020);
      
   	// title
      g.setColor(Color.black);
   	g.fillRect(185,25,430,80);
   	g.setColor(new Color(224, 240, 244));
   	g.fillRect(190,30,420,70);
   	g.setColor(Color.black);
   	g.setFont(new Font("Calibri", Font.BOLD, 70));
   	g.drawString("High Scores", 230, 85);
   	
   	// shows highscores
   	g.setColor(Color.black);
   	g.fillRect(20,135,757,730);
   	g.setColor(new Color(224, 240, 244));
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
   	g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
	   for (int i = 1; i <= 10; i++) {
   		String savedData = data[i-1];

   		// stores the name and data into a variable 
   		String name = savedData.substring(0, savedData.indexOf("/")), score = savedData.substring(savedData.indexOf("/")+1);
   		
   		// depending on which highscore #, draws it onto the console at the correct location
   		g.setColor(Color.black);
   		if (i <= 5) {
   		    g.drawString(i + ". " + name, 50, 200 + (i-1) % 5 * 120);
   		    g.drawString(score, 260, 200 + (i-1) % 5 * 120);
   		}
   		else {
   		    g.drawString(i + ". " + name, 420, 200 + (i-1) % 5 * 120);
   		    g.drawString(score, 630, 200 + (i-1) % 5 * 120);
   		}
	   }
   	g.fillRect(384,140,5,720);
      
   	// waits until the user is ready to continue
   	g.setColor(Color.black);
   	g.fillRect(70,900,640,50);
   	g.setColor(Color.white);
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
   
   public void run() {
      try {
         while (true) {
            Thread.sleep(500);
            if (finished) break;
         }
      } catch(Exception ex) {
    
      }
   }
}