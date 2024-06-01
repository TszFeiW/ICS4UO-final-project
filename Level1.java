/**
 * This class is used to display Level 1 (Deficiencies level) for our game.
 * @version 1.0
 * May 29th, 2024
 * Time Spent: 8 hours
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
   private BufferedImage instructionsL1;
   private BufferedImage user; 
   private BufferedImage stool;
   private BufferedImage level1text;
   private BufferedImage desk;
   private BufferedImage person;
   private BufferedImage personComputer;
   private BufferedImage computer;
   private BufferedImage computerPeople;
   private BufferedImage transition1;
   private String username = ""; 
   private boolean finished = false;
   private int currScene;
   private int counter;
   private Color bg; 
   private char ch = '\\';
   int numDisplayed;
   int numMessages;
   public String[] messageTextDisplayed;
   public int[] messageUserDisplayed;
   public String[] messageText;
   public int[] messageUser;
   //private long lastTime = 0; 
   //private static final long INTERVAL = (long)500;
   public Level1() {
      this.addKeyListener(new KeyHandler());
      try {
         instructionsL1 = ImageIO.read(new File("instructionsL1.png"));
         user = ImageIO.read(new File("enter username.jpg"));
         stool = ImageIO.read(new File("stool.png"));
         level1text = ImageIO.read(new File("level1text.png"));
         desk = ImageIO.read(new File("desk.png"));
         person = ImageIO.read(new File("person.png"));
         personComputer = ImageIO.read(new File("personComputer.png"));
         computer = ImageIO.read(new File("computer.png"));
         computerPeople = ImageIO.read(new File("computerPeople.png"));
         transition1 = ImageIO.read(new File("transition1.png"));
         bg = new Color(245,228,255);
         messageTextDisplayed = new String[4];
         messageUserDisplayed = new int[4];
         messageText = new String[20];
         messageUser = new int[20];
         numMessages = 0; 
         
         BufferedReader br = new BufferedReader(new FileReader("level1.txt"));
         for (int i = 0; i < 20; i++) {
            String line = br.readLine();
            if (line == null) break;
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
         ch = e.getKeyChar();
         Level1.this.repaint();
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
      if (currScene == 0) {
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1080);
         g.drawImage(user, 0, 0, this);
         
         try {Thread.sleep(50);} catch (InterruptedException ie) {}
         char c = ch;
         
         if (c == '\\') return;
         else if (c == '\n' && username.length() > 1) { 
            currScene = 1;
            ch = '\\';
            this.repaint();
         }
         else if (c == 8) {
            if (username.length() != 0) {
               username = username.substring(0, username.length() - 1);
               g.setColor(Color.black);
               g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
               g.drawString(username, 110, 700);
               c = '\\';
            }
         }
         else if (!Character.isLetterOrDigit(c) && c != '\n') {
            displayWarning("Please use alphanumerical characters.", g);
         }
         else if (c == '\n' && username.length() < 2) {
            displayWarning("This username you chose is too short.", g);
         }
         else if(username.length() >= 20) {
            displayWarning("You cannot exceed twenty characters.", g);
         }
         else {
            username += c;
            
            g.setColor(Color.black);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));             
            g.drawString(username, 110, 700);
             
            c = '\\';
            ch = '\\';
         }
      }
      else if (currScene == 1) {
         g.setColor(bg);
         g.fillRect(0, 0, 810, 1080);
         g.drawImage(instructionsL1, -10, -50, this);
         if (ch == '\n') {
            ch = '\\';
            currScene++;
            this.repaint();
         }
      }
      else if (currScene == 2) {
         if (counter < 100) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1080);
            g.drawImage(level1text, 100, 150, this);
            g.drawImage(desk, 30, 350, this);
            g.drawImage(stool, 135, 700, this);
            g.drawImage(person, 650-counter, 550, this);
            g.setColor(Color.white);
            g.fillRect(842-counter, 550, 1, 400);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 355) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1080);
            g.drawImage(level1text, 100, 150, this);
            g.drawImage(desk, 30, 350, this);
            g.drawImage(stool, 135, 700, this);
            g.drawImage(person, 550, 550, this);
            g.setColor(new Color(0, 0, 0, counter-100));
            g.fillRect(0, 0, 810, 1080);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else if (counter < 610) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 810, 1080);
            g.drawImage(personComputer, 30, 350, this);
            g.setColor(new Color(0, 0, 0, 610-counter));
            g.fillRect(0, 0, 810, 1080);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            this.repaint();
         }
         else {
            try {Thread.sleep(100);} catch (InterruptedException ie) {}
            currScene++;
            counter = 0;
            ch = '\\';
            this.repaint();
         }
      }
      else {
         if (counter < 125) {
            g.setColor(new Color(224, 240, 244));
            g.fillRect(0, 0, 810, 1080);
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
         else if (counter < 199) {
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
            g.setColor(new Color(162, 210, 255, (counter-100)*2));
            g.fillRect(20, 240, 750, 420);
            try {Thread.sleep(10);} catch (InterruptedException ie) {}
            counter++;
            ch = '\\';
            this.repaint();
         }
         else if (counter < 220) {
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
            
            g.setColor(new Color(254, 189, 225));
            g.fillRect(50, 880, 700, 90);
            g.setColor(Color.black);
            g.setFont(new Font("Calibri", Font.BOLD, 64));     
            g.drawString("Press Enter to Continue", 95, 945); 
            try {Thread.sleep(50);} catch (InterruptedException ie) {}
            if (ch == '\n') {
               ch = '\\';
               counter++;
               if (counter == 220) {
                  this.repaint();
                  return;
               }
            }
            else {
               displayMessages(g);
               return;
            }
            
            int nextMessage = counter-200; // index of next message in array
            if (messageUser[nextMessage] == -1) {
               numDisplayed = 0;
            }
            else if (numDisplayed == 4) {
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
         else {
            finished = true;
         }
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
   
   public void displayMessages(Graphics g) {
      Font calibri = new Font("Calibri", Font.BOLD, 20);
      g.setFont(calibri);
      for (int i = 0; i < numDisplayed; i++) {
         if(numMessages == 61) {
            g.drawImage(transition1, 0, 0, this);
            numMessages++;
         }
         else if (messageUserDisplayed[i] == 0) {
            g.setColor(Color.red);
            g.fillRect(45, 260+i*100, 700, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 55, 290+i*100);
            numMessages++;
         }
         else if (messageUserDisplayed[i] == 1) {
            g.setColor(new Color(254, 189, 225));
            g.fillRect(365, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 375, 290+i*100);
            numMessages++;
         }
         else if (messageUserDisplayed[i] == 2) {
            g.setColor(new Color(103, 157, 255));
            g.fillRect(30, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 290+i*100);
            numMessages++;
         }
         else {
            g.setColor(new Color(126, 217, 87));
            g.fillRect(30, 260+i*100, 395, 60);
            g.setColor(Color.black); 
            g.drawString(messageTextDisplayed[i], 40, 290+i*100);
         }
      }
   }
      
   public String getUsername() {
      return username;
   }
   
   public boolean getFinished() {
      return finished;
   }
}
