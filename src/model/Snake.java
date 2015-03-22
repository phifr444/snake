package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake is the representation of the snake. It uses a List containing SpriteParts to store all parts. Each individual part
 * knows it's direction and which sprite to display.
 * The snake uses a lockable state in order to only use one movement command issued.
 */
public class Snake {

    private final List<SpritePart> parts;
    private int movementSpeed;

    private SnakeState state = null;
    private boolean stateLock;

    public Snake(SpritePart head) {
        parts = new ArrayList<SpritePart>();
        parts.add(head);
        stateLock = false;
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
            spritePart.setSpriteMap(Resources.getSnakeTailMapping());
            if (parts.size() > 1) {
                parts.get(parts.size() - 1).setSpriteMap(Resources.getSnakeBodyMapping());
            }
        }
        parts.add(spritePart);
    }

    public SpritePart getHead() {
        return parts.get(0);
    }

    public SnakeState getState() {
        return state;
    }

    public void setState(SnakeState state) {
        if (isStateLock()) {
            this.state = state;
            getHead().setCurrentState(this.state);
            stateLock = true;
        }
    }

    boolean isStateLock() {
        return !stateLock;
    }

    public void openStateLock() {
        stateLock = false;
    }
}
