package Tetris;

import Tetris.Game;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private BorderPane root;
    private Game game;

    public PaneOrganizer() {

        //instantiates the root node as a BorderPane
        this.root = new BorderPane();
        this.root.setFocusTraversable(false);

        //Instantiates the gamePane
        Pane gamePane = new Pane();
        gamePane.setFocusTraversable(true);
        this.root.setCenter(gamePane);

        //instantiates our game
        this.game = new Game(gamePane);

        //calling setUpButtons() method
        this.setUpButtons();
    }

    /**
     *
     * @return
     * getter method that return the root Node
     */
    public BorderPane getRoot() {
        return this.root;

    }

    /**
     * This is a private method that creates the buttons and adds them to the buttonPane, and
     * defines what happens to them when they are pressed. The method passes an HBox buttonPane
     * as a parameter, creates a "Quit" Button and a "Reset" Button, sets their preferred size
     * and style. It then adds them to the buttonPane, and uses a lambda expression to state what
     * each button does when it is clicked.
     * */

    private void setUpButtons() {
        HBox quitPane = new HBox(20);
        quitPane.setPrefSize(Constants.SCENE_WIDTH, Constants.QUIT_PANE_HEIGHT);
        quitPane.setStyle(Constants.QUIT_PANE_COLOR);

        //creating quit button and restart button and setting up pref size
        Button quit = new Button("Quit");
        quit.setPrefSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        quitPane.setAlignment(Pos.CENTER);

        Button restart = new Button("Restart");
        restart.setPrefSize(Constants.RESTART_WIDTH, Constants.BUTTON_HEIGHT);


        //adding buttons to quitPane
        quitPane.getChildren().addAll(quit, restart);
        this.root.setBottom(quitPane);

        quitPane.setFocusTraversable(false);
        quit.setFocusTraversable(true);
        restart.setFocusTraversable(true);

        //lambda expression
        quit.setOnAction((ActionEvent e) -> System.exit(0));
        restart.setOnAction((ActionEvent e) -> this.game.resetGame());
    }
}