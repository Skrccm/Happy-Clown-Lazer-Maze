package files;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.imageio.stream.*;
/**
 * The screen that displays a animation of a lazer hitting mirrors, displaying the
 * company logo
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 * 
 * <br>version 3 changes - compatible with aniamted gifs.
 */
public class SplashScreen extends JPanel
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The JLabel to put the animation images on
   */
  private JLabel animation;
  /**
   * Adds a jlabel taking up the entire jpanel.
   */
  public SplashScreen()
  {
    animation=new JLabel(ImageLoader.imageIcon("/images/logo/logo.png"));
    animation.setPreferredSize(new Dimension(700,550));
    add(animation);
  }
  /**
   * Opens a gif and runs it.
   */
  public synchronized void run()
  {
    try
    {
      ImageReader imageReader = (ImageReader) ImageIO.getImageReadersBySuffix("gif").next();
      ImageInputStream imageInputStream = ImageIO.createImageInputStream(ImageLoader.inputStream("/images/splash/test.gif"));
      imageReader.setInput(imageInputStream, false);
      for (int i = 0;; ++i) {
        try
        {
          animation.setIcon(new ImageIcon(imageReader.read(i)));
          Thread.sleep(10);
        }
        catch(IndexOutOfBoundsException ioobe)
        {
          break;
        }
        catch(InterruptedException ie)
        {
        }
      }
      try{
        Thread.sleep(2000);
      }
      catch(InterruptedException ie)
      {
      }
    }
    catch(FileNotFoundException fnfe)
    {
    }
    catch(IOException ioe)
    {
    }
    animation=null;
  }
}