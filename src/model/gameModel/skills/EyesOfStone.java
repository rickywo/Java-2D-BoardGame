package model.gameModel.skills;

import model.gameModel.Entity;

public class EyesOfStone extends Command {

    private Entity target;
    private int remainder;

    public EyesOfStone(int remainder) {
    	this.remainder = remainder;
    }

    @Override
    public void execute(Entity target) {
        //target.setVisibility(Visibility.INVISIBLE);
    	this.target = target;
        System.out.println("Damage: " + (target.getCurrentHP() - remainder));
        target.setCurrentHP(remainder);
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
    	return "Eyes of Stone " + target.getName();
    }

}

