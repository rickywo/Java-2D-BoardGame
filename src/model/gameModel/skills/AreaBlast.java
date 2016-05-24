package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class AreaBlast extends Command {
	
	private int damage;
	
	public AreaBlast(int damage, ObservationSubject subject){
		this.damage = damage;
        this.subject = subject;
	}
	
	@Override
	public void execute(Entity target) {
	  	this.target = target;
    	oldHp = target.getCurrentHP();
        oldStr = target.getStrength();
        oldDef = target.getDefense();

        target.beAttacked(damage);
    	target.beStrengthAttacked(damage);
    	target.beDefenseAttacked(damage);
        notifySubject(subject);
	}
	
    @Override
    public void undo() {
        if (target != null) {

            target.setCurrentHP(oldHp);
            target.setStrength(oldStr);
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
    	return "Area Blast " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.DOWNCAST);
    }


}
