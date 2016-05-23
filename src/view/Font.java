package view;

import model.graphicModel.Art;
import model.graphicModel.GameScreen;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Font {

    /** The Constant BLOCKFONT_LETTERS. */
    public static final String BLOCKFONT_LETTERS =
                            " !\"#$%&\'()*+,-./0123456789;:<=>?" +
                            "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                            "`abcdefghijklmnopqrstuvwxyz{|}~";
    
    /** The Constant FONT8X8_LETTERS. */
    public static final String FONT8X8_LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-*!@";

    /** The Constant FONT_WIDTH. */
    public static final int FONT_WIDTH = 18;
    
    /**
     * Instantiates a new font.
     */
    private Font() {}
    
    /**
     * Gets the string width.
     *
     * @param string the string
     * @return the string width
     */
    public static int getStringWidth(String string) {
        return string.length() * FONT_WIDTH;

    }

    /**
     * Draw_blockfont.
     *
     * @param screen the screen to be rendered
     * @param string the message string
     * @param x the x
     * @param y the y
     */
    public static void draw_blockfont(GameScreen screen, String string, int x, int y) {
        final int CHARY_OFFSET = 32;
        string = string.toUpperCase();
        int length = string.length();

        for(int i = 0; i < length; i ++) {
            int c = BLOCKFONT_LETTERS.indexOf(string.charAt(i));
            if(c < 0) continue;
            screen.render(Art.BLOCKFONT[c % 32][c / 32], x, y);
            x += 18;
            //y += 32;
        }
    }

    /**
     * Draw_krenfont.
     *
     * @param screen the screen to be rendered
     * @param string the message string
     * @param x the x
     * @param y the y
     */
    public static void draw_krenfont(GameScreen screen, String string, int x, int y) {
        string = string.toUpperCase();
        int length = string.length();

        for(int i = 0; i < length; i ++) {
            int c = BLOCKFONT_LETTERS.indexOf(string.charAt(i));
            if(c < 0) continue;
            screen.render(Art.KRENFONT[c % 16][(c / 16) + 2], x, y);
            x += 10;
            //y += 32;
        }
    }

    /**
     * Draw_font8x8.
     *
     * @param screen the screen
     * @param string the string
     * @param x the x
     * @param y the y
     */
    public static void draw_font8x8(GameScreen screen, String string, int x, int y) {
        string = string.toUpperCase();
        int length = string.length();

        for(int i = 0; i < length; i ++) {
            int c = FONT8X8_LETTERS.indexOf(string.charAt(i));
            if(c >= 0) screen.render(Art.FONT8X8[c % 8][c / 8], x, y);
            x += 8;
        }
    }

    /**
     * Draw_font16x16.
     *
     * @param screen the screen
     * @param string the string
     * @param x the x
     * @param y the y
     */
    public static void draw_font16x16(GameScreen screen, String string, int x, int y) {
        string = string.toUpperCase();
        int length = string.length();

        for(int i = 0; i < length; i ++) {
            int c = FONT8X8_LETTERS.indexOf(string.charAt(i));
            if(c < 0) continue;
            screen.render(Art.FONT16X16[c % 8][c / 8], x, y);
            x += 16;
        }
    }

}
