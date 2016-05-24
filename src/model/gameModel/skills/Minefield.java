package model.gameModel.skills;
import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class Minefield extends Command {
	
    private int hpDamage;
    private int strengthDamage;
    private int defenseDamage;
    private Entity target;

    public Minefield(int hpDamage, int strengthDamage, int defenseDamage, ObservationSubject subject) {
        this.hpDamage = hpDamage;
        this.strengthDamage = strengthDamage;
        this.defenseDamage = defenseDamage;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        oldHp = target.getCurrentHP();
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        target.beAttacked(hpDamage);
        target.beStrengthAttacked(strengthDamage);
        target.beDefenseAttacked(defenseDamage);
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
        return "Minefield " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.DOWNCAST);
    }
}
