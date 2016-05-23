package view;

import model.graphicModel.GameScreen;
import resources.Consts;

import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class DashBoard {
    private static int X = 0;
    private static int Y = 12;
    private static int fi = 250, limit = 5000;
    private static long ft = System.currentTimeMillis();
    private static String[] characterInfo;
    public static void render(GameScreen screen) {
        //Font.draw_blockfont(screen, "test", 20, 20);
        if (characterInfo != null) {
            Font.draw_krenfont(screen,  characterInfo[0], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET);
            Font.draw_krenfont(screen, characterInfo[1], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + Y);
            Font.draw_krenfont(screen, "HP " + characterInfo[3] + "/" + characterInfo[2], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + 2 * Y);
            Font.draw_krenfont(screen, "STR " + characterInfo[4], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + 3 * Y);
            Font.draw_krenfont(screen, "DEX " + characterInfo[5], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + 4 * Y);
            Font.draw_krenfont(screen, "DEF " + characterInfo[6], Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + 5 * Y);
        }
        Font.draw_krenfont(screen, "--------------", Consts.DASHBOARD_X_OFFSET, Consts.DASHBOARD_Y_OFFSET + 6 * Y);
    }

    public static void parseCharInfo(String characterInfoStr) {
        characterInfo = characterInfoStr.split(";");
    }
}
