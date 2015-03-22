package controller;

import model.GameState;
import model.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * PlayerController is used when the game is in RUNNING-state. It listens for the movement keys and spacebar as well as
 * ESC.
 * When one of the movement keys is pressed, the SnakeController is informed and the action is stored. Space pauses the game
 * and ESC closes the program.
 */
public class PlayerController extends KeyAdapter
{
    private final SnakeController snakeController;


    private GameState gameState;

    //When this KeyListener is active the game is always running
    public PlayerController(Snake snake) {
        gameState = GameState.RUNNING;
        snakeController = new SnakeController(snake);
    }

    public GameState updateController() {
        snakeController.updateMovement();
        return gameState;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //Movement keys changes the states of the snake
        //Space and Escape changes the state of the game
        super.keyPressed(e);
	switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                snakeController.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                snakeController.moveRight();
                break;
            case KeyEvent.VK_UP:
                snakeController.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                snakeController.moveDown();
                break;
            case KeyEvent.VK_SPACE:
                gameState = GameState.PAUSED;
                break;
            case KeyEvent.VK_ESCAPE:
                gameState = GameState.MENU;
                break;
        }
    }


    public void setGameState(GameState state) {
        this.gameState = state;
    }

    public SnakeController getSnakeController() {
        return snakeController;
    }

}
