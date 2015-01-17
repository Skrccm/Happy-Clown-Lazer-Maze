package files;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
/**
 * Sets up the highscores table and saves the images to give to other objects to display.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class GenerateTableImages extends JPanel
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The table of names for the highscore.
   */
  private JPanel namesTable;
  /**
   * The table of times for the highscores.
   */
  private JPanel timesTable;
  /**
   * Array of all the names on the table.
   */
  JLabel[] names;
  /**
   * Array of all the times on the table.
   */
  JLabel[]times;
  /**
   * Temp image of the table.
   */
  private BufferedImage tempImage;
  /**
   * Sets up the jpanel. Sets dimensions, adds jcomponents, and sets fonts.
   */
  public GenerateTableImages()
  {
    setPreferredSize(new Dimension(577,340));
    setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
    setOpaque(false);
    namesTable=new JPanel();
    namesTable.setLayout(new BoxLayout(namesTable, BoxLayout.PAGE_AXIS));
    namesTable.setOpaque(false);
    timesTable=new JPanel();
    timesTable.setLayout(new BoxLayout(timesTable, BoxLayout.PAGE_AXIS));
    timesTable.setOpaque(false);
    add(namesTable);
    add(Box.createHorizontalGlue());
    add(timesTable);
    names=new JLabel[MainMenu.NUM_LEVELS];
    times=new JLabel[MainMenu.NUM_LEVELS];
    for(int x=0;x<10;x++)
    {
      names[x]=new JLabel();
      times[x]=new JLabel();
      names[x].setFont(new Font("Arial",Font.BOLD,25));
      times[x].setFont(new Font("Arial",Font.BOLD,25));
      namesTable.add(names[x]);
      timesTable.add(times[x]);
    }
  }
  /**
   * Renders all the level tables and returns them.
   * @param temp2 An array of the scores
   * @return Returns an array of images (One for each level)
   */
  public Image[] renderImages(ArrayList<ArrayList<String>> temp2)
  {
    Image[] temp=new Image[MainMenu.NUM_LEVELS];
    for(int x=0;x<MainMenu.NUM_LEVELS;x++)
    {
      for(int y=0;y<10;y++)
      {
        names[y].setText("<HTML>"+temp2.get(x).get(y*2)+"</HTML>");
        int time=Integer.parseInt(temp2.get(x).get(y*2+1));
        String seconds = Integer.toString (time % 60);
        String minutes = Integer.toString ((int) (time / 60) % 60);
        if (Integer.parseInt (seconds) < 10)
          seconds = "0" + seconds;
        if (Integer.parseInt (minutes) < 10)
          minutes = "0" + minutes;
        String full=minutes+":"+seconds;
        times[y].setText(full);
      }
      tempImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = (Graphics2D)tempImage.createGraphics();
      paint(g2d);
      g2d.dispose();
      temp[x]=tempImage;
      tempImage.flush();
    }
    return temp;
  }
}