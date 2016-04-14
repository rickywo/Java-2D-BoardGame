package model.graphicModel;

import java.util.Arrays;

/**
 * Created by Human v Alien Team on 2016/4/14.
 */
public class Bitmap {
    public int width, height;
    public int[] pixels;

    public Bitmap(int w, int h) {
        this.width = w;
        this.height = h;
        pixels = new int[w * h];
    }

    public void color(int color) {
        Arrays.fill(pixels, color);
    }

    public void render(Bitmap bmp, int x, int y) {
        int x0 = x;
        int x1 = x0 + bmp.width;
        int y0 = y;
        int y1 = y0 + bmp.height;
        if(x0 < 0) x0 = 0;
        if(x1 > width) x1 = width;
        if(y0 < 0) y0 = 0;
        if(y1 > height) y1 = height;
        int ww = x1 - x0;

        for(int yy = y0; yy < y1; yy ++) {
            int tp = yy * width + x0;
            int sp = (yy - y) *bmp.width + (x0 - x);
            tp -= sp;
            for(int xx = sp; xx < sp + ww; xx ++) {
                int col = bmp.pixels[xx];
                if(col < 0) pixels[tp + xx] = col;
            }
        }

    }
}
