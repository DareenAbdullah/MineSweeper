import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Dimension;

public class Board extends JPanel
{
  //Instances for the class
  private int height;
  private int width;
  private int mines;
  private JLabel statusbar;
  private int flagHolder;
  private Minefield mine1;
  
 public Board(int height, int width, int mines, JLabel statusbar)
 {
   //initialize all instances
   this.height = height;
   this.width = width;
   this.mines = mines;
   this.statusbar = statusbar;
   flagHolder = mines;
   mine1 = new Minefield(height, width, mines);
   
   setPreferredSize(new Dimension(Configuration.BOARD_WIDTH, Configuration.BOARD_HEIGHT));
   addMouseListener(new MouseReader(this));
 } 

 @Override
 public void paintComponent(Graphics g)
 {
   //invokes the draw method
   mine1.draw(g);
 }
 
//return the minefield array
 public Minefield getMinefield(){
   return mine1;
 }

//helper method for isGameOver
private boolean youLost(){
  //traverse the mine array
   for (int i = 0; i < Configuration.ROWS; i++){
     for (int j = 0; j < Configuration.COLS; j++) {
       
       if (mine1.getCellByRowCol(i, j) instanceof MineCell){
         MineCell mined = (MineCell) mine1.getCellByRowCol(i, j);
         
         if (mined.getStatus().equals(Configuration.STATUS_OPENED)){
           //reveal all incorrect marked cells
           mine1.revealIncorrectMarkedCells();
           return true;
         }
       }
     }
   }
   return false;
 }
 //helper method for isGameOver
 private boolean youWon(){
   for (int i = 0; i < Configuration.ROWS; i++){
    for (int j = 0; j < Configuration.COLS; j++) {
      if (mine1.getCellByRowCol(i, j) instanceof InfoCell) {
        InfoCell informed = (InfoCell) mine1.getCellByRowCol(i, j);
        //if even one of infocell are covered then the game is not over
        if (informed.getStatus().equals(Configuration.STATUS_COVERED)){
          return false;
        }
      }
    }
  }
   return true;
 }
 
 public boolean isGameOver(){
   //whether won or lost the game is over
   if (youLost()){
     return true;
   }
   if (youWon()){
     return true;
   }
   else {
   //if not returns false
     return false;
   }
 }
 
 public void setStatusbar(String text){
   // set text
   statusbar.setText(text);
 }
 
 public String getStatusbar(){
   // if statement to return when game is over
   if (youWon()) {
     setStatusbar("Game over - You won!");
   }
   if (youLost()){
     setStatusbar("Game over - You lost!");
   }
   // return the status bar
   return statusbar.getText();
 }
 public boolean removeMine(){
   //checks if there are enough flags
   if (mines > 0) {
     mines -= 1;
     //changes status bar after
     setStatusbar(mines + " mines remaining");
     return true;
   }
   else {
     setStatusbar("Invalid action");
     return false;
   }
 }
 
 public boolean addMine(){
   //return true or false whether or not the action is done
   if (mines < flagHolder){
     mines += 1;
     setStatusbar(mines + " mines remaining");
     return true;
   }
   else{
     return false;
   } 
   // add one and change status bar
 }

 public void mouseClickOnLocation(int x, int y, String button)
 {
   //start by checking if the game is already over
   if (isGameOver()) {
     return;
   }
   Object unknown = mine1.getCellByScreenCoordinates(x, y);
   if (button.equals("left")) {
     //caste the object and set status to open
     if (unknown instanceof MineCell) {
       MineCell m = (MineCell) unknown;
       m.setStatus(Configuration.STATUS_OPENED);
       m.getImage();
     }
     else {
       //caste the object
       InfoCell i = (InfoCell) unknown;
       i.setStatus(Configuration.STATUS_OPENED);
       i.getImage();/////
       mine1.openCells(i);
     } 
     getStatusbar();
   }
   else if (button.equals("right")) {
     //first see if there is already a flag on the cell
     //then invoke addmine or removemine
     if (unknown instanceof MineCell) {
       //caste the object
       MineCell m = (MineCell) unknown;
       //check if marked first, if so add a mine and change status to covered
       if (m.getStatus().equals(Configuration.STATUS_MARKED)){
         m.setStatus(Configuration.STATUS_COVERED);
         addMine();
         m.getImage();
       }
       else {
         //check if removeMine allows
         if (removeMine()) {
           m.setStatus(Configuration.STATUS_MARKED);
           m.getImage();
         }
       }
     }
     else {
       //caste
       InfoCell i = (InfoCell) unknown;
       //if it is already open, dont let user flag it
       if (i.getStatus().equals(Configuration.STATUS_OPENED)){
         return;
       }
       if (i.getStatus().equals(Configuration.STATUS_MARKED)){
         i.setStatus(Configuration.STATUS_COVERED);
         addMine();
         i.getImage();
       }
       else {
         //check if user can remove mine still
         if (removeMine()) {
           i.setStatus(Configuration.STATUS_MARKED);
           i.getImage();
         }
       }
     }
   }
  repaint();
 }

 
}
