package files;
import javax.swing.*;
import java.awt.*;
/**
 * JPanel that holds and controls the PauseMenu, SidePanel, and LaserGrid
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 * 
 * <br>version 4 chamges - added animate(),changeComponents(boolean x),readLevel(int level),
 * stopDrawing(), done(), getTime(), and resetLevel().
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
   * Reference variable to the win animation.
   */
  private WinScreen winScreen;
  /**
   * Contructor that sets up the gameScreen. Makes the panels and adds them.
   * @param parent reference to the parent program.
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
    winScreen=new WinScreen(this);
    winScreen.setBounds(0,0,700,550);
    add(winScreen);
    winScreen.setVisible(false);
    hidePauseMenu();
  }
  /**
   * Calls the animateLaser method of the LaserGrid
   */
  public synchronized void animate()
  {
    laserGrid.animateLaser();
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
    changeComponents(false);
    validate();
    repaint();
  }
  /**
   * Enables all game components and hides the pauseMenu.
   */
  public void hidePauseMenu()
  {
    changeComponents(true);
    pauseMenu.stop();
    timerControl(1);
    repaint();
  }
  /**
   * Changes the components, disable if false, enable if true.
   * @param x Whether to disable or enable all the components
   */
  public void changeComponents(boolean x)
  {
    for (Component component : gameScreen.getComponents())
      component.setEnabled(x);
    for (Component component : panel.getComponents())
    {
      component.setEnabled(x);
      component.setVisible(x);
    }
    for (Component component : laserGrid.getComponents())
    {
      component.setEnabled(x);
      component.setVisible(x);
    }
  }
  /**
   * Tells laser grid to read a level.
   * @param level The level to read
   */
  public void readLevel(int level)
  {
    laserGrid.readLevel(level);
  }
  /**
   * Tells the lasergrid to stopdrawing.
   */
  public void stopDrawing()
  {
    laserGrid.stopDrawing();
  }
  /**
   * Displays the win animation. Pauses the timer and runs the animation.
   */
  public void displayWinImage()
  {
    timerControl(2);
    changeComponents(false);
    winScreen.setVisible(true);
    winScreen.run();
  }
  /**
   * called when the win animation is completed. Hides the win animation and 
   * sends the user to the level complete screen.
   */
  public void done()
  {
    winScreen.setVisible(false);
    changeComponents(true);
    parent.switchScreens("LevelComplete");
    parent.updateScore();
  }
  /**
   * Returns the time of the timer.
   * @return the time elasped
   */
  public int getTime()
  {
    return panel.getTime();
  }
  /**
   * Tells the lasergrid to reset the level.
   */
  public void resetLevel()
  {
    laserGrid.resetLevel();
  }
}