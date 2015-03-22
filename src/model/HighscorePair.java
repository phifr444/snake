package model;

/**
 * HighscorePair is a pair consisting of a name and a score, stored as a String and an Integer respectively.
 */
public class HighscorePair {
    private final String name;
    private final int score;

    public HighscorePair(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }


}
