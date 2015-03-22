package view;

import controller.FileController;
import model.GameConfig;
import model.GameState;
import model.Highscore;

import javax.swing.*;
import java.awt.*;

/**
 * Menu creates and updates a menu with three buttons, Play, Exit and Reset Highscore
 */
class Menu extends JPanel {

    private static JButton playButton = initButton("Play");
    private static JButton exitButton = initButton("Exit");
    private static JButton resetButton = initButton("Reset Highscore");

    private static JTextField userName = new JTextField();

    private String user = null;


    Menu() {
        initMenu();
    }


    private void initMenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.add(playButton);

        this.add(resetButton);

        this.add(exitButton);
    }

    //Init a JButton named after the input
    private static JButton initButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setMaximumSize(GameConfig.BUTTON_DIMENSION);
        button.setMinimumSize(GameConfig.BUTTON_DIMENSION);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    //Update buttons
    public GameState updateButtons() {
        //If the play button is pressed start a dialog asking for a username
        if (playButton.getModel().isPressed()) {
            final JComponent[] inputs = new JComponent[]{
                    new JLabel("Enter username"), userName
            };
            JOptionPane.showMessageDialog(null, inputs, GameConfig.GAME_NAME, JOptionPane.PLAIN_MESSAGE);
            user = userName.getText();
            if (!user.isEmpty()) {
                return GameState.RUNNING;
            } else {
                return GameState.MENU;
            }
        }
        //Adds an empty highscore to the text file to reset it
        else if (resetButton.getModel().isPressed()) {
            Highscore highscore = new Highscore();
            FileController.writeHighscoreOnFile(highscore);
            return GameState.MENU;
        }
        //System exit
        else if (exitButton.getModel().isPressed()) {
            System.exit(1);
        }
        return GameState.MENU;
    }


    public String getUser() {
        return user;
    }

}
