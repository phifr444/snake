package controller;

import model.GameState;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * MenuController is a KeyListener used when the game is in MENU-state or in PAUSED-state.
 * It is only used to disable the keys used when running.
 */
public class MenuController extends KeyAdapter
{
    private GameState gameState;


    //This KeyListener is only used when in the menu or when GameState is or paused
    public MenuController() {
        gameState = GameState.MENU;
    }

    public GameState updateController() {
        return gameState;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Changes the gameState to running
	super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameState != GameState.MENU) {
            gameState = GameState.RUNNING;
        }
    }

    public void setGameState(GameState state) {
        this.gameState = state;
    }

}
