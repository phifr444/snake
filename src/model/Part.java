package model;

import java.awt.Color;

/**
 * Part is the definition of all parts in the game. They consist of X and Y coordinates, as well as a width and height and
 * color. Sprites however do not use a specifyable color and as such dont have one either.
 * Parts are changed via getters and setters.
 */
public class Part {
    private int xCoord, yCoord, width, height;
    private Color color = null;

    public Part(int xCoord, int yCoord, int width, int height, Color color) {
	this.xCoord = xCoord;
	this.yCoord = yCoord;
	this.width = width;
	this.height = height;
	this.color = color;
    }

    public Part(int xCoord, int yCoord, int width, int height) {
	this.xCoord = xCoord;
	this.yCoord = yCoord;
	this.width = width;
	this.height = height;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return color;
    }
}
