/*
 * To do:
 * complete startTime () method to stop
 * Compensate for pauses
 */ 

import main.LaserGrid;

/**
 * This class contains the timer for the game.
 * It counts up minutes and seconds. We are assuming that the 
 * player is not going to play a level for more than 99 minutes
 * and 59 seconds (that would be pretty crazy).
 * Variable Dictionary
 * <PRE>Name                  Type                 Description</PRE>
 * <PRE>second                int                  stores the amount of seconds</PRE>
 * <PRE>minute                int                  stores the amount of minutes</PRE>
 * @author Jun Hee Cho, Calvin Chan
 * @since 24 May 2012
 * @version 2
 */ 
public void Timer
{
  private int second;
  private int minute;
  
  /**
   * The constructor for the Timer sets the seconds and minutes to 0 and starts the timer.
   */ 
  public Timer ()
  {
    second = 0;
    minute = 0;
    startTime ();
  }
  
  /**
   * This method counts the time.
   * Every 1000 milliseconds, the second variable increases.
   * Variable Dictionary
   * <PRE>Name                  Type                 Description</PRE>
   * <PRE>e                     Exception            stores the exception if it is caught</PRE>
   */ 
  public synchronized startTime ()
  {
    try
    {
      while (true)
      {
        Thread.sleep (1000);
        second ++;
        if (second == 60)
        {
          minute ++;
          second = 0;
        }
      }
    }
    catch (Exception e)
    {
    }
  }
  
  /**
   * Returns the time in seconds.
   * The lower the time, the better the score.
   * @return the time.
   */
  public int getTime ()
  {
    return ((minute * 60) + second);
  }
}