package controller;

import model.*;

/**
 * Factory is used to create all parts and it's subtypes, as well as the snake-array used to represent the snake.
 */
public final class Factory {

    //Make sure there are no other instances
    private Factory() {
    }

    //Build the board
    public static Part buildBoardConfig() {
        return new Part(0, 0, GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, GameConfig.BOARD_COLOR);
    }

    //Build the snake
    public static Snake buildSnakeConfig(int x, int y) {
        SpritePart snakeHead = buildSnakeHead(x, y);
        return new Snake(snakeHead);
    }

    //Build the apple
    public static SpritePart buildAppleConfig(int x, int y) {
        SpritePart appleConfig = new SpritePart(x, y, GameConfig.PART_SIZE, GameConfig.PART_SIZE);

        appleConfig.setCurrentState(SnakeState.UP);
        appleConfig.setSpriteMap(Resources.getAppleMapping());

        return appleConfig;
    }

    //Build the snakes head
    private static SpritePart buildSnakeHead(int x, int y) {
        SpritePart snakeHead = new SpritePart(x, y, GameConfig.PART_SIZE, GameConfig.PART_SIZE);

        snakeHead.setCurrentState(SnakeState.UP);

        snakeHead.setSpriteMap(Resources.getSnakeHeadMapping());

        return snakeHead;
    }

    //Build the highscore-board
    public static Part buildHighscoreConfig() {
        return new Part(GameConfig.HIGHSCORE_START_X, GameConfig.HIGHSCORE_START_Y, GameConfig.HIGHSCORE_WIDTH, GameConfig.HIGHSCORE_HEIGHT, GameConfig.HIGHSCORE_COLOR);
    }

    //Build the body of the snake
    public static SpritePart buildSnakeBody(Snake snake, SnakeState state) {
        SpritePart snakeBody = new SpritePart(snake.getParts().get(snake.getParts().size() - 1).getxCoord(), snake.getParts().get(snake.getParts().size() - 1).getyCoord(),
                GameConfig.PART_SIZE, GameConfig.PART_SIZE);

        snakeBody.setCurrentState(state);
        snakeBody.setSpriteMap(Resources.getSnakeBodyMapping());


        return snakeBody;
    }
}
