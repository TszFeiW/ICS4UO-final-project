import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

//canvas is similar to creating graphics window in Processing.
//JPanel is the container which holds all gui objects and must be created first
public class SplashScreen extends JPanel
{
  private BufferedImage logo;
  private BufferedImage play;
  private BufferedImage exit;
  Color color = new Color(245, 228, 255);

  public SplashScreen(GridLayout gl)
  {
    this.setLayout(gl);
    try {
      logo = ImageIO.read(new File("logo.png"));
    }
    catch (IOException ioe) {
    
    }
  }

  public void paintComponent(Graphics g)
  {
    setBackground(color);
    super.paintComponent(g);
    int w = getWidth();
    int h = getHeight();

    int x0 = 0;
    int y0 = 200;
    g.drawImage(logo, 100, 100, this);
    
    g.setColor(Color.black);
    g.setFont(new Font("SansSerif", Font.PLAIN, 50));
    g.drawString("Chat-Mod AI Inc.", x0, y0);
  }
}

