package model;

import java.util.ArrayList;
import java.util.List;

/**
 * HighscoreList is a list of HighscorePairs and is accessed when a new score is added and when the top-10 is displayed.
 */
public class HighscoreList
{
    //Format Name:Score
    private final List<HighscorePair> highscoreList;

    public HighscoreList() {
        highscoreList = new ArrayList<HighscorePair>();
    }

    public List<HighscorePair> getHighscoreList() {
        return highscoreList;
    }

    public void addHighscore(String name, int score) {
        //Find the right position
        for (int i = 0; i < highscoreList.size(); i++) {
            if (highscoreList.get(i).getScore() < score) {
                highscoreList.add(i, new HighscorePair(name, score));
                return;
            }
        }
        highscoreList.add(new HighscorePair(name, score));
    }
}
