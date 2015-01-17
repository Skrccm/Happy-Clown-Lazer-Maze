package files;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The MainMenu screen. Holds all other screens and can switch between them.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 * 
 * <br>version 3 changes - added game screen and edited starting image to logo. Also added TimerControl
 * <br>version 4 changes - added more methods.
 */
class MainMenu extends JPanel implements ActionListener, KeyListener
{
  /**
   * Access to the gameScreen;
   */
  private GameScreen gameScreen;
  /**
   * The Jpanel that is set to CardLayout to hold all screens.
   */
  private JPanel card;
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The background image.
   */
  private ImageIcon image;
  /**
   * The controller for all actionevents
   */
  private ActionController con;
  /**
   * The highscores screen
   */
  private HighScoresScreen highScores;
  /**
   * The level complete screen
   */
  private LevelCompleteScreen levelComplete;
  /**
   * The help screen.
   */
  private Help help;
  /**
   * Static variable for the total number of levels
   */
  public static final int NUM_LEVELS=18;
  /**
   * sets image as the background image, and sets up the jpanel and screens.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>mainMenuPanel         JPanel               stores the panel of the MainMenu </PRE>
   * <PRE>title                 JLabel               stores the JLabel of the title</PRE>
   */
  public MainMenu ()
  {
    image = ImageLoader.imageIcon("/images/logo/logo.png");
    card = new JPanel (new CardLayout ());
    add (card);
    card.setOpaque(false);
    JPanel mainMenuPanel = new JPanel ();
    mainMenuPanel.setLayout(new BoxLayout(mainMenuPanel,BoxLayout.PAGE_AXIS));
    mainMenuPanel.setOpaque(false);
    JLabel title=new JLabel(ImageLoader.imageIcon("/images/title/title.png"));
    mainMenuPanel.add(title);
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    mainMenuPanel.add(putAccel(ButtonMaker.makeButton("play",this),KeyEvent.VK_1));
    mainMenuPanel.add (putAccel(ButtonMaker.makeButton("help",this),KeyEvent.VK_2));
    mainMenuPanel.add(putAccel(ButtonMaker.makeButton("highscores",this),KeyEvent.VK_3));
    mainMenuPanel.add(putAccel(ButtonMaker.makeButton("credits",this),KeyEvent.VK_4));
    mainMenuPanel.add(putAccel(ButtonMaker.makeButton("exit",this),KeyEvent.VK_5));
    card.add (mainMenuPanel, "MainMenu");
    card.add(new LevelSelect(this),"LevelSelect");
    help=new Help(this);
    card.add(help,"Help");
    card.add(new CreditsScreen(this),"Credits");
    highScores=new HighScoresScreen(this);
    card.add(highScores ,"HighScores");
    gameScreen=new GameScreen(this);
    card.add(gameScreen,"GameScreen");
    levelComplete=new LevelCompleteScreen(this);
    card.add(levelComplete,"LevelComplete");
    addKeyListener(this);
    con=new ActionController(this,gameScreen);
  }
  /**
   * Called when a key is pressed. Does nothing
   * @param e Contains more information about the KeyEvent
   */
  public void keyPressed(KeyEvent e)
  {
  }
  /**
   * Called when a key is released. If it is f1, it displays the help file.
   * @param e Contains more information about the KeyEvent
   */
  public void keyReleased(KeyEvent e)
  {
    if(e.getKeyCode()==112)
    {
      try
      {
        Runtime.getRuntime().exec("hh.exe data/helpfile.chm");
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
      }
    }
  }
  /**
   * Called when a key is typed. Does nothing
   * @param e Contains more information about the KeyEvent
   */
  public void keyTyped(KeyEvent e)
  {
  }
  
  /**
   * Puts a shortcut on the button.
   * @param temp the button to put the shortcut on
   * @param keystroke the integer of the keystroke to set the shortcut to
   * @return the button with the keystroke shortcut added.
   */
  public JButton putAccel(JButton temp,int keystroke)
  {
    temp.setMnemonic(keystroke);
    return temp;
  }
  /**
   * Sends the ActionController the ActionEvent
   * @param ae Contains information about the event in which the button was pressed
   */
  public void actionPerformed(ActionEvent ae)
  {
    con.actionPerformed(ae);
  }
  /**
   * Paints the background image.
   * @param g the Graphics object to draw on
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image.getImage(), 5, 5, null); // see javadoc for more info on the parameters
  }
  
  /**
   * Allows the screens to be switched
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  CardLayout           stores an instance of CardLayout</PRE>
   * @param screen the screen to switch to
   */
  public void switchScreens (String screen)
  {
    CardLayout temp = (CardLayout) (card.getLayout ());
    temp.show (card, screen);
  }
  /**
   * Tells the gameScreen to control the timer
   * @param x integer controlling the timer (0 to restart, 1 to continue, 2 to pause, 3 to stop and set to 0)
   */
  public void timerControl(int x)
  {
    gameScreen.timerControl(x);
  }
  /**
   * Tells the gamescreen to display the win animation
   */
  public void winLevel()
  {
    gameScreen.displayWinImage();
  }
  /**
   * Returns the game screen timer value.
   * @return the time elasped for the level.
   */
  public int getTime()
  {
    return gameScreen.getTime();
  }
  /**
   * Tells the level complete screen to update the scores.
   */
  public void updateScore()
  {
    levelComplete.updateScore();
  }
  /**
   * Tells the highscores screen to read the save data.
   */
  public void readScores()
  {
    highScores.readScores();
  }
  /**
   * Tells the highscores screen to unselect all buttons
   */
  public void unselect()
  {
    highScores.unselect();
  }
  /**
   * Checks if the user has made a highscore.
   * @param score The score to check for
   */
  public void addScore(int score)
  {
    highScores.addScore(score,con.getLevel()-1);
  }
  /**
   * Tells the highscores to clear the scores and make a blank savedata file.
   */
  public void clearHighScores()
  {
    highScores.newScore();
  }
  /**
   * tells the help screen to print the help file.
   */
  public void print()
  {
    help.print();
  }
}
