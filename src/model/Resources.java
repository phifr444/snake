package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Resources holds maps for all resources, consisting of sprites which we map to the snake's parts as well as the apple.
 * These are loaded into the database and finally loaded into their respective maps.
 * All images are included in the Images-folder.
 */
public class Resources {

    private static final String IMG_PATH = "Images";

    private static final String FILE_SEPARATOR = File.separator;


    private static final class ResourcesHolder
    {
	private static final Resources INSTANCE = new Resources();
    }

    public static Resources getInstance() {
	return ResourcesHolder.INSTANCE;
    }

    private Resources() {
    }

    public static void loadResources(){
	Resources instance = getInstance();
	instance.initImageDB();
	instance.loadImageMaps();
    }
    private static Map<String, Image> imageDB = new HashMap<String, Image>();

    private static Map<SnakeState, Image> snakeHeadMapping = new EnumMap<SnakeState, Image>(SnakeState.class);
    private static Map<SnakeState, Image> snakeBodyMapping = new EnumMap<SnakeState, Image>(SnakeState.class);
    private static Map<SnakeState, Image> snakeTailMapping = new EnumMap<SnakeState, Image>(SnakeState.class);
    private static Map<SnakeState, Image> appleMapping = new EnumMap<SnakeState, Image>(SnakeState.class);

    public static Map<SnakeState, Image> getSnakeHeadMapping(){
	return snakeHeadMapping;
    }

    public static void putSnakeHeadMapping(SnakeState snakeState, Image image){
    	snakeHeadMapping.put(snakeState, image);
    }

    public static Map<SnakeState, Image> getSnakeBodyMapping(){
	return snakeBodyMapping;
    }

    public static void putSnakeBodyMapping(SnakeState snakeState, Image image){
	snakeBodyMapping.put(snakeState, image);
    }

    public static Map<SnakeState, Image> getSnakeTailMapping(){
	return snakeTailMapping;
    }

    public static void putSnakeTailMapping(SnakeState snakeState, Image image){
	snakeTailMapping.put(snakeState, image);
    }

    public static Map<SnakeState, Image> getAppleMapping(){
	return appleMapping;
    }

    public static void putAppleMapping(SnakeState snakeState, Image image){
	appleMapping.put(snakeState, image);
    }

    //Below are paths for images
    private static final String DEFAULT_IMAGE = IMG_PATH + FILE_SEPARATOR + GameConfig.DEFAULT_SPRITE_NAME;

    //Snake head images
    private static final String SNAKE_HEAD_UP = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_HEAD_UP_SPRITE_NAME;
    private static final String SNAKE_HEAD_DOWN = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_HEAD_DOWN_SPRITE_NAME;
    private static final String SNAKE_HEAD_LEFT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_HEAD_LEFT_SPRITE_NAME;
    private static final String SNAKE_HEAD_RIGHT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_HEAD_RIGHT_SPRITE_NAME;

    //Snake body images
    private static final String SNAKE_BODY_UP = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_BODY_UP_SPRITE_NAME;
    private static final String SNAKE_BODY_DOWN = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_BODY_DOWN_SPRITE_NAME;
    private static final String SNAKE_BODY_LEFT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_BODY_LEFT_SPRITE_NAME;
    private static final String SNAKE_BODY_RIGHT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_BODY_RIGHT_SPRITE_NAME;

    //Apple images
    private static final String APPLE_IMG = IMG_PATH + FILE_SEPARATOR + GameConfig.APPLE_SPRITE_NAME;

    //Tail images
    private static final String SNAKE_TAIL_UP = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_TAIL_UP_SPRITE_NAME;
    private static final String SNAKE_TAIL_DOWN = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_TAIL_DOWN_SPRITE_NAME;
    private static final String SNAKE_TAIL_LEFT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_TAIL_LEFT_SPRITE_NAME;
    private static final String SNAKE_TAIL_RIGHT = IMG_PATH + FILE_SEPARATOR + GameConfig.SNAKE_TAIL_RIGHT_SPRITE_NAME;



