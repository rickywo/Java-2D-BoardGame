package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class UltimateDestruct extends Command {
    private int damage;
    private Entity target;

    public UltimateDestruct(int damage, ObservationSubject subject) {
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
        return "Ultimate Destruct " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }
}
