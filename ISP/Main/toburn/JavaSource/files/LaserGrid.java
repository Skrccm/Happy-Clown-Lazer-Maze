package files;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.imageio.stream.*;
/**
 * This class contains and draws the grid for the laser game.
 * It also loads the level from a file that is premade.
 * @author Calvin Chan, JunHee Cho
 * @version 4 June 12th, 2012
 * 
 * <br>version 4 changes - button background white, changed alot of methods
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
  /**
   * Stores the Serial Version UID. 
   */
  private static final long serialVersionUID = 1L;
  //Stores the grid and the items in it.
  private int [][] grid;
  //Stores an instance of MainMenu
  private MainMenu parent;
  /**
   * To remember what level the game is on.
   */
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
  
  final private int OBSTACLE = 9;
  
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
  /**
   * Array of buttons on the grid
   */
  private LaserButton [][] gridButton;
  /**
   * Arraylist of buttons to animate
   */
  private ArrayList<LaserButton> toDraw;
  /**
   * Booleans to control whether it can draw or if the user has won
   */
  private boolean canDraw=false,won=false;
  /**
   * ArrayList of all animations
   */
  private ArrayList<ImageIcon[]> images;
  /**
   * The current position of the jbutton animating.
   */
  private int pos;
  /**
   * Holds images for all the blocks
   */
  private ImageIcon[]blocks;
  /**
   * The constructor for LaserGrid.
   * This class first initializes the grid variable to be an array 10 by 10.
   * It then calls the readLevel () method, which assigns values to the grid variable.
   * The JPanel is set as not opaque, 520x520 pixels with the FlowLayout.
   * It then creates a series of buttons using for loops that represents the grid. 
   * Sets up all images.
   * @param parent stores the instance of MainMenu
   */
  public LaserGrid (MainMenu parent)
  {
    this.parent = parent;
    grid = new int [10][10];
    setOpaque(false);
    setPreferredSize (new Dimension (520, 520));
    setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    gridButton=new LaserButton[10][10];
    blocks=new ImageIcon[28];
    for(int x=0;x<28;x++)
    {
      if(x!=18&&x!=19)
        blocks[x]=ImageLoader.imageIcon("/images/gridItem/blocks/" +x+".png");
    }
    for (int x = 0; x < grid.length; x ++)
    {
      for (int y = 0; y < grid.length; y ++)
      {
        gridButton [x][y] = new LaserButton (this);
        gridButton[x][y].setActionCommand (x +""+ y);
        gridButton[x][y].addActionListener (this);
        add(gridButton[x][y]);
      }
    }
    images=new ArrayList<ImageIcon[]>();
    try
    {
      BufferedReader in=new BufferedReader(new InputStreamReader(ImageLoader.inputStream("/data/LASERS.txt")));
      while(true)
      {
        String temp=in.readLine();
        if(temp==null)
          break;
        images.add(imageCreator("/images/gridItem/"+temp+".gif"));
      }
    }
    catch(Exception e)
    {
    }
  }
  /**
   * Creates an array of images from the path
   * @param path the path to get the image from
   * @return Array of images
   */
  public ImageIcon[] imageCreator(String path)
  {
    ImageIcon[] image=new ImageIcon[50];
    try
    {
      ImageReader imageReader = (ImageReader) ImageIO.getImageReadersBySuffix("gif").next();
      ImageInputStream imageInputStream = ImageIO.createImageInputStream(ImageLoader.inputStream(path));
      imageReader.setInput(imageInputStream, false);
      for(int z=0;z<50;z++)
      {
        image[z]=new ImageIcon(imageReader.read(z));
      }
    }
    catch(FileNotFoundException fnfe)
    {
      System.out.println(fnfe);
    }
    catch(IOException ioe)
    {
      System.out.println(ioe);
    }
    return image;
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
   * @param level The level to read data from
   */
  public void readLevel (int level)
  {
    this.level=level;
    try {
      BufferedReader in = new BufferedReader (new InputStreamReader (ImageLoader.inputStream("/data/leveldat"+level+".d2k")));
      if (!in.readLine ().equals ("Level data for D2K Games Studio (For Kidz!)"))
      {
        JOptionPane.showMessageDialog (null, "Level Data not compatible with program, or file is empty", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
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
            JOptionPane.showMessageDialog (null, "This level file is missing information, or is corrupt.");
            System.exit(0);
          }
        }
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (null, "Level Data not found. The game will now close.", "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    updateGrid();
  }
  /**
   * Animates the next jbutton if possible, otherwise checks if the user has won, or resets the grid.
   */ 
  public void run()
  {
    if(toDraw!=null&&toDraw.size()>0&&toDraw.size()>pos+1&&canDraw)
    {
      pos++;
      toDraw.get(pos).draw();
    }
    else
    {
      updateGrid();
      if(toDraw!=null&&pos==toDraw.size()-1)
        checkWin();
      toDraw=null;
      canDraw=false;
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
    if(toDraw==null)
    {
      pos=-1;
      won=false;
      canDraw=true;
      toDraw=new ArrayList<LaserButton>();
      int laserDir;
      int x= 0, y =0;
      boolean found=false;
      
      //sequential search for values 10-17
      for (x = 0; x < grid.length; x++)
      {
        for (y = 0; y < grid.length; y++)
        {
          if (grid [x][y] >= EMITTER_NORTH && grid [x][y] <= EMITTER_NORTHWEST)
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
      animateLaser(x,y,laserDir);
      run();
    }
    else
    {
      toDraw=null;
      won=false;
      canDraw=false;
    }
  }
  /**
   * Checks if the user has won the level, then calls winLevel in mainmenu
   */
  public void checkWin()
  {
    if(won)
    {
      parent.winLevel();
    }
  }
  /**
   * Checks if the position and direction wins the level
   * @param x the x coordinate of the grid
   * @param y the y coordinate of the grid
   * @param laserDir the direction of the laser
   * @return true if the user has won, false if not
   */
  public boolean checkWin(int x,int y,int laserDir)
  {
    if (x > 9 || y > 9 || x<0||y<0||grid [x][y] == OBSTACLE || grid[x][y] <COLLECTOR_NORTH)
      return false;
    if (grid [x][y] >= COLLECTOR_NORTH && grid[x][y] <= COLLECTOR_NORTHWEST && (laserDir+180)%360==(grid[x][y] - 20) * 45)
    {
      won=true;
      return true;
    }
    return false;
  }
  /**
   * Sets the current position to animate, continues until win or stops. Recursive.
   * @param x the x coordinate of the grid
   * @param y the y coordinate of the grid
   * @param laserDir the direction of the laser
   */
  public void animateLaser(int x, int y, int laserDir)
  {
    if (x > 9 || y > 9 || x<0||y<0||grid [x][y] == OBSTACLE)
      return;
    //laser goes one point north (y+1)
    if (laserDir == 0)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(19));
      }
      else if (grid [x][y] == MIRROR_SOUTHEAST)
      {
        gridButton[x][y].draw(images.get(7));
        laserDir = 90;
      }
      else if (grid[x][y] == MIRROR_SOUTHWEST)
      {
        gridButton[x][y].draw(images.get(11));
        laserDir = 270;
      }
      else if(grid[x][y]==EMITTER_NORTH)
      {
        gridButton[x][y].draw(images.get(32));
      }
      else
      {
        return;
      }
    }
    //laser goes one point north and one point east
    else if (laserDir == 45)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(21));
      }
      else if (grid[x][y] == MIRROR_SOUTH)
      {
        gridButton[x][y].draw(images.get(9));
        laserDir = 135;
      }
      else if (grid[x][y] == MIRROR_WEST)
      {
        gridButton[x][y].draw(images.get(13));
        laserDir = 315;
      }
      else if(grid[x][y]==EMITTER_NORTHEAST)
      {
        gridButton[x][y].draw(images.get(33));
      }
      else
      {
        return;
      }
    }
    //laser goes one point east
    else if (laserDir == 90)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(17));
      }
      else if (grid[x][y] == MIRROR_NORTHWEST)
      {
        gridButton[x][y].draw(images.get(15));
        laserDir = 0;
      }
      else if (grid [x][y] == MIRROR_SOUTHWEST)
      {
        gridButton[x][y].draw(images.get(10));
        laserDir = 180;
      }
      else if(grid[x][y]==EMITTER_EAST)
      {
        gridButton[x][y].draw(images.get(34));
      }
      else
      {
        return;
      }
    }
    //laser goes one point south, one point east
    else if (laserDir == 135)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(22));
      }
      else if (grid[x][y] == MIRROR_WEST)
      {
        gridButton[x][y].draw(images.get(12));
        laserDir = 225;
      }
      else if (grid[x][y] == MIRROR_NORTH)
      {
        gridButton[x][y].draw(images.get(1));
        laserDir = 45;
      }
      else if(grid[x][y]==EMITTER_SOUTHEAST)
      {
        gridButton[x][y].draw(images.get(35));
      }
      else
      {
        return;
      }
    }
    //laser goes south one point
    else if (laserDir == 180)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(18));
      }
      else if (grid [x][y] == MIRROR_NORTHEAST)
      {
        gridButton[x][y].draw(images.get(3));
        laserDir = 90;
      }
      else if (grid[x][y] == MIRROR_NORTHWEST)
      {
        gridButton[x][y].draw(images.get(14));
        laserDir = 270;
      }
      else if(grid[x][y]==EMITTER_SOUTH)
      {
        gridButton[x][y].draw(images.get(36));
      }
      else
      {
        return;
      }
    }
    //laser goes one point south, one point west
    else if (laserDir == 225)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(20));
      }
      else if (grid[x][y] == MIRROR_NORTH)
      {
        gridButton[x][y].draw(images.get(0));
        laserDir = 315;
      }
      else if (grid[x][y] == MIRROR_EAST)
      {
        gridButton[x][y].draw(images.get(4));
        laserDir = 135;
      }
      else if(grid[x][y]==EMITTER_SOUTHWEST)
      {
        gridButton[x][y].draw(images.get(37));
      }
      else
      {
        return;
      }
    }
    //laser goes one point west
    else if (laserDir == 270)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(16));
      }
      else if (grid[x][y] == MIRROR_NORTHEAST)
      {
        gridButton[x][y].draw(images.get(2));
        laserDir = 0;
      }
      else if (grid[x][y] == MIRROR_SOUTHEAST)
      {
        gridButton[x][y].draw(images.get(6));
        laserDir = 180;
      }
      else if(grid[x][y]==EMITTER_WEST)
      {
        gridButton[x][y].draw(images.get(38));
      }
      else
      {
        return;
      }
    }
    //laser goes one point north, one point west
    else if (laserDir == 315)
    {
      if (grid[x][y] == EMPTY)
      {
        gridButton[x][y].draw(images.get(23));
      }
      else if (grid [x][y] == MIRROR_EAST)
      {
        gridButton[x][y].draw(images.get(5));
        laserDir = 45;
      }
      else if (grid[x][y] == MIRROR_SOUTH)
      {
        gridButton[x][y].draw(images.get(8));
        laserDir = 225;
      }
      else if(grid[x][y] == EMITTER_NORTHWEST)
      {
        gridButton[x][y].draw(images.get(39));
      }
      else
      {
        return;
      }
    }
    toDraw.add(gridButton[x][y]);
    switch(laserDir)
    {
      case 0:case 45:case 315: x--;break;
      case 135: case 180: case 225: x++;break;
    }
    switch(laserDir)
    {
      case 225: case 270: case 315: y--;break;
      case 45: case 90:case 135:y++;break;
    }
    if (x > 9 || y > 9 || x<0||y<0||grid [x][y] == OBSTACLE)
      return;
    if(! checkWin(x,y,laserDir))
      animateLaser(x,y,laserDir);
    else
    {
      gridButton[x][y].draw(images.get(((laserDir+180)/45)%8+24));
      toDraw.add(gridButton[x][y]);
    }
  }
  /**
   * This method resets the level to the level in the file.
   */ 
  public void resetLevel ()
  {
    readLevel (level);
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
        gridButton [x][y].setIcon (blocks[grid[x][y]]);
        gridButton[x][y].draw(null);
      }
    }
  }
  /**
   * This variable sets the level to a different value.
   * @param level the level that the variable is to be changed to
   */ 
  public void setLevel (int level)
  {
    this.level = level;
  }
  /**
   * returns whether the lasergrid is currently running or not.
   * @return true if running, false if not
   */
  public boolean isDrawing()
  {
    return canDraw;
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
    if (temp2 > EMPTY && temp2 < (MIRROR_NORTHWEST + 1)&& !canDraw)
    {
      grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] ++;
      if (grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] == (MIRROR_NORTHWEST + 1))
        grid[Integer.parseInt(""+temp.charAt(0))][Integer.parseInt(""+temp.charAt(1))] = MIRROR_NORTH;
      updateGrid();
    }
  }
  /**
   * Tells it to stop drawing
   */
  public void stopDrawing()
  {
    canDraw=false;
  }
}