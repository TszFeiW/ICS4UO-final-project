/**
 * This class is used to display the Main Menu for our game.
 * @version 1.0
 * May 27th, 2024
 * Time Spent: 1 hour
 * @author Tsz Fei Wang
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
   private Color bg; 
   private long lastTime = 0; 
   private static final long INTERVAL = (long)500;
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
         int key = e.getKeyCode();
         //if (key == KeyEvent.VK_ENTER) currScreen++;
         //if (currScreen == 3) finished = true;
         
      }
      
      public void keyReleased(KeyEvent e) {
         long timePressed = e.getWhen(); 
         long diff = timePressed - lastTime; 
         if(diff > INTERVAL) {
            char c = e.getKeyChar();
            username = username + c; 
            Level1.this.repaint();
            System.out.println("character entered: " + c); 
            System.out.println("username: " + username);
         }
         lastTime = timePressed; 
      }
   }
   
   public void paintComponent(Graphics g) {
      g.drawImage(user, 0, 0, this);
      char c = username.charAt(username.length()-1); 
      int temp = 0; 
        
      while(true) {
         if (c == '\n' && username.length() >= 1) { 
            username = username.substring(0, username.length()-1); 
            break;
         }
         if (c == 8) {
            if (username.length() == 1) {
               JOptionPane.showMessageDialog(null, "There are no characters to delete.", "Error", JOptionPane.WARNING_MESSAGE);
               username = ""; 
               continue;
            } 
            else {
               username = username.substring(0, username.length() - 2);
               g.setColor(bg);
               g.fillRect(500, 420, 350, 100);
               g.setColor(Color.black);
               g.drawString(username, 480, 478);
               continue;
            }
         }
         while (!Character.isLetterOrDigit(c)) {
             JOptionPane.showMessageDialog(null, "Please only use alphanumerical characters.", "Error", JOptionPane.WARNING_MESSAGE);
             username = username.substring(0, username.length()-1);
         }
          if (c == '\n' && username.length() < 2) {
              JOptionPane.showMessageDialog(null, "Username too short!", "Error", JOptionPane.WARNING_MESSAGE);
              username = username.substring(0, username.length()-1);
              temp++; if(temp > 5) break; 
              continue;
          }
         
          if(username.length() > 20) {
              JOptionPane.showMessageDialog(null, "Username has reached maximum length!", "Error", JOptionPane.WARNING_MESSAGE);
              username.substring(0, username.length()-1);
              temp++; if(temp > 5) break; 
              continue;
          }
          if (c != 8 && c != '\n') continue;
          c = ' '; 
          g.setColor(bg);
          g.fillRect(520, 520, 350, 100);
          g.setColor(Color.black);
          g.drawString(username, 480, 478);
      }
  }
      
   public String getUsername() {
      return username;
   }
   
   public boolean getFinished() {
      return finished;
   }
}
