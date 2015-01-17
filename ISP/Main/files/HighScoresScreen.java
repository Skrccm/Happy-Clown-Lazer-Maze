package files;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.event.*;
/**
 * The screen to display all highscores
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class HighScoresScreen extends JPanel implements ListSelectionListener
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID =1L;
  /**
   * Arraylist to hold all scores and names
   */
  private ArrayList<ArrayList<String>> scores;
  /**
   * JList to display the levels to choose from
   */
  private JList<String> jlist;
  /**
   * Reference variable to the parent program.
   */
  private MainMenu parent;
  /**
   * Reference variable to the TableViewer that creates the scorebox.
   */
  private TableViewer table;
  /**
   * Refernce variable to the object that generates the table images to display.
   */
  private GenerateTableImages tableImages;
  /**
   * Sets up the jpanel with components.
   * @param parent Reference variable to the parent
   */
  public HighScoresScreen(MainMenu parent)
  {
    this.parent=parent;
    setLayout(new BorderLayout(5,5));
    setOpaque(false);
    String[] temp=new String[MainMenu.NUM_LEVELS];
    for(int x=0;x<MainMenu.NUM_LEVELS;x++)
    {
      temp[x]="Level "+(x+1);
    }
    jlist=new JList<String>(temp);
    jlist.setOpaque(false);
    jlist.setFont(new Font("Arial",Font.PLAIN,14));
    jlist.setCellRenderer(new cellRenderer());
    jlist.addListSelectionListener(this);
    JLabel title=new JLabel(ImageLoader.imageIcon("/images/highscores/scores.png"));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    JPanel bottom=new JPanel();
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.LINE_AXIS));
    bottom.setOpaque(false);
    JButton clear=ButtonMaker.makeButton("clear",parent);
    JButton back=ButtonMaker.makeButton("back",parent);
    bottom.add(clear);
    bottom.add(Box.createHorizontalGlue());
    bottom.add(back);
    tableImages=new GenerateTableImages();
    table=new TableViewer(this);
    add(title,BorderLayout.NORTH);
    add(jlist,BorderLayout.WEST);
    add(table,BorderLayout.CENTER);
    add(bottom,BorderLayout.SOUTH);
    readScores();
  }
  /**
   * Reads the savedata and loads all the scores.
   */
  public void readScores()
  {
    scores=new ArrayList<ArrayList<String>>();
    try
    {
      BufferedReader in=new BufferedReader(new FileReader("data/savedata.d2k"));
      if (!in.readLine().equals ("HighScore data for D2K Games Studio (For Kidz!)"))
      {
        throw new IOException();
      }
      for(int x=0;x<MainMenu.NUM_LEVELS;x++)
      {
        scores.add(new ArrayList<String>());
        String temp=in.readLine();
        if(temp==null||!temp.equals("Level "+(x+1)))
        {
          throw new IOException();
        }
        for(int y=0;y<10;y++)
        {
          StringTokenizer temp2=new StringTokenizer(in.readLine(),"|");
          scores.get(x).add(temp2.nextToken());
          scores.get(x).add(temp2.nextToken());
        }
      }
      in.close();
    }
    catch(NoSuchElementException nsee)
    {
      JOptionPane.showMessageDialog (this, "The highscores file is corrupt or missing. A new one will be created", "Error", JOptionPane.ERROR_MESSAGE);
      newScore();
      return;
    }
    catch(IOException ioe)
    {
      JOptionPane.showMessageDialog (this, "The highscores file is corrupt or missing. A new one will be created", "Error", JOptionPane.ERROR_MESSAGE);
      newScore();
      return;
    }
  }
  /**
   * Saves the scores to the savedata file.
   */
  public void saveHighScores()
  {
    try
    {
      File files = new File ("data");
      if (!files.exists ())
      {
        files.mkdir ();
      }
      PrintWriter out=new PrintWriter(new FileWriter("data/savedata.d2k"));
      out.println("HighScore data for D2K Games Studio (For Kidz!)");
      for(int x=0;x<MainMenu.NUM_LEVELS;x++)
      {
        out.println("Level "+(x+1));
        for(int y=0;y<10;y++)
        {
          out.println(scores.get(x).get(y*2)+"|"+scores.get(x).get(y*2+1));
        }
      }
      out.close();
      readScores();
    }
    catch(IOException ioe)
    {
    }
  }
  /**
   * Checks if the string has any funny characters, not including spaces.
   * @param temp The string to check
   * @return true if has funny chars, false if not
   */
  public boolean noFunnyChars(String temp)
  {
    for(int x=0;x<temp.length();x++)
    {
      if(temp.charAt(x)!=' '&&Character.getNumericValue(temp.charAt(x))<10)
      {
        return false;
      }
    }
    return true;
  }
  /**
   * Asks the user for their name, checks if it is valid, then returns it
   * @param place the position in the rankings the user achieved
   * @param level the level the user has completed
   * @return valid name of the user.
   */
  public String getName(int place,int level)
  {
    String temp;
    JOptionPane.showMessageDialog (null, "You are "+place+" on the highscores for level "+level+"!", "Congratulations!", JOptionPane.ERROR_MESSAGE);
    while(true)
    {
      temp = JOptionPane.showInputDialog("Please enter your name:");
      if(temp==null||temp.length()==0||temp.length()>20||temp.indexOf(' ',temp.indexOf(' ')+1)!=-1||temp.charAt(0)==' '||temp.charAt(temp.length()-1)==' '||!noFunnyChars(temp))
        JOptionPane.showMessageDialog (null, "There was an error with the name. Make sure it consists of max 20 letters and max one space and is not blank", "Error", JOptionPane.ERROR_MESSAGE);
      else
        break;
    }
    return temp;
  }
  /**
   * Tells the user that they completed the level, but did not achieve the highscores.
   * @param level The level the user completed
   */
  public void tryAgain(int level)
  {
    JOptionPane.showMessageDialog (null, "You've completed level "+level+". However, you did not make the highscores. Replay level to try again!", "Congratulations!", JOptionPane.ERROR_MESSAGE);
  }
  /**
   * Checks the scores if the user made it to the highscores. Asks for their name if yes, comforts them if no.
   * @param score The time the user took to complete the level
   * @param level The level the user completed
   */
  public void addScore(int score, int level)
  {
    for(int x=0;x<10;x++)
    {
      if(Integer.parseInt(scores.get(level).get(x*2+1))>score||Integer.parseInt(scores.get(level).get(x*2+1))==0)
      {
        scores.get(level).remove(18);
        scores.get(level).remove(18);
        scores.get(level).add(x*2,Integer.toString(score));
        scores.get(level).add(x*2,getName(x+1,level+1));
        saveHighScores();
        return;
      }
    }
    tryAgain(level+1);
  }
  /**
   * Creates a new blank highscore file and reads it.
   */
  public void newScore()
  {
    try
    {
      File files = new File ("data");
      if (!files.exists ())
      {
        files.mkdir ();
      }
      PrintWriter out=new PrintWriter(new FileWriter("data/savedata.d2k"));
      out.println("HighScore data for D2K Games Studio (For Kidz!)");
      for(int x=0;x<MainMenu.NUM_LEVELS;x++)
      {
        out.println("Level "+(x+1));
        for(int y=0;y<10;y++)
        {
          out.println("No One|0");
        }
      }
      out.close();
      readScores();
    }
    catch(IOException ioe)
    {
    }
  }
  /**
   * Called when the jlist changes options. It calls the tableviewer to draw the score box.
   * @param e contains information about the event
   */
  public void valueChanged(ListSelectionEvent e)
  {
    if (e.getValueIsAdjusting() == false)
    {
      if (jlist.getSelectedIndex() != -1)
      {
        table.renderImages();
        table.changeLevel(jlist.getSelectedIndex());
      }
    }
  }
  /**
   * called to make the jlist not select anything.
   */
  public void unselect()
  {
    table.renderImages();
    jlist.clearSelection();
    table.changeLevel(-1);
  }
  /**
   * calls the generatetableimages object to generate images and sends them to the tableviewer to display.
   * @return Array of images to display.
   */
  public Image[] renderImages()
  {
    add(tableImages,BorderLayout.EAST);
    validate();
    Image[]temp=tableImages.renderImages(scores);
    remove(tableImages);
    validate();
    return temp;
  }
  /**
   * Nested class to set the color of the text in the jlist to rainbow colors
   */
  public class cellRenderer extends DefaultListCellRenderer
  {
    /**
     * Stores the Serial Version UID. 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Holds the array of 7 colors.
     */
    private Color[]rainbow;
    /**
     * Sets it to opaque and sets the 7 colors.
     */
    public cellRenderer() {
      setOpaque(false);
      rainbow=new Color[7];
      rainbow[0]=Color.RED;
      rainbow[1]=Color.CYAN;
      rainbow[2]=Color.BLACK;
      rainbow[3]=Color.GREEN;
      rainbow[4]=Color.BLUE;
      rainbow[5]=Color.MAGENTA;
      rainbow[6]=Color.PINK;
    }
    /**
     * The method that is called to draw the words on the jlist
     * @param list The jlist to draw in
     * @param value the value of the index
     * @param index the index of the word to draw
     * @param isSelected whether the word is selected or not
     * @param cellHasFocus whether the word has focus or not
     * @return the component to draw.
     */
    public Component getListCellRendererComponent(JList<?> list,Object value,int index, boolean isSelected,boolean cellHasFocus) {
      Component temp=super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
      temp.setForeground(rainbow[index%7]);
      return temp;
    }
  }
}