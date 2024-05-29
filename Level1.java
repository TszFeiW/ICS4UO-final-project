/**
 * This class is used to display Level 1 (Deficiencies level) for our game.
 * @version 1.0
 * May 29th, 2024
 * Time Spent: 3 hours
 * @author Tsz Fei Wang, Eric Ning
 *
 * Modifications: Class was created to play level 1 of the game.
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Level1 extends JComponent {
   private BufferedImage user; 
   private String username = ""; 
   private boolean finished = false;
   private boolean isUsername = true;
   private Color bg; 
   private char ch = 'x';
   //private long lastTime = 0; 
   //private static final long INTERVAL = (long)500;
   public Level1() {
      this.addKeyListener(new KeyHandler());
      try {
         user = ImageIO.read(new File("enter username.jpg"));
         bg = new Color(245,228,255);
      }
      catch (IOException ioe) {  
         System.out.println("Missing image file.");
      }
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
         if (isUsername) {
            
            ch = e.getKeyChar();
            System.out.println("char: " + ch);
            Level1.this.repaint();
         }
         //if (key == KeyEvent.VK_ENTER) currScreen++;
         //if (currScreen == 3) finished = true;
         
      }
      /*
      public void keyReleased(KeyEvent e) {
         long timePressed = e.getWhen(); 
         long diff = timePressed - lastTime; 
         System.out.println("hihihi");
         if(diff > INTERVAL) {
            char c = e.getKeyChar();
            username = username + c; 
            Level1.this.repaint();
            System.out.println("character entered: " + c); 
            System.out.println("username: " + username);
         }
         lastTime = timePressed; 
      }
      */
   }
   
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      if (isUsername) {
         g.drawImage(user, 0, 0, this);
         int temp = 0; 
         
         try {Thread.sleep(50);} catch (InterruptedException ie) {}
         char c = ch;
         
         if (c == 'x') return;
         else if (c == '\n' && username.length() > 1) { 
            isUsername = false;
            return;
         }
         else if (c == 8) {
            if (username.length() != 0) {
               username = username.substring(0, username.length() - 1);
               g.setColor(Color.black);
               g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
               g.drawString(username, 110, 700);
               c = 'x';
            }
         }
         else if (!Character.isLetterOrDigit(c) && c != '\n') {
            displayWarning("Please use alphanumerical characters.", g);
         }
         else if (c == '\n' && username.length() < 2) {
            displayWarning("This username you chose is too short.", g);
            temp++; if(temp > 5) isUsername = false;
         }
         else if(username.length() >= 20) {
            displayWarning("You cannot exceed twenty characters.", g);
            temp++; if(temp > 5) isUsername = false;
         }
         else {
            username += c;
            
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));             
            g.drawString(username, 110, 700);
             
            c = 'x';
            ch = 'x';
         }
         System.out.println("username: " + username);
      }
   }
   
   public void displayWarning(String message, Graphics g) {
      g.setFont(new Font("Calibri", Font.BOLD, 40));
      g.setColor(new Color(162, 210, 255));
      g.fillRect(50, 180, 710, 120);
      g.setColor(Color.black);
      g.drawString(message, 85, 250);
      g.setColor(Color.black);
      g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));             
      g.drawString(username, 110, 700);
   }
      
   public String getUsername() {
      return username;
   }
   
   public boolean getFinished() {
      return finished;
   }
}
