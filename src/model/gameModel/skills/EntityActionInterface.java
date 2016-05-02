package model.gameModel.skills;

import model.gameModel.Entity;
import model.gameModel.Point;

/**
 * Created by blahblah Team on 2016/5/1.
 */
public interface EntityActionInterface {

    void attack(Entity target);
    void moveTo(Entity target, int x, int y);
    void invokeSkill(Command command, Entity target);
    void invokeSkill(Command command, Entity[] targets);
    void undoLastInvoke();
    void redoLastInvoke();

}
