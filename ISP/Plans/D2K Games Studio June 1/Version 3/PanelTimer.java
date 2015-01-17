import javax.swing.*;
import java.awt.event.*;
/**
 * This class contains the timer for the game.
 * @author Jun Hee Cho, Calvin Chan
 * @since 31 May 2012
 * @version 3
 */ 
public class PanelTimer extends JPanel implements ActionListener
{
  /**
   * timer to fire every second.
   */
  private Timer timer;
  /**
   * Contains 5 jlabels for each number
   */
  private JLabel[] numbers;
  /**
   * Contains the time
   */
  private int counter;
  /**
   * Holds all numbers as images.
   */
  private ImageIcon[] images;
  /**
   * The constructor for the Timer loads all images and sets the seconds and minutes to 0 and starts the timer.
   */ 
  public PanelTimer ()
  {
    timer=new Timer(1000,this);
    timer.setInitialDelay(1000);
    timer.setRepeats(true);
    numbers=new JLabel[5];
    images=new ImageIcon[10];
    for(int x=0;x<10;x++)
    {
      images[x]=new ImageIcon("images/numbers/"+x+".png");
    }
    for(int x=0;x<5;x++)
    {
      if(x==2)
      {
        numbers[x]=new JLabel(new ImageIcon("images/numbers/colon.png"));
      }
      else
      {
        numbers[x]=new JLabel(images[0]);
      }
      add(numbers[x]);
    }
  }
  /**
   * Sets the time to 0 and restarts the timer.
   */
  public void start()
  {
    counter=0;
    setNumbers(0);
    timer.restart();
  }
  /**
   * Stops the timer and sets it to 0.
   */
  public void stop()
  {
    timer.stop();
    setNumbers(0);
  }
  /**
   * Returns the time as an integer
   * @return the time
   */
  public int getTime()
  {
    return counter;
  }
  /**
   * Controls the timer depending on the value of x.
   * @param x x integer controlling the timer (0 to restart, 1 to continue, 2 to pause, 3 to stop and set to 0)
   */
  public void timerControl(int x)
  {
    if(x==0)
    {
      start();
    }
    else if(x==1)
    {
      timer.start();
    }
    else if(x==2)
    {
      timer.stop();
    }
    else
    {
      stop();
    }
  }
  /**
   * Sets the jlabels to the correct time.
   * @param the number of seconds past
   */
  public void setNumbers(int count)
  {
    String seconds = Integer.toString (count % 60);
    String minutes = Integer.toString ((int) (count / 60) % 60);
    if (Integer.parseInt (seconds) < 10)
      seconds = "0" + seconds;
    if (Integer.parseInt (minutes) < 10)
      minutes = "0" + minutes;
    String full=minutes+":"+seconds;
    for(int x=0;x<5;x++)
    {
      if(x!=2)
        numbers[x].setIcon(images[Integer.parseInt(""+full.charAt(x))]);
    }
  }
  /**
   * Called every second, increases the timer.
   */
  public void actionPerformed(ActionEvent ae)
  {
    counter++;
    setNumbers(counter);
  }
}