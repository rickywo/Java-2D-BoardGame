package model.gameModel.skills;

import model.gameModel.Entity;

public class AllIn extends Command {
	
    private int damage;
    private Entity target;

    public AllIn(int damage) {
        this.damage = damage;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
    	System.out.println("Damage:" + damage);
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
        return "All In " + target.getName();
    }
}
