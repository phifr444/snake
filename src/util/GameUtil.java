package util;

/**
 * GameUtil is a utility-class. It only contains a function to return a random integer, used to place an apple onto the board.
 */
public final class GameUtil {

    private GameUtil() {
    }

    public static int getRandomInt(int span, int mod) {
        return (((int) (Math.random() * (span / mod))) * mod);
    }


}
