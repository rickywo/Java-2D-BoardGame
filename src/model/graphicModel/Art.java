package model.graphicModel;


import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Art {

    //public static final Bitmap[][] sprites = cut();
    public static final Bitmap background = getBackGround();
    private static Bitmap[][] cut(String string, int w, int h) {
        return cut(string, w, h, 0, 0);
    }

    private static Bitmap[][] cut(String string, int w, int h, int sx, int sy) {
        BufferedImage image = ImageManager.getImage(string);
        int xTiles = (image.getWidth() - sx) / w;
        int yTiles = (image.getHeight() - sy) / h;

        Bitmap[][] result = new Bitmap[xTiles][yTiles];
        for(int x = 0; x < xTiles; x ++) {
            for(int y = 0; y < yTiles; y ++) {
                result[x][y] = new Bitmap(w, h);
                image.getRGB(sx + x * w, sy + y * h, w, h, result[x][y].pixels, 0, w);

            }
        }
        return result;
    }

    private static Bitmap getBackGround() {
        BufferedImage image = ImageManager.getBackGroundImage("background.png");
        int w = image.getWidth();
        int h = image.getHeight();
        Bitmap result = new Bitmap(w, h);
        image.getRGB(0, 0, w, h, result.pixels, 0, w);

        return result;
    }
}
