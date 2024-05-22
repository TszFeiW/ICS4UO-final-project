import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class SplashScreen extends JComponent {
   private BufferedImage logo;
   private BufferedImage play;
   private BufferedImage play2;
   private BufferedImage exit;
   private BufferedImage exit2;
   private boolean selected;
   private int choice = -1;
   private Color bg;

   public SplashScreen() {
      this.addKeyListener(new KeyHandler());
      try {
         logo = ImageIO.read(new File("logo.png"));
         play = ImageIO.read(new File("playButton.png"));
         play2 = ImageIO.read(new File("playButton2.png"));
         exit = ImageIO.read(new File("exitButton.png"));
         exit2 = ImageIO.read(new File("exitButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      selected = true; // on the first button
      bg = new Color(245, 228, 255);
   }
   
   private class KeyHandler extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_DOWN)
            selected = false;
         else if (key == KeyEvent.VK_UP)
            selected = true;
         else if (key == KeyEvent.VK_ENTER)
            choice = selected ? 0 : 1;
            
         System.out.println(choice);
         SplashScreen.this.repaint();
      }
   }
   
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      int w = getWidth();
      int h = getHeight();

      g.drawImage(logo, 245, 100, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 50));
      g.drawString("Chat-Mod AI Inc.", 235, 520);
      
      if (selected) {
         System.out.println("wee");
         g.drawImage(play2, 255, 600, this);
         g.drawImage(exit, 255, 720, this);
      }
      else {
         System.out.println("woo");
         g.drawImage(play, 255, 600, this);
         g.drawImage(exit2, 255, 720, this);
      }
      
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 900);
      g.drawString("‘Enter’ to Continue.", 135, 970);
   }
   
   public int getChoice() {
      return choice;
   }
}

