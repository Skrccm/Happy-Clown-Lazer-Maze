import javax.swing.*;
import java.awt.*;
/**
 * JPanel that holds and controls the PauseMenu, SidePanel, and LaserGrid
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 */
public class GameScreen extends JPanel
{
  /**
   * Reference variable to control the sidePanel.
   */
  private SidePanel panel;
  /**
   * Reference variable to access the mainMenu program.
   */
  private MainMenu parent;
  /**
   * Reference variable to control the gameScreen.
   */
  private JPanel gameScreen;
  /**
   * Reference variable to control the pauseMenu.
   */
  private PauseMenu pauseMenu;
  /**
   * Reference variable to control the lasergrid.
   */
  private LaserGrid laserGrid;
  /**
   * Stores the Serial Version UID.
   */
  private static final long serialVersionUID = 1L;
  /**
   * Contructor that sets up the gameScreen. Makes the panels and adds them.
   */
  public GameScreen(MainMenu parent)
  {
    setLayout(null);
    setOpaque(false);
    gameScreen=new JPanel();
    gameScreen.setBounds(0,0,700,550);
    gameScreen.setOpaque(false);
    add(gameScreen);
    gameScreen.setLayout(new BorderLayout(5,5));
    this.parent=parent;
    panel=new SidePanel(parent);
    panel.setOpaque(false);
    laserGrid=new LaserGrid(parent);
    gameScreen.add(laserGrid,BorderLayout.CENTER);
    laserGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
    gameScreen.add(panel,BorderLayout.EAST);
    pauseMenu=new PauseMenu(parent);
    pauseMenu.setBounds(0,0,700,550);
    add(pauseMenu);
    hidePauseMenu();
  }
  /**
   * Calls the sidePanel to control the timer.
   * @param x integer controlling the timer (0 to restart, 1 to continue, 2 to pause, 3 to stop and set to 0)
   */
  public void timerControl(int x)
  {
    panel.timerControl(x);
  }
  /**
   * Disables all game components and shows the pauseMenu.
   */
  public void showPauseMenu()
  {
    pauseMenu.run();
    timerControl(2);
    for (Component component : gameScreen.getComponents())
      component.setEnabled(false);
    for (Component component : panel.getComponents())
    {
      component.setEnabled(false);
      component.setVisible(false);
    }
    for (Component component : laserGrid.getComponents())
    {
      component.setEnabled(false);
      component.setVisible(false);
    }
    validate();
    repaint();
  }
  /**
   * Enables all game components and hides the pauseMenu.
   */
  public void hidePauseMenu()
  {
    for (Component component : gameScreen.getComponents())
      component.setEnabled(true);
    for (Component component : panel.getComponents())
    {
      component.setEnabled(true);
      component.setVisible(true);
    }
    for (Component component : laserGrid.getComponents())
    {
      component.setEnabled(true);
      component.setVisible(true);
    }
    pauseMenu.stop();
    timerControl(1);
    repaint();
  }
}