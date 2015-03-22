package model;

/**
 * Game is the class where all the contents of the game is constructed. The contents are the snake, the apple, the board,
 * the highscore and it's board, and the current state of the game.
 */
public class Game {

    private final Snake snake;
    private final SpritePart apple;
    private final Part board;
    private final Part highscorePart;
    private final String gameName;
    private final GameState gameState;


    //Highscore
    private int score = 0;
    private String userName = null;
    private Highscore highscore;


    public Game(Snake snake, SpritePart apple, Part board, Part highscorePart, GameState gameState) {
        highscore = new Highscore();
        this.gameName = GameConfig.GAME_NAME;
        this.snake = snake;
        this.highscorePart = highscorePart;
        this.apple = apple;
        this.board = board;
        this.gameState = gameState;
    }

    public String getGameName() {
        return gameName;
    }

    public int getCurrentScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighscore(Highscore highscore) {
        this.highscore = highscore;
    }

    public Highscore getHighscore() {
        return highscore;
    }

    public Snake getSnake() {
        return snake;
    }

    public SpritePart getApple() {
        return apple;
    }

    public Part getBoard() {
        return board;
    }

    public Part getHighscorePart() {
        return highscorePart;
    }

    public GameState getGameState() {
        return gameState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
