package model;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;


/**
 * SpritePart is a part without a color, it has a Map for sprites instead
 * SpritePart is used to map different sprites to a part depending on that parts SnakeState
 */
public class SpritePart extends Part {
    private Map<SnakeState, Image> spriteMap
	    = new EnumMap<SnakeState, Image>(SnakeState.class);

    private SnakeState currentState = null;


    public SpritePart(int x, int y, int width, int height) {
        super(x, y, width, height);
        spriteMap = new EnumMap<SnakeState, Image>(SnakeState.class);
    }

    public Image getImage() {
        if (currentState != null && spriteMap.containsKey(currentState))
            return spriteMap.get(currentState);
        else
            return Resources.getDefaultImage();
    }

    public void setSpriteMap(Map<SnakeState, Image> spriteMap) {
        this.spriteMap = spriteMap;

    }

    public SnakeState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SnakeState currentState) {
        this.currentState = currentState;
    }
}
