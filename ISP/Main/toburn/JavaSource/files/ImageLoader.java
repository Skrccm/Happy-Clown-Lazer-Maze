package files;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
/**
 * Class to generate images and imageicons, and inputstreams.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class ImageLoader
{
  /**
   * Returns a inputstream created from the filename
   * @param filename the path to create the inputstream from
   * @return An inputstream from the filename
   */
  static public InputStream inputStream(String filename)
  {
    return ImageLoader.class.getResourceAsStream(filename);
  }
  /**
   * Returns a image from the filename
   * @param filename The path to create the image from
   * @return A image from the filename
   */
  static public Image image(String filename)
  {
    try
    {
      Image image = ImageIO.read(new BufferedInputStream(inputStream(filename)));
      return image;
    }
    catch(IOException ioe)
    {
      return null;
    }
  }
  /**
   * Returns a imageicon from filename
   * @param filename the path to create the imageicon from
   * @return The imageicon from filename
   */
  static public ImageIcon imageIcon(String filename)
  {
    ImageIcon imageIcon = new ImageIcon(image(filename));
    return imageIcon;
  }
}