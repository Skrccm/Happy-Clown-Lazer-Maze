package files;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * This class is displays a clown image that slowly fades in then out.
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 */
public class FadingClown extends JLabel implements ActionListener
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * A reference variable to give access to the parent program.
   */
  private PauseMenu parent;
  /**
   * The clown image to display
   */
  private ImageIcon image;
  /**
   * Holds the transparency value
   */
  private float alpha=0.0f;
  /**
   * 0 if fading in, 1 if fading out.
   */
  private int dir=0;
  /**
   * Timer that controls fading rate.
   */
  private Timer timer;
  /**
   * Contructor sets parent program and image to display, and starts the timer.
   */
  public FadingClown(PauseMenu parent,ImageIcon image)
  {
    setOpaque(false);
    this.parent=parent;
    this.image=image;
    timer=new Timer(10,this);
    timer.start();
  }
  
  /**
   * Overrides the drawing method to draw the image instead.
   * Sets the transparency of the image as well.
   * @param g The graphics object to draw on.
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>g2d                   Graphics2D           Allows extra features to draw the image (transparency)
   */
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    repaint();
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g2d.setColor(Color.BLACK);
    g2d.drawImage (image.getImage(),0, 0,null);
    g2d.dispose();
  }
  /**
   * Called when completed fading in and out, it removes itself from the pauseMenu
   * and stops the timer.
   */
  public void done()
  {
    parent.removeClown(this);
    timer.stop();
  }
  /**
   * Called every time the timer fires. Changes the transparency of the image and 
   * repaints it.
   * @param ae Holds extra information on the actionevent. (Not used)
   */
  public void actionPerformed(ActionEvent ae)
  {
    if(dir==0)
    {
      if(alpha<0.5f)
      {
        alpha+=0.01f;
      }
      else
      {
        dir=1;
      }
    }
    else
    {
      if(alpha>0.01f)
      {
        alpha-=0.01f;
      }
      else
      {
        done();
      }
    }
    repaint();
  }
}