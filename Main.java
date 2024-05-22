
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
   
   public static void main(String[] args) throws InterruptedException {
      JFrame window = new JFrame("CMod Socializer");
      window.setSize(810, 1080);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      SplashScreen ss = new SplashScreen();
      window.getContentPane().add(ss);
      ss.setFocusable(true);
      ss.requestFocusInWindow();
      window.setVisible(true);
      while (true) { // until the user makes a choice
         if (ss.getChoice() != -1) break;
      }
      if (ss.getChoice() == 1) { // chooses to exit
         window.setVisible(false);
      }
      window.getContentPane().remove(ss);
      
      
   }
  
}

