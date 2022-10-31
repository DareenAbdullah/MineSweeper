import java.util.Random;
import java.awt.Graphics;

public class Minefield{
  private Object[][] field;
  private int rows;
  private int columns;
  
  public Minefield(){
    //Cascading Constructor, sets the default to 10,10,10
    this(10,10,10);
    
  }
  
  public Minefield(int numRows, int numColumns, int numMines){
    //initializes all instance fields
    rows = numRows;
    columns = numColumns;
    field = new Object[numRows][numColumns];
    mineLaying(numMines);
    addInfoCells();
  }
  
   public void mineLaying(int numOfMines){
   
    int col;
    int row;
    int numMinesPlaced = 0;
    Random rando = new Random();
    //numMinesPlaced keeps track of whether I have placed enough Mines yet
    while (numMinesPlaced < numOfMines) {
      row = rando.nextInt(this.rows);
      col = rando.nextInt(this.columns);
      
      //if it is not already a mineCell
      if (!(getCellByRowCol(row, col) instanceof MineCell)){
        MineCell mine = new MineCell(row, col);
        field[row][col] = mine;
        numMinesPlaced++;
      }
    }
       
  }
  //Helper method to find the parameter for InfoCell constructor
  private int findNumAdjacent(int rowLoc, int colLoc){
    
    int count = 0;
    //ADJMINES HOLDER
    
    //CORNER CELLS
    if ((rowLoc == 0 && colLoc == 0))  {
      if (getCellByRowCol(rowLoc + 1, colLoc) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1, colLoc + 1) instanceof MineCell) {
        count++;
      }
    }
    //TOP LEFT CORNER COMPLETED
    
