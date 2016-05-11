package model.gameModel.skills;
import model.gameModel.Entity;

public class Minefield extends Command {
	
    private int hpDamage;
    private int strengthDamage;
    private int defenseDamage;
    private Entity target;

    public Minefield(int hpDamage, int strengthDamage, int defenseDamage) {
        this.hpDamage = hpDamage;
        this.strengthDamage = strengthDamage;
        this.defenseDamage = defenseDamage;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;

        System.out.println("HP Damage:" + hpDamage);
        System.out.println("Strength Damage:" + strengthDamage);
        System.out.println("Defense Damage:" + defenseDamage);
        target.beAttacked(hpDamage);
        target.beStrengthAttacked(strengthDamage);
        target.beDefenseAttacked(defenseDamage);
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
        return "Minefield " + target.getName();
    }
}
