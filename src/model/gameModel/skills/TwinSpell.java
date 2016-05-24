package model.gameModel.skills;

import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;
import org.omg.CORBA.OBJ_ADAPTER;

public class TwinSpell extends Command {

    private Entity target;
    private int amount;

    public TwinSpell(int amount, ObservationSubject subject) {
    	this.amount = amount;
        this.subject = subject;
    }
    
    @Override
    public void execute(Entity target) {
    	this.target = target;
        oldHp = target.getCurrentHP();
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        target.setCurrentHP(target.getMaxHP());
        target.beDefended(amount);
        target.beStrengthened(amount);
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
        return "Twin Spell " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }
}
