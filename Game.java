package Tetris;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Game{

    //private instance variables

    private int score;
    private Pane gamePane;
    private Tetris tetris;

    private Board board;
    private Timeline timeline;
    private double speed;
    private Label scoreLabel;
    private int num;

    /**
     *
     * @param gamePane
     * This is the constructor and the gamePane is passed through as a parameter,
     * but it is not instantiated.
     */

    public Game(Pane gamePane){
        this.gamePane = gamePane;
        this.board = new Board(this.gamePane);
        this.pickPieces(this.gamePane);
        this.num = 1;
        this.speed = Constants.TIMELINE_DURATION;
        this.startGame();
        this.setUpScoreLabel();
    }

    /**
     *
     * @param gamePane
     * @return
     *
     * This is a pickPieces method that passes the gamePane as a parameter.
     * This method generates random Tetris pieces using a switch statement.
     * This tetris piece is then returned.
     */
    private Tetris pickPieces(Pane gamePane){
        //create random integer
        int randomPiece = (int)(Math.random()*7);
        //based on random integer, create a different piece with different color
        switch (randomPiece){
            case 0:
                this.tetris = new Tetris(gamePane, Constants.I_PIECE_COORDS, Constants.ACCESSIBLE_PURPLE);
                break;
            case 1:
                this.tetris = new Tetris(gamePane, Constants.T_PIECE_COORDS, Constants.ACCESSIBLE_GREEN);
                break;
            case 2:
                this.tetris = new Tetris(gamePane, Constants.BOX_PIECE_COORDS, Constants.ACCESSIBLE_ORANGE);
                break;
            case 3:
                this.tetris = new Tetris(gamePane, Constants.LEFT_L_PIECE_COORDS, Constants.ACCESSIBLE_GOLD);
                break;
            case 4:
                this.tetris = new Tetris(gamePane, Constants.RIGHT_L_PIECE_COORDS, Constants.ACCESSIBLE_BLUE);
                break;
            case 5:
                this.tetris = new Tetris(gamePane, Constants.UP_PIECE_COORDS, Constants.ACCESSIBLE_BRIGHT_GREEN);
                break;
            case 6:
                this.tetris = new Tetris(gamePane, Constants.DOWN_PIECE_COORDS, Constants.ACCESSIBLE_BLUE_TWO);
                break;
            default:
                this.tetris = new Tetris(gamePane, Constants.I_PIECE_COORDS, Constants.ACCESSIBLE_PURPLE);
                break;
        }
        return this.tetris;
    }

    /**
     *
     * @param e
     *
     * This is a method that handles the key inputs. Based on what key is pressed,
     * the moveLeft, moveRight, moveDown, rotate, pauseGame, and spaceBar methods
     * are called.
     */
    private void handleKeyPress(KeyEvent e){
        switch(e.getCode()){
            case LEFT:
                this.tetris.moveLeft(this.board.getBoardSquares());
                break;
            case RIGHT:
                this.tetris.moveRight(this.board.getBoardSquares());
                break;
            case DOWN:
                this.tetris.moveDown(this.board.getBoardSquares());
                break;
            case UP:
                this.tetris.rotate(this.board.getBoardSquares(), this.tetris.getCoords());
                break;
            case P:
                this.num *= -1;
                this.pauseGame(num);
                break;
            case SPACE:
                this.spaceBar();
                break;
        }
        e.consume();
    }

    /**
     * This is teh update method that is passed in a lambda expression in the keyFrame.
     * If the piece is not locked, the piece is moveDown. Once that piece is locked,
     * create a new piece using the continuousPieces method. The score is also increased
     * based on the amount of lines cleared at a time.
     */
    private void update(){
        //if not locked
        if (!this.tetris.isLocked()) {
            //piece moveDown
            this.tetris.moveDown(this.board.getBoardSquares());
        }
        else {
            //else, generate more pieces
            this.continuousPieces(this.gamePane);
        }
        //increase score
        int linesCleared = this.board.clearingLines();
        this.increaseScore(linesCleared);
        this.scoreLabel.setText("Score: " + this.score);
    }

    /**
     * This is a startGame method which creates the keyFrame and instantiated the
     * timeline and starts it. It also calls setOnKeyPressed which passed the handleKeyPressed
     * method in the lambda expression.
     */
    private void startGame(){
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(this.speed), (ActionEvent event) -> this.update());
        this.timeline = new Timeline(keyFrame);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     *
     * @param pane
     * This is a private method that creates continuousPieces that passes pane as a parameter.
     * the TopPiece is set to the current piece on screen. If this piece is at the top, the endGame
     * method is called and the method is exited. If it's not at the top, newPiece is generated,
     * and the topPiece is set to the newPiece.
     */
    private void continuousPieces(Pane pane){
        Tetris topPiece = this.tetris;
        //if the piece is at the top
        if (topPiece.atTop()){
            //end game
            this.endGame();
            //exit method
            return;
        }
        Tetris newPiece = this.pickPieces(pane);
        topPiece = newPiece;
    }

    /**
     *
     * @param num
     * This is a private method that paused the game that passes a num as a parameter.
     * If the number is -1, the timeline is paused, and isPaused is set to true. If it's
     * equal to 1, timeline is set to play, the timeline is set to indefinite, and isPaused
     * is set to false.
     */
    private void pauseGame(int num){
        //if -1
        if (num == -1){
            //pause timeline and isPaused set to true
            this.timeline.pause();
            this.tetris.setIsPaused(true);
        } else if (num == 1){
            //play timeline
            this.timeline.play();
            this.timeline.setCycleCount(Animation.INDEFINITE);
            //isPaused is set to false
            this.tetris.setIsPaused(false);
        }
    }

    /**
     * This is a private method, that moves the piece all the way until it is locked.
     * This is called in handleKeyPressed method, so that when the space bar is pressed, the
     * piece moves all the way down.
     */

    private void spaceBar(){
        //while the tetris piece is not locked
        while (!this.tetris.isLocked()){
            //move it down until it is locked
            this.tetris.moveDown(this.board.getBoardSquares());
        }
    }

    /**
     * This is a private method that ends the game. It created a label which
     * shows up on screen and stops the timeline.
     */
    private void endGame(){
        //if the piece use is at the top
        if (this.tetris.atTop()){
            //create label and stop timeline
            HBox labelPane = new HBox();
            labelPane.setPrefSize(100,60);
            Label finished = new Label("Game Over!!!");
            finished.setTextFill(Color.WHITE);
            labelPane.setAlignment(Pos.TOP_LEFT);
            labelPane.getChildren().add(finished);
            labelPane.toFront();
            this.gamePane.getChildren().add(labelPane);
            this.timeline.stop();
        }
    }

    /**
     * This is a method that resets the game. It removes the current piece from the
     * pane and calls the continuousPieces method to create newPieces.
     */
    public void resetGame(){
        this.board.reset();
        this.tetris.removeFromPane();
        this.continuousPieces(this.gamePane);
        this.score = 0;
    }

    /**
     * This is a private method that creates the scoreLabel and puts it on screen.
     */
    private void setUpScoreLabel(){
        this.scoreLabel = new Label("Score: ");
        this.scoreLabel.setTextFill(Color.WHITE);
        this.scoreLabel.setAlignment(Pos.TOP_RIGHT);
        this.scoreLabel.setPrefSize(Constants.SCORE_LABEL_WIDTH, Constants.SCORE_LABEL_HEIGHT);
        this.gamePane.getChildren().add(this.scoreLabel);
        scoreLabel.toFront();
    }

    /**
     *
     * @param linesCleared
     * This is the public method that increase the score based on the amount of linesCleared
     * which is passed as a parameter. If four lines are cleared, the score is set to 1000.
     * In every other case, the amount of lines cleared is multiplied by 100. This is added
     * to the score.
     */
    public void increaseScore(int linesCleared){
        int score = linesCleared;
        //if linesCleared is equal to 4
        if (linesCleared == 4){
            //score is 1000
            score += 1000;
        } else {
            //else, score is multiplied by 100
            score *= 100;
        }
        //add to score
        this.score += score;
    }

}