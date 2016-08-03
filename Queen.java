package chess;

import javax.swing.ImageIcon;

/**********************************************************************
 * Class sets function of the Queen chess piece
 *
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 * @version March 2013
 *********************************************************************/

public class Queen extends ChessPiece{

   
    /******************************************************************
     * Constructor for the Queen
     *
     * @param owner sets owner of the Queen
     * @param icon sets appearance of the Queen
     *****************************************************************/
    public Queen(Player owner, ImageIcon icon){
        super(owner);
        this.setIcon(icon);
    }
  
    /******************************************************************
     * Returns the Queen chess piece's name
     *
     * @return String Queen's name
     *****************************************************************/
    public String type() {
        return "Queen";
    }
   
    /******************************************************************
     * Returns the Queen chess piece's appearance
     *
     * @return icon determines Queen's appearance
     *****************************************************************/
    public ImageIcon getIcon()
    {
        return icon;
    }
   
    /******************************************************************
        * Sets the Queen chess piece's appearance
        *
        * @param icon determines Queen's appearance
        *****************************************************************/
    public void setIcon(ImageIcon icon)
    {
        this.icon = icon;
    }
  
    /******************************************************************
        * Determines if the Queen's move is valid
        *
        * @param move determines Queen's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if move is valid
        *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board){
       
        //generic test for a valid move
        if(super.isValidMove(move, board) == false)
            return false;
       
        //prevents the queen from jumping pieces diagonally
        //from left to right
        if(diagLeftToRightJump(move, board) == false)
            return false;
       
        //prevents the queen from jumping pieces diagonally
        //from right to left
        if(diagRightToLeftJump(move, board) == false)
            return false;
      
        //prevents the queen from jumping pieces vertically
        if(verticalJump(move, board) == false)
            return false;
      
        //prevents the queen from jumping pieces horizontally
        if(horizontalJump(move, board) == false)
            return false;
      
        //allows Queen to move diagonally
        if(Math.abs(move.toRow - move.fromRow) == 
        		Math.abs(move.toColumn - move.fromColumn)
                &&(move.toRow != move.fromRow || move.toColumn != 
                move.fromColumn))
            return true;
      
        //allows Queen to move horizontally
        if((move.fromRow != move.toRow && move.fromColumn == move.toColumn)
                || (move.fromColumn != move.toColumn && move.fromRow == 
                move.toRow))
            return true;
      
        return false;
    }
  
    /******************************************************************
        * Determines if the Queen's would jump pieces diagonally from
        * left to right
        *
        * @param move determines Queen's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if piece would be jumped
        *****************************************************************/
    private boolean diagLeftToRightJump(Move move, IChessPiece[][] board){
  
        //piece moving from top to bottom of the board
        if(move.toRow <= 7 && move.toColumn <= 7
            && move.toRow>move.fromRow
            && move.toColumn>move.fromColumn){
          
            //position in front of where queen is moving
            int r1 = move.fromRow+1;
            int c1 = move.fromColumn+1;
      
            while(r1<=move.toRow && c1<=move.toColumn){
                if(board[r1][c1] != null && (board[r1][c1].player()
                    == board[move.fromRow][move.fromColumn].player()
                    && board[r1][c1].player()
                    == board[move.fromRow][move.fromColumn].player()))
                    return false;
                else{
                    r1++;
                    c1++;
                }
            }
        }
  
        //piece moving from bottom to top of the board
        if(move.toRow >= 0 && move.toColumn <= 7){
      
            //position in front of where queen is moving
            int r2 = move.fromRow-1;
            int c2 = move.fromColumn+1;
      
            while(r2>=move.toRow && c2<=move.toColumn){
                if(board[r2][c2] != null && (board[r2][c2].player()
                    == board[move.fromRow][move.fromColumn].player()
                    && board[r2][c2].player()
                    == board[move.fromRow][move.fromColumn].player()))
                    return false;
                else{
                    r2--;
                    c2++;
                }
            }      
        }
        return true;
    }

