package model.gameModel.skills;
import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class Heal extends Command {
	
	private int oldHP;
	private int amount;
	private Entity target;
	
	public Heal(int amount, ObservationSubject subject){
		this.amount = amount;
		this.subject = subject;
	}
	
	@Override
	public void execute(Entity target) {
		this.target = target;
		oldHP = target.getCurrentHP();
		target.beHealed(amount);
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
        return "Heal " + target.getName();
    }

	@Override
	public void notifySubject(ObservationSubject subject) {
		subject.commandInvoked(CommandType.UPCAST);
	}
}
