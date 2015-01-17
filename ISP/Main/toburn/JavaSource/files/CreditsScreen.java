package files;
import javax.swing.*;
import java.awt.*;
/**
 * Displays the credits
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class CreditsScreen extends JPanel
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
   * Sets up the credits
   * @param parent Reference variable to the parent
   */
  public CreditsScreen (MainMenu parent)
  {
    this.parent=parent;
    setOpaque(false);
    setPreferredSize (new Dimension (700, 550));
    setLayout(new BorderLayout(5,5));
    JLabel temp=new JLabel(ImageLoader.imageIcon("/images/credits/credits.png"));
    add(temp,BorderLayout.NORTH);
    JLabel creditsDisplay=new JLabel(ImageLoader.imageIcon("/images/credit/credit.png"));
    add(creditsDisplay,BorderLayout.CENTER);
    JButton back=ButtonMaker.makeButton("back",parent);
    JPanel backPanel=new JPanel();
    backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.LINE_AXIS));
    backPanel.setOpaque(false);
    backPanel.add(Box.createHorizontalGlue());
    backPanel.add(back);
    add(backPanel,BorderLayout.SOUTH);
  }
}