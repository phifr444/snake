package model;


/**
 * SnakeDirection contains enums which are the directions the snake can move
 */
public enum SnakeDirection
{
    /**
     * Direction left
     */
    LEFT,

    /**
     * Direction right
     */
    RIGHT,

    /**
     * Direction up
     */
    //Up is the most logical name
    @SuppressWarnings("EnumeratedConstantNamingConvention")UP,

    /**
     * Direction down
     */
    DOWN
}
