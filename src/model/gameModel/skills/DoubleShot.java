package model.gameModel.skills;
import model.gameModel.Entity;

public class DoubleShot extends Command {

    private int damage;
    private int defenseDamage;

    public DoubleShot(int defenseDamage, int damage) {
        this.defenseDamage = defenseDamage;
    	this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
    	this.target = target;
        oldHp = target.getCurrentHP();
        oldDef = target.getDefense();
        target.beDefenseAttacked(defenseDamage);
    	target.beAttacked(damage);
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


}
