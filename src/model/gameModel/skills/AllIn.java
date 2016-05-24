package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class AllIn extends Command {

    private int damage;

    public AllIn(int damage, ObservationSubject subject) {
        this.damage = damage;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
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
        return "All In " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }
}
