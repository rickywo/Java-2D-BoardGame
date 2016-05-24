package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class EyesOfStone extends Command {

    private Entity target;
    private int remainder;

    public EyesOfStone(int remainder, ObservationSubject subject) {
    	this.remainder = remainder;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldHp = target.getCurrentHP();
        target.setCurrentHP(remainder);
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
    	return "Eyes of Stone " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.DOWNCAST);
    }

}

