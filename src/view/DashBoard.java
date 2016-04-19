package view;

import model.graphicModel.GameScreen;
import resources.Consts;

import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class DashBoard {
    private static int X = 0;
    private static int Y = 20;
    private static int fi = 250, limit = 5000;
    private static long ft = System.currentTimeMillis();
    private static String[] characterInfo;
    public static void render(GameScreen screen) {
        //Font.draw_blockfont(screen, "test", 20, 20);
        if (characterInfo != null) {
            Font.draw_font8x8(screen, "NAME   " + characterInfo[0], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + Y);
            Font.draw_font8x8(screen, "PRO    " + characterInfo[1], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 2 * Y);
            Font.draw_font8x8(screen, "HP MAX " + characterInfo[2], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 3 * Y);
            Font.draw_font8x8(screen, "HP     " + characterInfo[3], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 4 * Y);
            Font.draw_font8x8(screen, "STR    " + characterInfo[4], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 5 * Y);
            Font.draw_font8x8(screen, "DEX    " + characterInfo[5], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 6 * Y);
            Font.draw_font8x8(screen, "DEF    " + characterInfo[6], Consts.DASHBOARD_X_OFFSET + 10, Consts.DASHBOARD_Y_OFFSET + 7 * Y);
        }
    }
    public static void tick() {
        /*long curTime = System.currentTimeMillis();

        if(curTime - ft > fi && curTime - ft < limit) {
            Y ++;
            ft += fi;
            System.out.println("ft: " + (curTime - ft));
        }*/
    }

    public static void parseCharInfo(String characterInfoStr) {
        characterInfo = characterInfoStr.split(";");
    }
}
