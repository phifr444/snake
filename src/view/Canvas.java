package view;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Canvas draws and paints all components of the game and it's board, the components are
 * HighscoreBoard, Board, Snake, Apple, ScoreLabel, CurrentHighscore, HighscoreLabe, Highscore and a PausedLabel
 */
public class Canvas extends JPanel {
    private final Game game;

    private static final int OFF_SET = 10;

    private boolean gamePaused;

    public Canvas(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(game.getBoard().getWidth() + game.getHighscorePart().getWidth(), game.getBoard().getHeight()));
        gamePaused = false;
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
        paintHighscoreBoard(g);
        paintBoard(g);
        paintSnake(g);
        paintApple(g);
        paintScoreLabel(g);
        paintCurrentHighscore(g);
        paintHighscoreLabel(g);
        paintHighscore(g);
        if (gamePaused) {
            paintPausedLabel(g);
        }
    }

    void paintSnake(Graphics g) {
        Iterable<SpritePart> snakeParts = game.getSnake().getParts();

        for (SpritePart spritePart : snakeParts) {
            g.drawImage(spritePart.getImage(), spritePart.getxCoord(), spritePart.getyCoord(), spritePart.getWidth(), spritePart.getHeight(), null);
        }
    }

    void paintApple(Graphics g) {
        SpritePart apple = game.getApple();
        g.drawImage(apple.getImage(), apple.getxCoord(), apple.getyCoord(), apple.getWidth(), apple.getHeight(), null);
    }

    void paintBoard(Graphics g) {
        g.setColor(game.getBoard().getColor());
        g.fillRect(game.getBoard().getxCoord(), game.getBoard().getyCoord(),
                game.getBoard().getWidth(), game.getBoard().getHeight());
    }

    void paintHighscoreBoard(Graphics g) {
        Part highscorePart = game.getHighscorePart();
        g.setColor(highscorePart.getColor());
        g.fillRect(game.getHighscorePart().getxCoord(), game.getHighscorePart().getyCoord(),
                game.getHighscorePart().getWidth(), game.getHighscorePart().getHeight());
    }

    //Paint a paused text in the middle of the game board
    void paintPausedLabel(Graphics g) {
        g.setColor(Color.RED);
        g.drawString(GameConfig.PAUSED_LABEL, (game.getBoard().getWidth() / 2 - GameConfig.PAUSED_LABEL.length()), game.getBoard().getHeight() / 3);
    }

    //Paint the score text in the highscore board
    void paintScoreLabel(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString(GameConfig.CURRENT_SCORE_LABEL, game.getBoard().getWidth() + game.getHighscorePart().getWidth() - game.getHighscorePart().getWidth() / 2 - GameConfig.CURRENT_SCORE_LABEL.length() * 2,
                game.getBoard().getyCoord() + OFF_SET);
    }

    //Paint the highscore text in the highscore board
    void paintHighscoreLabel(Graphics g) {
        g.drawString(GameConfig.HIGHSCORE_LABEL,
                ((game.getBoard().getWidth() + game.getHighscorePart().getWidth()) - game.getHighscorePart().getWidth() / 2) - GameConfig.HIGHSCORE_LABEL.length() * 2,
                game.getBoard().getyCoord() + OFF_SET *4);
    }

    //Paint the current name and score "Name:Score" format
    void paintCurrentHighscore(Graphics g) {
        String highscoreString;
        highscoreString = game.getUserName() + ":" + game.getCurrentScore();
        g.drawString(highscoreString,
		     (game.getBoard().getWidth() + game.getHighscorePart().getWidth() - game.getHighscorePart().getWidth() / 2) - highscoreString.length() * 2,
		     game.getBoard().getyCoord() + OFF_SET *2);
    }

    //Paint the top ten highscores or all highscores if there are less than 10
    void paintHighscore(Graphics g) {
        HighscorePair pair;
        String highscore;
        if (game.getHighscore().getHighscoreList().size() < 10) {
            for (int i = 0; i < game.getHighscore().getHighscoreList().size(); i++) {
                pair = game.getHighscore().getHighscoreList().get(i);
                highscore = pair.getName() + ":" + pair.getScore();
                g.drawString(highscore, (game.getBoard().getWidth() + game.getHighscorePart().getWidth() - game.getHighscorePart().getWidth() / 2) - highscore.length() * 2,
                        game.getBoard().getyCoord() + OFF_SET *5 + (i * OFF_SET));
            }
        } else {
            for (int i = 0; i < 10; i++) {
                pair = game.getHighscore().getHighscoreList().get(i);
                highscore = pair.getName() + ":" + pair.getScore();
                g.drawString(highscore, (game.getBoard().getWidth() + game.getHighscorePart().getWidth() - game.getHighscorePart().getWidth() / 2) - highscore.length() * 2,
                        game.getBoard().getyCoord() + OFF_SET *5 + (i * OFF_SET));
            }
        }
    }


    public void setGamePaused() {
        this.gamePaused = true;
    }

    public void clearGamePaused() {
        this.gamePaused = false;
    }
}
