package model.gameModel.skills;

import model.gameModel.Entity;

public class TwinSpell extends Command {

    private Entity target;
    private int amount;

    public TwinSpell(int amount) {
    	this.amount = amount;
    }
    
    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        System.out.println("Restoring HP to maximum, raising strength "
        		+ "and defense to: " + amount);
        target.setCurrentHP(target.getMaxHP());
        target.setDefense(amount);
        target.setStrength(amount);
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
        return "Twin Spell " + target.getName();
    }


}
