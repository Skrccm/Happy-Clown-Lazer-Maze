import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/*
 * Required:
 * Many images of the laser bouncing off the mirrors.
 * Laser images.
 * getLevel (); param must be acquired somehow
 */ 

/**
 * This class contains and draws the grid for the laser game.
 * It also loads the level from a file that is premade.
 * @author Jun Hee Cho, Calvin Chan
 * @since 18 May 2012
 * @version 2
 * <p>
 * Variable Dictionary
 * <PRE>Name                  Type                 Description</PRE>
 * <PRE>grid                  int [] []            stores the grid</PRE>
 * <PRE>parent                MainMenu             stores the instance of MainMenu</PRE>
 * <PRE>gridButton            JButton[][]          stores an array of JButtons. Represents the grid.</PRE>
 * <PRE>level                 int                  stores the level that the user is on.</PRE>
 * <PRE>MIRROR_NORTH          int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_NORTHEAST      int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_EAST           int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_SOUTHEAST      int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_SOUTH          int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_SOUTHWEST      int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_WEST           int                  used to identify which object is in a grid cell</PRE>
 * <PRE>MIRROR_NORTHWEST      int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMPTY                 int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_NORTH         int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_NORTHEAST     int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_EAST          int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_SOUTHEAST     int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_SOUTH         int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_SOUTHWEST     int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_WEST          int                  used to identify which object is in a grid cell</PRE>
 * <PRE>EMITTER_NORTHWEST     int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_NORTH       int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_NORTHEAST   int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_EAST        int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_SOUTHEAST   int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_SOUTH       int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_SOUTHWEST   int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_WEST        int                  used to identify which object is in a grid cell</PRE>
 * <PRE>COLLECTOR_NORTHWEST   int                  used to identify which object is in a grid cell</PRE>
 */ 
public class LaserGrid extends JPanel implements ActionListener
{
  //Stores the grid and the items in it.
  private int [][] grid;
  //Stores an instance of MainMenu
  private MainMenu parent;
  
  private int level;
  
  /**
   * These final int values define what object each number represents on the grid [] [] variable.
   */ 
  final private int MIRROR_NORTH = 1;
  final private int MIRROR_NORTHEAST = 2;
  final private int MIRROR_EAST = 3;
  final private int MIRROR_SOUTHEAST = 4;
  final private int MIRROR_SOUTH = 5;
  final private int MIRROR_SOUTHWEST = 6;
  final private int MIRROR_WEST = 7;
  final private int MIRROR_NORTHWEST = 8;
  
  final private int EMITTER_NORTH = 10;
  final private int EMITTER_NORTHEAST = 11;
  final private int EMITTER_EAST = 12;
  final private int EMITTER_SOUTHEAST = 13;
  final private int EMITTER_SOUTH = 14;
  final private int EMITTER_SOUTHWEST = 15;
  final private int EMITTER_WEST = 16;
  final private int EMITTER_NORTHWEST = 17;
  
  final private int COLLECTOR_NORTH = 20;
  final private int COLLECTOR_NORTHEAST = 21;
  final private int COLLECTOR_EAST = 22;
  final private int COLLECTOR_SOUTHEAST = 23;
  final private int COLLECTOR_SOUTH = 24;
  final private int COLLECTOR_SOUTHWEST = 25;
  final private int COLLECTOR_WEST = 26;
  final private int COLLECTOR_NORTHWEST = 27;
  
  final private int EMPTY = 0;
  
  final private int OBSTACLE = 30;
  
  private JButton [][] gridButton;
  
  /**
   * The constructor for LaserGrid.
   * This class first initializes the grid variable to be an array 10 by 10.
   * It then calls the readLevel () method, which assigns values to the grid variable.
   * The JPanel is set as not opaque, 520x520 pixels with the FlowLayout.
   * It then creates a series of buttons using for loops that represents the grid. 
   * @param parent stores the instance of MainMenu
   */
  public LaserGrid (MainMenu parent)
  {
    this.parent = parent;
    grid = new int [10][10];
    readLevel (1);
    setOpaque(false);
    setPreferredSize (new Dimension (520, 520));
    setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    gridButton=new JButton[10][10];
    for (int x = 0; x < grid.length; x ++)
    {
      for (int y = 0; y < grid.length; y ++)
      {
        gridButton [x][y] = new JButton ();
        gridButton [x][y].setActionCommand (x +""+ y);
        gridButton[x][y].setBorder (null);
        gridButton[x][y].setBorderPainted(false);
        gridButton[x][y].setFocusable(false);
        gridButton[x][y].addActionListener (this);
        add(gridButton[x][y]);
      }
    }
    updateGrid();
    /*
     gridButton[x][y].setAlignmentX(Component.CENTER_ALIGNMENT);
     gridButton[x][y].setMargin(new Insets(0,0,0,0));
     */ 
  }
  
