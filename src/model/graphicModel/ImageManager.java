package model.graphicModel;

import java.awt.Image;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.geom.*;

public class ImageManager {
	
	private static final String TILES = "tiles/";
    private static final String BACKGROUND = "background/";
    private static final String BGIMAGE = "background.png";
	private static final String CHARACTERS = "characters/";
	private static final String RESOURCE_PATH = "/resources/";
	
	//set to 1.0 for non-Retina image instances
	public static final float REAL_DENSITY = 2.0f;

	private static HashMap<String,BufferedImage> cache = new HashMap<String,BufferedImage>();
	private static HashMap<String,Image> instanceCache = new HashMap<String,Image>();
	private static Properties defaultScales = null;	

	private static void load(String name) throws Exception {

		BufferedImage b = ImageIO.read(ImageManager.class.getClass().getResource(name));
		cache.put(name, b);
		System.out.println("Loaded image resource "+name);

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

	public static ImageIcon getIcon(String name) {

		return new ImageIcon(ImageManager.getImage(name).getScaledInstance(64, 64, Image.SCALE_SMOOTH));

	}

	public static Image getImageInstance(String name, float xScale, float yScale, float rotation) {

		//check if an instance with given scale and rotation is already in the instanceCache
		String key = String.format("%s@xs=%.5f;ys=%.5f;rot=%.5f", name, xScale, yScale, rotation);
		if (instanceCache.containsKey(key))
			return instanceCache.get(key);

		BufferedImage raw = getImage(name);
		float defaultScale = getDefaultScale(name);
		Image tbr = null;

		//if the image is too small, don't try to do transforms on it
		if (raw.getWidth() <= 10 || raw.getHeight() <= 10 || xScale*raw.getWidth() < 5 || yScale*raw.getHeight() < 5)
			return raw;

		try {
			tbr = getImageInstance(raw, xScale*defaultScale, yScale*defaultScale, rotation);
		} catch (Exception e) {
			e.printStackTrace();
			return raw;
		}

		if (tbr == null)
			return raw;

		instanceCache.put(key, tbr);
		return tbr;

	}

	private static Image getImageInstance(BufferedImage raw, float xScale, float yScale, float rotation) {

		//draw the rotated image at full (raw file pixels) size
		BufferedImage step1 = new BufferedImage(raw.getWidth()*2, raw.getHeight()*2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D s1g = (Graphics2D)step1.getGraphics();
		AffineTransform s1tf = new AffineTransform();
		s1tf.concatenate(AffineTransform.getTranslateInstance(raw.getWidth()*0.5f, raw.getHeight()*0.5f));
		s1tf.concatenate(AffineTransform.getRotateInstance(rotation / 180f * Math.PI, raw.getWidth()*0.5f, raw.getHeight()*0.5f));
		s1g.drawImage(raw, s1tf, null);
		s1g.dispose();

		Image step2 = null;
		step2 = step1.getScaledInstance((int)Math.round(step1.getWidth()*xScale*REAL_DENSITY), (int)Math.round(step1.getHeight()*yScale*REAL_DENSITY), Image.SCALE_SMOOTH);



		return step2;

	}

	public static float getDefaultScale(String name) {

		if (defaultScales == null) {
			defaultScales = new Properties();
		}

		String val = defaultScales.getProperty(name);
		if (val == null)
			return 1.0f;

		try {
			return Float.parseFloat(val);
		} catch (Exception e) {
			return 1.0f;
		}

	}

}