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
    	this.target = target;
        oldHp = target.getCurrentHP();
        oldStr = target.getStrength();
        oldDef = target.getDefense();
        target.setCurrentHP(target.getMaxHP());
        target.beDefended(amount);
        target.beStrengthened(amount);
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


}
