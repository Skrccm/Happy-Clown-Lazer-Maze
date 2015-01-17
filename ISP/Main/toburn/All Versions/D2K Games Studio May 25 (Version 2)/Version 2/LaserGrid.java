import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/*
 * Required:
 * Many images of the laser bouncing off the mirrors.
 * Laser images.
 * Laser path method
 * Flipping images across various axes/lines
 * Fix readLevel () to use StringTokenizer since some final variables are two digits
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
  
  final private int MIRROR_NORTH_LASER = 111232131232;
  
  final private int EMPTY = 0;
  
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
    readLevel ();
    setOpaque(false);
    setPreferredSize (new Dimension (520, 520));
    setLayout(new FlowLayout());
    
    displayButton ();
  }
  
  /**
   * This class creates the buttons that represents the grid on the cells.
   * It uses a nested for-loop to create 10x10 buttons.
   * The button name is xy, where x and y represent the x and y coordinates in the grid.
   */ 
  public void displayButton ()
  {
    for (int x = 0; x < grid.length; x++)
    {
      for (int y = 0; y < grid.length; y++)
      {
        String name = Integer.toString (x) + Integer.toString (y);
        JButton buttons=ButtonMaker.makeButton(name,this);
        this.add(buttons);
      }
    }
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
   */
  public void readLevel ()
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
        for (int y = 0; y < grid.length; y++)
        {
          line = in.readLine ();
          grid [x] [y] = Integer.parseInt (line.charAt (y));
        }
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (this, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * This method is called when the Play button is pressed on the game screen.
   * When this method is called, it animates the laser from the laser emitter.
   * It takes appropriate direction change when it hits an obstacle.
   * After placing a laser on the grid, it then sets the appropriate icons to the buttons.
   * If the laser hits the wall, the laser stops.
   * If the laser reaches the laser collector, the level is won.
   */ 
  public void animateLaser ()
  {
    int laserDir;
  }
  
  /**
   * This method checks if the cell has a mirror in it. If there is a mirror in that cell, it rotates it clockwise 45 degrees.
   * Variable Dictionary
   * @param ae stores the information about the action
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    String temp = ae.getActionCommand();
    if (maze[(int)temp.charAt (0)][(int)temp.charAt(1)] > EMPTY && maze[(int)temp.charAt (0)][(int)temp.charAt(1)] < (MIRROR_NORTHWEST + 1))
    {
      maze[(int)temp.charAt (0)][(int)temp.charAt(1)] ++;
      if (maze[(int)temp.charAt (0)][(int)temp.charAt(1)] == (MIRROR_NORTHWEST + 1))
      {
        maze[(int)temp.charAt (0)][(int)temp.charAt(1)] = MIRROR_NORTH;
      }
    }
  }
}