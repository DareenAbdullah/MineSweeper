import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
  
public class InfoCell{
  private int row;
  private int column;
  private int numOfAdjacentMines;
  private String status;
  
  public InfoCell(int row, int column, int numOfAdjacentMines){
    
    this.row = row;
    this.column = column;
    this.numOfAdjacentMines = numOfAdjacentMines;
    status = Configuration.STATUS_COVERED;
    //status variable is set to static field in Configuration
    
  }
  
  public void draw(Graphics g){
    
    g.drawImage(getImage(), getHorizontalPosition(), getVerticalPosition(), null);
    
  }
  
  public int getHorizontalPosition(){
    //Multiplying the cell size by the column results in actual point in the cell
    return (column * Configuration.CELL_SIZE);
  
  }
  
  public int getVerticalPosition(){
    
    return (row * Configuration.CELL_SIZE);
  
  }
  
  
  public String getStatus(){
    
    return(status);
    
  }
  
  public void setStatus(String status){
  
    this.status = status;
    
  }
  public Image getImage() {
    //Sets each image to its corresponding status
    ImageIcon icon;
    if (status.equals(Configuration.STATUS_COVERED)){
        icon = new ImageIcon("img/covered_cell.png");
    }
    else {
      if (status.equals(Configuration.STATUS_MARKED)) {
        icon = new ImageIcon("img/marked_cell.png");
      }
      else {
        if (status.equals(Configuration.STATUS_WRONGLY_MARKED)) {
          icon = new ImageIcon("img/wrong_mark.png");
        }
        else {
          int holder = getNumOfAdjacentMines();
          switch (holder) {
            case 1:
              icon = new ImageIcon("img/info_1.png");
              break;
            case 2:
              icon = new ImageIcon("img/info_2.png");
              break;
            case 3:
              icon = new ImageIcon("img/info_3.png");
              break;
            case 4:
              icon = new ImageIcon("img/info_4.png");
              break;
            case 5:
              icon = new ImageIcon("img/info_5.png");
              break;
            case 6:
              icon = new ImageIcon("img/info_6.png");
              break;
            case 7:
              icon = new ImageIcon("img/info_7.png");
              break;
            case 8:
              icon = new ImageIcon("img/info_8.png");
              break;
            default:
              icon = new ImageIcon("img/info_0.png");
              break;
          }
        }
      }
    }
    Image image;
    image = icon.getImage();
    return image;
  }
  
  public int getNumOfAdjacentMines() {
    
    return numOfAdjacentMines;
    
  }
}