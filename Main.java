
import java.awt.*;
import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    JFrame window = new JFrame("CMod Socializer");
    window.setSize(1920, 1080);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SplashScreen ss = new SplashScreen(new GridLayout(5,1));
    window.getContentPane().add(ss);
    window.setVisible(true);
  }
  
}

