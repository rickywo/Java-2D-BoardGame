package model.gameModel.skills;

import model.gameModel.Entity;
import model.gameModel.Point;

/**
 * Created by blahblah Team on 2016/5/2.
 */
public class Move extends Command {

    private int originX, originY;
    private int destX, destY;
    private Entity target;

    public Move(int x, int y) {
        destX = x;
        destY = y;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
        originX = target.getXPos();
        originY = target.getYPos();
        target.setPos(destX, destY);
        this.target = target;
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setPos(originX, originY);
            System.out.println("Restore Move");
            //target.setVisibility(Visibility.VISIBLE);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            //target.setVisibility(Visibility.INVISIBLE);
        }
    }

    @Override
    public String toString() {
        return "Move to x:" + destX + " y:" + destY;
    }
}
