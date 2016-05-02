package model.gameModel.skills;

import model.gameModel.Entity;

//TODO - requires 2 targets - one for heal and one for lower enemy defense
public class TwinSpell extends Command {

    private int defenseDamage;
    private int healAmount;
    private Entity[] targets;

    public TwinSpell(int defenseDamage, int healAmount) {
        this.defenseDamage = defenseDamage;
        this.healAmount = healAmount;
    }

	@Override
	public void execute(Entity[] targets) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.targets = targets;
        System.out.println(targets[0].getName() + "'s HP rose by: " + healAmount);
        targets[0].beHealed(healAmount);
        System.out.println(targets[1].getName() + "'s Defense falls by: " + 
        		defenseDamage);
        targets[1].beDefenseAttacked(defenseDamage);
        
		
	}
    @Override
    public void undo() {
        if (targets != null) {
            //target.setVisibility(Visibility.VISIBLE);
        }
    }

    @Override
    public void redo() {
        if (targets != null) {
            //target.setVisibility(Visibility.INVISIBLE);
        }
    }

    @Override
    public String toString() {
        return "Twin Spell, Heal " + targets[0].getName() + 
        		"\nDeal Damage to Defense: " + targets[1].getName();
    }

    @Override
    public void execute(Entity target) {

    }
}
