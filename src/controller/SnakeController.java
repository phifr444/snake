package controller;

import model.*;

/**
 * SnakeController is used to update the snakes movements and to reset the snake when the GameEngine switches states
 * from RUNNING to MENU
 */
public class SnakeController {
    private final Snake snake;


    public SnakeController(Snake snake) {
        this.snake = snake;
        snake.setMovementSpeed(GameConfig.SNAKE_START_SPEED);
        snake.setState(GameConfig.SNAKE_START_STATE);
    }

    //Checks for invalid movement and changes the SnakeState
    public void moveLeft() {
        if (snake.getState() != SnakeState.RIGHT) {
            snake.setState(SnakeState.LEFT);
        }
    }

    public void moveRight() {
        if (snake.getState() != SnakeState.LEFT) {
            snake.setState(SnakeState.RIGHT);
        }
    }

    public void moveUp() {
        if (snake.getState() != SnakeState.DOWN) {
            snake.setState(SnakeState.UP);
        }
    }

    public void moveDown() {
        if (snake.getState() != SnakeState.UP) {
            snake.setState(SnakeState.DOWN);
        }
    }

    //Updates SnakeHead position and opens the StateLock to enable new movement
    public void updateSnakeHead() {
        switch (snake.getState()) {
            case LEFT:
                snake.getHead().setxCoord(snake.getHead().getxCoord() - snake.getMovementSpeed());
                break;
            case RIGHT:
                snake.getHead().setxCoord(snake.getHead().getxCoord() + snake.getMovementSpeed());
                break;
            case UP:
                snake.getHead().setyCoord(snake.getHead().getyCoord() - snake.getMovementSpeed());
                break;
            case DOWN:
                snake.getHead().setyCoord(snake.getHead().getyCoord() + snake.getMovementSpeed());
                break;
        }
        snake.openStateLock();
    }

    private void updateSnakeBody() {
        for (int i = snake.getParts().size() - 1; i > 0; i--) {
            SpritePart partA = snake.getParts().get(i);
            SpritePart partB = snake.getParts().get(i - 1);

            partA.setCurrentState(partB.getCurrentState());
            partA.setxCoord(partB.getxCoord());
            partA.setyCoord(partB.getyCoord());
        }
    }

    public static void resetSnake(Snake snake) {
        if (snake.getParts().size() > 1) {
            SpritePart snakeHead = snake.getHead();
            snake.getParts().clear();

            snakeHead.setxCoord(GameConfig.BOARD_WIDTH / 2);
            snakeHead.setyCoord(GameConfig.BOARD_HEIGHT / 2);
            snake.addPart(snakeHead);
        } else {
            snake.getHead().setyCoord(GameConfig.BOARD_HEIGHT / 2);
            snake.getHead().setxCoord(GameConfig.BOARD_WIDTH / 2);
        }
        snake.setState(GameConfig.SNAKE_START_STATE);
    }

    public void updateMovement() {
        //Body is updated first
        updateSnakeBody();
        updateSnakeHead();
    }
}
