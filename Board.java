package Tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board {

    private BoardSquare[][] squares;

    private int score;

    /**
     *
     * @param gamePane
     * This is the constructor of the Board class. This is where the 2D Array
     * that represents the Board is instantiated. The score is instantiated as 0.
     */
    public Board(Pane gamePane){
        this.squares = new BoardSquare[Constants.NUM_ROWS][Constants.NUM_COLS];
        this.generateSquares(gamePane);
        this.score = 0;

    }

    /**
     *
     * @param gamePane
     * This is a factory method that creates the Board of the method. BoardSquares are created
     * based on the number of rows and the number of columns. Also, this method determines
     * if it is a border or not. That is passed in the constructors of the BoardSquares
     */
    private void generateSquares(Pane gamePane){
        //iterating through the number of rows
        for (int row = 0; row < Constants.NUM_ROWS; row++){
            //iterating through the number of columns
            for (int col = 0; col < Constants.NUM_COLS; col++){
                //determining if it a border
                boolean isBorder =
                        (row == 0 || col == 0 || row == Constants.NUM_ROWS-1 ||col == Constants.NUM_COLS-1);
                //creating a BorderSquare at each position
                this.squares[row][col] =
                        new BoardSquare(gamePane,isBorder, row * Constants.SQUARE_WIDTH,col * Constants.SQUARE_WIDTH);
            }
        }
    }

    /**
     * This is a getter method that returns the 2D array
     * @return
     */
    public BoardSquare[][] getBoardSquares(){
        return this.squares;
    }

    /**
     * @param row
     * @return
     *
     * This is a private method that determines whether a row is full based on color checking.
     * It passes each row as a parameter and returns a boolean of if it is full or empty.
     */
    private boolean isFull(BoardSquare[] row){
        boolean isFull = true;
        for (BoardSquare oneSquare: row){
            double positionRow = oneSquare.getSquareY()/Constants.SQUARE_WIDTH;
            if (oneSquare.getColor() == Color.BLACK || positionRow == Constants.NUM_ROWS || positionRow == 0){
                isFull = false;
            }
        }
        return isFull;
    }

    /**
     *
     * @return
     *
     * This is private method that clears each line and moves the squares down. It iterates through
     * the board from top to bottom to determine which line is full. If a line is full, it iterates
     * from that row to the top, and moves the color of each column down at that row. It also returns
     * the amount of lines cleared.
     */
    public int clearingLines(){
        int linesCleared = 0;
        //iterates through every row but the last one
        for (int i = 0; i < this.squares.length - 1; i++){
            //if row is full
            if (this.isFull(this.squares[i])){
                //increasing the amount of lines cleared
                linesCleared++;
                //iterating from that lien to top
                for (int n = i; n > 1; n--){
                    //iterating through each column
                    for (int j = 1; j < this.squares[n].length-1; j++) {
                        //setting the color of the top square to the square below it
                        BoardSquare square1 = this.squares[n - 1][j];
                        BoardSquare square2 = this.squares[n][j];
                        Color topColor = square1.getColor();
                        square2.setColor(topColor);
                    }
                }
            }
        }
        //returning lines cleared
        return linesCleared;
    }

    /**
     * This is the reset method that sets every single block of the board
     * to black. Since my game uses color checking, this is the same thing
     * as aborting the game.
     */
    public void reset(){
        for (int i = 1; i < this.squares.length - 1; i++){
            for (int j = 1; j < this.squares[i].length - 1; j++){
                this.squares[i][j].setColor(Color.BLACK);
            }
        }
    }

}