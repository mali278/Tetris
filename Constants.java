package Tetris;

import javafx.scene.paint.Color;

public class Constants {

    // width of each square
    public static final int SQUARE_WIDTH = 30;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = {{1 * SQUARE_WIDTH, 4*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 5*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 6 * SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 7 * SQUARE_WIDTH}};
    public static final int[][] T_PIECE_COORDS = {{1*SQUARE_WIDTH, 4*SQUARE_WIDTH}, {1*SQUARE_WIDTH, 5*SQUARE_WIDTH}, {1*SQUARE_WIDTH, 6 * SQUARE_WIDTH}, {2*SQUARE_WIDTH, 5*SQUARE_WIDTH}};

    public static final int[][] BOX_PIECE_COORDS = {{1 * SQUARE_WIDTH, 5*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 6*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 5*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 6*SQUARE_WIDTH}};
    public static final int[][] LEFT_L_PIECE_COORDS = {{1 * SQUARE_WIDTH, 4*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 4*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 5*SQUARE_WIDTH}, {2*SQUARE_WIDTH, 6 * SQUARE_WIDTH}};

    public static final int[][] RIGHT_L_PIECE_COORDS = {{2 * SQUARE_WIDTH, 4*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 4*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 5*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 6 * SQUARE_WIDTH}};

    public static final int[][] UP_PIECE_COORDS = {{1 * SQUARE_WIDTH, 4*SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 5*SQUARE_WIDTH}, {2 * SQUARE_WIDTH, 5 * SQUARE_WIDTH}, {2 * SQUARE_WIDTH, 6 * SQUARE_WIDTH}};

    public static final int[][] DOWN_PIECE_COORDS = {{ 1 * SQUARE_WIDTH, 5 * SQUARE_WIDTH}, {1 * SQUARE_WIDTH, 6 * SQUARE_WIDTH}, {2 * SQUARE_WIDTH, 4 * SQUARE_WIDTH}, {2 * SQUARE_WIDTH, 5 * SQUARE_WIDTH}};


    public static final int SCENE_WIDTH = 360;

    public static final int SCENE_HEIGHT = 670;

    public static final int QUIT_PANE_HEIGHT = 40;

    public static final int BUTTON_WIDTH = 50;

    public static final int RESTART_WIDTH = 70;

    public static final int BUTTON_HEIGHT = 30;

    public static final int OFFSET = 30;

    public static final double TIMELINE_DURATION = 0.5;

    public static final String QUIT_PANE_COLOR = "-fx-background-color: #add8e6";

    public static final int NUM_ROWS = 21;

    public static final int NUM_COLS = 12;

    public static final Color ACCESSIBLE_PURPLE = Color.web("#af46f0");

    public static final Color ACCESSIBLE_GREEN = Color.web("#a7ca01");

    public static final Color ACCESSIBLE_ORANGE = Color.web("#f88f52");

    public static final Color ACCESSIBLE_GOLD = Color.web("#b6770f");

    public static final Color ACCESSIBLE_BLUE = Color.web("#1186a4");

    public static final Color ACCESSIBLE_BRIGHT_GREEN = Color.web("#51cf7d");

    public static final Color ACCESSIBLE_BLUE_TWO = Color.web("#33cee3");

    public static final int SCORE_LABEL_HEIGHT = 45;
    public static final int SCORE_LABEL_WIDTH = 330;
}