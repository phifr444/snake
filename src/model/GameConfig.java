package model;

import java.awt.*;

/**
 * GameConfig is used to configure certain parameters of the game. Notably you can use this class to change the resolution and
 * color of the board. All parameters are final and can only be changed directly in the class itself; there are no options
 * in the game to change these.
 */
public final class GameConfig {

    /**
     * Color of the background of the board.
     */
    public final static Color BOARD_COLOR = Color.gray;

    /**
     * Size of all parts (apple, snake-head etc).
     */
    public final static int PART_SIZE = 10;

    /**
     * Width of the board in pixels.
     */
    public final static int BOARD_WIDTH = 800;

    /**
     * Height of the board in pixels.
     */
    public final static int BOARD_HEIGHT = 600;

    /**
     * A factor of the snakes starting-speed. 1 is default, 2 is twice as fast etc.
     */
    public final static int SNAKE_START_SPEED = 1;

    /**
     * Starting direction of the snake.
     */
    public final static SnakeDirection SNAKE_START_STATE = SnakeDirection.UP;

    /**
     * Name displayed in the title.
     */
    public final static String GAME_NAME = "Snake";

    /**
     * Starting state.
     */
    public final static GameState GAME_START_STATE = GameState.MENU;

    /**
     * Label to be displayed when the game is paused.
     */
    public final static String PAUSED_LABEL = "Game Paused";

    /**
     * Color of the background within the highscore panel.
     */
    public final static Color HIGHSCORE_COLOR = Color.BLACK;

    /**
     * Width of the highscore panel.
     */
    public final static int HIGHSCORE_WIDTH = 200;

    /**
     * Height of the highscore panel.
     */
    public final static int HIGHSCORE_HEIGHT = 600;

    /**
     * X-coordinate for the highscore panel.
     */
    public final static int HIGHSCORE_START_X = 800;

    /**
     * Y-coordinate for the highscore panel.
     */
    public final static int HIGHSCORE_START_Y = 0;

    /**
     * Label to be displayed within the highscore panel.
     */
    public final static String HIGHSCORE_LABEL = "Top 10 Highscores";

    /**
     * Score obtained in the current ongoing game.
     */
    public final static String CURRENT_SCORE_LABEL = "Current score";

    /**
     * Dimensions of all buttons. Buttons are used in the menu.
     */
    public final static Dimension BUTTON_DIMENSION = new Dimension(150, 50);

    private GameConfig() {}
}
