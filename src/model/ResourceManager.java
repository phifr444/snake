package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * ResourceManager holds maps for all resources, consisting of sprites which we map to the snake's parts as well as the apple.
 * These are loaded into the database and finally loaded into their respective maps.
 * All images are included in the Images-folder.
 */
public class ResourceManager
{

    private static final String IMG_PATH = "Images";

    private static final String FILE_SEPARATOR = File.separator;


    private static final class ResourcesHolder
    {
	private static final ResourceManager INSTANCE = new ResourceManager();
    }

    public static ResourceManager getInstance() {
	return ResourcesHolder.INSTANCE;
    }

    private ResourceManager() {
    }

    public static void loadResources(){
	ResourceManager instance = getInstance();
	instance.loadImageMaps();
    }

    private static Map<ResourceType, Map<SnakeDirection, Image>> mapping = new EnumMap<ResourceType,Map<SnakeDirection, Image>>(ResourceType.class);
    public static Map<SnakeDirection, Image> getMapping(ResourceType type){
	return mapping.get(type);
    }

   public static void putMapping(ResourceType type, SnakeDirection dir, Image img){
       getMapping(type).put(dir,img);
   }

    private void loadImageMaps() {
	mapping.put(ResourceType.HEAD,new EnumMap<SnakeDirection, Image>(SnakeDirection.class));
	mapping.put(ResourceType.BODY,new EnumMap<SnakeDirection, Image>(SnakeDirection.class));
	mapping.put(ResourceType.TAIL,new EnumMap<SnakeDirection, Image>(SnakeDirection.class));
	mapping.put(ResourceType.APPLE,new EnumMap<SnakeDirection, Image>(SnakeDirection.class));

        putMapping(ResourceType.HEAD, SnakeDirection.UP, getImage("SnakeHeadUp.png"));
	putMapping(ResourceType.HEAD, SnakeDirection.DOWN, getImage("SnakeHeadDown.png"));
	putMapping(ResourceType.HEAD, SnakeDirection.LEFT, getImage("SnakeHeadLeft.png"));
	putMapping(ResourceType.HEAD, SnakeDirection.RIGHT, getImage("SnakeHeadRight.png"));

	putMapping(ResourceType.BODY, SnakeDirection.UP, getImage("SnakeBodyVertical.png"));
	putMapping(ResourceType.BODY, SnakeDirection.DOWN, getImage("SnakeBodyVertical.png"));
	putMapping(ResourceType.BODY, SnakeDirection.LEFT, getImage("SnakeBodyHorizontal.png"));
	putMapping(ResourceType.BODY, SnakeDirection.RIGHT, getImage("SnakeBodyHorizontal.png"));

	putMapping(ResourceType.TAIL, SnakeDirection.UP, getImage("SnakeTailUp.png"));
	putMapping(ResourceType.TAIL, SnakeDirection.DOWN, getImage("SnakeTailDown.png"));
	putMapping(ResourceType.TAIL, SnakeDirection.LEFT, getImage("SnakeTailLeft.png"));
	putMapping(ResourceType.TAIL, SnakeDirection.RIGHT, getImage("SnakeTailRight.png"));

	putMapping(ResourceType.APPLE, SnakeDirection.UP, getImage("Apple.png"));
    }

    private static Image getImage(String id){

	try {
	    return ImageIO.read(new File(IMG_PATH + FILE_SEPARATOR + id));
	} catch (IOException e) {
	    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	}

	return null;
    }
    public static Image getDefaultImage(){

	try {
	    return ImageIO.read(new File(IMG_PATH + FILE_SEPARATOR + "default.png"));
	} catch (IOException e) {
	    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
	}

	return null;
    }


}
