package view;

import model.graphicModel.Art;
import model.graphicModel.GameScreen;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Font {

    public static final String BLOCKFONT_LETTERS =
                            " !\"#$%&\'()*+,-./0123456789;:<=>?" +
                            "@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_" +
                            "`abcdefghijklmnopqrstuvwxyz{|}~";
    public static final String FONT8X8_LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-*!@";
    private Font() {}
    public static int getStringWidth(String string) {
        return string.length() * 18;

    }

    public static void draw_blockfont(GameScreen screen, String string, int x, int y) {
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

    public static void draw_font8x8(GameScreen screen, String string, int x, int y) {
        string = string.toUpperCase();
        int length = string.length();

        for(int i = 0; i < length; i ++) {
            int c = FONT8X8_LETTERS.indexOf(string.charAt(i));
            if(c >= 0) screen.render(Art.FONT8X8[c % 8][c / 8], x, y);
            x += 8;
        }
    }

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
