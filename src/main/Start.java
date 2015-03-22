package main;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *Start initalises the GameEngine which is a state-machine that runs the game
 * The GameEngine updates at a given frequency
 */
public final class Start
{
    private Start() {}

    private static final int UPDATE_FREQUENCY = 50;
    public static void main(String[] args) {
        final GameEngine gameEngine = new GameEngine();

        final Action doOneStep = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameEngine.updateGame();
            }
        };


        final Timer clockTimer = new Timer(UPDATE_FREQUENCY, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }
}
