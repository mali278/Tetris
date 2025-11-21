package Tetris;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Tetris {


    private int[][] coords;

    private Color color;

    private BoardSquare[] tetrisPiece;

    private boolean isLocked;

    private boolean isPaused;

    /**
     *
     * @param myPane
     * @param coords
     * @param color
     *
     * The constructor where all instance variables are instantiated or referenced. factory Method
     * for creating Tetris Pieces is called.
     */
    public Tetris(Pane myPane, int[][] coords, Color color) {
        this.color = color;
        this.coords = coords;
        this.isPaused = false;
        this.tetrisPiece = new BoardSquare[this.coords.length];
        this.generateTetris(this.coords, myPane);
    }

    /**
     *
     * @param coords
     * @param pane
     *
     * This is factory method creates a tetris piece. Based on the 2D array that is passed
     * through them, this determines the x and y coordinates of the shape. Since the 2d
     * array is based off of 4 by 2 array, so 4 blocks are made and the color is set.
     * the x and y change to create a block.
     */
    public void generateTetris(int[][] coords, Pane pane) {
        //iterate through 2D coords array
        for (int i = 0; i < coords.length; i++) {
            int x = coords[i][0];
            int y = coords[i][1];
            //create new BoardSquare at that position
            this.tetrisPiece[i] = new BoardSquare(pane, false, x, y);
            this.tetrisPiece[i].setColor(this.color);
        }
    }

    /**
     *
     * @param squares
     * This is a method that allows the tetris piece to moveRight by passing through 2D array that
     * makes up the board as a parameter. If the piece is isLocked or isPaused, the method is exited.
     * A helper method that returns a boolean, canMove, is called. Based on what is returned, if it's true,
     * each square made up in the tetris piece is moved right.
     */
    public void moveRight(BoardSquare[][] squares) {
        //if isPaused or isLocked, leave method
        if (this.isLocked || this.isPaused) {
            return;
        }
        int x = 0;
        int y = Constants.SQUARE_WIDTH;
        //if the piece can move
        if (canMove(squares,x,y)){
            //iterate through tetris piece and moveRight
            for (BoardSquare board : this.tetrisPiece) {
                board.moveRight();
            }
        }
    }

    /**
     *
     * @param squares
     * This is a method that allows the tetris piece to moveLeft by passing through 2D array that
     * makes up the board as a parameter. If the piece is isLocked or isPaused, the method is exited.
     * A helper method that returns a boolean, canMove, is called. Based on what is returned, if it's true,
     * each square made up in the tetris piece is moved left.
     */
    public void moveLeft(BoardSquare[][] squares) {
        //if isPaused or isLocked, leave method
        if (this.isLocked || this.isPaused) {
            return;
        }
        int x = 0;
        int y = -Constants.SQUARE_WIDTH;
        //if the piece can move
        if (canMove(squares,x,y)){
            //iterate through tetris piece and moveLeft
            for (BoardSquare board : this.tetrisPiece) {
                board.moveLeft();
            }
        }
    }

    /**
     *
     * @param squares
     * This is a method that allows the tetris piece to moveDown by passing through 2D array that
     * makes up the board as a parameter. If the piece isPaused, the method is exited.
     * A helper method that returns a boolean, canMove, is called. Based on what is returned, if it's true,
     * each square made up in the tetris piece is moved down. If it's not true, isLocked is set to true, and the
     * tetris piece is added to the board via another method, add to Board
     */
    public void moveDown(BoardSquare[][] squares) {
        //if isPaused, leave method
        if (this.isPaused) {
            return;
        }
        this.isLocked = false;
        int x = Constants.SQUARE_WIDTH;
        int y = 0;
        //if the piece can move
        if (canMove(squares,x,y)){
            //iterate through tetris piece and moveDown
            for (BoardSquare board : this.tetrisPiece) {
                board.moveDown();
            }
        } else {
            //if can't move, the square isLocked and add that piece to board
            this.isLocked = true;
            this.addToBoard(squares);
        }
    }

    /**
     *
     * @param squares
     *
     * This is a method that adds pieces to the board, and passed the @D array of the board as a
     * parameter. The board piece at the same row and column as the tetris piece is set to the
     * square that makes up the tetris piece.
     */
    public void addToBoard(BoardSquare[][] squares) {
        //iterate through BoardSquares in the tetris piece
        for (BoardSquare square: this.tetrisPiece){
            //add board to square
            int row = (int) square.getSquareY() / Constants.SQUARE_WIDTH;
            int col = (int) square.getSquareX() / Constants.SQUARE_WIDTH;
            squares[row][col] = square;
        }
    }

    /**
     * @param squares
     * @param x
     * @param y
     * @return
     *
     * This is a private helper method that passes the 2D array the x and y value of the tetris piece.
     * The boolean is originally set to true. This method uses color checking to check if any piece
     * around teh tetris piece is full/ is a border or another tetris piece. If it is, canMove is set to false.
     * canMove is returned.
     */

    private boolean canMove(BoardSquare[][] squares, int x, int y) {
        boolean canMove = true;
        //iterate through BoardSquares in the tetris piece
        for (BoardSquare board : this.tetrisPiece) {
            int row = (int) ((board.getSquareY() + x) / Constants.SQUARE_WIDTH);
            int col = (int) ((board.getSquareX() + y) / Constants.SQUARE_WIDTH);
            //color checking to see if BoardSquare is full
            if (squares[row][col].getColor() != Color.BLACK) {
                //if the color is not black, it can not move
                canMove = false;
            }
        }
        //return canMove
        return canMove;
    }

    /**
     *
     * @param squares
     * @param type
     *
     * This is a method that allows the Tetris piece to rotate. It takes in the 2D array that makes up the board,
     * and the type of tetris piece. If the method is not Locked, or paused, and canRotate, helper method, then
     * the tetris pieces is rotated counterclockwise.
     */
    public void rotate(BoardSquare[][] squares, int[][] type){
        //if the tetris piece is not locked, paused, and canRotate
        if (!(this.isLocked || isPaused) && canRotate(squares, type)) {
            //find centerX and centerY
            int centerRotateX = (int) this.tetrisPiece[0].getSquareX();
            int centerRotateY = (int) this.tetrisPiece[0].getSquareY();
            for (int i = 1; i < this.tetrisPiece.length; i++) {
                //find oldY and oldX position
                int oldLocX = (int) this.tetrisPiece[i].getSquareX();
                int oldLocY = (int) this.tetrisPiece[i].getSquareY();
                //find newX and NewY position
                int newLocX = centerRotateX - centerRotateY + oldLocY;
                int newLocY = centerRotateX + centerRotateY - oldLocX;
                //set square in tetris piece to newX and newY
                this.tetrisPiece[i].setSquareX(newLocX);
                this.tetrisPiece[i].setSquareY(newLocY);
            }
        }
    }

    /**
     *
     * @param squares
     * @param type
     * @return
     *
     * This is a method that check that the tetris piece can rotate and return a boolean. It passed the 2D array
     * that is the board and the type of tetris piece. This method also uses color checking to check if any square
     * around the tetris piece is already full. If it is, canRotate is set to false or if it is a box tetris piece.
     * canRotate is returned.
     */
    private boolean canRotate(BoardSquare[][] squares, int[][] type){
        boolean canRotate = true;
        //if the type is a box
        if (type == Constants.BOX_PIECE_COORDS){
            //canRotate is false
            canRotate = false;
        }
        //finding the centerX and centerY
        int centerRotateX = (int) this.tetrisPiece[0].getSquareX();
        int centerRotateY = (int) this.tetrisPiece[0].getSquareY();
        //iterates through the BoardSquares in the tetrisPieces
        for (BoardSquare board: this.tetrisPiece){
            //finding the oldX and oldY position
            int oldLocX = (int) board.getSquareX();
            int oldLocY = (int) board.getSquareY();
            //finding newY and newX position
            int newLocX = centerRotateX - centerRotateY + oldLocY;
            int newLocY = centerRotateX + centerRotateY - oldLocX;
            int row = newLocY/Constants.SQUARE_WIDTH;
            int col = newLocX/Constants.SQUARE_WIDTH;
            //if it's a border, canRotate is false
            if (row < 0 || row >= squares.length || col < 0 || col >= squares[0].length) {
                canRotate = false;
                //or color checking to see if the square is already full
            } else if (squares[row][col].getColor() != Color.BLACK){
                canRotate = false;
            }
        }
        return canRotate;
    }

    /**
     *
     * @return boolean
     *
     * This is a method that returns a boolean. It returns if a tetris piece is at
     * the top of the screen.
     */

    public boolean atTop(){
        boolean atTop = false;
        //iterates through all the BoardSquares in the tetris pieces
        for (BoardSquare square: this.tetrisPiece){
            //if the square is locked and the Y position is at the top
            if (isLocked && square.getSquareY() <= 31){
                //setTop is true
                atTop = true;
            }
        }
        return atTop;
    }

    /**
     *
     * @return boolean
     *
     * This is a method that returns if a Tetris piece is locked the bottom.
     */

    public boolean isLocked() {
        return isLocked;
    }

    /**
     *
     * @return
     *
     * This is a method that returns the 2D coordinates. Basically, what type of
     * shape the Tetris piece is.
     */
    public int[][] getCoords(){
        return this.coords;
    }

    /**
     *
     * @return boolean
     *
     * This is setter method that sets the isPaused instance variable based on the
     * parameter that is passed.
     */
    public void setIsPaused(boolean isPaused){
        this.isPaused = isPaused;
    }

    /**
     * This is a method that passes through the tetris pieces and removed them from the
     * pane.
     */
    public void removeFromPane(){
        //iterates through all the BoardSquares in the tetris pieces
        for (BoardSquare square: this.tetrisPiece){
            //removed them from pane
            square.removeFromPane();
        }
    }

}

