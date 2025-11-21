package Tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardSquare {

    private Rectangle square;

    private Color color;

    private Pane gamePane;

    /**
     *
     * @param gamePane
     * @param side
     * @param row
     * @param col
     * This is teh constructor to creates a square. It takes in Pane, side, determines if it's a border,
     * and the row and col as parameters.
     */

    public BoardSquare(Pane gamePane, boolean side, int row, int col){
        this.gamePane = gamePane;

        //creating a rectangle to represent one block
        this.square = new Rectangle(col, row, Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        this.square.setFill(Color.BLACK);
        this.color = Color.BLACK;


        //if it is a square on the border
        if (side){
            //fill is set to GREY
            this.square.setFill(Color.GREY);
            this.color = Color.GREY;
        }

        //making a black line around each square
        this.square.setStroke(Color.BLACK);
        this.square.setStrokeWidth(1.2);
        //adding to pane
        this.gamePane.getChildren().add(this.square);
    }

    /**
     *
     * @param color
     * This a setter method which changes the color of a square graphically,
     * through setFill, and logically, by updating the color instance variable,
     * based on the color that is passed in the parameter of the method
     *
     */
    public void setColor(Color color){
        this.color = color;
        this.square.setFill(color);
    }

    /**
     * This is a method that moves a square down by the Constants.SQUARE_WIDTH
     */
    public void moveDown(){
        this.square.setY(this.square.getY() + Constants.SQUARE_WIDTH);
    }

    /**
     * This is a method that moves a square right by the Constants.SQUARE_WIDTH
     */
    public void moveRight(){
        this.square.setX(this.square.getX() + Constants.OFFSET);
    }

    /**
     * This is a method that moves a square left by the Constants.SQUARE_WIDTH
     */
    public void moveLeft(){
        this.square.setX(this.square.getX() - Constants.OFFSET);
    }

    /**
     *
     * @return
     * This is a getter method that gets the color of the square
     */
    public Color getColor(){
        return this.color;
    }

    /**
     *
     * @return
     * This is a method that returns the Y position of the square
     */
    public double getSquareY(){
        return this.square.getY();
    }
    /**
     *
     * @return
     * This is a method that returns the X position of the square
     */
    public double getSquareX(){
        return this.square.getX();
    }

    /**
     * This is a method that sets the X position of the square based
     * on the x location passed in the parameter
     */
    public void setSquareX(int xLoc){
        this.square.setX(xLoc);
    }


    /**
     * This is a method that sets the Y position of the square based
     * on the y location passed in the parameter
     */
    public void setSquareY(int yLoc){
        this.square.setY(yLoc);
    }

    /**
     * This is a method that removes the square from the gamePane
     */
    public void removeFromPane(){
        this.gamePane.getChildren().remove(this.square);
    }

}