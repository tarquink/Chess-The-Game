package chess;

import javax.swing.ImageIcon;

/*************************************************************************
 * A Bishop for the game of chess.
 *
 * @author Kevin Tarquinio, Jeffrey Nolan, Thomas Szczesny, RJ Hamilton
 * @version March 2013
 ************************************************************************/

public class Bishop extends ChessPiece {

    /** A representation of a bishop icon */
    private ImageIcon icon;

    /*********************************************************************
     * Constructor for the Bishop class
     *
     * @param owner Which player's bishop.
     * @param icon the icon for the bishop.
     ********************************************************************/
    public Bishop(Player owner, ImageIcon icon) {
        super(owner);
        this.icon = icon;
    }

    /*********************************************************************
     * States that Bishop is the type of chess piece.
     *
     * @return returns the string bishop
     ********************************************************************/
    public String type() {
        return "Bishop";
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

    /*******************************************************************
     * Checks for a valid move for a Bishop
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     *******************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
        if(super.isValidMove(move, board) == false)
            return false;

        if(leftToRightJump(move,board) == false)
            return false;

        if(rightToLeftJump(move,board) == false)
            return false;

        if(Math.abs(move.toRow - move.fromRow) == Math.abs(move.toColumn - move.fromColumn))

            return true;

        return false;
    }   

    /*********************************************************************
     * Checks for a valid move from left to right
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     ********************************************************************/
    private boolean leftToRightJump(Move move, IChessPiece[][] board){

        if(move.toRow <= 7 && move.toColumn <= 7
                && move.toRow>move.fromRow
                && move.toColumn>move.fromColumn){
            //top to bottom
            int r1 = move.fromRow+1;
            int c1 = move.fromColumn+1;

            while(r1<=move.toRow && c1<=move.toColumn){
                if(board[r1][c1] != null && board[r1][c1].player()
                        == board[move.fromRow][move.fromColumn].player())
                    return false;
                else{
                    r1++;
                    c1++;
                }
            }
        }

        if(move.toRow >= 0 && move.toColumn <= 7){

            //bottom to top
            int r2 = move.fromRow-1;
            int c2 = move.fromColumn+1;

            while(r2>=move.toRow && c2<=move.toColumn){
                if(board[r2][c2] != null && board[r2][c2].player()
                        == board[move.fromRow][move.fromColumn].player())
                    return false;
                else{
                    r2--;
                    c2++;
                }
            }
        }
        return true;
    }

    /*********************************************************************
     * Checks for a valid move from right to left
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     ********************************************************************/
    private boolean rightToLeftJump(Move move, IChessPiece[][] board){
        if(move.toRow >= 0 && move.toColumn >= 0
                && move.toRow<move.fromRow
                && move.toColumn<move.fromColumn){
            //bottom to top
            int r1 = move.fromRow-1;
            int c1 = move.fromColumn-1;

            while(r1>=move.toRow && c1>=move.toColumn){
                if(board[r1][c1] != null && board[r1][c1].player()
                        == board[move.fromRow][move.fromColumn].player())
                    return false;
                else{
                    r1--;
                    c1--;
                }
            }
        }

        if(move.toRow <= 7 && move.toColumn <= 7
                && move.toRow>move.fromRow
                && move.toColumn<move.fromColumn){
            //top to bottom
            int r2 = move.fromRow+1;
            int c2 = move.fromColumn-1;

            while(r2<=move.toRow && c2>=move.toColumn){
                if(board[r2][c2] != null && board[r2][c2].player()
                        == board[move.fromRow][move.fromColumn].player())
                    return false;
                else{
                    r2++;
                    c2--;
                }
            }
        }

        return true;
    }
}