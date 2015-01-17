import javax.swing.*;
import java.awt.*;
/**
 * The level select screen. Allows the user to choose a level to play, or go back to the mainmenu screen.
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
 */
class LevelSelect extends JPanel
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
   * Sets up default settings for the screen, including transparency, size, and layout.
   * It adds a title image and a back button, plus level buttons
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  JLabel               stores a JLabel</PRE>
   * <PRE>back                  JButton              stores a back button</PRE>
   * <PRE>backPanel             JPanel               stores the JPanel</PRE>
   * <PRE>levels                JPanel               stores the JPanel that contains the level select screen</PRE>
   */
  public LevelSelect(MainMenu parent)
  {
    this.parent=parent;
    setOpaque(false);
    setPreferredSize (new Dimension (700, 550));
    setLayout(new BorderLayout(5,5));
    JLabel temp=new JLabel(new ImageIcon("images/levelselect/levelselect.png"));
    add(temp,BorderLayout.NORTH);
    JButton back=ButtonMaker.makeButton("back",parent);
    JPanel backPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    backPanel.setOpaque(false);
    backPanel.add(back);
    add(backPanel,BorderLayout.SOUTH);
    JPanel levels=new JPanel(new FlowLayout(FlowLayout.LEFT));
    levels.setOpaque(false);
    for(int x=1;x<=10;x++)
    {
      JButton buttons=ButtonMaker.makeButton("level/"+x,parent);
      levels.add(buttons);
    }
    add(levels,BorderLayout.CENTER);
  }
}