package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class HigherPower extends Command {
	
    private Entity target;
    private int strengthDamage;
    private int defenseDamage;
    
    public HigherPower(int strengthDamage, int defenseDamage, ObservationSubject subject) {
    	this.strengthDamage = strengthDamage;
    	this.defenseDamage = defenseDamage;
        this.subject = subject;
    }

	@Override
	public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        target.beStrengthAttacked(strengthDamage);
        target.beDefenseAttacked(defenseDamage);
        notifySubject(subject);
  
	}
    
    @Override
    public void undo() {
        if (target != null) {
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
    	return "Higher Power " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.DOWNCAST);
    }

}
