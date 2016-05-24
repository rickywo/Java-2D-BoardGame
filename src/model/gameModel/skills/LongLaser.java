package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class LongLaser extends Command {
    private int damage;
    private Entity target;

    public LongLaser(int damage, ObservationSubject subject) {
        this.damage = damage;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldHp = target.getCurrentHP();
        target.beAttacked(damage);
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
        return "Long Laser " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }
}
