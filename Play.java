/**
 * Lines 17 and 24 are the only ones you must edit
*/
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Play
{
 public static void main(String[] args)
 {
  EventQueue.invokeLater(new Runnable()
   {
    public void run()
    {
     // Create Configuration Object and call function
     Configuration config = new Configuration();
     config.loadParameters(args[0]);
     // insert here the call to method loadParameters
     JFrame frame = new JFrame();
     JLabel statusbar = new JLabel("select a cell");
     frame.add(statusbar, BorderLayout.SOUTH);
     frame.add(new Board(Configuration.ROWS, Configuration.COLS, Configuration.MINES, statusbar));
     frame.setResizable(false);
     frame.pack();
     frame.setTitle("CS211 is a minefield!");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
    }
   });
 }
}
