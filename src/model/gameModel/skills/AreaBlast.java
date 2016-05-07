package model.gameModel.skills;

import model.gameModel.Entity;

public class AreaBlast extends Command {
	
	private int damage;
	private Entity target;
	
	public AreaBlast(int damage){
		this.damage = damage;
	}
	
	@Override
	public void execute(Entity target) {
		 //target.setVisibility(Visibility.INVISIBLE);
	  	this.target = target;
        System.out.println("Damage to HP, Strength, Defense: " + damage);
    	target.beAttacked(damage);
    	target.beStrengthAttacked(damage);
    	target.beDefenseAttacked(damage);
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
    	return "Area Blast " + target.getName();
    }


}
