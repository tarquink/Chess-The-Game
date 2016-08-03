package chess;

import javax.swing.ImageIcon;

/**********************************************************************
 * Class sets function of the Pawn chess piece
 *
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 * @version March 2013
 *********************************************************************/
public class Pawn extends ChessPiece {

    /*owner of the pawn*/
    private Player owner;
   
    /*pawns appearance*/
    private ImageIcon icon;

    /**********************************************************************
     * Constructor for the Pawn
     *
     * @param owner sets owner of the Pawn
     * @param icon sets appearance of the Pawn
     *****************************************************************/
    public Pawn(Player owner, ImageIcon icon){

        super(owner);
        this.owner = owner;
        this.icon = icon;
    }

    /******************************************************************
     * Returns the Pawn chess piece's appearance
     *
     * @return icon determines Pawn's appearance
     *****************************************************************/
    public ImageIcon getIcon(){
        return icon;
    }

    /******************************************************************
        * Sets the Pawn chess piece's appearance
        *
        * @param icon determines Pawn's appearance
        *****************************************************************/
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    /******************************************************************
     * Returns the Pawn chess piece's name
     *
     * @return String Pawn's name
     *****************************************************************/
    public String type() {
        return "Pawn";
    }

    /******************************************************************
        * Determines if the Pawn's diagonal move is valid
        *
        * @param move determines Pawn's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if move is valid
        *****************************************************************/
    private boolean isDiagMove(Move move, IChessPiece[][] board){
       
       
        boolean flag3 = false;
        boolean flag4 = false;

        //determines if a white Pawn can move diagonally
        if(move.toRow < move.fromRow){
            if(move.toRow == move.fromRow - 1
                    && (move.toColumn == move.fromColumn + 1
                    || move.toColumn == move.fromColumn - 1)
                    && owner == Player.WHITE)
                flag3 =  true;
        }

        //determines if a black Pawn can move diagonally
        if(move.toRow > move.fromRow){
            if(move.toRow == move.fromRow + 1
                    && (move.toColumn == move.fromColumn + 1
                    || move.toColumn == move.fromColumn - 1)
                    && owner == Player.BLACK)
                flag4 =  true;
        }

        //determines if there is an opposite player 1 space diagonally
        //from the current player's piece
        if((flag3 == true || flag4 == true)
                && (board[move.toRow][move.toColumn]
                != null && board[move.toRow][move.toColumn].player()
                != board[move.fromRow][move.fromColumn].player())){
            return true;
        }

        return false;
    }
   
    /******************************************************************
        * Determines if the Pawn's move is valid
        *
        * @param move determines Pawn's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if move is valid
        *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
       
        //generic test for a valid move
        if(super.isValidMove(move, board) == false)
            return false;
       
        //prevents pawn from moving diagonally if there's no piece
        //at the position
        if(isDiagMove(move, board) == true)
            return true;

        boolean flag1 = false;
        boolean flag2 = false;

        //determines where the pawns are at the beginning of the game
        if(board[move.fromRow][move.fromColumn].type().equals("Pawn")){
            if(move.fromRow == 1)
                flag1 = true;
            else if(move.fromRow == 6)
                flag2 = true;
        }

        //allows the black pawn to move 2 spaces if in
        //starting position
        if(flag1 == true && owner == Player.BLACK){
            if(move.toRow <= move.fromRow + 2
                    && (move.toColumn == move.fromColumn)
                    && board[move.toRow][move.toColumn] == null
                    && move.toRow > move.fromRow)
                return true;
        }

        //allows the white pawn to move 2 spaces if in
        //starting position
        else if(flag2 == true && owner == Player.WHITE){


            if(move.toRow >= move.fromRow - 2
                    && (move.toColumn == move.fromColumn)
                    && board[move.toRow][move.toColumn] == null
                    && move.toRow < move.fromRow)
                return true;
        }

        //allows the white pawn to move 1 space
        else if(flag1 == false && owner == Player.BLACK){
            if(move.toRow == (move.fromRow +1)
                    && (move.toColumn == move.fromColumn)
                    && board[move.toRow][move.toColumn] == null){
                return true;
            }
        }

        //allows the white pawn to move 1 space
        else if(flag2 == false && owner == Player.WHITE){
            if(move.toRow == (move.fromRow -1)
                    && (move.toColumn == move.fromColumn)
                    && board[move.toRow][move.toColumn] == null){
                return true;
            }
        }

        return false;

    }

}