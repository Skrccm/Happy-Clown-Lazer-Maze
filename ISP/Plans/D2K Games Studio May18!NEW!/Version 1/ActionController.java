import java.awt.event.*;
/**
 * Controls all ActionEvents fired from all buttons in the program. It determines what happens
 * depending on what button was pressed
 * 
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
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
   * Contructor which keeps a reference variable of the mainmenu program where it was
   * created in.
   * @param parent The mainmenu program which created this class. Needed to call methods from it.
   */
  public ActionController(MainMenu parent)
  {
    this.parent=parent;
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
    else
    {
      if(temp.equals("back"))
      {
        parent.switchScreens("MainMenu");
      }
    }
  }
}