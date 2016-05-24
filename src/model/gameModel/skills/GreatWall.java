package model.gameModel.skills;
import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class GreatWall extends Command {
	private int amount;
    private Entity target;

    public GreatWall(int amount, ObservationSubject subject) {
        this.amount = amount;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
    	oldDef = target.getDefense();
        target.beDefended(amount);
        notifySubject(subject);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setDefense(oldDef);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
        }
    }

    @Override
    public String toString() {
        return "Great Wall " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.UPCAST);
    }
}
