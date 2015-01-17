package files;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.imageio.stream.*;
/**
 * Displays the Win animation
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class WinScreen extends JPanel implements ActionListener
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
  private int counter=-1;
  /**
   * Array of images to draw
   */
  private ImageIcon[] images;
  /**
   * Timer to control the animation speed
   */
  private Timer timer;
  /**
   * Reference variable to the gamescreen parent.
   */
  private GameScreen parent;
  /**
   * sets up the jpanel, jlabels, and loads images.
   * @param parent reference variable to the parent
   */
  public WinScreen(GameScreen parent)
  {
    this.parent=parent;
    images=new ImageIcon[166];
    animation=new JLabel();
    setLayout(new FlowLayout(FlowLayout.CENTER,0,50));
    setPreferredSize(new Dimension(700,500));
    setBackground(Color.WHITE);
    animation.setPreferredSize(new Dimension(550,400));
    add(animation);
    timer=new Timer(25,this);
    try
    {
      ImageReader imageReader = (ImageReader) ImageIO.getImageReadersBySuffix("gif").next();
      ImageInputStream imageInputStream = ImageIO.createImageInputStream(ImageLoader.inputStream("/images/win/win.gif"));
      imageReader.setInput(imageInputStream, false);
      for (int i = 0;i<166; ++i) {
        images[i]=new ImageIcon(imageReader.read(i));
      }
    }
    catch(FileNotFoundException fnfe)
    {
    }
    catch(IOException ioe)
    {
    }
  }
  /**
   * Starts the animation
   */
  public void run()
  {
    counter=-1;
    timer.start();
  }
  /**
   * Called every time the timer fires, increments the image to draw
   * @param ae Contains more information about the event.
   */
  public void actionPerformed(ActionEvent ae)
  {
    counter++;
    animation.setIcon(images[counter]);
    if(counter==165)
    {
      timer.stop();
      parent.done();
    }
  }
}