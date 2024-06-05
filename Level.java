import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

abstract class Level extends JComponent {

   protected String username = ""; 
   protected Color bg;
   protected boolean finished;
   
   public Level(String username, Color bg) {
      this.username = username;
      this.bg = bg;
   }
   
   abstract void displayBackground(Graphics g);
   abstract void displayMessages(Graphics g);
   abstract void displayTransition(Graphics g);
   
   /**
    * This method allows the Main class to access whether the user is done reading or not
    * @return Whether the user has finished the level
    */
   public boolean getFinished() {
      return finished;
   }
}