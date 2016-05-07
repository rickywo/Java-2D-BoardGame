package model.gameModel.skills;
import model.gameModel.Entity;

public class DoubleShot extends Command {

    private int damage;
    private int defenseDamage;
    private Entity target;

    public DoubleShot(int defenseDamage, int damage) {
        this.defenseDamage = defenseDamage;
    	this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
    	this.target = target;
        System.out.println("Defense Damage: " + defenseDamage);
    	System.out.println("Damage: " + damage);
        target.beDefenseAttacked(defenseDamage);
    	target.beAttacked(damage);
    }

    @Override
    public void undo() {
        if (target != null) {
            //target.setVisibility(Visibility.VISIBLE);
        }
    }

    @Override
    public void redo() {
        if (target != null) {
            //target.setVisibility(Visibility.INVISIBLE);
        }
    }

    @Override
    public String toString() {
    	return "Double Shot " + target.getName();
    }


}
