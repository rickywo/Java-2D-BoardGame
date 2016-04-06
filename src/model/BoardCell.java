package model;

import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public class BoardCell {
    Entity entity;
    BufferedImage charImg;
    BufferedImage tileImg;
    BoardCell() {
        entity = null;
        charImg = null;
        tileImg = ImageManager.getRandomTiles();
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
