package model.gameModel;

import model.graphicModel.ImageManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Human v Alien Team on 2016/4/6.
 */
public class BoardCell implements Serializable {
    boolean cursorHover;
    Entity entity;
    Weapon weapon;
    transient BufferedImage charImg;
    transient BufferedImage tileImg;
    
    public BoardCell() {
        cursorHover = false;
        entity = null;
        charImg = null;
        tileImg = ImageManager.getRandomTiles();
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if(entity != null) {
            String p = entity.getProfessionName();
            this.charImg = ImageManager.getCharSkin(p);
        }
    }

    public Entity getEntity() {
        return entity;
    }

    public void clearEntity() {
        this.entity = null;
    }
    
    public void setWeapon(Weapon weapon){
    	this.weapon = weapon;
    	//add image
    }
    
    public Weapon getWeapon(){
    	return weapon;
    }

    public void clearWeapon() {
        this.weapon = null;
    }

    public BufferedImage getCharImg() {
        return charImg;
    }

    public BufferedImage getTileImg() {
        return tileImg;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if(charImg != null)
            ImageIO.write(charImg, "png", out); // png is lossless
        if(tileImg != null)
            ImageIO.write(tileImg, "png", out); // png is lossless
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        charImg = ImageIO.read(in);
        tileImg = ImageIO.read(in);
    }
}
