package util;

import model.Part;

/**
 * Intersector is used to see if the X-axis and Y-axis of two parts lines are intersecting,
 * in which case we can assume the two parts are colliding.
 */
public final class Intersector {
    public static boolean isIntersectingPart(Part partA, Part partB) {
        return isLineIntersecting(partA.getxCoord(), partA.getxCoord() + partB.getWidth(), partB.getxCoord(), partB.getxCoord() + partB.getWidth())
                && isLineIntersecting(partA.getyCoord(), partA.getyCoord() + partA.getHeight(), partB.getyCoord(), partB.getyCoord() + partB.getHeight());
    }

    //Make sure there are no other instances
    private Intersector() {
    }

    /**
     * Checking if part a contains part b
     *
     * @param partA One of the parts
     * @param partB The other part
     * @return return true or false
     */
    public static boolean isContainingPart(Part partA, Part partB) {
        boolean containingXAxis = partA.getxCoord() <= partB.getxCoord() && (partA.getxCoord() + partA.getWidth()) >= (partB.getxCoord() + partB.getWidth());
        boolean containingYAxis = partA.getyCoord() <= partB.getyCoord() && (partA.getyCoord() + partA.getHeight()) >= (partB.getyCoord() + partB.getHeight());

        return containingXAxis && containingYAxis;
    }

    /**
     * @param aX  aX is the most left coordinate of a
     * @param aX2 aX2 is the most right coordinate of a
     * @param bX  bX is the most left coordinate of b
     * @param bX2 bX2 is the most right coordinate of b
     * @return return true or false
     */
    private static boolean isLineIntersecting(int aX, int aX2, int bX, int bX2) {
        if (aX < bX) {
            //aX is the most left coordinate of the two lines
            if (bX < aX2) {
                return true;
            }
        } else {
            //bX is the most left coordinate of the two lines
            if (aX < bX2) {
                return true;
            }
        }
        return false;
    }


}
