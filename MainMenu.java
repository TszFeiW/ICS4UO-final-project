import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class MainMenu extends JComponent {
   private BufferedImage instructions;
   private BufferedImage instructions2;
   private BufferedImage firstLevel;
   private BufferedImage firstLevel2;
   private BufferedImage secondLevel;
   private BufferedImage secondLevel2;
   private BufferedImage quit;
   private BufferedImage quit2;
   
   private int selected = 0;
   private int choice = -1;
   private Color bg;

   public MainMenu() {
      this.addKeyListener(new KeyHandler());
      try {
         instructions = ImageIO.read(new File("instructionsButton.png"));
         instructions2 = ImageIO.read(new File("instructionsButton2.png"));
         firstLevel = ImageIO.read(new File("level1Button.png"));
         firstLevel2 = ImageIO.read(new File("level1Button2.png"));
         secondLevel = ImageIO.read(new File("level2Button.png"));
         secondLevel2 = ImageIO.read(new File("level2Button2.png"));
         quit = ImageIO.read(new File("quitGameButton.png"));
         quit2 = ImageIO.read(new File("quitGameButton2.png"));
      }
      catch (IOException ioe) {
         System.out.println("Missing image file.");
      }
      selected = 0; // on the first button
      bg = new Color(245, 228, 255);
   }
   
   private class KeyHandler extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         
         if (key == KeyEvent.VK_DOWN)
            selected = Math.min(selected+1, 4);
         else if (key == KeyEvent.VK_UP)
            selected = Math.max(selected-1, 0);
         else if (key == KeyEvent.VK_ENTER)
            choice = selected;
         
         MainMenu.this.repaint();
      }
   }
   
   public void paintComponent(Graphics g) {
      g.setColor(bg);
      g.fillRect(0, 0, 810, 1080);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 100));
      g.drawString("CMOD Socializer", 65, 150);
      
      g.drawImage(instructions, 205, 250, this);
      g.drawImage(firstLevel, 205, 400, this);
      g.drawImage(secondLevel, 205, 550, this);
      g.drawImage(quit, 205, 700, this);
      
      if (selected == 0)
         g.drawImage(instructions2, 205, 250, this);
      else if (selected == 1)
         g.drawImage(firstLevel2, 205, 400, this);
      else if (selected == 2)
         g.drawImage(secondLevel2, 205, 550, this);
      else 
         g.drawImage(quit2, 205, 700, this);
      
      g.setColor(Color.black);
      g.setFont(new Font("Calibri", Font.BOLD, 64));
      g.drawString("Use Arrow Keys and press", 50, 900);
      g.drawString("‘Enter’ to Continue.", 135, 970);
   }
   
   public int getChoice() {
      return choice;
   }
}

