package view;

import model.Game;
import model.GameState;

import javax.swing.*;


/**
 * GameFrame contains a canvas and a menu. The canvas is displayed when the game is not in menu mode, in which case
 * the menu is instead displayed.
 * The canvas is where we paint the board and all graphics for the game. The menu contains buttons for navigation.
 */
public class GameFrame extends JFrame {
    private final Canvas gameCanvas;
    private final Menu menu;
    private String userName = null;


    public GameFrame(Game game) {
        // Must be a WindowsConstant
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        menu = new Menu();
        gameCanvas = new Canvas(game);

        //Add menu
        if (game.getGameState() == GameState.MENU) {
            this.add(menu);
            this.pack();
        }
    }


    public Canvas getGameCanvas() {
        return gameCanvas;
    }

    public GameState updateFrame() {
        //Remove menu frame and add game frame
        if (menu.updateButtons() == GameState.RUNNING) {
            userName = menu.getUser();
            this.remove(menu);
            this.add(gameCanvas);
            this.pack();
            return GameState.RUNNING;
        }
        if (menu.updateButtons() == GameState.MENU) {
            return GameState.MENU;
        }

        return menu.updateButtons();
    }

    public void canvasToMenu() {
        this.remove(gameCanvas);
        this.add(menu);
        this.pack();
    }

    public String getUserName() {
        return userName;
    }

}
