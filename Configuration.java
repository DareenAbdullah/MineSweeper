import java.io.File;
import java.util.Scanner;

public class Configuration{
  public static int ROWS;
  public static int COLS;
  public static int CELL_SIZE;
  public static int MINES;
  public static int BOARD_WIDTH;
  public static int BOARD_HEIGHT;
  //These variables never change so will remain non-static
  public static String STATUS_COVERED;
  public static String STATUS_OPENED;
  public static String STATUS_MARKED;
  public static String STATUS_WRONGLY_MARKED;
  
  public static void loadParameters(String filename){
    try {
      Scanner input = new Scanner(new File(filename));
      //
      while (input.hasNextLine()) {
        String [] line = input.nextLine().split(" ", 0);
        //switch statement to assign the members with their 
        //corresponding variables in the file
        switch (line[0]){
          case "ROWS":
            //int variables are converted from string to integer
            ROWS = Integer.parseInt(line[1]);
            break;
          case "COLS":
            COLS = Integer.parseInt(line[1]);
            break;
          case "MINES":
            MINES = Integer.parseInt(line[1]);
            break;
          case "CELL_SIZE":
            CELL_SIZE = Integer.parseInt(line[1]);
            break;
          case "STATUS_WRONGLY_MARKED":
            STATUS_WRONGLY_MARKED = line[1];
            break;
          case "STATUS_COVERED":
            STATUS_COVERED = line[1];
            break;
          case "STATUS_OPENED":
            STATUS_OPENED = line[1];
            break;
          case "STATUS_MARKED":
            STATUS_MARKED = line[1];
            break;
        }
      }
      BOARD_WIDTH = COLS * CELL_SIZE + 1;
      BOARD_HEIGHT = ROWS * CELL_SIZE + 1;
    }
    // Exception for files that do no exist
    catch(Exception e) {
      throw new RuntimeException();
    }
  }
}