    else if ((rowLoc == 0 && colLoc == this.columns-1)){
      if (getCellByRowCol(rowLoc , colLoc - 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1, colLoc) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1, colLoc - 1) instanceof MineCell) {
        count++;
      }
    }
    //TOP RIGHT CORNER COMPLETED
    
    else if ((rowLoc == this.rows-1 && colLoc == this.columns-1)){
      if (getCellByRowCol(rowLoc , colLoc - 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc - 1, colLoc) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc - 1, colLoc - 1) instanceof MineCell) {
        count++;
      }    
    }
    //BOTTOM RIGHT CORNER COMPLETED
    
    else if ((rowLoc == this.rows-1 && colLoc == 0)){
      if (getCellByRowCol(rowLoc -1 , colLoc ) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc - 1, colLoc + 1) instanceof MineCell) {
        count++;
      }  
    }
    //BOTTOM LEFT CORNOR COMPLETED
    //CORNER STATEMENTS COMPLETED
    
    //SIDES
    else if (rowLoc == 0){
       if (getCellByRowCol(rowLoc, colLoc - 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1 , colLoc -1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1, colLoc) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count++;
      }
      if (getCellByRowCol(rowLoc + 1, colLoc + 1) instanceof MineCell) {
        count++;
      } 
    }
    //TOP ROW COMPLETED
    else if (rowLoc == this.rows - 1) {
      if (getCellByRowCol(rowLoc, colLoc - 1) instanceof MineCell) {
        count++;
      }
      //LEFT
      if (getCellByRowCol(rowLoc - 1 , colLoc -1) instanceof MineCell) {
        count++;
      }
      //LEFT DIAGONAL
      if (getCellByRowCol(rowLoc -1, colLoc) instanceof MineCell) {
        count++;
      }
      //ABOVE
      if (getCellByRowCol(rowLoc - 1, colLoc + 1) instanceof MineCell) {
        count++;
      }
      //RIGHT DIAGONAL
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count++;
      }
      //RIGHT
     }
    //BOTTOM ROW COMPLETED
    else if  (colLoc == 0){
      if (getCellByRowCol(rowLoc - 1, colLoc) instanceof MineCell) {
        count++;
      }
      //LEFT
      if (getCellByRowCol(rowLoc - 1 , colLoc + 1) instanceof MineCell) {
        count++;
      }
      //LEFT DIAGONAL
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count++;
      }
      //ABOVE
      if (getCellByRowCol(rowLoc + 1, colLoc) instanceof MineCell) {
        count++;
      }
      //RIGHT
      if (getCellByRowCol(rowLoc + 1, colLoc + 1) instanceof MineCell) {
        count++;
      }
      //RIGHT DIAGONAL
    }
    //LEFT COLUMN COMPLETED
    
    else if (colLoc == this.columns - 1){
      if (getCellByRowCol(rowLoc +1, colLoc - 1) instanceof MineCell) {
        count++;
      }
      //LEFT DIAGONAL
      
      if (getCellByRowCol(rowLoc + 1 , colLoc) instanceof MineCell) {
        count++;
      }
      //LEFT
      if (getCellByRowCol(rowLoc, colLoc - 1) instanceof MineCell) {
        count++;
      }
      //ABOVE
      if (getCellByRowCol(rowLoc - 1, colLoc - 1) instanceof MineCell) {
        count++;
      }
      //RIGHT DIAGONAL
      if (getCellByRowCol(rowLoc - 1, colLoc) instanceof MineCell) {
        count++;
      } 
      //RIGHT
    }
    //RIGHT COLUMN COMPLETED
    
    else {
      count = 0;
      if (getCellByRowCol(rowLoc, colLoc - 1) instanceof MineCell) {
        count+=1;
      }
      //LEFT
      if (getCellByRowCol(rowLoc + 1 , colLoc -1) instanceof MineCell) {
        count+=1;
      }
      //LEFT-DOWN DIAGONAL
      if (getCellByRowCol(rowLoc + 1, colLoc) instanceof MineCell) {
        count+=1;
      }
      //BELOW
      if (getCellByRowCol(rowLoc, colLoc + 1) instanceof MineCell) {
        count+=1;
      }
      //RIGHT
      if (getCellByRowCol(rowLoc + 1, colLoc + 1) instanceof MineCell) {
        count+=1;
      }
      //RIGHT-DOWN DIAGONAL
      if (getCellByRowCol(rowLoc - 1, colLoc - 1) instanceof MineCell) {
        count+=1;
      }
      //UPPER LEFT DIAGONAL
      if (getCellByRowCol(rowLoc - 1, colLoc) instanceof MineCell) {
        count++;
      }
      //ABOVE
      if (getCellByRowCol(rowLoc - 1, colLoc + 1) instanceof MineCell) {
        count+=1;
      }
      //UPPER RIGHT DIAGONAL
    }
    return count;
    
  }
  
  public void addInfoCells(){
    //Double for loop to loop thru the field array
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j <field[i].length; j++) {
        if (!(getCellByRowCol(i, j) instanceof MineCell)) {
          //Calls helper method
          InfoCell info = new InfoCell(i, j, findNumAdjacent(i, j));
          field[i][j] = info;
        }
      }
    }
  }
    
 
  
  public void draw(Graphics g){
    MineCell mine;
    InfoCell info;
    //Double for loop to loop thru the field array
    for (int i = 0; i < field.length; i++) {
      for (int j = 0; j < field[i].length; j++) {
        //Check what object it is and then call draw
        if (field[i][j] instanceof MineCell){
          mine = (MineCell) field[i][j];
          mine.draw(g);
        }
        else{
          info = (InfoCell) field[i][j];
          info.draw(g);
        }
      }
    }
    
  }
  
  public Object getCellByScreenCoordinates(int x, int y){
  //formula to find row and column of a point
   int xlocation = (x) / Configuration.CELL_SIZE;
   int ylocation = (y) / Configuration.CELL_SIZE;
   return field[ylocation][xlocation];
  
  }
  
  //returns the object
  public Object getCellByRowCol(int row, int col){
   
    return field[row][col];
    
  }
  
  //sets a cell as MineCell
  public void setMineCell(int row, int col, MineCell cell){
   
    field[row][col] = cell;
    
  }
  
  //sets a cell as infocell
  public void setInfoCell(int row, int col, InfoCell cell){
  
    field[row][col] = cell;
 
  }
  
  
  public int countCellsWithStatus(String status){
    
    MineCell mine;
    InfoCell info;
    int count = 0;
    String holdString;
    
    //nested loop to traverse the array
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        //if it is empty go to next one
        if (field[i][j] == null){
          continue;
        }
        //if it is minefield, use that method to get the status
        if (field[i][j] instanceof MineCell){
          mine = (MineCell) field[i][j];
          holdString = mine.getStatus();
        }
        //same for infocell
        else{
          info = (InfoCell) field[i][j];
          holdString = info.getStatus();
        }
        //check if the status are equal and add if so
        if (holdString.equals(status)){
          count++;
        }
      }
    }
    return count;
  }
  
