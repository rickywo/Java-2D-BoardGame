package model.gameModel;
import model.gameModel.skills.Command;

/**
 * Created by blahblah Team on 2016/5/1.
 */
public interface AttackerInterface {

    void attack(Entity target, ObservationSubject subject);
    void moveTo(Entity target, int x, int y);
    void invokeSkill(Command command, Entity target);
    void undoLastInvoke();
    // void redoLastInvoke();

}
