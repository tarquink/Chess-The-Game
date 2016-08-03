package chess;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**********************************************************************
 * Sets up the structure of the chess game. Each chess piece is
 * created and given its own variable name for usage in this class.
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 * @version March 2013
 *********************************************************************/

public class ChessModel implements IChessModel {
  
    /** Two dimensional array of IChessPieces */
    private IChessPiece[][] board;
  
    /** A chess piece */
    private ChessPiece piece;
  
    /** stores a value associated with a player's turn */
    private int turn;
  
    /** assigns a variable to the image of a black bishop at specified
     *  file location */
    private ImageIcon blackB = new ImageIcon ("Black B.ico");
  
    /** assigns a variable to the image of a black king at specified
     *  file location */
    private ImageIcon blackK = new ImageIcon ("Black K.ico");
  
    /** assigns a variable to the image of a black knight at specified
     *  file location */
    private ImageIcon blackN = new ImageIcon ("Black N.ico");
  
    /** assigns a variable to the image of a black pawn at specified
     *  file location */
    private ImageIcon blackP = new ImageIcon ("Black P.ico");
  
    /** assigns a variable to the image of a black queen at specified
     *  file location */
    private ImageIcon blackQ = new ImageIcon ("Black Q.ico");
  
    /** assigns a variable to the image of a black rook at specified
     *  file location */
    private ImageIcon blackR = new ImageIcon ("Black R.ico");
  
    /** assigns a variable to the image of a white bishop at specified
     *  file location */
    private ImageIcon whiteB = new ImageIcon ("White B.ico");
  
    /** assigns a variable to the image of a white king at specified
     *  file location */
    private ImageIcon whiteK = new ImageIcon ("White K.ico");
  
    /** assigns a variable to the image of a white knight specified
     *  file location */
    private ImageIcon whiteN = new ImageIcon ("White N.ico");
  
    /** assigns a variable to the image of a white pawn at specified
     *  file location */
    private ImageIcon whiteP = new ImageIcon ("White P.ico");
  
    /** assigns a variable to the image of a white queen at specified
     *  file location */
    private ImageIcon whiteQ = new ImageIcon ("White Q.ico");
  
    /** assigns a variable to the image of a white rook at specified
     *  file location */
    private ImageIcon whiteR = new ImageIcon ("White R.ico");
  
    /******************************************************************
     * Constructor method. Creates all of the chess pieces and places
     * each one at specified location. Sets the player turn to be player
     * one.
     ******************************************************************/
    public ChessModel(){
        board = new IChessPiece[8][8];

        board[0][0] = new Rook(Player.BLACK, blackR);
        board[0][7] = new Rook(Player.BLACK, blackR);
        board[7][0] = new Rook(Player.WHITE, whiteR);
        board[7][7] = new Rook(Player.WHITE, whiteR);

        board[0][1] = new Knight(Player.BLACK, blackN);
        board[0][6] = new Knight(Player.BLACK, blackN);
        board[7][1] = new Knight(Player.WHITE, whiteN);
        board[7][6] = new Knight(Player.WHITE, whiteN);

        board[0][2] = new Bishop(Player.BLACK, blackB);
        board[0][5] = new Bishop(Player.BLACK, blackB);
        board[7][2] = new Bishop(Player.WHITE, whiteB);
        board[7][5] = new Bishop(Player.WHITE, whiteB);

        board[0][3] = new King(Player.BLACK, blackK);
        board[7][3] = new King(Player.WHITE, whiteK);

        board[0][4] = new Queen(Player.BLACK, blackQ);
        board[7][4] = new Queen(Player.WHITE, whiteQ);

        // Creates 8 new pawns at the specified row.
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(Player.BLACK, blackP);
        }

        // Creates 8 new pawns at the specified row.
        for(int i = 0; i < 8; i++){
            board[6][i] = new Pawn(Player.WHITE, whiteP);
        }

