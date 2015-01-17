import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
/**
 * Easily creates buttons with predefined settings and sets the icons
 * @author Calvin Chan, JunHee Cho
 * @version 1 May 18th, 2012
 */
class ButtonMaker
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Creates a JButton with a image from button. If a rollover or pressed icon exists, it also
   * sets them accordingly. Next it makes the button background transparent, center aligned, no margins, 
   * and adds a ActionListener which is connected to the parent, which is the mainMenu class.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  JButton              stores a newly created JButton with an image icon</PRE>
   * @param button The link to get the buttom from and the action command to set the button to
   * @param parent The ActionListener to set this button to connect to. (the Mainmenu class)
   * @return returns a JButton with the above settings.
   */
  static public JButton makeButton(String button,ActionListener parent)
  {
    JButton temp = new JButton (new ImageIcon ("images/buttons/"+button+"/button.png"));
    if(new File("images/buttons/"+button+"/button_rollover.png").exists())
    {
      temp.setRolloverIcon (new ImageIcon ("images/buttons/"+button+"/button_rollover.png"));
    }
    if(new File("images/buttons/"+button+"/button_pressed.png").exists())
    {  
      temp.setPressedIcon(new ImageIcon("images/buttons/"+button+"/button_pressed.png"));
    }
    temp.setBorder (null);
    temp.setBorderPainted(false);
    temp.setContentAreaFilled (false);
    temp.setOpaque(false);
    temp.setFocusable(false);
    temp.setActionCommand(button);
    temp.setAlignmentX(Component.CENTER_ALIGNMENT);
    temp.addActionListener(parent);
    temp.setMargin(new Insets(0,0,0,0));
    return temp;
  }
}
