package chess;

import javax.swing.ImageIcon;

/**
 * decribes a chess piece for the game of chess.
 *
 * @author Kevin Tarquinio, Jeffrey Nolan, Thomas Szczesny, RJ Hamilton
 * @version March 2013
 */

public abstract class ChessPiece implements IChessPiece {
	
	/** A representation of who's turn it is */
	private Player owner;
	
	/** A representation of a chess game */
	private ChessModel model;
	
	/** A representation of a chess piece icon */
	ImageIcon icon;
	
	/******************************************************************
     * Constructor for the chess piece
     *
     * @param player states who's chess piece it is
     *****************************************************************/
	protected ChessPiece(Player player){
		this.owner = player;
	}
	
	/******************************************************************
     * Constructor for the chess piece that sets the icon
     *
     * @param owner sets image icon
     *****************************************************************/
	public ChessPiece(ImageIcon icon){
		this.icon = icon;
	}
	
	/******************************************************************
     * Method for returning a type of string for the chess piece
     *
     *****************************************************************/
	public abstract String type();
	
	/******************************************************************
     * Determines who's turn it is
     *
     * @return owner returns who's turn it is
     *****************************************************************/
	public Player player() {
			return owner;
	}
	
	/**
     * Checks for a valid move for any chess piece
     *
     * @param move moving from one spot to another.
     * @param board represents the chess board.
     */
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		if(board[move.fromRow][move.fromColumn] != null){						   //The place you are moving from is not empty.
			if(move.toRow != move.fromRow || move.toColumn != move.fromColumn){	   //If you aren't moving on yourself.	
				if(board[move.toRow][move.toColumn] == null 					   //If the space you are moving to is empty.
						|| board[move.toRow][move.toColumn].player() != board[move.fromRow][move.fromColumn].player())  
					return true;
			}
		}
		return false;
	}
}