  /**
   * This class reads from a file and sets the grid variable with
   * many objects in it. This method uses a nested for-loop to set
   * different numbers to each cell.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>header                String               stores the header of the file</PRE>
   * <PRE>in                    BufferedReader       stores the instance of BufferedReader</PRE> 
   * <PRE>line                  String               stores a line in the file</PRE>
   * Precondition: The file is valid, not corrupted and is compatible with the game.
   */
  public void readLevel (int level)
  {
    String header;
    try {
      BufferedReader in = new BufferedReader (new FileReader ("leveldat.d2k"));
      header = in.readLine ();
      if (!header.equals ("Level data for D2K Games Studio (For Kidz!)"))
      {
        JOptionPane.showMessageDialog (this, "File not compatible with program, or file is empty", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }
      for (int x = 0; x < grid.length; x++)
      {
        String line;
        line = in.readLine ();
        StringTokenizer num = new StringTokenizer (line, " ");
        for (int y = 0; y < grid.length; y++)
        {
          if (num.hasMoreTokens ())
            grid [x] [y] = Integer.parseInt (num.nextToken());
          else
          {
            JOptionPane.showMessageDialog (this, "This level file is missing information, or is corrupt.");
            return;
          }
        }
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Animates a single cell with a sequence of .png files.
   * This method utilises a for-loop structure to set the icon of a button.
   * It changes the icon every 10 milliseconds, resulting in an animation.
   * @param path the location of the .png sequence files
   */ 
  public synchronized void run(String path, JButton animation)
  {
    for(int x=1;x<=50;x++)
    {
      String zeros="000";
      animation.setIcon(new ImageIcon("images/gameItem/" + path + zeros + x + ".png"));
      try
      {
        Thread.sleep(10);
      }
      catch(InterruptedException ie)
      {
      }
    }
  }
  
  /**
   * This method is called when the Play button is pressed on the game screen.
   * When this method is called, it animates the laser from the laser emitter.
   * It takes appropriate direction change when it hits an obstacle.
   * After placing a laser on the grid, it then sets the appropriate icons to the buttons.
   * If the laser hits the wall, the laser stops.
   * If the laser reaches the laser collector, the level is won.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>laserDir              int                  stores the compass direction of the laser</PRE>
   * <PRE>x                     int                  stores the x coordinate of the front of the laser</PRE>
   * <PRE>y                     int                  stores the y coordinate of the front of the laser</PRE>
   * <PRE>found                 boolean              true or false based on if the laser emitter has been found or not</PRE>
   * <PRE>done                  boolean              true or false based on if the laser has been stopped by something</PRE>
   * Precondition: The grid[][] variable has one value 10-17 AND 20-27 (collectors and emitters).
   */ 
  public boolean animateLaser ()
  {
    int laserDir;
    int x= 0, y =0;
    boolean found = false, win = false;
    
    //sequential search for values 10-17
    for (x = 0; x < grid.length; x++)
    {
      for (y = 0; x < grid.length; y++)
      {
        if (grid [x][y] > 9 && grid [x][y] < 18)
        {
          found = true;
          break;
          
        }
      }
      if (found)
        break;
    }
    
    //this sets the initial laser direction to the direction of the laser emitter.
    laserDir = (grid[x][y] - 10) * 45;
    
    //changes the grid until the laser stops.
    //Have a for-loop in here to change the button icons while the laser is moving?
    while (true)
    {
      //laser goes one point north (y+1)
      if (laserDir == 0)
      {
        y++;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_S_N", gridButton [x][y]);
        }
        else if (grid [x][y] == MIRROR_SOUTHEAST)
        {
          //animate the stuff here
          laserDir = 90;
        }
        else
        {
          if (grid[x][y] == MIRROR_SOUTHWEST)
          {
            //animate the stuff here
            
            laserDir = 270;
          }
        }
      }
      //laser goes one point north and one point east
      else if (laserDir == 45)
      {
        y++;
        x++;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_SW_NE", gridButton [x][y]);
        }
        else if (grid[x][y] == MIRROR_SOUTH)
        {
          //animation
          laserDir = 135;
        }
        else
        {
          if (grid[x][y] == MIRROR_WEST)
          {
            //animation
            
            laserDir = 315;
          }
        }
      }
      //laser goes one point east
      else if (laserDir == 90)
      {
        x++;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_W_E", gridButton [x][y]);
        }
        else if (grid[x][y] == MIRROR_NORTHWEST)
        {
          //animate the stuff here
          
          laserDir = 0;
        }
        else
        {
          if (grid [x][y] == MIRROR_SOUTHWEST)
          {
            //animate the stuff here
            
            laserDir = 180;
          }
        }
      }
      //laser goes one point south, one point east
      else if (laserDir == 135)
      {
        y--;
        x++;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_NW_SE", gridButton [x][y]);
        }
        else if (grid[x][y] == MIRROR_WEST)
        {
          //animation
          laserDir = 225;
        }
        else
        {
          if (grid[x][y] == MIRROR_NORTH)
          {
            //animation
            laserDir = 45;
          }
        }
      }
      //laser goes south one point
      else if (laserDir == 180)
      {
        y--;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_N_S", gridButton [x][y]);
        }
        else if (grid [x][y] == MIRROR_NORTHEAST)
        {
          //animate the stuff here
          laserDir = 90;
        }
        else
        {
          if (grid[x][y] == MIRROR_NORTHWEST)
          {
            //animation
            laserDir = 270;
          }
        }
      }
      //laser goes one point south, one point west
      else if (laserDir == 225)
      {
        y--;
        x--;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_NE_SW", gridButton [x][y]);
        }
        if (grid[x][y] == MIRROR_NORTH)
        {
          //animation
          laserDir = 315;
        }
        else
        {
          if (grid[x][y] == MIRROR_EAST)
          {
            //animation
            laserDir = 135;
          }
        }
      }
      //laser goes one point west
      else if (laserDir == 270)
      {
        x--;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_E_W", gridButton [x][y]);
        }
        else if (grid[x][y] == MIRROR_NORTHEAST)
        {
          //animation
          laserDir = 0;
        }
        else
        {
          if (grid[x][y] == MIRROR_SOUTHEAST)
          {
            //animation
            laserDir = 180;
          }
        }
      }
      //laser goes one point north, one point west
      else if (laserDir == 315)
      {
        x--;
        y++;
        if (grid[x][y] == EMPTY)
        {
          run ("laser_SE_NW", gridButton [x][y]);
        }
        if (grid [x][y] == MIRROR_EAST)
        {
          //animation
          laserDir = 45;
        }
        else
        {
          if (grid[x][y] == MIRROR_SOUTH)
          {
            //animation
            laserDir = 225;
          }
        }
      }
      
      //if the laser goes into a correct collector (depending on laserDir), the player wins.
      if (grid [x][y] > 19 && grid[x][y] < 28)
      {
        win = true;
        updateGrid ();
        return true;
      }
      //the laser goes out of bounds, hits an obstacle, the emitter, or any collector
      if (x > 9 || y > 9 || grid [x][y] == OBSTACLE || (grid[x][y] > 9 && grid[x][y] < 18) || (grid[x][y] > 19 && grid[x][y] < 28))
        break;
    }
    return false;
  }
  
  /**
   * This method resets the level to the level in the file.
   */ 
  public void resetLevel ()
  {
    readLevel (1);
    updateGrid ();
  }
  
  /**
   * Visually updates the grid based on the grid variable.
   * It sets the ImageIcon to the appropriate image.
   * The image names are the integer values of the final variables.
   * This method can also be used to remove the laser animations from the grid.
   */ 
  public void updateGrid ()
  {
    for (int x = 0; x < grid.length; x++)
    {
      for (int y = 0; y < grid.length; y++)
      {
        gridButton [x][y].setIcon (new ImageIcon ("images/gridItem/" +grid[x][y]+".png"));
      }
    }
  }
  
  /**
   * This variable sets the level to a different value.
   * @param the level that the variable is to be changed to
   */ 
  public void setLevel (int level)
  {
    this.level = level;
  }
  
  /**
   * Returns the current level of the user.
   * @return the level
   */ 
  public int getLevel ()
  {
    return level;
  }
  
  /**
   * This method checks if the cell has a mirror in it. If there is a mirror in that cell, it rotates it clockwise 45 degrees.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>temp                  String               stores the string representation of ae (the button name)</PRE>
   * @param ae stores the information about the action
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    String temp = ae.getActionCommand();
    int temp2=grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))];
    if (temp2 > EMPTY && temp2 < (MIRROR_NORTHWEST + 1))
    {
      grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] ++;
      if (grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] == (MIRROR_NORTHWEST + 1))
        grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] = MIRROR_NORTH;
      updateGrid();
    }
  }
}