package chess;

import javax.swing.ImageIcon;

/*************************************************************************
 * A Rook for the game of chess.
 *
 * @author Kevin Tarquinio, Jeffrey Nolan, Thomas Szczesny, RJ Hamilton
 * @version March 2013
 ************************************************************************/

public class Rook extends ChessPiece{

    /** A representation of a rook icon */
    private ImageIcon icon;

    /********************************************************************
     * Constructor for the Rook class
     *
     * @param owner Which player's rook.
     * @param icon the icon for the rook.
     *******************************************************************/
    public Rook(Player owner, ImageIcon icon) {
        super(owner);
        this.icon = icon;
    }

    /*********************************************************************
     * States that Rook is the type of chess piece.
     *
     * @return returns the string Rook
     *******************************************************************/
    public String type() {
        return "Rook";
    }
   
    /*****************************************************************
     * displays the image
     *
     * @return returns an image icon.
     *****************************************************************/
    public ImageIcon getIcon() {
        return icon;
    }

    /*****************************************************************
     * Sets the image
     *****************************************************************/
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /********************************************************************
     * Checks for a valid move for a Rook
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     *******************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
 
        if(super.isValidMove(move, board) == false)
            return false;
        if(verticalJump(move,board) == false)
            return false;
        if(horizontalJump(move,board) == false)
            return false;
        
        if((move.fromRow != move.toRow && move.fromColumn == move.toColumn)
                || (move.fromColumn != move.toColumn && move.fromRow == 
                move.toRow))
            return true;
        
        return false;
    }

    /********************************************************************
     * Checks for a valid vertical move.
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     *******************************************************************/
    private boolean verticalJump(Move move, IChessPiece[][] board){
        int count1 = 0;

         //top to bottom vertical jump
        if(move.fromRow != 7){
            for(int r = move.fromRow+1; r < move.toRow; r++){
                if(board[r][move.fromColumn] != null
                     && (board[r][move.fromColumn].player()

                     == board[move.fromRow][move.fromColumn].player()
                     || board[r][move.fromColumn].player()
                     != board[move.fromRow][move.fromColumn].player()))
                    count1++;
            }
            if(count1 >= 1)
                return false;
        }
      
        int count2 = 0;

        //bottom to top vertical jump
        if(move.fromRow != 0){
            for(int r = move.fromRow-1; r > move.toRow; r--){
                if(board[r][move.fromColumn] != null
                     && (board[r][move.fromColumn].player()

                     == board[move.fromRow][move.fromColumn].player()
                     || board[r][move.fromColumn].player()
                     != board[move.fromRow][move.fromColumn].player()))
                      count2++;
              }
            if(count2 >= 1)
                return false;
        }
        return true;
      
    }
  
    /*********************************************************************
     * Checks for a valid horizontal move.
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     ********************************************************************/
    private boolean horizontalJump(Move move, IChessPiece[][] board){
        int count1 = 0;

        //Horizontal jump from right to left
        if(move.fromColumn != 0){
            for(int c = move.fromColumn-1; c > move.toColumn; c--){
                if(board[move.fromRow][c] != null
                        && (board[move.fromRow][c].player()

                       == board[move.fromRow][move.fromColumn].player()
                       || board[move.fromRow][c].player()
                       != board[move.fromRow][move.fromColumn].player()))
                    count1++;
                if(board[move.toRow][move.toColumn] != null &&
                        board[move.toRow][move.toColumn].player() !=
                        board[move.fromRow][move.fromColumn].player() && 
                        count1 == 0)
                    return true;
            }
            if(count1 >= 1)
                return false;
        }
      
        int count2 = 0;

        //Horizontal jump from left to right
        if(move.fromColumn != 7){
            for(int c = move.fromColumn+1; c < move.toColumn; c++){
                if(board[move.fromRow][c] != null
                        && (board[move.fromRow][c].player()

                        == board[move.fromRow][move.fromColumn].player()
                        || board[move.fromRow][c].player()
                       != board[move.fromRow][move.fromColumn].player()))
                    count2++;
               
                if(board[move.toRow][move.toColumn] != null &&
                        board[move.toRow][move.toColumn].player() !=
                        board[move.fromRow][move.fromColumn].player() && 
                        count2 == 0){
                    return true;
                }
                   
            }
          
            if(count2 >= 1)
                return false;
        }
        return true;
    }
} 