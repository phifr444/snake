package model;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;


/**
 * SpritePart is a part without a color, it has a Map for sprites instead
 * SpritePart is used to map different sprites to a part depending on that parts SnakeDirection
 */
public class SpritePart extends Part {
    private Map<SnakeDirection, Image> spriteMap
	    = new EnumMap<SnakeDirection, Image>(SnakeDirection.class);

    private SnakeDirection currentState = null;


    public SpritePart(int x, int y, int width, int height) {
        super(x, y, width, height);
        spriteMap = new EnumMap<SnakeDirection, Image>(SnakeDirection.class);
    }

    public Image getImage() {
        if (currentState != null && spriteMap.containsKey(currentState))
            return spriteMap.get(currentState);
        else
            return ResourceManager.getDefaultImage();
    }

    public void setSpriteMap(Map<SnakeDirection, Image> spriteMap) {
        this.spriteMap = spriteMap;

    }

    public SnakeDirection getCurrentState() {
        return currentState;
    }

    public void setCurrentState(SnakeDirection currentState) {
        this.currentState = currentState;
    }
}
