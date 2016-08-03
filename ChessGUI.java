package chess;

import javax.swing.*;

/**********************************************************************
 * Driver class. Creates a new frame and adds a new ChessPanel
 * to the program.
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 * @version March 2013
 *********************************************************************/

public class ChessGUI
{
    /******************************************************************
     * Main method. Creates a new frame that adds the ChessPanel()
     * class to it.
     ******************************************************************/
   
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Chess Game");
      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        ChessPanel panel = new ChessPanel();
        frame.getContentPane().add(panel);
      
        frame.pack();
        frame.setVisible(true);   
    }
}