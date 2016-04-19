package view;

import model.graphicModel.GameScreen;
import resources.Consts;

/**
 * Created by blahblah Team on 2016/4/19.
 */
public class Verbose {

    private static String verboseMessage = "";
    private static long verboseTime;

    public static void render(GameScreen screen) {
        int width = Font.getStringWidth(verboseMessage);
        if(System.currentTimeMillis() - verboseTime < 2500)
            Font.draw_blockfont(screen,
                    verboseMessage,
                    (Consts.SCR_WIDTH - Consts.MAP_X_OFFSET) / 2 + Consts.MAP_X_OFFSET - width / 2,
                    Consts.SCR_HEIGHT / 2);
    }
    public static void tick() {
        /*long curTime = System.currentTimeMillis();

        if(curTime - ft > fi && curTime - ft < limit) {
            Y ++;
            ft += fi;
            System.out.println("ft: " + (curTime - ft));
        }*/
    }

    public static void verbose(String message) {
        verboseMessage = message;
        verboseTime = System.currentTimeMillis();

    }
}
