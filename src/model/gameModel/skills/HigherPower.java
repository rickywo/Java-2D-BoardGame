package model.gameModel.skills;

import model.gameModel.Entity;

public class HigherPower extends Command {
	
    private Entity target;
    private int strengthDamage;
    private int defenseDamage;
    
    public HigherPower(int strengthDamage, int defenseDamage) {
    	this.strengthDamage = strengthDamage;
    	this.defenseDamage = defenseDamage;
    }

	@Override
	public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        System.out.println("Strength Damage: " + strengthDamage);
        System.out.println("Defense Damage: " + defenseDamage);
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
    	return "Higher Power " + target.getName();
    }


}
