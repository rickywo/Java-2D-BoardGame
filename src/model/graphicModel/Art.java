package model.graphicModel;


import model.gameModel.ProfessionNames;
import resources.Consts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Art {

    public static final int COMMANDER = 0,
            SOLDIER = 1,
            MEDIC = 2,
            AREAATTACKER = 3,
            WARRIOR = 4,
            PROTECTOR = 5,
            COMBATENGINEER = 6,
            CHEERLEADER = 7,
            CHIEF = 8,
            SPAWN = 9,
            LADYLISA = 10,
            WITCH = 11,
            GOBLIN = 12,
            SNIPER = 13,
            TROLL = 14,
            DRAGON = 15;


    //public static final Bitmap[][] sprites = cut();
    public static final Bitmap[][] BLOCKFONT = cut("/resources/fonts/Block Font.png", 18, 32);
    public static final Bitmap[][] KRENFONT = cut("/resources/fonts/Kren_12x12.png", 12, 12);
    public static final Bitmap[][] FONT8X8 = cut("/resources/fonts/font8x8.png", 8, 8);
    public static final Bitmap[][] FONT16X16 = cut("/resources/fonts/font16x16.png", 16, 16);
    public static final Bitmap[] CHARACTERS = getCharImages();
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
        System.out.println("Filename: "+ string);
        System.out.println("xTiles: "+ xTiles + "yTiles: " + yTiles);
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

    private static Bitmap[] getCharImages() {
        int i = 0;
        Bitmap[] result = new Bitmap[16];
        for(ProfessionNames p: ProfessionNames.values()) {
            BufferedImage charImge = ImageManager.getCharSkin(p.getCharacterName());
            BufferedImage image = ImageManager.resizeImage(charImge, (double) Consts.RECTSIZE / (double) charImge.getWidth());

            int w = image.getWidth();
            int h = image.getHeight();
            result[i] = new Bitmap(w, h);
            image.getRGB(0, 0, w, h, result[i].pixels, 0, w);

            i ++;
        }
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
