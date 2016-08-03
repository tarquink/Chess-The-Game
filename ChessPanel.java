package chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**********************************************************************
 * Creates the chess board and set its functions
 *
 *
 * @author Jeff Nolan, Kevin Tarquinio, RJ Hamilton, Thomas Szczesny
 *********************************************************************/

public class ChessPanel extends JPanel {
   
    /*2D array of buttons*/
    private JButton[][] board;
    
    /*determines game's functions*/
    private ChessModel model;
    
    /*determines if its the player's first or second click*/
    private boolean fromMove = false;
    
    /*stores the row and column of the from and to move*/
    private Move move;
    
    /*displays current player's turn*/
    private JLabel playColor;
    
    /*contains game board*/
    private JPanel panel;
    
    /*contains current player label*/
    private JPanel panel2;
    
    /*sets image for black bishop*/
    private ImageIcon blackB = new ImageIcon ("Black B.ico");
    
    /*sets image for black king*/
    private ImageIcon blackK = new ImageIcon ("Black K.ico");
    
    /*sets image for black knight*/
    private ImageIcon blackN = new ImageIcon ("Black N.ico");
    
    /*sets image for black pawn*/
    private ImageIcon blackP = new ImageIcon ("Black P.ico");
    
    /*sets image for black queen*/
    private ImageIcon blackQ = new ImageIcon ("Black Q.ico");
    
    /*sets image for black rook*/
    private ImageIcon blackR = new ImageIcon ("Black R.ico");
    
    /*sets image for white bishop*/
    private ImageIcon whiteB = new ImageIcon ("White B.ico");
    
    /*sets image for white king*/
    private ImageIcon whiteK = new ImageIcon ("White K.ico");
    
    /*sets image for white knight*/
    private ImageIcon whiteN = new ImageIcon ("White N.ico");
    
    /*sets image for white pawn*/
    private ImageIcon whiteP = new ImageIcon ("White P.ico");
    
    /*sets image for white queen*/
    private ImageIcon whiteQ = new ImageIcon ("White Q.ico");
    
    /*sets image for white rook*/
    private ImageIcon whiteR = new ImageIcon ("White R.ico");

    /*sets image for black bishop*/
    private ButtonListener buttonListener = new ButtonListener();
   
    
    /**********************************************************************
     * Constructor for the panel
     *********************************************************************/
    public ChessPanel(){
       
        //chess panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8, 5, 5));
       
        //panel displaying current player's turn
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,1));
       
        board = new JButton[8][8];
       
        model = new ChessModel();
       
        //displays the current player's turn
        playColor = new JLabel(model.currentPlayer() + "'s Turn");
        panel2.add(playColor);

        //adds buttons to the board
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++) {
               board[row][col] = new JButton("");
               board[row][col].addActionListener(buttonListener);
               board[row][col].setPreferredSize(new Dimension(100,70));
               panel.add(board[row][col]);
            }
       
        add(panel);
        add(panel2);
        colorBoard();
        displayBoard();
    }
   
    /**********************************************************************
     * Creates the chess board
     *********************************************************************/
    private void displayBoard(){
        //sets each icon to a specific piece
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                if (model.pieceAt(r,c) != null){
                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("Bishop"))
                        board[r][c].setIcon(blackB);

                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("King"))
                        board[r][c].setIcon(blackK);

                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("Knight"))
                        board[r][c].setIcon(blackN);

                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("Pawn"))
                        board[r][c].setIcon(blackP);

                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("Queen"))
                        board[r][c].setIcon(blackQ);

                    if((model.pieceAt(r,c).player() == Player.BLACK) &&
                            model.pieceAt(r,c).type().equals("Rook"))
                        board[r][c].setIcon(blackR);
                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("Bishop"))
                        board[r][c].setIcon(whiteB);

                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("King"))
                        board[r][c].setIcon(whiteK);

                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("Knight"))
                        board[r][c].setIcon(whiteN);

                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("Pawn"))
                        board[r][c].setIcon(whiteP);

                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("Queen"))
                        board[r][c].setIcon(whiteQ);

                    if((model.pieceAt(r,c).player() == Player.WHITE) &&
                            model.pieceAt(r,c).type().equals("Rook"))
                        board[r][c].setIcon(whiteR);
                }
                else
                    board[r][c].setIcon(null);
            }
        
        //checks if the current player is in check
        model.inCheck(model.currentPlayer());
    }

    /**********************************************************************
     * Creates a black and white checkered pattern on the board
     *********************************************************************/
    private void colorBoard(){
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++){
                if((r%2==0 && (c==0 || c%2 == 0))
                        || (r%2 != 0 && c%2 != 0))
                    board[r][c].setBackground(Color.WHITE);
                else
                    if(c==0 || (r%2 == 0 && c%2 != 0)
                    ||(c%2 == 0 && r%2!=0))
                    board[r][c].setBackground(Color.BLACK);
            }
    }
    
    /**********************************************************************
     * Creates the button's function
     *********************************************************************/
    private class ButtonListener implements ActionListener{
       
        public void actionPerformed(ActionEvent event){
            
            //finds the location that was clicked
            for(int r = 0; r < 8; r++){
                for(int c = 0; c < 8; c++){
                    if(board[r][c] == event.getSource()
                            && fromMove == false){
                        
                        //sets initial position if its
                        //that player's turn
                        if(model.pieceAt(r,c)!= null
                                && model.pieceAt(r,c).player()
                                == model.currentPlayer()){
                            
                            //sets from position
                            move = new Move(r,c,-1,-1);
                            
                            fromMove = true;
                         
                        }
                    }
                   
                    else
                        //sets to position
                        if(board[r][c] == event.getSource()
                            && fromMove == true){
                       
                            move.toRow = r;
                            move.toColumn = c;
                            
                            //checks if the move was valid
                            if(model.isValidMove(move) == true){
                                //moves piece
                                model.move(move);
                                
                                //updates board appearance after move
                                displayBoard();
                                
                                //updates the current player label
                                playColor.setText(model.currentPlayer()
                                        + "'s Turn");
                                
                                fromMove = false;
                            }
                       
                        else

                            fromMove = false;
                      }
                   
                }
               
            }
           
        }
    }
} 