        turn = 1;

    }

    /******************************************************************
     * Determines if the game is complete.
     *
     * @return returns false if the game is not over and returns true if
     * the king has no other moves that can be made
     ******************************************************************/
    public boolean isComplete() {
        return false;
    }

    /******************************************************************
     * Checks to see if a simple move from one space to another is valid.
     *
     * @param move a simple move from one location to another
     * @return returns true if the move is valid and returns false if
     * the move is invalid
     ******************************************************************/
    public boolean isValidMove(Move move) {

        return board[move.fromRow]
                [move.fromColumn].isValidMove(move, board);
    }

    /******************************************************************
     * Tracks the move of a chess piece and increments the the player's
     * turn {@value turn}.
     *
     * @param move integer values represented when making a move
     ******************************************************************/
    public void move(Move move) {
        turn++;

        board[move.toRow][move.toColumn]
                = board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn]
                = null;
    }

    /******************************************************************
     * Returns whether the given player is in check and checkmate
     * @return true if the player is in check, false otherwise
     ******************************************************************/
    public boolean inCheck(Player p) {

        Move mv = new Move(0,0,1,1);
        boolean check = false;
        int count = 0;
       

        //scans every space on the board to find the Player p's King
        if(p == currentPlayer()){         
            for(int r = 0; r < 8; r++){
                for(int c = 0; c < 8; c++){
                    if(board[r][c] != null){
                        if(pieceAt(r, c).type().equals("King") && pieceAt(r,c).player() == p){
                            mv.toRow = r;
                            mv.toColumn = c;                         
                        }
                    }
                }
            }
        }

        //scans every space on the board to see if any enemy piece can make a valid move to the King's location
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(board[row][col] != null && pieceAt(row, col) != null && pieceAt(row, col).player() != p){
                    mv.fromRow = row;
                    mv.fromColumn = col;
                    if(board[row][col].type().equals("Pawn") && pieceAt(row, col).player() != p){
                        if(board[row][col].isValidMove(mv, board) == true){
                            check = true;                           
                        }
                    }
                    else if(board[row][col].type().equals("Bishop") && pieceAt(row, col).player() != p){
                        if(board[row][col].isValidMove(mv, board) == true)
                            check = true;                      
                    }
                    else if(board[row][col].type().equals("Knight") && pieceAt(row, col).player() != p){
                        if(board[row][col].isValidMove(mv, board) == true){
                            check = true; 
                        }
                    }
                    else if(board[row][col].type().equals("Rook") && pieceAt(row, col).player() != p){                     
                        if(board[row][col].isValidMove(mv, board))
                            check = true; 
                    }
                    else if(board[row][col].type().equals("Queen") && pieceAt(row, col).player() != p){
                        if(board[row][col].isValidMove(mv, board) == true){
                            check = true; 
                        }
                    }              
                }
            }
        }       
       
        //if check is true, scans the board to find the player's king
        if(check == true){
            JOptionPane.showMessageDialog(null, "You are in check!");
            for(int r = 0; r < 8; r++){
                for(int c = 0; c < 8; c++){
                    if(board[r][c] != null){
                    if(pieceAt(r, c).type().equals("King") && pieceAt(r,c).player() == p){
                        mv.fromRow = r;
                        mv.fromColumn = c;
                    }
                }
            }
            }
         
            //scans the board to see if the King has any possible moves
            for(int row2 = 0; row2 < 8; row2++){
                for(int col2 = 0; col2 < 8; col2++){
                    //if(board[row2][col2] != null){
                    mv.toRow = row2;
                    mv.toColumn = col2;
                    if(board[mv.fromRow][mv.fromColumn].isValidMove(mv, board) == true){
                        count++;
                    }
                    }
                //}
            }
           
            //if no moves are possible, the king is in checkmate and the game is over
            if(count == 0){
                JOptionPane.showMessageDialog(null, "Checkmate! The game is over!");
                System.exit(0);
            }
            else if(count > 0)
                return true;

        }
         
        return false;
 
     
        }

    /******************************************************************
     * Keeps track of the current player by checking to see if turn is
     * an even or an odd number.
     *
     * @return returns Player.White if turn is odd and Player.Black if
     * turn is even
     ******************************************************************/
    public Player currentPlayer() {
        if(turn % 2 != 0)
            return Player.WHITE;
        else
            return Player.BLACK;
    }

    /******************************************************************
     * Returns an IChessPiece at the associated parameter location.
     *
     * @param row integer value corresponding to the row location
     * @param column integer value corresponding to the column location
     * @return returns an IChessPiece at the associated parameter
     * location
     ******************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }
  
    /******************************************************************
     * Get method for a ChessPiece.
     *
     * @return returns a chess piece
     ******************************************************************/
    public ChessPiece getPiece(){
        return piece;
    }

    /******************************************************************
     * Set method for a ChessPiece.
     *
     * @param piece a chess piece
     ******************************************************************/
    public void setPiece(ChessPiece piece){
        this.piece = piece;
    }
}