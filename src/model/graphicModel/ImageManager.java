package model.graphicModel;

import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.awt.geom.*;

public class ImageManager {
	
	private static final String TILES = "tiles/";
    private static final String BACKGROUND = "background/";
	private static final String CHARACTERS = "characters/";
	private static final String WEAPONS = "weapons/";
	private static final String RESOURCE_PATH = "/resources/";

	private static HashMap<String,BufferedImage> cache = new HashMap<String,BufferedImage>();

	private static void load(String name) throws Exception {

		BufferedImage b = ImageIO.read(ImageManager.class.getClass().getResource(name));
		cache.put(name, b);

	}

	public static BufferedImage getImage(String name) {

		if (!cache.containsKey(name)) {
			try {
				load(name);
			} catch (Exception e) {
				System.out.println("Failed to load image resource "+name);
				return new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
			}
		}

		return cache.get(name);

	}

	public static BufferedImage getBackGroundImage(String filename) {
        return getImage(RESOURCE_PATH+BACKGROUND+filename);
    }
	
	public static BufferedImage getRandomTiles() {
		Random rand = new Random();
		String filename = ".png";

		int  n = rand.nextInt(38) + 1;
		if(n <= 30) filename = 1 + filename;
		if(n > 30) filename = n % 30 + filename;
		return getImage(RESOURCE_PATH+TILES+filename);
	}

	public static BufferedImage getCharSkin(String name) {
		String filename = name + ".png";

		return getImage(RESOURCE_PATH+CHARACTERS+filename);
	}

	public static BufferedImage getAttackEffect(String name) {
		String filename = name + ".png";

		return getImage(RESOURCE_PATH+WEAPONS+filename);
	}

    public static BufferedImage resizeImage(BufferedImage before, double scale) {
        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        return after;
    }

}