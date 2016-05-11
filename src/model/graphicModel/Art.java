package model.graphicModel;


import resources.Consts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Art {

    private static final int WIDTH_INDEX = 0, LENGTH_INDEX = 1;
    private static final int BLOCK_FONT_INDEX = 0;
    private static final int KREN_FONT_INDEX = 1;
    private static final int FONT_8X8_INDEX = 2;
    private static final int FONT_16X16_INDEX = 3;
    public static final int[][] FONT_SIZE = {{18, 32}, {12, 12}, {8, 8}, {16, 16}};
    private static final String RESOURCE_PATH = "/resources/fonts/";
    private static final String BLOCK_FONT_FILE = "Block Font.png";
    private static final String KREN_FONT_FILE = "Kren_12x12.png";
    private static final String FONT_8X8_FILE = "font8x8.png";
    private static final String FONT_16X16_FILE = "font16x16.png";
    public static final Bitmap[][] BLOCKFONT = cut(RESOURCE_PATH + BLOCK_FONT_FILE,
            FONT_SIZE[BLOCK_FONT_INDEX][WIDTH_INDEX],
            FONT_SIZE[BLOCK_FONT_INDEX][LENGTH_INDEX]);
    public static final Bitmap[][] KRENFONT = cut(RESOURCE_PATH + KREN_FONT_FILE,
            FONT_SIZE[KREN_FONT_INDEX][WIDTH_INDEX],
            FONT_SIZE[KREN_FONT_INDEX][LENGTH_INDEX]);
    public static final Bitmap[][] FONT8X8 = cut(RESOURCE_PATH + FONT_8X8_FILE,
            FONT_SIZE[FONT_8X8_INDEX][WIDTH_INDEX],
            FONT_SIZE[FONT_8X8_INDEX][LENGTH_INDEX]);
    public static final Bitmap[][] FONT16X16 = cut(RESOURCE_PATH + FONT_16X16_FILE,
            FONT_SIZE[FONT_16X16_INDEX][WIDTH_INDEX],
            FONT_SIZE[FONT_16X16_INDEX][LENGTH_INDEX]);
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
        renderTiles(result);
        return result;
    }

    private static void renderTiles(Bitmap bkg) {
        for (int i = 0; i < Consts.BSIZE; i++) {
            for (int j = 0; j < Consts.BSIZE; j++) {

                // If this cell has a entity in it
                // To draw the image of a piece
                BufferedImage tileImge = ImageManager.getRandomTiles();
                BufferedImage image = ImageManager.resizeImage(tileImge, (double) Consts.RECTSIZE / (double) tileImge.getWidth());
                int w = image.getWidth();
                int h = image.getHeight();
                Bitmap result = new Bitmap(w, h);
                // To make border line for tiles
                for(int k = 0; k < Consts.RECTSIZE; k ++) {
                    image.setRGB(0, k, Color.LIGHT_GRAY.getRGB());
                    image.setRGB(k, 0, Color.LIGHT_GRAY.getRGB());
                }

                image.getRGB(0, 0, w, h, result.pixels, 0, w);
                bkg.render(result, i * Consts.RECTSIZE + Consts.MAP_X_OFFSET, j * Consts.RECTSIZE + Consts.MAP_Y_OFFSET);

            }
        }
    }
}
