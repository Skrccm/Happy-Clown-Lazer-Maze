package files;
import javax.swing.*;
import java.awt.*;
/**
 * The screen that is shown when the user completes a level. Allows the user to go to the next level, 
 * replay the level, or return to the mainmenu.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class LevelCompleteScreen extends JPanel
{
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID =1L;
  /**
   * reference variable to the parent program.
   */
  private MainMenu parent;
  /**
   * 5 jlabels for each digit of the clock
   */
  private JLabel[] time;
  /**
   * ArrayList of images for each number
   */
  private ImageIcon[] numbers;
  /**
   * Sets up the screen, adds components, and loads images.
   * @param parent The reference variable to the parent
   */
  public LevelCompleteScreen(MainMenu parent)
  {
    setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    setOpaque(false);
    this.parent=parent;
    JLabel title=new JLabel(ImageLoader.imageIcon("/images/levelcomplete/levelcomplete.png"));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    JPanel scoreIs=new JPanel();
    scoreIs.setOpaque(false);
    scoreIs.setAlignmentX(Component.CENTER_ALIGNMENT);
    scoreIs.setLayout(new BoxLayout(scoreIs, BoxLayout.LINE_AXIS));
    JLabel score=new JLabel(ImageLoader.imageIcon("/images/yourscore/ysi.png"));
    time=new JLabel[5];
    scoreIs.add(score);
    numbers=new ImageIcon[10];
    for(int x=0;x<5;x++)
    {
      time[x]=new JLabel();
      time[x].setOpaque(false);
      scoreIs.add(time[x]);
    }
    time[2].setIcon(ImageLoader.imageIcon("/images/numbers/colon.png"));
    for(int x=0;x<10;x++)
    {
      numbers[x]=ImageLoader.imageIcon("/images/numbers/"+x+".png");
    }
    JButton nextLevel=ButtonMaker.makeButton("nextlevel",parent);
    JButton replayLevel=ButtonMaker.makeButton("replay",parent);
    JButton backMain =ButtonMaker.makeButton("backtomain",parent);
    add(title);
    add(scoreIs);
    add(nextLevel);
    add(replayLevel);
    add(backMain);
  }
  /**
   * updates the clock to the score from the timer. Checks if the user made a highscore.
   */
  public void updateScore()
  {
    int temp=parent.getTime();
    String seconds = Integer.toString (temp % 60);
    String minutes = Integer.toString ((int) (temp / 60) % 60);
    if (Integer.parseInt (seconds) < 10)
      seconds = "0" + seconds;
    if (Integer.parseInt (minutes) < 10)
      minutes = "0" + minutes;
    String full=minutes+":"+seconds;
    for(int x=0;x<5;x++)
    {
      if(x!=2)
        time[x].setIcon(numbers[Integer.parseInt(""+full.charAt(x))]);
    }
    parent.addScore(temp);
  }
}