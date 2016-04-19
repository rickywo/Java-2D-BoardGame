package model.graphicModel;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class GameScreen extends Bitmap {

    public BufferedImage image;

    public GameScreen(int w, int h) {
        super(w, h);
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }
}
