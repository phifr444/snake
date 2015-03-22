package model;

/**
 * Denodes which state the game is currently in.
 */
public enum GameState {
    /**
     * PAUSED means that the game has paused; the snake is alive but not moving. All score is kept and resumed when unpaused.
     */

    PAUSED,
    /**
     * RUNNING means that the game is active and the snake is moving.
     */
    RUNNING,

    /**
     * GAME_OVER means the snake has collided with itself or is out of bounds. Score is stored into highscore.txt.
     */
    GAME_OVER,

    /**
     * MENU means that the game is residing in the menu.
     */
    MENU
}