    /******************************************************************
        * Determines if the Queen's would jump pieces diagonally from
        * right to left
        *
        * @param move determines Queen's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if piece would be jumped
        *****************************************************************/
    private boolean diagRightToLeftJump(Move move, IChessPiece[][] board){
       
        //piece moving from bottom to top of the board
        if(move.toRow >= 0 && move.toColumn >= 0
            && move.toRow<move.fromRow
            && move.toColumn<move.fromColumn){
          
            //position in front of where queen is moving
            int r1 = move.fromRow-1;
            int c1 = move.fromColumn-1;
      
            while(r1>=move.toRow && c1>=move.toColumn){
                if(board[r1][c1] != null && board[r1][c1].player()
                    == board[move.fromRow][move.fromColumn].player())
                    return false;
                else if(board[r1][move.fromColumn] != null && board[r1][c1]
                		!= null
                    && (board[r1][c1].player()
                    != board[move.fromRow][move.fromColumn].player()
                    && board[r1][c1].player()
                    == board[move.fromRow][move.fromColumn].player()))
                    return true;
              
                else{
                    r1--;
                    c1--;
                }
            }
        }
  
       //piece moving from top to bottom of the board
       if(move.toRow <= 7 && move.toColumn <= 7
            && move.toRow>move.fromRow
            && move.toColumn<move.fromColumn){
         
           //position in front of where queen is moving
           int r2 = move.fromRow+1;
           int c2 = move.fromColumn-1;
      
           while(r2<=move.toRow && c2>=move.toColumn){
               if(board[r2][c2] != null && board[r2][c2].player()
                   == board[move.fromRow][move.fromColumn].player())
                   return false;
               if(board[r2][move.fromColumn] != null && board[r2][c2] 
            		   != null
                        && (board[r2][c2].player()
                        != board[move.fromRow][move.fromColumn].player()
                        &&board[r2][c2].player()
                        == board[move.fromRow][move.fromColumn].player()))
                        return true;
               else{
                   r2++;
                   c2--;
               }
           }
       }
  
       return true;
    }
  
    /******************************************************************
        * Determines if the Queen's would jump pieces vertically
        *
        * @param move determines Queen's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if piece would be jumped
        *****************************************************************/
    private boolean verticalJump(Move move, IChessPiece[][] board){
       
        //counts the number of pieces in the Queen's path
        //from top to bottom
        int count1 = 0;
        
        //tests top to bottom vertical jump test
        if(move.fromRow != 7
                && ((move.fromRow == move.toRow  && move.fromColumn != 
                move.toColumn) || (move.fromColumn == move.toColumn && 
                move.fromRow != move.toRow))){
            for(int r = move.fromRow+1; r < move.toRow; r++){
                if(board[r][move.fromColumn] != null
                        && (move.fromRow != move.toRow
                        || move.fromColumn != move.toColumn))
                    count1++;
            }
            if(count1 >= 1)
                return false;
        }
      
        //counts the number of pieces in the Queen's path
        //from bottom to top
        int count2 = 0;
       
        //bottom to top vertical jump test
        if(move.fromRow != 0
                && ((move.fromRow == move.toRow  && move.fromColumn != 
                move.toColumn) || (move.fromColumn == move.toColumn && 
                move.fromRow != move.toRow))){
            for(int r = move.fromRow-1; r > move.toRow; r--){
                if(board[r][move.fromColumn] != null
                        && (move.fromRow != move.toRow
                        || move.fromColumn != move.toColumn))
                      count2++;
              }
            if(count2 >= 1)
                return false;
        }
        return true;
      
    }
  
    /******************************************************************
        * Determines if the Queen's would jump pieces horizontally
        *
        * @param move determines Queen's starting and ending position
        * @param board shows where pieces are on the board
        * @return true or false if piece would be jumped
        *****************************************************************/
    private boolean horizontalJump(Move move, IChessPiece[][] board){
       
        //counts the number of pieces in the Queen's path
        //from bottom to top
        int count1 = 0;
       
        //Horizontal jump from right to left test
        if(move.fromColumn != 0 && (move.fromRow == move.toRow || 
        		move.fromColumn == move.toColumn)){
            for(int c = move.fromColumn-1; c > move.toColumn; c--){
                if(board[move.fromRow][c] != null)
                    count1++;
                if(board[move.toRow][move.toColumn] != null
                         && board[move.toRow][move.toColumn].player() !=
                       board[move.fromRow][move.fromColumn].player() && 
                       count1 == 0)
                    return true;
            }
            if(count1 >= 1)
                return false;
        }
      
        //counts the number of pieces in the Queen's path
        //from bottom to top
        int count2 = 0;
       
        //Horizontal jump from left to right test
        if(move.fromColumn != 7
                && (move.fromRow == move.toRow || move.fromColumn == 
                move.toColumn)){
            for(int c = move.fromColumn+1; c < move.toColumn; c++){
                if(board[move.fromRow][c] != null
                        )
                    count2++;
               if(board[move.toRow][move.toColumn] != null
                        && board[move.toRow][move.toColumn].player() !=
                       board[move.fromRow][move.fromColumn].player() 
                       && count2 == 0)
                    return true;
            }
          
            if(count2 >= 1)
                return false;
        }
        return true;
     }
}
