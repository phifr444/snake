package controller;


import model.Game;
import model.Part;
import util.Intersector;

/**
 * CollisionController used in all types of collison-checks. Checks if the snake has collided with itself or if the head has
 * succesfully hit an apple.
 * Accomplishes this by temporarily moving the snake forward one step in the current direction it's heading and then calling
 * on the intersector-class to check if the head is intersecting the coordinates of the apple or one of its bodyparts.
 * To check if the snake is out of bounds the intersector checks if the board actually still contains the snake.
 */
public class CollisionController {
    private final Game game;
    private final Part boardConfig;
    private final SnakeController snakeController;
    private int oldSize;


    public CollisionController(SnakeController controller, Game game, Part boardConfig) {
        this.game = game;
        this.boardConfig = boardConfig;
        this.snakeController = controller;
        oldSize = game.getSnake().getParts().size();
    }

    public boolean hasSelfCollided() {
        return checkOneFrameAhead(true);
    }

    public boolean hasAppleCollided() {
        return Intersector.isIntersectingPart(game.getSnake().getHead(), game.getApple());
    }

    private boolean checkOneFrameAhead(boolean selfCollision) {
        boolean returnVal = false;
        int tempX = game.getSnake().getHead().getxCoord();
        int tempY = game.getSnake().getHead().getyCoord();
        snakeController.updateSnakeHead();
        //Self collision
        if (selfCollision) {
            //Special case after apple collision
            if (game.getSnake().getParts().size() != oldSize) {
                oldSize = game.getSnake().getParts().size();
                returnVal = false;
            }
            if (game.getSnake().getParts().size() > 1) {
                for (int i = 1; i < game.getSnake().getParts().size(); i++) {
                    if (Intersector.isIntersectingPart(game.getSnake().getHead(), game.getSnake().getParts().get(i))) {
                        returnVal = true;
                    }
                }
            }
        }
        //Frame collision
        else {
            if (!Intersector.isContainingPart(boardConfig, game.getSnake().getHead())) {
                returnVal = true;
            }
        }
        game.getSnake().getHead().setxCoord(tempX);
        game.getSnake().getHead().setyCoord(tempY);
        return returnVal;
    }

    public boolean hasCollidedFrame() {
        return checkOneFrameAhead(false);
    }
}
