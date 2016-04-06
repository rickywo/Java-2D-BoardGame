package model;

import java.awt.image.BufferedImage;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public class BoardCell {
    boolean cursorHover;
    Entity entity;
    BufferedImage charImg;
    BufferedImage tileImg;
    public BoardCell() {
        cursorHover = false;
        entity = null;
        charImg = null;
        tileImg = ImageManager.getRandomTiles();
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        Profession p = entity.getProfession();
        this.charImg = ImageManager.getCharSkin(p.getName());
    }

    public Entity getEntity() {
        return entity;
    }

    public BufferedImage getCharImg() {
        return charImg;
    }

    public BufferedImage getTileImg() {
        return tileImg;
    }
}
