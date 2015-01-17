package files;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.Desktop;
import java.io.*;
/**
 * The help screen. Displays information on how to play the game and a button to get back to
 * the mainmenu screen.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 * 
 * <br>version 4 changes - added helpscreen.html and printing
 */
class Help extends JPanel
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
   * It adds a title image and a back button
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  JLabel               stores a JLabel</PRE>
   * <PRE>back                  JButton              stores a back button</PRE>
   * <PRE>backPanel             JPanel               stores the JPanel</PRE>
   */
  public Help(MainMenu parent)
  {
    this.parent=parent;
    setOpaque(false);
    setPreferredSize (new Dimension (700, 550));
    setLayout(new BorderLayout(5,5));
    JLabel temp=new JLabel(ImageLoader.imageIcon("/images/help/help.png"));
    add(temp,BorderLayout.NORTH);
    JTextPane instructionsDisplay = new JTextPane ();
    instructionsDisplay.setBorder (BorderFactory.createEtchedBorder (EtchedBorder.RAISED));
    try
    {
      instructionsDisplay.setPage (getClass().getResource("/data/helpscreen.html"));
    }
    catch(java.io.IOException ioe)
    {
      JOptionPane.showMessageDialog (null, "You somehow managed to mess up the instructions.\nInstructions will not be displayed in-game.");
    }
    instructionsDisplay.setEditable (false);
    JScrollPane instructionScroll = new JScrollPane (instructionsDisplay);
    instructionScroll.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    add(instructionScroll,BorderLayout.CENTER);
    JButton back=ButtonMaker.makeButton("back",parent);
    JButton print=ButtonMaker.makeButton("print",parent);
    JPanel backPanel=new JPanel();
    backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.LINE_AXIS));
    backPanel.setOpaque(false);
    backPanel.add(print);
    backPanel.add(Box.createHorizontalGlue());
    backPanel.add(back);
    add(backPanel,BorderLayout.SOUTH);
  }
  /**
   * Prints the helpscreen file.
   */
  public void print() 
  {
    if (Desktop.isDesktopSupported())  
    {  
      Desktop desktop = Desktop.getDesktop();  
      if (desktop.isSupported(Desktop.Action.PRINT))  
      {  
        try
        {
          desktop.print(new File("data/helpscreen.html")); 
        }
        catch(IOException ioe)
        {
          System.out.println("Error occured while printing");
          ioe.printStackTrace();
        }
      }  
    }  
  }
}