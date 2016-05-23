/*
 * Copyright (C) 2016 Ricky Wu.
 */
package view;

import model.graphicModel.GameScreen;
import resources.Consts;

// TODO: Auto-generated Javadoc
/**
 * Created by blahblah Team on 2016/4/19.
 */

public class Verbose {

    /** The verbose message. */
    private static String verboseMessage = "";
    
    /** The verbose time. */
    private static long verboseTime;
    
    /** The time limit. */
    private static long timeLimit = 2500;

    /**
     * Render.
     *
     * @param screen the screen for rendering
     */
    public static void render(GameScreen screen) {
        int width = Font.getStringWidth(verboseMessage);
        if(System.currentTimeMillis() - verboseTime < timeLimit)
            Font.draw_blockfont(screen,
                    verboseMessage,
                    (Consts.SCR_WIDTH - Consts.MAP_X_OFFSET) / 2 + Consts.MAP_X_OFFSET - width / 2,
                    Consts.SCR_HEIGHT / 2);
    }

    /**
     * Verbose.
     *
     * @param message the message
     * @param limit the time limit for displaying message
     */
    public static void verbose(String message, long limit) {
        verboseMessage = message;
        verboseTime = System.currentTimeMillis();
        timeLimit = limit;
    }
}
