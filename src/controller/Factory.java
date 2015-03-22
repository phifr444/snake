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
        SpritePart snakeHead = buildSpritePart(ResourceType.HEAD, x, y);
        return new Snake(snakeHead);
    }

    //Build a SpritePart
    public static SpritePart buildSpritePart(ResourceType type, int x, int y) {
        SpritePart spriteConfig = new SpritePart(x, y, GameConfig.PART_SIZE, GameConfig.PART_SIZE);

	spriteConfig.setCurrentState(SnakeDirection.UP);
	spriteConfig.setSpriteMap(ResourceManager.getMapping(type));

        return spriteConfig;
    }

    //Build the highscore-board
    public static Part buildHighscoreConfig() {
        return new Part(GameConfig.HIGHSCORE_START_X, GameConfig.HIGHSCORE_START_Y, GameConfig.HIGHSCORE_WIDTH, GameConfig.HIGHSCORE_HEIGHT, GameConfig.HIGHSCORE_COLOR);
    }

    //Build the body of the snake
    public static SpritePart buildSnakeBody(Snake snake, SnakeDirection state) {
        SpritePart snakeBody = new SpritePart(snake.getParts().get(snake.getParts().size() - 1).getxCoord(), snake.getParts().get(snake.getParts().size() - 1).getyCoord(),
                GameConfig.PART_SIZE, GameConfig.PART_SIZE);

        snakeBody.setCurrentState(state);
        snakeBody.setSpriteMap(ResourceManager.getMapping(ResourceType.BODY));


        return snakeBody;
    }
}
