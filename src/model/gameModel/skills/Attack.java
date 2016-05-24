package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

/**
 * Created by blahblah Team on 2016/4/30.
 */
public class Attack extends Command {

    private int damage;

    public Attack(int damage, ObservationSubject subject) {
        this.damage = damage;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        oldHp = target.getCurrentHP();
        target.beAttacked(damage);
        this.target = target;
        notifySubject(subject);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setCurrentHP(oldHp);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
        }
    }

    @Override
    public String toString() {
        return "Attack " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }
}
