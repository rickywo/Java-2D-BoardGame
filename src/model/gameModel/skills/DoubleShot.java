package model.gameModel.skills;
import model.gameModel.CommandType;
import model.gameModel.Entity;
import model.gameModel.ObservationSubject;

public class DoubleShot extends Command {

    private int damage;
    private int defenseDamage;

    public DoubleShot(int defenseDamage, int damage, ObservationSubject subject) {
        this.defenseDamage = defenseDamage;
    	this.damage = damage;
        this.subject = subject;
    }

    @Override
    public void execute(Entity target) {
    	this.target = target;
        oldHp = target.getCurrentHP();
        oldDef = target.getDefense();
        target.beDefenseAttacked(defenseDamage);
    	target.beAttacked(damage);
        notifySubject(subject);
    }

    @Override
    public void undo() {
        if (target != null) {
            target.setCurrentHP(oldHp);
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
    	return "Double Shot " + target.getName();
    }

    @Override
    public void notifySubject(ObservationSubject subject) {
        subject.commandInvoked(CommandType.ATTACK);
    }


}
