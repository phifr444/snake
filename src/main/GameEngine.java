package main;

import controller.*;
import model.*;
import util.GameUtil;
import view.GameFrame;

/**
 * GameEngine is a state machine that runs the game, there are 4 statse MENU, RUNNING, PAUSED, GAME_OVER
 * When in the MENU-state the MenuController is active and the frame that is shown is the menu
 * When in the RUNNING-state the PlayerController is active and the frame that is shown is the gameFrame
 * When in the PAUSED-state the MenuController is active and the frame that is shown is the gameFrame
 * When in the GAME_OVER-state the system exits
 * We have also med a switchState function that removes and adds the keylisteners depending on what transition we have
 * it also removes and adds the right frame, when switching state from RUNNING to MENU it will write the current score
 * and name to the highscore file "highscore.txt"
 */
class GameEngine {
    private CollisionController collisionController;
    private Game game;
    private PlayerController playerController;
    private GameState currentState = GameConfig.GAME_START_STATE;

    private GameFrame gameFrame;

    private MenuController menuController;

    GameEngine() {
        initGame();
        initGUI();
        initFrame();
    }

    private void initFrame() {
        gameFrame = new GameFrame(game);
        gameFrame.setTitle(game.getGameName());
        gameFrame.addKeyListener(menuController);

    }

    private void initGame() {
        //Load sprites first
        Resources.loadResources();

        Snake snake = Factory.buildSnakeConfig(GameConfig.BOARD_HEIGHT / 2, GameConfig.BOARD_WIDTH / 2
        );

        SpritePart apple = Factory.buildAppleConfig(GameUtil.getRandomInt(GameConfig.BOARD_WIDTH, GameConfig.PART_SIZE),
                GameUtil.getRandomInt(GameConfig.BOARD_HEIGHT, GameConfig.PART_SIZE)
        );

        Part board = Factory.buildBoardConfig();

        Part highscorePart = Factory.buildHighscoreConfig(
        );

        game = new Game(snake, apple, board, highscorePart, currentState);

        playerController = new PlayerController(game.getSnake());
        collisionController = new CollisionController(playerController.getSnakeController(), game, game.getBoard());

    }


    private void initGUI() {
        menuController = new MenuController();
    }


    public void updateGame() {
        switch (currentState) {
            case PAUSED:
                currentState = gamePaused();
                break;
            case RUNNING:
                currentState = gameRunning();
                break;
            case GAME_OVER:
                currentState = GameState.GAME_OVER;
                break;
            case MENU:
                currentState = gameMenu();
                break;
        }
        if (currentState != GameState.GAME_OVER)
            gameFrame.repaint();

    }

    private GameState gameMenu() {
        GameState tmpState = gameFrame.updateFrame();
        if (tmpState != currentState) {
            game.setUserName(gameFrame.getUserName());
            switchState(tmpState);
        }
        return tmpState;
    }

    private GameState gamePaused() {
        GameState tmpState = menuController.updateController();
        if (tmpState != currentState) {
            switchState(tmpState);
        }
        return tmpState;
    }

    private GameState gameRunning() {
        if (collisionController.hasCollidedFrame() || collisionController.hasSelfCollided()) {
            switchState(GameState.MENU);
            return GameState.MENU;
        }
        GameState tmpState = playerController.updateController();
        if (tmpState != currentState) {
            switchState(tmpState);
            return tmpState;
        }
        if (collisionController.hasAppleCollided()) {
            game.getApple().setxCoord(GameUtil.getRandomInt(GameConfig.BOARD_WIDTH, GameConfig.PART_SIZE));
            game.getApple().setyCoord(GameUtil.getRandomInt(GameConfig.BOARD_HEIGHT, GameConfig.PART_SIZE));

            game.getSnake().addPart(Factory.buildSnakeBody(game.getSnake(), game.getSnake().getHead().getCurrentState()));

            game.setScore(game.getCurrentScore() + 10);
        }
        return GameState.RUNNING;
    }

    //State transitions
    private void switchState(GameState state) {
        if (currentState == GameState.RUNNING && state == GameState.PAUSED) {
            gameFrame.removeKeyListener(playerController);
            gameFrame.addKeyListener(menuController);
            menuController.setGameState(GameState.PAUSED);
            playerController.setGameState(GameState.RUNNING);

            gameFrame.getGameCanvas().setGamePaused();

        } else if (currentState == GameState.PAUSED && state == GameState.RUNNING) {
            gameFrame.removeKeyListener(menuController);
            gameFrame.addKeyListener(playerController);
            menuController.setGameState(GameState.PAUSED);

            gameFrame.getGameCanvas().clearGamePaused();

        } else if (currentState == GameState.MENU && state == GameState.RUNNING) {
            gameFrame.removeKeyListener(menuController);
            gameFrame.addKeyListener(playerController);
            gameFrame.requestFocus();
            playerController.setGameState(GameState.RUNNING);
            menuController.setGameState(GameState.RUNNING);

            game.setHighscore(FileController.readHighscore());
            SnakeController.resetSnake(game.getSnake());

        } else if (currentState == GameState.RUNNING && state == GameState.MENU) {
            gameFrame.canvasToMenu();
            gameFrame.removeKeyListener(playerController);
            gameFrame.addKeyListener(menuController);
            gameFrame.requestFocus();

            menuController.setGameState(GameState.MENU);
            playerController.setGameState(GameState.MENU);


            game.getHighscore().addHighscore(game.getUserName(), game.getCurrentScore());
            game.setScore(0);
            FileController.writeHighscoreOnFile(game.getHighscore());
        }
    }
}
