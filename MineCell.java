import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;

public class MineCell{

  private int row;
  private int column;
  private String status;
  
  public MineCell(int row, int column){
    
    this.row = row;
    this.column = column;
    status = Configuration.STATUS_COVERED;
    //status is set to the static variable in configuration
    //It should start as covered
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
  
  public void setStatus(String Status){
  
    this.status = Status;
    
  }
  
  public Image getImage(){
    //Sets each image to its corresponding status
    ImageIcon icon;
    if (status.equals(Configuration.STATUS_COVERED)){
      icon = new ImageIcon("img/covered_cell.png");
    }
    else {
      if (status.equals(Configuration.STATUS_OPENED)) {
        icon = new ImageIcon("img/mine_cell.png");
      }
      else {
        icon = new ImageIcon("img/marked_cell.png");
      }
    }
    Image image;
    image = icon.getImage();
    return image;
    
  }
}