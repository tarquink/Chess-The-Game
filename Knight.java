package chess;

import javax.swing.ImageIcon;

/*****************************************************************
Controls each Knight chess piece on the board.

@author Thomas Szczesny, Kevin Tarquino, R.J. Hamilton, Jeff Nolan
@version Winter 2013
*****************************************************************/
public class Knight extends ChessPiece{
   
    /** The image icon to be displayed on the Knight pieces */
    private ImageIcon icon;
   
    /****************************************************
     * Constructor that sets the status and icon of a cell
     * based on what is given as parameters
     * @param owner the player who owns the Knight
     * @param icon the image the cell is displaying
     ****************************************************/
    public Knight(Player owner, ImageIcon icon){
        super(owner);
        this.icon = icon;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    public String type() {
        return "Knight";
    }

    /****************************************************
     * Checks to confirm that the move a user is trying
     * to make is valid.
     * @param status the status of the cell
     * @param icon the image the cell is displaying
     ****************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
        if(super.isValidMove(move, board) == false){
            return false;
        }

        if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn + 1 ||
                move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn -1 ||
                move.toColumn == move.fromColumn + 2 && move.toRow == move.fromRow + 1 ||
                move.toColumn == move.fromColumn + 2 && move.toRow == move.fromRow - 1 ||
                move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn - 1 ||
                move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn + 1 ||
                move.toColumn == move.fromColumn - 2 && move.toRow == move.fromRow - 1 ||
                move.toColumn == move.fromColumn - 2 && move.toRow == move.fromRow + 1){
            return true;          
        }
        else
        {
            return false;
        }

    }

}