    private void initImageDB() {
        //Add all the images
	try {
	    BufferedImage img01 = ImageIO.read(new File(SNAKE_HEAD_UP));
	    imageDB.put(SNAKE_HEAD_UP, img01);

	    BufferedImage img02 = ImageIO.read(new File(SNAKE_HEAD_DOWN));
            imageDB.put(SNAKE_HEAD_DOWN, img02);

	    BufferedImage img03 = ImageIO.read(new File(SNAKE_HEAD_LEFT));
            imageDB.put(SNAKE_HEAD_LEFT, img03);

	    BufferedImage img04 = ImageIO.read(new File(SNAKE_HEAD_RIGHT));
            imageDB.put(SNAKE_HEAD_RIGHT, img04);

	    BufferedImage img05 = ImageIO.read(new File(SNAKE_BODY_UP));
            imageDB.put(SNAKE_BODY_UP, img05);

	    BufferedImage img06 = ImageIO.read(new File(SNAKE_BODY_DOWN));
            imageDB.put(SNAKE_BODY_DOWN, img06);

	    BufferedImage img07 = ImageIO.read(new File(SNAKE_BODY_LEFT));
            imageDB.put(SNAKE_BODY_LEFT, img07);

	    BufferedImage img08 = ImageIO.read(new File(SNAKE_BODY_RIGHT));
            imageDB.put(SNAKE_BODY_RIGHT, img08);

	    BufferedImage img09 = ImageIO.read(new File(APPLE_IMG));
            imageDB.put(APPLE_IMG, img09);

	    BufferedImage img10 = ImageIO.read(new File(SNAKE_TAIL_UP));
            imageDB.put(SNAKE_TAIL_UP, img10);

	    BufferedImage img11 = ImageIO.read(new File(SNAKE_TAIL_DOWN));
            imageDB.put(SNAKE_TAIL_DOWN, img11);

	    BufferedImage img12 = ImageIO.read(new File(SNAKE_TAIL_LEFT));
            imageDB.put(SNAKE_TAIL_LEFT, img12);

	    BufferedImage img13 = ImageIO.read(new File(SNAKE_TAIL_RIGHT));
            imageDB.put(SNAKE_TAIL_RIGHT, img13);

	    BufferedImage img14 = ImageIO.read(new File(DEFAULT_IMAGE));
            imageDB.put(DEFAULT_IMAGE, img14);
        }
        //If the images are not loaded, exit game
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void loadImageMaps() {
        putSnakeHeadMapping(SnakeState.UP, getImage(SNAKE_HEAD_UP));
	putSnakeHeadMapping(SnakeState.DOWN, getImage(SNAKE_HEAD_DOWN));
	putSnakeHeadMapping(SnakeState.LEFT, getImage(SNAKE_HEAD_LEFT));
	putSnakeHeadMapping(SnakeState.RIGHT, getImage(SNAKE_HEAD_RIGHT));

        putSnakeBodyMapping(SnakeState.UP, getImage(SNAKE_BODY_UP));
	putSnakeBodyMapping(SnakeState.DOWN, getImage(SNAKE_BODY_DOWN));
	putSnakeBodyMapping(SnakeState.LEFT, getImage(SNAKE_BODY_LEFT));
	putSnakeBodyMapping(SnakeState.RIGHT, getImage(SNAKE_BODY_RIGHT));

        putSnakeTailMapping(SnakeState.UP, getImage(SNAKE_TAIL_UP));
	putSnakeTailMapping(SnakeState.DOWN, getImage(SNAKE_TAIL_DOWN));
	putSnakeTailMapping(SnakeState.LEFT, getImage(SNAKE_TAIL_LEFT));
	putSnakeTailMapping(SnakeState.RIGHT, getImage(SNAKE_TAIL_RIGHT));

        putAppleMapping(SnakeState.UP, getImage(APPLE_IMG));
    }

    private static Image getImage(String id) {
        if (imageDB.containsKey(id)) {
            return imageDB.get(id);
        } else {
            return imageDB.get(DEFAULT_IMAGE);
        }
    }

    public static Image getDefaultImage() {
        return imageDB.get(DEFAULT_IMAGE);
    }
}
