import java.awt.event.*;
/**
 * Controls all ActionEvents fired from all buttons in the program. It determines what happens
 * depending on what button was pressed
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 3 May 31st, 2012
 * 
 * version 3 changes - Added more actions for buttons
 */
class ActionController implements ActionListener
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * A reference variable to allow this class to connect to the mainmenu program. (Run reference
   * variable requiring methods from it)
   */
  private MainMenu parent;
  /**
   * A reference variable to allow this class to coonec to the gamescreen program. Gives access to
   * control the pauseMenu.
   */
  private GameScreen gameScreen;
  /**
   * Contructor which keeps a reference variable of the mainmenu program where it was
   * created in and the gameScreen.
   * @param parent The mainmenu program which created this class. Needed to call methods from it.
   * @param gameScreen the GameScreen object connected to the mainMenu.
   */
  public ActionController(MainMenu parent,GameScreen gameScreen)
  {
    this.parent=parent;
    this.gameScreen=gameScreen;
  }
  /**
   * The method that is called when a button is pressed. It determines which button was 
   * pressed and what to do.
   * @param ae Contains information about the event in which the button was pressed
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  String               stores the action command (the button pressed)</PRE>
   */
  public void actionPerformed(ActionEvent ae)
  {
    String temp=ae.getActionCommand();
    if(temp.equals("exit"))
    {
      System.exit(0);
    }
    else if(temp.equals("play"))
    {
      parent.switchScreens("LevelSelect");
    }
    else if(temp.equals("help"))
    {
      parent.switchScreens("Help");
    }
    else if(temp.equals("level/1"))
    {
      parent.switchScreens("GameScreen");
      parent.timerControl(0);
    }
    else if(temp.equals("pause"))
    {
      gameScreen.showPauseMenu();
    }
    else if(temp.equals("resumegame"))
    {
      gameScreen.hidePauseMenu();
    }
    else if(temp.equals("quittodesk"))
    {
      System.exit(0);
    }
    else if(temp.equals("quittomain"))
    {
      gameScreen.hidePauseMenu();
      parent.switchScreens("MainMenu");
    }
    else
    {
      if(temp.equals("back"))
      {
        parent.switchScreens("MainMenu");
      }
    }
  }
}