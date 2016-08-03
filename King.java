package chess;

import javax.swing.ImageIcon;

/**********************************************************************
 * Class function of the King chess piece.
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 * @version March 2013
 *********************************************************************/
public class King extends ChessPiece {
   
    /** stores an image icon */
    private ImageIcon icon;

    /**********************************************************************
    * Constructor for the king class.
    *
    * @param owner sets this parameter to be used in the super class
    * @param icon instantiates the image icon
    *********************************************************************/
    public King(Player owner, ImageIcon icon){
        super(owner);
        this.icon = icon;
    }
   
    /**********************************************************************
     * Get method for the icon variable.
     *
     * @return returns an icon
     *********************************************************************/
    public ImageIcon getIcon(){
        return icon;
    }

    /**********************************************************************
     * Set method for the icon variable.
     *
     * @param icon ImageIcon variable
     *********************************************************************/
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
   
    /**********************************************************************
     * Gets the type of string for the King chess piece.
     *
     * @return returns the string "King"
     *********************************************************************/
    public String type() {
        return "King";
    }

    /******************************************************************
     * Determines if the King's move is valid
     *
     * @param move determines King's starting and ending position
     * @param board shows where pieces are on the board
     * @return true or false if move is valid
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
        if(super.isValidMove(move, board) == false){
            return false;
        }
        if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn + 1 ||
                move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn - 1 ||
                move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn + 1 ||
                move.toRow == move.fromRow - 1 && move.toColumn == move.fromColumn - 1)
            return true;
        else if(move.toRow == move.fromRow + 1 && (move.toColumn == move.fromColumn) || move.toRow == move.fromRow - 1
                && move.toColumn == move.fromColumn)
            return true;
        else if(move.toColumn == move.fromColumn + 1 && (move.toRow == move.fromRow) || move.toColumn ==
                move.fromColumn - 1 && move.toRow == move.fromRow)
            return true;
        return false;
    }
}