//Helper for opencells, sets the status of the adjacent cells to opened
  private void openHelper(InfoCell cell){
    int x = cell.getHorizontalPosition() / Configuration.CELL_SIZE;
    int y = cell.getVerticalPosition() / Configuration.CELL_SIZE;
    InfoCell info;

    if (cell.getNumOfAdjacentMines()!=0){
      return;
    }
    //Top-Left Corner
    if ((y == 0 && x == 0)){
      //Open bottom cell
      info = (InfoCell)field[y+1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Open right of cell
      info = (InfoCell)field[y][x+1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Open Diagonal of cell
      info = (InfoCell)field[y+1][x+1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    
    //Top-Right Corner
    else if ((y == 0 && x == this.columns-1)){
      //Left of Cell
      info = (InfoCell)field[y][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Bottom of Cell
      info = (InfoCell)field[y + 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal of Cell
      info = (InfoCell)field[y + 1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    
    //Bottom-Right Corner
    else if ((y == this.rows-1 && x == this.columns-1)){
      //Left of Cell
      info = (InfoCell)field[y][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Top of Cell
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal of Cell
      info = (InfoCell)field[y - 1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
    }
    //Bottom-Left Corner
    else if ((y == this.rows-1 && x == 0)){
      //Top of Cell
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Right of Cell
      info = (InfoCell)field[y][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal of Cell
      info = (InfoCell)field[y - 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    //Sides
    //TopRow
    else if (y == 0){
      //Left
      info = (InfoCell)field[y ][x -1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y +1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Bottom
      info = (InfoCell)field[y + 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Right
      info = (InfoCell)field[y][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal of Cell
      info = (InfoCell)field[y + 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    //Bottom Row
    else if (y == this.rows - 1) {
      //Left
      info = (InfoCell)field[y][x -1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y -1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Top
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y - 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Right of Cell
      info = (InfoCell)field[y][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    
    //Left Column
    else if  (x == 0){
      //Top
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y -1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Right
      info = (InfoCell)field[y][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Bottom
      info = (InfoCell)field[y + 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal of Cell
      info = (InfoCell)field[y + 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    
    //Right Column
    else if (x == this.columns - 1){
      //Diagonal
      info = (InfoCell)field[y + 1][x -1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Bottom
      info = (InfoCell)field[y + 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Left
      info = (InfoCell)field[y][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y - 1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Top
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    //Middle
    else {
      //Diagonal
      info = (InfoCell)field[y + 1][x -1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Bottom
      info = (InfoCell)field[y + 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Left
      info = (InfoCell)field[y][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y - 1][x - 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Top
      info = (InfoCell)field[y - 1][x];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Right
      info = (InfoCell)field[y][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y + 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
      
      //Diagonal
      info = (InfoCell)field[y - 1][x + 1];
      info.setStatus(Configuration.STATUS_OPENED);
    }
    
    
  }
  public void openCells(Object cell){
    //if the cell is a minecell just return it
    if (cell instanceof MineCell){
      return;
    }
    //open the cell and all the adjacent cells, if there are zero mines next to it
    InfoCell iCell = (InfoCell) cell;
    iCell.setStatus(Configuration.STATUS_OPENED);
    openHelper(iCell);
    int loop = 0;

    while (loop < this.rows){
    //traverse the array
    for (int i = 0; i < this.rows; i++){
      for (int j = 0; j < this.columns; j++) {
        //continue when empty
        if (field[i][j] == null){
          continue;
        }
        //if the object is an InfoCell and is already open and has no adj mines call openHelper
        if (field[i][j] instanceof InfoCell){
          InfoCell currentCell = (InfoCell) field[i][j];
          if (currentCell.getStatus().equals(Configuration.STATUS_OPENED)){
            if (currentCell.getNumOfAdjacentMines() == 0 ){
              openHelper(currentCell);
            }
          }
        }
      }
    }
    loop+=1;
    }
  }
  
  //Call this method, when the game is over
  public void revealIncorrectMarkedCells(){
    for (int i = 0; i < this.rows; i++){
      for (int j = 0; j < this.columns; j++) {
        //if it is empty continue
        if (field[i][j] == null){
          continue;
        }
        if (field[i][j] instanceof InfoCell){
          InfoCell info = (InfoCell) field[i][j];
          if (info.getStatus().equals(Configuration.STATUS_MARKED)) {
            info.setStatus(Configuration.STATUS_WRONGLY_MARKED);
          }
        }
        //CHECK TO SEE IF NO MESS UP
        else {
          MineCell mine = (MineCell) field[i][j];
          mine.setStatus(Configuration.STATUS_OPENED);
        }
      }
    }
  }
  
  
}
