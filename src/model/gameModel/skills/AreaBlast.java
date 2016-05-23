package model.gameModel.skills;

import model.gameModel.Entity;

public class AreaBlast extends Command {
	
	private int damage;
	
	public AreaBlast(int damage){
		this.damage = damage;
	}
	
	@Override
	public void execute(Entity target) {
	  	this.target = target;
    	oldHp = target.getCurrentHP();
        oldStr = target.getStrength();
        oldDef = target.getDefense();

        target.beAttacked(damage);
    	target.beStrengthAttacked(damage);
    	target.beDefenseAttacked(damage);
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
    	return "Area Blast " + target.getName();
    }


}
