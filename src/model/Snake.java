package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake is the representation of the snake. It uses a List containing SpriteParts to store all parts. Each individual part
 * knows it's direction and which sprite to display.
 * The snake uses a lockable direction in order to only use one movement command issued.
 */
public class Snake {

    private final List<SpritePart> parts;
    private int movementSpeed;

    private SnakeDirection direction = null;
    private boolean dirLock;

    public Snake(SpritePart head) {
        parts = new ArrayList<SpritePart>();
        parts.add(head);
        dirLock = false;
    }

    public List<SpritePart> getParts() {
        return parts;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed * GameConfig.PART_SIZE;
    }

    public void addPart(SpritePart spritePart) {
        if (!parts.isEmpty()) {
            spritePart.setSpriteMap(ResourceManager.getMapping(ResourceType.TAIL));
            if (parts.size() > 1) {
                parts.get(parts.size() - 1).setSpriteMap(ResourceManager.getMapping(ResourceType.BODY));
            }
        }
        parts.add(spritePart);
    }

    public SpritePart getHead() {
        return parts.get(0);
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        if (isDirLock()) {
            this.direction = direction;
            getHead().setCurrentState(this.direction);
            dirLock = true;
        }
    }

    boolean isDirLock() {
        return !dirLock;
    }

    public void openStateLock() {
        dirLock = false;
    }
}
