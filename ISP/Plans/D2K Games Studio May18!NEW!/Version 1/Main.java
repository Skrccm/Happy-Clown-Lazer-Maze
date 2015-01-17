import java.awt.*;
import javax.swing.*;
/**
 * Sets up the splash screen and loads the program. After loading, it runs the animation.
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
 */
class Main extends JFrame
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Sets up the JFrame, adding a splashscreen and running an animation after it loads the program.
   * Setting up includes: default close operations, set to not resizable, no border, middle of the screen.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>dim                   Dimension            stores the dimension of the screen</PRE>
   * <PRE>x                     int                  stores the horizontal length of the JPanel</PRE>
   * <PRE>y                     int                  stores the vertical length of the JPAnel</PRE>
   * <PRE>s                     SplashScreen         stores an instance of SplashScreen</PRE>
   * <PRE>t                     MainMenu             stores an instance of MainMenu</PRE>
   */
  public Main ()
  {
    setTitle ("Happy Klown: Lazer Maze");
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setResizable (false);
    setUndecorated (true);
    Dimension dim = Toolkit.getDefaultToolkit ().getScreenSize ();
    int x = (dim.width - 640) / 2;
    int y = (dim.height - 500) / 2;
    setLocation (x, y);
    SplashScreen s=new SplashScreen();
    getContentPane ().add (s);
    setFocusable(true);
    pack ();
    setVisible (true);
    MainMenu t = new MainMenu ();
    s.run();
    remove (s);
    getContentPane ().add (t);
    validate();
  }
  /**
   * The main method to run. Creates a object of itself. (runs the constructor)
   * 
   * @param args Arguments that this program has been set to run with
   */
  public static void main (String[] args)
  {
    new Main ();
  }
}
