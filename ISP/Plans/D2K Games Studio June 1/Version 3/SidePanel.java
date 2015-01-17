import javax.swing.*;
/**
 * Holds the timer, go, and pause buttons
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 */
public class SidePanel extends JPanel
{
  /**
   * Reference variable to the mainmenu program.
   */
  private MainMenu parent;
  /**
   * The timer.
   */
  private PanelTimer timer;
  /**
   * Stores the Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Sets up the panel and adds buttons.
   * @param parent The parent program
   */
  public SidePanel(MainMenu parent)
  {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.parent=parent;
    timer=new PanelTimer();
    timer.setOpaque(false);
    JButton run=ButtonMaker.makeButton("run",parent);
    JButton pause=ButtonMaker.makeButton("pause",parent);
    add(timer);
    add(run);
    add(pause);
  }
  /**
   * Controls the timer.
   * @param x integer controlling the timer (0 to restart, 1 to continue, 2 to pause, 3 to stop and set to 0)
   */
  public void timerControl(int x)
  {
    timer.timerControl(x);
  }
}