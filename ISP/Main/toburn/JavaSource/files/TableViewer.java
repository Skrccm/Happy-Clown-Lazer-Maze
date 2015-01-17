package files;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * Displays the scorebox
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 */
public class TableViewer extends JLabel implements ActionListener
{ 
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  /**
   * The timer to control the scorebox speed
   */
  private Timer timer;
  /**
   * Holds the current level
   */
  private int level;
  /*
   * Values for each line to draw
   * 
   * 0=line
   * 1=up
   * 2=down
   * 3=side
   * 4=inwards
   */
  private int[] counter;
  /*
   * Holds a value for what to draw.
   * 
   * 0=line
   * 1=updown
   * 2=side
   * 3=inwards
   */
  private int toDraw;
  /**
   * Array of scores to draw
   */
  private Image[] images;
  /**
   * Reference variable to the parent
   */
  private HighScoresScreen parent;
  /**
   * sets up the jlabel and timer.
   * @param parent the highscoresscreen parent
   */
  public TableViewer(HighScoresScreen parent)
  {
    this.parent=parent;
    counter=new int[5];
    for(int x=0;x<5;x++)
    {
      counter[x]=0;
    }
    toDraw=0;
    level=1;
    timer=new Timer(1,this);
  }
  /**
   * Gets the images to draw
   */
  public void renderImages()
  {
    if(images!=null)
    {
      for(Image a:images)
        a.flush();
    }
    images=parent.renderImages();
  }
  /**
   * Starts drawing the box, if level is -1, it does not draw
   * @param level the level to draw
   */
  public void changeLevel( int level)
  {
    this.level=level;
    for(int x=0;x<5;x++)
    {
      counter[x]=0;
    }
    toDraw=0;
    if(level!=-1)
      timer.start();
  }
  /**
   * called every time the timer fires. Increments the line values to draw
   * @param ae Contains more information about the event
   */
  public void actionPerformed(ActionEvent ae)
  {
    switch(toDraw)
    {
      case 0:counter[0]++;break;
      case 1:
        if(counter[1]<9+level*19)
        counter[1]++;
        if(counter[2]<341-(9+level*19))
          counter[2]++;
        break;
      case 2:counter[3]++;break;
      case 3:counter[4]++;break;
    }
    repaint();
    if(counter[0]==50&&toDraw==0)
    {
      toDraw++;
    }
    else if(counter[1]+counter[2]==341&&toDraw==1)
    {
      toDraw++;
    }
    else if(counter[3]==589&&toDraw==2)
    {
      toDraw++;
    }
    else 
    {
      if(counter[4]==171&&toDraw==3)
      {
        timer.stop();
        toDraw++;
      }
    }
  }
  /*
   * Dimensions=639x342, P=1962, half=981
   * Draws the lines.
   * @param g the graphics object to draw on.
   */
  @Override
  public void paintComponent(Graphics g)
  {
    if(images!=null&&level!=-1)
    {
      super.paintComponent(g);
      if(counter[0]!=0)
        g.drawLine(0,9+level*19,counter[0],9+level*19);
      if(counter[1]!=0||counter[2]!=0)
        g.drawLine(50,(9+level*19)-counter[1],50,9+level*19+counter[2]);
      if(counter[3]!=0&&level!=-1)
      {
        g.drawLine(50,0,50+counter[3],0);
        g.drawLine(50,341,50+counter[3],341);
        g.drawImage(images[level],55,1,55+counter[3],341,-1,0,-1+counter[3],340,null);
      }
      if(counter[4]!=0)
      {
        g.drawLine(638,0,638,counter[4]);
        g.drawLine(638,341,638,341-counter[4]);
      }
    }
  }
}